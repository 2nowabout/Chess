package aI;

import interfaces.iTile;
import saveLibraries.Moves;

import java.util.List;

public interface iMinMaxAlgorithm {
    void algorithm(List<List<iTile>> borden);
    List<List<iTile>> getAllBorden();
    List<Moves> getMoves();
}
