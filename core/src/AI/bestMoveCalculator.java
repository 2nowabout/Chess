package AI;

import java.util.List;

public class bestMoveCalculator {

    private Moves bestmove;

    public Moves calcbestMove(List<Moves> moves)
    {
        bestmove = null;
        for (Moves move: moves) {
            if(bestmove == null)
            {
                bestmove = move;
            }
            else
            {
                if(bestmove.getPoints() < move.getPoints())
                {
                    bestmove = move;
                }
                else if (bestmove.getPoints() == move.getPoints())
                {
                    if(bestmove.getChesspieces().getPoints() > move.getChesspieces().getPoints())
                    {
                        bestmove = move;
                    }
                }
            }
        }
        return bestmove;
    }



}
