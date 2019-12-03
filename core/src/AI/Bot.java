package AI;

import Interfaces.iBot;
import Interfaces.iTile;
import Objects.ChessPieces.Chesspieces;
import SaveLibraries.Position;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot implements iBot {
    private ArrayList<iTile> bord;
    private List<Chesspieces> enemyChesspieces;
    private List<Chesspieces> botChesspieces;
    private List<Position> possibleEnemyPositions;
    private List<Position> possibleAllyPositions;
    private bestMoveCalculator calculator = new bestMoveCalculator();
    allMovesCalculator allmovescalc = new allMovesCalculator();

    public Bot(ArrayList<iTile> tiles)
    {
        bord = tiles;
    }

    public void updateBord(ArrayList<iTile> tiles)
    {
        bord = tiles;
    }

    public void act()
    {
        enemyChesspieces = new ArrayList<>();
        botChesspieces = new ArrayList<>();
        possibleAllyPositions = new ArrayList<>();
        possibleEnemyPositions = new ArrayList<>();
        List<iTile> toRemove = getAlliesAndEnemys();
        List<Moves> CheckMoves =  allmovescalc.calcAllMoves(botChesspieces, bord);

/*        for (Chesspieces chesspiece : enemyChesspieces) {
            chesspiece.calculateMoves(bord);
            if(!chesspiece.getPossibleMoves().isEmpty() || chesspiece.getPossibleMoves() != null) {
                possibleEnemyPositions.addAll(chesspiece.getPossibleMoves());
            }
        }*/

        Random rnd = new Random();
        boolean canWork = false;
        Chesspieces chesspiece = null;
        if(CheckMoves.isEmpty()) {
            while (!canWork) {
                int nummer = rnd.nextInt(botChesspieces.size());
                chesspiece = botChesspieces.get(nummer);
                chesspiece.resetMoves();
                chesspiece.calculateMoves(bord);
                if (!chesspiece.getPossibleMoves().isEmpty()) {
                    toRemove.get(nummer).removeChestpiece();
                    canWork = true;
                }
            }
            int move = rnd.nextInt(chesspiece.getPossibleMoves().size());
            Position pos = chesspiece.getPossibleMoves().get(move);
            for (iTile tile: bord) {
                if(tile.getX() == pos.getX() && tile.getY() == pos.getY())
                {
                    tile.setChesspieces(chesspiece);
                }
            }
        }
        else
        {
            Moves bestmove = calculator.calcbestMove(CheckMoves);

            chesspiece = bestmove.getChesspieces();
            for (Position pos: chesspiece.getPossibleMoves()) {
                if(pos.getX() == bestmove.getTile().getX() && pos.getY() == bestmove.getTile().getY())
                {
                    bestmove.getTile().setChesspieces(bestmove.getChesspieces());
                    for (iTile tile: toRemove) {
                        if(tile.getChesspieces() == bestmove.getChesspieces())
                        {
                            tile.removeChestpiece();
                        }
                    }
                }
            }
        }

        chesspiece.resetMoves();
    }

    private void minMaxAlgorithm()
    {

    }

    private List<iTile> getAlliesAndEnemys()
    {
        List<iTile> toRemove = new ArrayList<>();
        for (iTile tile: bord) {
            if(tile.hasChesspiece())
            {
                tile.getChesspieces().resetMoves();
                if(tile.getChesspieces().getColor())
                {
                    enemyChesspieces.add(tile.getChesspieces());
                }
                else
                {
                    botChesspieces.add(tile.getChesspieces());
                    toRemove.add(tile);
                }
            }
        }
        return toRemove;
    }
}
