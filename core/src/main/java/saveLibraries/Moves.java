package saveLibraries;

import interfaces.iTile;
import objects.chessPieces.Chesspieces;

public class Moves {
    private double points;
    private iTile tile;
    private Chesspieces chesspieces;
    private int ID;

    public Moves(double points, iTile tile, Chesspieces chesspieces)
    {
        this.points = points;
        this.chesspieces = chesspieces;
        this.tile = tile;
    }

    public double getPoints() { return points; }
    public iTile getTile() { return tile; }
    public Chesspieces getChesspieces() { return chesspieces; }
    public void addPoints(double value) { points = points + value; }
    public int getID() { return ID; }
    public void setID(int ID) { this.ID = ID; }
}
