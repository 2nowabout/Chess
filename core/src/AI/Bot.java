package AI;

import Objects.Tile;

import java.util.List;

public class Bot {
    private List<Tile> bord;
    public Bot(List<Tile> tiles)
    {
        bord = tiles;
    }

    public void updateBord(List<Tile> tiles)
    {
        bord = tiles;
    }

    public void act()
    {

    }
}
