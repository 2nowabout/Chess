package Objects.ChessPieces;

import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.graphics.Texture;
import com.twonowabout.Chess;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class King extends Chesspieces {

    private boolean checked = false;

    public King(boolean white, int x, int y) {
        super(white, x, y);
        if (white) {
            texture = new Texture("WhiteKing.png");
        } else {
            texture = new Texture("BlackKing.png");
        }
        isKing = true;
    }

    @Override
    public void calculateMoves(List<Tile> tiles) {
        Postition pos = new Postition(x + 1, y);
        possibleMoves.add(pos);
        pos = new Postition(x - 1, y);
        possibleMoves.add(pos);
        pos = new Postition(x, y + 1);
        possibleMoves.add(pos);
        pos = new Postition(x, y - 1);
        possibleMoves.add(pos);
        pos = new Postition(x - 1, y + 1);
        possibleMoves.add(pos);
        pos = new Postition(x - 1, y - 1);
        possibleMoves.add(pos);
        pos = new Postition(x + 1, y + 1);
        possibleMoves.add(pos);
        pos = new Postition(x + 1, y - 1);
        possibleMoves.add(pos);
        possibleMoves.removeAll(notPossibleMoves(tiles));
    }

    public void checkChecked(List<Tile> tiles)
    {
        List<Postition> allenemysmoves = new ArrayList<>();
        List<Chesspieces> enemypieces = new ArrayList<>();
        for (Tile tile: tiles) {
            if(tile.hasChesspiece())
            {
                if(white && !tile.getChesspieces().white)
                {
                    enemypieces.add(tile.getChesspieces());
                }
                else if(!white && tile.getChesspieces().white)
                {
                    enemypieces.add(tile.getChesspieces());
                }
            }
        }
        for (Chesspieces piece: enemypieces) {
            piece.calculateMoves(tiles);
            allenemysmoves.addAll(piece.possibleMoves);
        }
        for (Postition position: allenemysmoves) {
            if(position.getX() == x && position.getY() == y)
            {
                checked = true;
                return;
            }
        }
        checked = false;
    }

    private List<Postition> notPossibleMoves(List<Tile> tiles) {
        List<Postition> notPossibleMoves = new ArrayList<>();
        List<Chesspieces> enemys = new ArrayList<>();
        List<Postition> allmovesenemy = new ArrayList<>();
        enemys = getAllEnemys(tiles);
        for (Chesspieces chess : enemys) {
            chess.calculateMoves(tiles);
            allmovesenemy.addAll(chess.possibleMoves);
        }
        List<Postition> positionsToRemove = new ArrayList<>();
        for (Postition kingmoves : possibleMoves) {
            for (Postition enemymoves : allmovesenemy) {
                if (kingmoves.getX() == enemymoves.getX() && kingmoves.getY() == enemymoves.getY()) {
                    positionsToRemove.add(kingmoves);
                }
            }
        }
        notPossibleMoves.addAll(positionsToRemove);
        for (Postition pos : possibleMoves) {
            if(pos.getX() < 0 || pos.getX() > 9 || pos.getY() < 0 || pos.getY() > 9)
            {
                notPossibleMoves.add(pos);
            }
            for (Tile tile : tiles) {
                if (pos.getX() == tile.getX() && pos.getY() == tile.getY()) {
                    if (tile.hasChesspiece()) {
                        if (white && tile.getChesspieces().white || !white && !tile.getChesspieces().white) {
                            notPossibleMoves.add(pos);
                        }
                    }
                }
            }
        }
        return notPossibleMoves;
    }

    public boolean checkCheckmate(List<Tile> tiles) {
        List<Chesspieces> enemys = new ArrayList<>();
        List<Postition> allmovesenemy = new ArrayList<>();
        List<Postition> allkingmoves;
        if(!checked)
        {
            return false;
        }
        enemys = getAllEnemys(tiles);
        for (Chesspieces chess : enemys) {
            chess.calculateMoves(tiles);
            allmovesenemy.addAll(chess.possibleMoves);
        }
        calculateMoves(tiles);
        allkingmoves = possibleMoves;
        List<Postition> positionsToRemove = new ArrayList<>();
        for (Postition kingmoves : possibleMoves) {
            for (Postition enemymoves : allmovesenemy) {
                if (kingmoves.getX() == enemymoves.getX() && kingmoves.getY() == enemymoves.getY()) {
                    positionsToRemove.add(kingmoves);
                }
            }
        }
        allkingmoves.removeAll(positionsToRemove);
        if (allkingmoves.isEmpty() && checked) {
            return true;
        } else {
            return false;
        }
    }

    private List<Chesspieces> getAllEnemys(List<Tile> tiles)
    {
        List<Chesspieces> chesspieces = new ArrayList<>();
        for (Tile tile : tiles) {
            if (tile.hasChesspiece()) {
                if (white) {
                    if (!tile.getChesspieces().white) {
                        if(!tile.getChesspieces().isKing) {
                            chesspieces.add(tile.getChesspieces());
                        }
                    }
                } else {
                    if (tile.getChesspieces().white) {
                        if(!tile.getChesspieces().isKing) {
                            chesspieces.add(tile.getChesspieces());
                        }
                    }
                }
            }
        }
        return chesspieces;
    }
}
