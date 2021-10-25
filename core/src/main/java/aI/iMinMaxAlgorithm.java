package aI;

import interfaces.iTile;
import saveLibraries.Moves;

import java.util.List;

public interface iMinMaxAlgorithm {
    void algorithm(List<BordAndMoves> borden, boolean first);
    List<BordAndMoves> getAllBorden();
    List<Moves> getMoves();
    void EmptyFirstMoves();
    void Reset();
}
