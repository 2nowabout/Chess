package aI;

import interfaces.iTile;
import objects.Tile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Moves;

import java.util.ArrayList;
import java.util.List;

public class MaxAlgorithm implements iMinMaxAlgorithm {
    private List<BordAndMoves> allBorden;
    private List<Moves> acceptedmoves;
    private List<Moves> moves;
    private BotGetChesspieces getChesspieces;
    private allMovesCalculator allMovesCalculator;
    private MakeFields makeFields;
    private AverageMoveCalculator averageCalculator;
    private AddIDOnMoves addIDOnMoves;

    public MaxAlgorithm() {
        getChesspieces = new BotGetChesspieces();
        allMovesCalculator = new allMovesCalculator();
        makeFields = new MakeFields();
        averageCalculator = new AverageMoveCalculator();
        addIDOnMoves = new AddIDOnMoves();
    }

    public void algorithm(List<BordAndMoves> borden, boolean first) {
        allBorden = new ArrayList<>();
        moves = new ArrayList<>();
        for (BordAndMoves lists : borden) {
            acceptedmoves = new ArrayList<>();
            List<Chesspieces> allies = getChesspieces.getAllyChessPieces(lists.getBord());
            List<Moves> allmoves = allMovesCalculator.calcAllMoves(allies, lists.getBord());
            allmoves = addIDOnMoves.AddIDToMoves(allmoves, first, lists);
            double average = averageCalculator.calculateAverage(allmoves);
            for (Moves move : allmoves) {
                if (move.getPoints() >= average) {
                    acceptedmoves.add(move);
                    Moves forMoves = new Moves(move.getPoints(), new Tile(0,0,move.getTile().getX(), move.getTile().getY()), move.getChesspieces().clone(move.getChesspieces()));
                    forMoves.setID(move.getID());
                    moves.add(forMoves);
                }
            }
            for (Moves move : acceptedmoves) {
                BordAndMoves toAdd = new BordAndMoves(makeFields.doMoveAndMakeField(lists.getBord(), move), move);
                allBorden.add(toAdd);
            }
            System.out.println(allBorden.size());
        }
    }

    public List<BordAndMoves> getAllBorden() { return allBorden; }
    public List<Moves> getMoves() { return moves; }
}
