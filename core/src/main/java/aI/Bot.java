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
    private ArrayList<iTile> bord;
    private SinglePlayerGameState single;
/*
    private List<Chesspieces> enemyChesspieces;
    private List<Chesspieces> botChesspieces;
    private List<Position> possibleEnemyPositions;
    private List<Position> possibleAllyPositions;
    allMovesCalculator allmovescalc = new allMovesCalculator();
    private bestMoveCalculator calculator = new bestMoveCalculator();*/
    private MinMaxAlgorithm algorithm;
    ThreadPoolExecutor pool;

    public Bot(ArrayList<iTile> tiles, int depth, SinglePlayerGameState single)
    {
        this.single = single;
        algorithm = new MinMaxAlgorithm(depth, single);
        pool = (ThreadPoolExecutor) Executors.newFixedThreadPool(1);
    }

    public void updateBord(ArrayList<iTile> tiles)
    {
        bord = tiles;
        ArrayList<iTile> botBord = BotMakeFakeBordCopy.generateFakeBord(tiles);
        System.out.println(botBord.size());
        algorithm.updateBord(botBord);
    }

    @Override
    public void run() {
        Future result = pool.submit(algorithm);
        try {
            Moves move = (Moves) result.get();
            iTile oldTile = null;
            iTile newTile = null;
            for (iTile tile : bord)
            {
                if (tile.getX() == move.getChesspieces().getX() && tile.getY() == move.getChesspieces().getY()) {
                    oldTile = tile;
                }
                else if (tile.getX() == move.getTile().getX() && tile.getY() == move.getTile().getY()) {
                    newTile = tile;
                }
            }
            newTile.setChesspieces(oldTile.getChesspieces());
            oldTile.removeChestpiece();
            single.switchTurn();
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

    public void act()
    {
        Thread thread1 = new Thread(this);
        thread1.run();

        /*enemyChesspieces = new ArrayList<>();
        botChesspieces = new ArrayList<>();
        possibleAllyPositions = new ArrayList<>();
        possibleEnemyPositions = new ArrayList<>();
        List<iTile> toRemove = getAlliesAndEnemys();
        List<Moves> CheckMoves =  allmovescalc.calcAllMoves(botChesspieces, bord);

*//*        for (Chesspieces chesspiece : enemyChesspieces) {
            chesspiece.calculateMoves(bord);
            if(!chesspiece.getPossibleMoves().isEmpty() || chesspiece.getPossibleMoves() != null) {
                possibleEnemyPositions.addAll(chesspiece.getPossibleMoves());
            }
        }*//*

        Random rnd = new Random();
        boolean canWork = false;
        Chesspieces chesspiece = null;
        if(CheckMoves.isEmpty()) {
            while (!canWork) {
                int nummer = rnd.nextInt(botChesspieces.size());
                chesspiece = botChesspieces.get(nummer);
                chesspiece.resetMoves();
                chesspiece.calculateMoves(bord);
                if (!chesspiece.getPossibleMoves().isEmpty()) {
                    toRemove.get(nummer).removeChestpiece();
                    canWork = true;
                }
            }
            int move = rnd.nextInt(chesspiece.getPossibleMoves().size());
            Position pos = chesspiece.getPossibleMoves().get(move);
            for (iTile tile: bord) {
                if(tile.getX() == pos.getX() && tile.getY() == pos.getY())
                {
                    tile.setChesspieces(chesspiece);
                }
            }
        }
        else
        {
            Moves bestmove = calculator.calcbestMove(CheckMoves);

            chesspiece = bestmove.getChesspieces();
            for (Position pos: chesspiece.getPossibleMoves()) {
                if(pos.getX() == bestmove.getTile().getX() && pos.getY() == bestmove.getTile().getY())
                {
                    bestmove.getTile().setChesspieces(bestmove.getChesspieces());
                    for (iTile tile: toRemove) {
                        if(tile.getChesspieces() == bestmove.getChesspieces())
                        {
                            tile.removeChestpiece();
                        }
                    }
                }
            }
        }

        chesspiece.resetMoves();*/
    }

/*
    private List<iTile> getAlliesAndEnemies()
    {
        List<iTile> toRemove = new ArrayList<>();
        for (iTile tile: bord) {
            if(tile.hasChesspiece())
            {
                tile.getChesspieces().resetMoves();
                if(tile.getChesspieces().getColor())
                {
                    enemyChesspieces.add(tile.getChesspieces());
                }
                else
                {
                    botChesspieces.add(tile.getChesspieces());
                    toRemove.add(tile);
                }
            }
        }
        return toRemove;
    }*/
}
