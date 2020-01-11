package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MinAlgorithm implements iMinMaxAlgorithm {
    private List<BordAndMoves> allBorden;
    private List<Moves> acceptedmoves;
    private List<Moves> moves;
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

    public void algorithm(List<BordAndMoves> borden, boolean first)
    {
        allBorden = new ArrayList<>();
        moves = new ArrayList<>();
        for (BordAndMoves lists : borden) {
            acceptedmoves = new ArrayList<>();
            List<Chesspieces> enemys = getChesspieces.getEnemyChesspieces(lists.getBord());
            List<Moves> allmoves = allMovesCalculator.calcAllMoves(enemys, lists.getBord());
            allmoves = addIDOnMoves.AddIDToMoves(allmoves, first, lists);
            double average = averageCalculator.calculateAverage(allmoves);
/*            for (Moves move : allmoves) {
                if (move.getPoints() <= average) {
                    acceptedmoves.add(move);
                }
            }*/
            for (Moves move : allmoves) {
                BordAndMoves toAdd = new BordAndMoves(makeFields.doMoveAndMakeField(lists.getBord(), move), move);
                allBorden.add(toAdd);
            }
            moves.addAll(acceptedmoves);
        }
    }

    public List<BordAndMoves> getAllBorden() { return allBorden; }
    public List<Moves> getMoves() { return moves; }
}
