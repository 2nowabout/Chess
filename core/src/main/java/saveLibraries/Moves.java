package saveLibraries;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;

public class Moves {
    private int points;
    private iTile tile;
    private Chesspieces chesspieces;

    public Moves(int points, iTile tile, Chesspieces chesspieces)
    {
        this.points = points;
        this.chesspieces = chesspieces;
        this.tile = tile;
    }

    public int getPoints() { return points; }
    public iTile getTile() { return tile; }
    public Chesspieces getChesspieces() { return chesspieces; }
}
