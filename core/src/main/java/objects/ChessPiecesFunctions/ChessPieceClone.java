package objects.ChessPiecesFunctions;

import com.rits.cloning.Cloner;
import objects.chessPieces.Chesspieces;

import java.util.Set;

public class ChessPieceClone {
    public static Chesspieces checkChessPiece(Chesspieces piece)
    {
        Cloner cloner=new Cloner();

        Chesspieces clone=cloner.deepClone(piece);
        return clone;
    }
}
