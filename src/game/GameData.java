package game;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import network.Server;
import players.*;
import question_answer.QAPair;
import question_answer.ResponseType;
import question_answer.RoundsType;
import rounds.Round;

public class GameData {
    private Server server;
    private HashMap<String, Player> players;
    private List<String> playerNames;
    private GameThread gameThread;
    private Thread gt;
    private Round round;
    private boolean answersOpen;
    private boolean questionsOpen;
    private final Object lock;
    private final Object playerLock;
    private boolean votingOpen;
    private boolean phaseOneOver;
    private QAPair currentPair;
    private HashMap<QAPair, Integer> voteScores;
    public  GameData(Server server) {
        this.server = server;
        this.players = new HashMap<>();
        this.playerNames = new ArrayList<>();
        this.lock = new Object();
        this.playerLock = new Object();
        this.gameThread = new GameThread(this);
        gt = null;
    }   
    public boolean addPlayer(String playerName) {
        synchronized (playerLock) {
            if (playerNames.contains(playerName)) {
                return false;
            }
            Player newPlayer;
            if (players.isEmpty()) {
            newPlayer = new Player(playerName, true);
            } else {
                newPlayer = new Player(playerName, false);
            }
            players.put(playerName, newPlayer);
            playerNames.add(playerName);
            return true;
        }
    }
    public Player getPlayerByName(String playerName) {
        return players.get(playerName);
    }
    public boolean startGameThread() {
        synchronized (playerLock) {
            if(gt != null && gt.isAlive()) {
                return false; // Game thread is already running
            }
            gt = new Thread(gameThread);
            gt.start();
            return true;
        }
    }
    public void startRound(RoundsType roundType) {
        synchronized (lock) {
            this.voteScores = new HashMap<>();
            this.round = new Round(playerNames.size(), roundType);
            switch (roundType) {
                case ONE:
                    questionsOpen = true;
                    answersOpen = false;
                    break;
                case TWO:
                    questionsOpen = false;
                    answersOpen = true;
                    break;
            }
            votingOpen = false;
            phaseOneOver = false;
        }
    }
    public void closePart1() {
        synchronized (lock) {
            switch (round.getRoundType()) {
                case ONE:
                    questionsOpen = false;
                    break;
                case TWO:
                    answersOpen = false;
                    break;
            }
            phaseOneOver = true;
        }
    }
    public void openPart2() {
        synchronized (lock) {
            switch (round.getRoundType()) {
                case ONE:
                answersOpen = true;
                break;
            case TWO:
                questionsOpen = true;
                break;
            }
        }
    }
    public void responsesDone() {
        synchronized (lock) {
            questionsOpen = false;
            answersOpen = false;
        }
    }
    //Phase 1 response (Starting a QAPair)
    private void addResponse(String playerName, String response, ResponseType rt) {
        synchronized (lock) {
            if(phaseOneOver) return;
            switch (rt) {
                case QUESTION:
                    if (!questionsOpen) return;
                case ANSWER:
                    if (!answersOpen) return;
            }
            Player player = players.get(playerName);
            round.addQAPair(player, response);
            switch (rt) {
                case QUESTION:
                    if (!round.allQuestionsWritten()) return;
                case ANSWER:
                    if (!round.allAnswersGiven()) return;
            }
            gameThread.interruptThread();
        }
    }
    //Phase 2 response (finishing a QAPair)
    private void addResponse(String playerName, String otherPlayerName, String response, ResponseType rt) {
        synchronized (lock) {
            if (!phaseOneOver) return;
            switch (rt) {
                case QUESTION:
                    if (!questionsOpen) return;
                case ANSWER:
                    if (!answersOpen) return;
            }
            Player player = players.get(playerName);
            Player otherPlayer = players.get(otherPlayerName);
            round.finishQAPair(player, otherPlayer, response);
            switch (rt) {
                case QUESTION:
                    if (!round.allQuestionsWritten()) return;
                case ANSWER:
                    if (!round.allAnswersGiven()) return;
            }
            gameThread.interruptThread();
        }
    }
    public List<QAPair> getPairs() {
        return round.getPairs();
    }
    public void startVote(QAPair pair) {
        synchronized (lock) {
            currentPair = pair;
            voteScores.put(pair, 0);
            votingOpen = true;
        }
    }
    public void nextVote() {
        synchronized (lock) {
            votingOpen = false;
        }
    }
    public void vote(int score) {
        synchronized (lock) {
            if (!votingOpen) return;
            if (currentPair == null) return;
            voteScores.put(currentPair, voteScores.get(currentPair) + score);
        }
    }
    public void roundScores() {
        synchronized (lock) {
            RoundsType roundType = round.getRoundType();
            for (QAPair pair : voteScores.keySet()) {
                switch (roundType) {
                    case ONE:
                        players.get(pair.getAnswerer()).addPoints(voteScores.get(pair).intValue());
                        players.get(pair.getQuestioner()).addPoints(voteScores.get(pair).intValue()/2);
                        break;
                    case TWO:
                        players.get(pair.getQuestioner()).addPoints(voteScores.get(pair).intValue());
                        players.get(pair.getAnswerer()).addPoints(voteScores.get(pair).intValue()/2);
                        break;
                }
            }
            // Logic here to send the updated scores to all clients
        }
    }
}