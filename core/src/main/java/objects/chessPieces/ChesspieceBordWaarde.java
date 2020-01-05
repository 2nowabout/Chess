package objects.chessPieces;

import interfaces.iChesspieceBordWaarde;

import java.util.ArrayList;

public class ChesspieceBordWaarde implements iChesspieceBordWaarde {
    @Override
    public ArrayList<Integer> pawnValues() {
        ArrayList values = new ArrayList();
        values.add(1);
        return values;

    }

    @Override
    public ArrayList<Integer> queenValues() {
        return null;
    }

    @Override
    public ArrayList<Integer> kingValues() {
        return null;
    }

    @Override
    public ArrayList<Integer> rookValues() {
        return null;
    }

    @Override
    public ArrayList<Integer> bishopValues() {
        return null;
    }

    @Override
    public ArrayList<Integer> knightValues() {
        return null;
    }
}
