package aI;

import objects.Tile;
import saveLibraries.Moves;
import interfaces.iBot;
import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Position;
import state.SinglePlayerGameState;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ThreadPoolExecutor;

public class Bot implements iBot, Runnable {
    private SinglePlayerGameState single;
    private MinMaxAlgorithm algorithm;
    ThreadPoolExecutor pool;

    public Bot(ArrayList<iTile> tiles, int depth, SinglePlayerGameState single)
    {
        this.single = single;
        algorithm = new MinMaxAlgorithm(depth, single);
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(4);
    }

    public void updateBord(ArrayList<iTile> tiles)
    {
        ArrayList<iTile> botBord = BotMakeFakeBordCopy.generateFakeBord(tiles);
        System.out.println(botBord.size());
        algorithm.updateBord(botBord);
    }

    @Override
    public void run() {
        try {
            pool.submit(algorithm);
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    public void shutdown()
    {
        pool.shutdown();
    }

    public void act() {
        Thread thread1 = new Thread(this);
        thread1.run();
    }
}
