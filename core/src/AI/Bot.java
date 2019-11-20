package AI;

import Interfaces.iBot;
import Interfaces.iTile;
import Objects.ChessPieces.Chesspieces;
import Objects.Tile;
import SaveLibraries.Postition;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Bot implements iBot {
    private List<iTile> bord;
    private List<Chesspieces> enemyChesspieces;
    private List<Chesspieces> botChesspieces;
    private List<Postition> possibleEnemyPositions;
    private List<Postition> possibleAllyPositions;

    public Bot(List<iTile> tiles)
    {
        bord = tiles;
    }

    public void updateBord(List<iTile> tiles)
    {
        bord = tiles;
    }

    public void act()
    {
        enemyChesspieces = new ArrayList<>();
        botChesspieces = new ArrayList<>();
        possibleAllyPositions = new ArrayList<>();
        possibleEnemyPositions = new ArrayList<>();
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
/*        for (Chesspieces chesspiece : enemyChesspieces) {
            chesspiece.calculateMoves(bord);
            if(!chesspiece.getPossibleMoves().isEmpty() || chesspiece.getPossibleMoves() != null) {
                possibleEnemyPositions.addAll(chesspiece.getPossibleMoves());
            }
        }*/
        Random rnd = new Random();
        boolean canWork = false;
        Chesspieces chesspiece = null;
        while(!canWork) {
            int nummer = rnd.nextInt(botChesspieces.size());
            chesspiece = botChesspieces.get(nummer);
            chesspiece.resetMoves();
            chesspiece.calculateMoves(bord);
            if(!chesspiece.getPossibleMoves().isEmpty())
            {
                toRemove.get(nummer).removeChestpiece();
                canWork = true;
            }
        }
        int move = rnd.nextInt(chesspiece.getPossibleMoves().size());
        Postition pos = chesspiece.getPossibleMoves().get(move);
        for (iTile tile: bord) {
            if(tile.getX() == pos.getX() && tile.getY() == pos.getY())
            {
                    tile.setChesspieces(chesspiece);
            }
        }
        chesspiece.resetMoves();
    }

    private void minMaxAlgorithm()
    {

    }
}
