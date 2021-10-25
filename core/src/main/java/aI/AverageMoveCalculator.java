package aI;

import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class AverageMoveCalculator {
    public double calculateAverage(List<Moves> moves)
    {
        ArrayList<Moves> allmoves = new ArrayList<>(moves);
        double average = 0;
        for (Moves move: allmoves) {
            average += move.getPoints();
        }
        average = average / (allmoves.size() +1);
        return average;
    }
    public ArrayList<String> testListToArray (List<String> test)
    {
        ArrayList<String> strings = new ArrayList<>(test);
        return strings;
    }
}
