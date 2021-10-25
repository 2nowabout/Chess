package objects.ChessPiecesFunctions;

public class TilePositionGenerator {
    private static Character[] vertical = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H'};

    public static String getPositionString(int x, int y)
    {
        return vertical[y].toString() + (x + 1);
    }
}
