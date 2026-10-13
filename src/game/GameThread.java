package game;

import question_answer.QAPair;
import question_answer.RoundsType;

public class GameThread implements Runnable {
    private GameData gameData;
    private Thread gameThread;
    public GameThread(GameData gameData) {
        this.gameData = gameData;
    }
    public void run() {
        gameThread = Thread.currentThread();
        if(!roundStart(RoundsType.ONE)) return;
        if(!roundStart(RoundsType.TWO)) return;
        //if(!roundStart(RoundsType.THREE)) return;
    }
    private boolean roundStart(RoundsType roundType) {
        gameData.startRound(roundType);
        sleepWait(60);
        gameData.closePart1();
        gameData.openPart2();
        sleepWait(90);
        gameData.responsesDone();
        for (QAPair pair : gameData.getPairs()) {
            sleepWait(5);
            gameData.startVote(pair);
            sleepWait(15);
            gameData.nextVote();
        }
        gameData.roundScores();
        sleepWait(15);
        return true;
    }
    private void sleepWait(int seconds) {
        try {
            Thread.sleep(seconds * 1000);
        } catch (InterruptedException e) {
            System.out.println("Clients are ready!");
        }
    }
    public void interruptThread() {
        if (gameThread != null && gameThread.isAlive()) {
            gameThread.interrupt();
        }
    }
}
