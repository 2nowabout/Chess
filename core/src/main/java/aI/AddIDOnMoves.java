package aI;

import saveLibraries.Moves;

import java.util.List;

public class AddIDOnMoves {
    private static int id;

    public List<Moves> AddIDToMoves(List<Moves> allmoves, boolean first, BordAndMoves bordandmoves)
    {
        if(first)
        {
            for (Moves move: allmoves) {
                move.setID(id);
                id++;
            }
        }
        else
        {
            for (Moves move: allmoves) {
                move.setID(bordandmoves.getMove().getID());
            }
        }
        return allmoves;
    }
}
