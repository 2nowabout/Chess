package Functions;

import Objects.ChessPieces.*;
import Objects.Tile;

import java.util.ArrayList;
import java.util.List;

public class GenerateBord {
    private List<Tile> bord;

    public GenerateBord()
    {
        bord = new ArrayList<>();
    }

    public List<Tile> generatBord() {
        int originalyPos = 940;
        int originalxPos = 560;
        int xPos = originalxPos;
        int yPos = originalyPos;
        boolean whiteTile = true;
        String whiteTexture = "WhiteTile.png";
        String blackTexture = "BlackTile.png";

        for (int y = 0; y < 8; y++) {
            for (int x = 0; x < 8; x++) {
                if (whiteTile) {
                    bord.add(new Tile(xPos, yPos, x, y, whiteTexture));
                    whiteTile = false;
                } else {
                    bord.add(new Tile(xPos, yPos, x, y, blackTexture));
                    whiteTile = true;
                }
                if (xPos >= 1260) {
                    xPos = originalxPos;
                    yPos = yPos - 100;
                    if (y%2 == 0) {
                        whiteTile = false;
                    } else {
                        whiteTile = true;
                    }
                } else {
                    xPos = xPos + 100;
                }
            }
        }
        placeChestPieces();
        return bord;
    }

    private void placeChestPieces()
    {
        for (Tile tile: bord) {
            if(tile.getY() < 3)
            {
                placeBlack(tile);
            }
            else if(tile.getY() > 5)
            {
                placeWhite(tile);
            }
        }
    }
    private void placeBlack(Tile tile)
    {
        if(tile.getY() == 0)
        {
            switch (tile.getX())
            {
                case 0:
                case 7:
                    tile.setChesspieces(new Rook(false, tile.getX(), tile.getY()));
                    break;
                case 1:
                case 6:
                    tile.setChesspieces(new Knight(false, tile.getX(), tile.getY()));
                    break;
                case 2:
                case 5:
                    tile.setChesspieces(new Bishop(false, tile.getX(), tile.getY()));
                    break;
                case 3:
                    tile.setChesspieces(new Queen(false, tile.getX(), tile.getY()));
                    break;
                case 4:
                    tile.setChesspieces(new King(false, tile.getX(), tile.getY()));
                    break;
            }
        }
        else if (tile.getY() == 1)
        {
            tile.setChesspieces(new Pawn(false, tile.getX(), tile.getY()));
        }
    }
    private void placeWhite(Tile tile) {
        if (tile.getY() == 6) {
            tile.setChesspieces(new Pawn(true, tile.getX(), tile.getY()));
        } else if (tile.getY() == 7) {
            switch (tile.getX()) {
                case 0:
                case 7:
                    tile.setChesspieces(new Rook(true, tile.getX(), tile.getY()));
                    break;
                case 1:
                case 6:
                    tile.setChesspieces(new Knight(true, tile.getX(), tile.getY()));
                    break;
                case 2:
                case 5:
                    tile.setChesspieces(new Bishop(true, tile.getX(), tile.getY()));
                    break;
                case 3:
                    tile.setChesspieces(new Queen(true, tile.getX(), tile.getY()));
                    break;
                case 4:
                    tile.setChesspieces(new King(true, tile.getX(), tile.getY()));
                    break;
            }
        }
    }
}
