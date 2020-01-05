package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MinAlgorithm implements iMinMaxAlgorithm {
    private List<List<iTile>> allBorden;
    private List<Moves> moves;
    private BotGetChesspieces getChesspieces;
    private allMovesCalculator allMovesCalculator;
    private MakeFields makeFields;
    private AverageMoveCalculator averageCalculator;

    public MinAlgorithm() {
        getChesspieces = new BotGetChesspieces();
        allMovesCalculator = new allMovesCalculator();
        makeFields = new MakeFields();
        averageCalculator = new AverageMoveCalculator();
    }

    public void algorithm(List<List<iTile>> borden)
    {
        allBorden = new ArrayList<>();
        moves = new ArrayList<>();
        List<Moves> allmoves;
        for (List<iTile> lists : borden) {
            List<Chesspieces> enemys = getChesspieces.getEnemyChesspieces(lists);
            allmoves = allMovesCalculator.calcAllMoves(enemys, lists);

            double average = averageCalculator.calculateAverage(moves);
            for (Moves move : allmoves) {
                if (move.getPoints() <= average) {
                    moves.add(move);
                }
            }
            for (Moves move : moves) {
                allBorden.add(makeFields.doMoveAndMakeField(lists, move));
            }
        }

    }

    public List<List<iTile>> getAllBorden() { return allBorden; }
    public List<Moves> getMoves() { return moves; }
}
