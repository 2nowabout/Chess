package aI;

import interfaces.iTile;
import saveLibraries.Moves;
import state.SinglePlayerGameState;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;

public class MinMaxAlgorithm implements Callable {
    private iMinMaxAlgorithm min;
    private iMinMaxAlgorithm max;
    private int depth;
    private List<iTile> bord;
    private List<BordAndMoves> borden;
    private List<BordAndMoves> finalMoves;
    private List<Moves> firstMoves;
    private SinglePlayerGameState single;

    public MinMaxAlgorithm(int depth, SinglePlayerGameState single) {
        min = new MinAlgorithm();
        max = new MaxAlgorithm();
        this.single = single;
        this.depth = depth;
    }

    public void updateBord(List<iTile> bord) {
        this.bord = bord;
    }

    @Override
    public Object call() {
        borden = new ArrayList<>();
        boolean first = true;
        for (iTile tile : bord) {
            tile.removeTextures();
        }
        BordAndMoves firstbord = new BordAndMoves(bord, null);
        borden.add(firstbord);
        System.out.println("start algo");
        for (int i = 0; i < depth; i++) {
            long startmax = System.nanoTime();
            max.algorithm(borden, first);
            long endmax = System.nanoTime();
            System.out.println("max algo Done");
            checkAlgoTime(startmax, endmax);
            if (first) {
                firstMoves = max.getMoves();
            }
            first = false;
            System.out.println(max.getAllBorden().size());
            long beginmin = System.nanoTime();
            min.algorithm(max.getAllBorden(), first);
            long endmin = System.nanoTime();
            System.out.println(min.getAllBorden().size());
            borden = min.getAllBorden();
            System.out.println("one depth done");
            checkAlgoTime(beginmin, endmin);
        }
        System.out.println("completed algo");
        BordAndMoves bestMove = null;
        Moves toReturn = null;
        double bestScore = -999;
        for (BordAndMoves bordAndMoves : borden) { //min is done last so we search for the least minimal one
            if (bordAndMoves.getMove().getPoints() > bestScore) {
                bestScore = bordAndMoves.getMove().getPoints();
                bestMove = bordAndMoves;
            }
        }
        for (Moves move : firstMoves) {
            if (move.getID() == bestMove.getMove().getID())
            {
                 toReturn = move;
            }
        }

        return toReturn;
        //TODO get best move in right order and return next move bot has too do
    }

    private void checkAlgoTime(long start, long end)
    {
        long time = end - start;
        System.out.println("time: " + time);
    }
}
