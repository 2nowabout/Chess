package aI;

import com.badlogic.gdx.utils.Timer;
import interfaces.iTile;
import objects.Tile;
import objects.chessPieces.Pawn;
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
    private List<List<Moves>> allmoves;
    private List<List<iTile>> borden;
    private SinglePlayerGameState single;

    public MinMaxAlgorithm(int depth, SinglePlayerGameState single)
    {
        min = new MinAlgorithm();
        max = new MaxAlgorithm();
        this.single = single;
        this.depth = depth;
    }

    public void updateBord(List<iTile> bord)
    {
        this.bord = bord;
    }

    @Override
    public Object call() {
        allmoves = new ArrayList<>();
        borden = new ArrayList<>();
        borden.add(bord);
        System.out.println("start algo");
        for(int i = 0; i < depth; i++)
        {
            max.algorithm(borden);
            allmoves.add(max.getMoves());
            System.out.println("max algo Done");
            min.algorithm(max.getAllBorden());
            borden = min.getAllBorden();
            allmoves.add(min.getMoves());
            System.out.println("one depth done");
        }
        System.out.println("completed algo");
        single.switchTurn();
        return null; //TODO get best move in right order and return next move bot has too do
    }
}
