package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MinAlgorithm implements iMinMaxAlgorithm {
    private List<BordAndMoves> allBorden;
    private List<Moves> acceptedmoves;
    private BotGetChesspieces getChesspieces;
    private allMovesCalculator allMovesCalculator;
    private MakeFields makeFields;
    private AverageMoveCalculator averageCalculator;
    private AddIDOnMoves addIDOnMoves;

    public MinAlgorithm() {
        getChesspieces = new BotGetChesspieces();
        allMovesCalculator = new allMovesCalculator();
        makeFields = new MakeFields();
        averageCalculator = new AverageMoveCalculator();
        addIDOnMoves = new AddIDOnMoves();
    }

    public void algorithm(List<BordAndMoves> borden, boolean first) {
        allBorden = new ArrayList<>();
        for (BordAndMoves lists : borden) {
            acceptedmoves = new ArrayList<>();
            List<Chesspieces> enemys = getChesspieces.getEnemyChesspieces(lists.getBord());
            List<Moves> allmoves = allMovesCalculator.calcAllMoves(enemys, lists.getBord(), lists.getMove().getPoints(), false);
            allmoves = addIDOnMoves.AddIDToMoves(allmoves, first, lists);
            double average = averageCalculator.calculateAverage(allmoves);
            for (Moves move : allmoves) {
                if (move.getPoints() <= average) {
                    acceptedmoves.add(move);
                }
            }
            enemys = new ArrayList<>();
            allmoves = new ArrayList<>();
            for (Moves move : acceptedmoves) {
                BordAndMoves toAdd = new BordAndMoves(makeFields.doMoveAndMakeField(lists.getBord(), move), move);
                allBorden.add(toAdd);
            }
        }
    }

    public List<BordAndMoves> getAllBorden() {
        return allBorden;
    }

    public List<Moves> getMoves() {
        return null;
    }

    public void EmptyFirstMoves() {
    }
    public void Reset() {
        allBorden = new ArrayList<>();
        acceptedmoves = new ArrayList<>();
    }
}
