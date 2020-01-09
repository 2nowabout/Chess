package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MaxAlgorithm implements iMinMaxAlgorithm {
    private List<List<iTile>> allBorden;
    private List<Moves> acceptedmoves;
    private List<Moves> moves;
    private BotGetChesspieces getChesspieces;
    private allMovesCalculator allMovesCalculator;
    private MakeFields makeFields;
    private AverageMoveCalculator averageCalculator;

    public MaxAlgorithm() {
        getChesspieces = new BotGetChesspieces();
        allMovesCalculator = new allMovesCalculator();
        makeFields = new MakeFields();
        averageCalculator = new AverageMoveCalculator();
    }

    public void algorithm(List<List<iTile>> borden) {
        allBorden = new ArrayList<>();
        moves = new ArrayList<>();
        for (List<iTile> lists : borden) {
            acceptedmoves = new ArrayList<>();
            List<Chesspieces> allies = getChesspieces.getAllyChessPieces(lists);
            List<Moves> allmoves = allMovesCalculator.calcAllMoves(allies, lists);
            double average = averageCalculator.calculateAverage(allmoves);
            for (Moves move : allmoves) {
                if (move.getPoints() >= average) {
                    acceptedmoves.add(move);
                }
            }
            for (Moves move : acceptedmoves) {
                allBorden.add(makeFields.doMoveAndMakeField(lists, move));
            }
            System.out.println(allBorden.size());
            moves.addAll(acceptedmoves);
        }
    }

    public List<List<iTile>> getAllBorden() { return allBorden; }
    public List<Moves> getMoves() { return moves; }
}
