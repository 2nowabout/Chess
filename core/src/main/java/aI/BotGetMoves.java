package aI;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;
import saveLibraries.Position;

import java.util.ArrayList;
import java.util.List;

public class BotGetMoves {
    private BotGetChesspieces getChesspieces;
    public BotGetMoves()
    {
        getChesspieces = new BotGetChesspieces();
    }
    public List<Position> getAllPossibleMoves(List<Chesspieces> chesspieces, ArrayList<iTile> bord)
    {
        List<Position> allMoves = new ArrayList<>();
        for (Chesspieces chesspiece: chesspieces) {
            chesspiece.calculateMoves(bord);
            allMoves.addAll(chesspiece.getPossibleMoves());
        }
        return allMoves;
    }
    public List<Position> getAllEnemyPossibleMoves(List<iTile> bord)
    {
        List<Position> allMoves = new ArrayList<>();
        ArrayList<iTile> tiles = new ArrayList<>(bord);
        for (Chesspieces chesspiece: getChesspieces.getEnemyChesspieces(bord)) {
            chesspiece.calculateMoves(tiles);
            allMoves.addAll(chesspiece.getPossibleMoves());
        }
        return allMoves;
    }
    public List<Position> getAllAllyPossibleMoves(List<iTile> bord)
    {
        List<Position> allMoves = new ArrayList<>();
        ArrayList<iTile> tiles = new ArrayList<>(bord);
        for (Chesspieces chesspiece: getChesspieces.getAllyChessPieces(bord)) {
            chesspiece.calculateMoves(tiles);
            allMoves.addAll(chesspiece.getPossibleMoves());
        }
        return allMoves;
    }
}
