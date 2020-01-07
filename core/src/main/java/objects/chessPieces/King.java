package objects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import interfaces.iTile;
import saveLibraries.Position;

import java.util.ArrayList;
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
        points = 900;
    }

    @Override
    public void calculateMoves(ArrayList<iTile> tiles) {
        Position pos = new Position(x + 1, y);
        possibleMoves.add(pos);
        pos = new Position(x - 1, y);
        possibleMoves.add(pos);
        pos = new Position(x, y + 1);
        possibleMoves.add(pos);
        pos = new Position(x, y - 1);
        possibleMoves.add(pos);
        pos = new Position(x - 1, y + 1);
        possibleMoves.add(pos);
        pos = new Position(x - 1, y - 1);
        possibleMoves.add(pos);
        pos = new Position(x + 1, y + 1);
        possibleMoves.add(pos);
        pos = new Position(x + 1, y - 1);
        possibleMoves.add(pos);

        possibleMoves.removeAll(notPossibleMoves(tiles));
    }

    public void checkChecked(ArrayList<iTile> tiles)
    {
        ArrayList<Position> allenemysmoves = new ArrayList<>();
        ArrayList<Chesspieces> enemypieces = new ArrayList<>();
        for (iTile tile: tiles) {
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
        for (Position position: allenemysmoves) {
            if(position.getX() == x && position.getY() == y)
            {
                checked = true;
                return;
            }
        }
        checked = false;
    }

    private ArrayList<Position> notPossibleMoves(ArrayList<iTile> tiles) {
        ArrayList<Position> notPossibleMoves = new ArrayList<>();
        ArrayList<Chesspieces> enemys = new ArrayList<>();
        ArrayList<Position> allmovesenemy = new ArrayList<>();
        enemys = getAllEnemys(tiles);
        for (Chesspieces chess : enemys) {
            /*if(!chess.isKing) {*/
                chess.calculateMoves(tiles);
                allmovesenemy.addAll(chess.possibleMoves);
            //}
        }
        List<Position> positionsToRemove = new ArrayList<>();
        for (Position kingmoves : possibleMoves) {
            for (Position enemymoves : allmovesenemy) {
                if (kingmoves.getX() == enemymoves.getX() && kingmoves.getY() == enemymoves.getY()) {
                    positionsToRemove.add(kingmoves);
                }
            }
        }
        notPossibleMoves.addAll(positionsToRemove);
        for (Position pos : possibleMoves) {
            if(pos.getX() < 0 || pos.getX() > 9 || pos.getY() < 0 || pos.getY() > 9)
            {
                notPossibleMoves.add(pos);
            }
            for (iTile tile : tiles) {
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

    public boolean checkCheckmate(ArrayList<iTile> tiles) {
        ArrayList<Chesspieces> enemys = new ArrayList<>();
        ArrayList<Position> allmovesenemy = new ArrayList<>();
        ArrayList<Position> allkingmoves;
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
        List<Position> positionsToRemove = new ArrayList<>();
        for (Position kingmoves : possibleMoves) {
            for (Position enemymoves : allmovesenemy) {
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

    private ArrayList<Chesspieces> getAllEnemys(List<iTile> tiles)
    {
        ArrayList<Chesspieces> chesspieces = new ArrayList<>();
        for (iTile tile : tiles) {
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

    public boolean isChecked() { return checked; }
}
