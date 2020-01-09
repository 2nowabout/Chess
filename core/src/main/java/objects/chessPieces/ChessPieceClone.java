package objects.chessPieces;

import com.rits.cloning.Cloner;

import java.util.Set;

public class ChessPieceClone {
    public static Chesspieces checkChessPiece(Chesspieces piece)
    {
        Cloner cloner=new Cloner();

        Chesspieces clone=cloner.deepClone(piece);
        return clone;
    }
}
