package Objects.ChessPieces;

import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;
import java.util.List;

public class Knight extends Chesspieces {
    public Knight(boolean white, int x, int y) {
        super(white, x, y);
        if (white) {
            texture = new Texture("WhiteKnight.png");
        } else {
            texture = new Texture("BlackKnight.png");
        }
    }

    @Override
    public void calculateMoves(List<Tile> tiles) {
        Postition pos = new Postition(x+2, y+1);
        possibleMoves.add(pos);
        pos = new Postition(x+2, y-1);
        possibleMoves.add(pos);
        pos = new Postition(x-2, y+1);
        possibleMoves.add(pos);
        pos = new Postition(x-2, y-1);
        possibleMoves.add(pos);
        pos = new Postition(x-1,y+2);
        possibleMoves.add(pos);
        pos = new Postition(x+1, y+2);
        possibleMoves.add(pos);
        pos = new Postition(x+1, y-2);
        possibleMoves.add(pos);
        pos = new Postition(x-1, y-2);
        possibleMoves.add(pos);
        possibleMoves.removeAll(notPossibleMoves(tiles));
    }

    private List<Postition> notPossibleMoves(List<Tile> tiles) {
        List<Postition> notPossiblePositions = new ArrayList<>();
        for (Postition pos : possibleMoves) {
            for (Tile tile : tiles) {
                if (pos.getX() == tile.getX() && pos.getY() == tile.getY()) {
                    if (tile.hasChesspiece) {
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
