package objects.chessPieces;

import com.badlogic.gdx.graphics.Texture;
import interfaces.iTile;
import saveLibraries.Position;

import java.util.ArrayList;

public class Knight extends Chesspieces {

    public Knight(boolean white, int x, int y) {
        super(white, x, y);
        if (white) {
            points = 30;
        } else {
            points = -30;
        }
    }

    @Override
    public void calculateMoves(ArrayList<iTile> tiles) {
        Position pos = new Position(x + 2, y + 1);
        possibleMoves.add(pos);
        pos = new Position(x + 2, y - 1);
        possibleMoves.add(pos);
        pos = new Position(x - 2, y + 1);
        possibleMoves.add(pos);
        pos = new Position(x - 2, y - 1);
        possibleMoves.add(pos);
        pos = new Position(x - 1, y + 2);
        possibleMoves.add(pos);
        pos = new Position(x + 1, y + 2);
        possibleMoves.add(pos);
        pos = new Position(x + 1, y - 2);
        possibleMoves.add(pos);
        pos = new Position(x - 1, y - 2);
        possibleMoves.add(pos);
        possibleMoves.removeAll(notPossibleMoves(tiles));
    }

    @Override
    public void loadTextures() {
        if (white) {
            texture = new Texture("WhiteKnight.png");
        } else {
            texture = new Texture("BlackKnight.png");
        }
    }

    @Override
    public ArrayList<ArrayList<Double>> getFieldPoints() {
        return generator.KingPoints();
    }

    private ArrayList<Position> notPossibleMoves(ArrayList<iTile> tiles) {
        ArrayList<Position> notPossiblePositions = new ArrayList<>();
        for (Position pos : possibleMoves) {
            if (pos.getX() < 0 || pos.getX() > 8 || pos.getY() < 0 || pos.getY() > 8) {
                notPossiblePositions.add(pos);
            }
            for (iTile tile : tiles) {
                if (pos.getX() == tile.getX() && pos.getY() == tile.getY()) {
                    if (tile.hasChesspiece()) {
                        if (white && tile.getChesspieces().white || !white && !tile.getChesspieces().white) {
                            notPossiblePositions.add(pos);
                        }
                    }
                }
            }
        }
        return notPossiblePositions;
    }
}
