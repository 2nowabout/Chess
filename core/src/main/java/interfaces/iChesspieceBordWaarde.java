package interfaces;

import java.util.ArrayList;

public interface iChesspieceBordWaarde {
    ArrayList<Integer> pawnValues();
    ArrayList<Integer> queenValues();
    ArrayList<Integer> kingValues();
    ArrayList<Integer> rookValues();
    ArrayList<Integer> bishopValues();
    ArrayList<Integer> knightValues();
}
