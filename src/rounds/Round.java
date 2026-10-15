package rounds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import players.*;
import question_answer.*;

public class Round {
    private final HashMap<Player, QAPair> questionPlayer;
    private final HashMap<Player, QAPair> answerPlayer;
    private final List<QAPair> qaPairs;
    private final int numPlayers;
    private final RoundsType round;
    public Round(int numPlayers, RoundsType round) {
        this.numPlayers = numPlayers;
        this.round = round;
        this.questionPlayer = new HashMap<>();
        this.answerPlayer = new HashMap<>();
        this.qaPairs = new ArrayList<>();
    }

    public boolean allQuestionsWritten() {return (questionPlayer.size() == numPlayers);}

    public boolean allAnswersGiven() {return (answerPlayer.size() == numPlayers);}

    public void addQAPair(Player player, String response) {
        if(round == RoundsType.ONE) {
            addToMap(questionPlayer, player, response);
        } else if(round == RoundsType.TWO) {
            addToMap(answerPlayer, player, response);
        }
    }

    private void addToMap(HashMap<Player, QAPair> map, Player player, String response) {
        if(map.containsKey(player)) {
            throw new IllegalArgumentException("Player has already given a response");
        }
        QAPair qaPair = new QAPair(response, player.getName(), round);
        map.put(player, qaPair);
        qaPairs.add(qaPair);
    }

    //Make it so assignment of player can be done first, than have another method for assignment of response
    public void finishQAPair(Player player, String response) {
        if(round == RoundsType.ONE) {
            QAPair qapair = answerPlayer.get(player);
            qapair.completeQA(response, player.getName());
            answerPlayer.put(player, qapair);
        } else if (round == RoundsType.TWO) {
            QAPair qapair = answerPlayer.get(otherPlayer);
            qapair.completeQA(response, player.getName());
            questionPlayer.put(player, qapair);
        }
    }

    public QAPair getQAPair(Player player, ResponseType rt) {
        if(rt == ResponseType.QUESTION) {
            return questionPlayer.get(player);
        } else if(rt == ResponseType.ANSWER) {
            return answerPlayer.get(player);
        }
        throw new IllegalArgumentException("Invalid ResponseType");
    }

    public List<QAPair> getPairs() {return new ArrayList<>(qaPairs);}

    public RoundsType getRoundType() {return this.round;}

    public HashMap<Player, QAPair> getQAPairMap() {
        if(round == RoundsType.ONE) {
            return new HashMap<>(questionPlayer);
        } else if(round == RoundsType.TWO) {
            return new HashMap<>(answerPlayer);
        }
        throw new IllegalStateException("Invalid round type");
    }
}