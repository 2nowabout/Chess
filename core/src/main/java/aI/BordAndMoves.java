package aI;

import interfaces.iTile;
import saveLibraries.Moves;

import java.util.List;

public class BordAndMoves {
    private List<iTile> bord;
    private Moves move;

    public BordAndMoves(List<iTile> bord, Moves move)
    {
        this.bord = bord;
        this.move = move;
    }
    public List<iTile> getBord() { return bord; }
    public Moves getMove() { return move; }
}
