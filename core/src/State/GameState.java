package State;

import AI.Bot;
import Functions.generateBord;
import Objects.ChessPieces.King;
import Objects.ChessPieces.Pawn;
import Objects.Tile;
import SaveLibraries.Postition;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State {

    private GameStateManager gsm;

    private List<Tile> bord;
    private boolean turn = true;
    private boolean clickedOnChesspiece = false;
    private List<Postition> canMovePosition;
    private Tile selectedChessPiece;
    private Bot bot;

    public GameState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        bord = new ArrayList<>();
        canMovePosition = new ArrayList<>();
        generateBord generate = new generateBord();
        bord = generate.generatBord(); //generate start layout
        if(gsm.singlePlayer)
        {
            bot = new Bot(bord);
        }
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {  //exit game if escape is pressed
            Gdx.app.exit();
        }
        for (Tile tile : bord) {
            if (tile.hasChesspiece() && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) // check if player clicked to reset possible tiles
            {
                tile.getChesspieces().resetMoves();
            }
            Rectangle mouseRectangle = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1); //get mouse position
            mouseRectangle.y = Gdx.graphics.getHeight() - mouseRectangle.y; // invert y, this is already inverted in the game
            if (tile.hasChesspiece()) { // check if tile has a chesspiece
                if (tile.getChesspieces().getRectangle().intersects(mouseRectangle) && (turn == tile.getChesspieces().getColor())) { // check if mouse rectangle is on a chesspiece
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) { // if user clicks on the chesspiece
                        tile.getChesspieces().calculateMoves(bord);
                        canMovePosition = tile.getChesspieces().getPossibleMoves();
                        clickedOnChesspiece = true;
                        selectedChessPiece = tile;

                    }
                }
            }
            if (clickedOnChesspiece) {
                if (tile.getRectangle().intersects(mouseRectangle)) {
                    if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                        if (tile.hasChesspiece()) {
                            if (selectedChessPiece.getChesspieces().getColor() != tile.getChesspieces().getColor() && tile.isCanMoveHere()) {
                                tile.setChesspieces(selectedChessPiece.getChesspieces());
                                tile.removeChestpiece();
                                checkWin();
                                clickedOnChesspiece = false;
                            }
                        }
                        if (!tile.hasChesspiece() && tile.isCanMoveHere()) {
                            tile.setChesspieces(selectedChessPiece.getChesspieces());
                            if (tile.getChesspieces().isPawn()) {
                                Pawn pawn = (Pawn) tile.getChesspieces();
                                pawn.setFirstmove(false);
                            }
                            tile.updateChessPiece();
                            clickedOnChesspiece = false;
                            for (Tile tileRemove : bord) {
                                if (tileRemove.getX() == selectedChessPiece.getX() && tileRemove.getY() == selectedChessPiece.getY()) {
                                    tileRemove.getChesspieces().resetMoves();
                                    canMovePosition = new ArrayList<>();
                                    tileRemove.removeChestpiece();
                                    turn = !turn;
                                }
                            }
                        }
                    }
                }
            }
        }
        checkKings();
    }

    @Override
    public void update(float dt) {
        for (Tile tile : bord) {
            tile.update(dt);
            tile.setCanMoveHere(false);
            if (!canMovePosition.isEmpty()) //check if list of positions is empty or filled
            {
                for (Postition position : canMovePosition) {
                    if (tile.getX() == position.getX() && tile.getY() == position.getY()) //check what tile has the position
                    {
                        tile.setCanMoveHere(true); // make tile render a blue square to move too
                    }
                }
            }
        }
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (Tile tile : bord) {
            tile.render(sb); //render all tiles and chesspieces if they are on the tile
        }
        sb.end();
    }

    @Override
    public void dispose() {

    }

    private void checkWin() {
        boolean white = false;
        boolean black = false;
        for (Tile tile : bord) {
            if (tile.hasChesspiece()) {
                if (tile.getChesspieces().isKing() && tile.getChesspieces().getColor()) {
                    white = true;
                }
                if (tile.getChesspieces().isKing() && !tile.getChesspieces().getColor()) {
                    black = true;
                }
            }
        }
        if (!white) {
            gsm.push(new GameState(gsm));
        }
        if (!black) {
            gsm.push(new GameState(gsm));
        }
    }

    private void checkKings() {
        boolean whiteKingDead = false;
        boolean blackKingDead = false;
        for (Tile tile : bord) {
            if (tile.hasChesspiece()) {
                if (tile.getChesspieces().isKing()) {
                    King king = (King) tile.getChesspieces();
                    if (king.getColor()) {
                        king.checkChecked(bord);
                        whiteKingDead = king.checkCheckmate(bord);
                    } else if (!king.getColor()) {
                        king.checkChecked(bord);
                        blackKingDead = king.checkCheckmate(bord);
                    }
                }
            }
        }
        if (blackKingDead) {
            gsm.push(new GameState(gsm));
        }
        if (whiteKingDead) {
            gsm.push(new GameState(gsm));
        }
    }
}
