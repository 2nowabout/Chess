package State;

import AI.Bot;
import Functions.generateBord;
import Interfaces.iBot;
import Interfaces.iButtons;
import Interfaces.iTile;
import Objects.Buttons;
import Objects.ChessPieces.King;
import Objects.ChessPieces.Pawn;
import Objects.Tile;
import Interfaces.iGenerateBord;
import SaveLibraries.Postition;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameState extends State {

    private GameStateManager gsm;

    private boolean turn;
    private boolean clickedOnChesspiece = false;
    private boolean settingsOpen;
    private boolean youAreWhite;
    private boolean localPlay;

    private iButtons settingsButton;

    private List<iTile> bord;
    private List<Postition> canMovePosition;
    private iTile selectedChessPiece;
    private iBot bot;
    private State settings;


    public GameState(GameStateManager gsm) {
        super(gsm);
        this.gsm = gsm;
        bord = new ArrayList<>();
        canMovePosition = new ArrayList<>();
        iGenerateBord bordGenerator = new generateBord();
        bord = bordGenerator.generate(); //generate start layout
        localPlay = gsm.isLocalPlay();
        turn = true;
        settingsButton = new Buttons(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100, "", 50, 50, "settings.png");
        if (gsm.isSinglePlayer()) {
            bot = new Bot(bord);
            youAreWhite = true;
        }
        settings = new Settings(gsm, this);
    }

    @Override
    protected void handleInput() {
        if (Gdx.input.isKeyPressed(Input.Keys.ESCAPE)) {  //exit game if escape is pressed
            Gdx.app.exit();
        }
        Rectangle mouseRectangle = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1); //get mouse position
        mouseRectangle.y = Gdx.graphics.getHeight() - mouseRectangle.y; // invert y, this is already inverted in the game
        if (settingsButton.getRectangle().intersects(mouseRectangle)) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                settingsOpen = true;
            }
        }
        for (iTile tile : bord) {
            if (tile.hasChesspiece() && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) // check if player clicked to reset possible tiles
            {
                tile.getChesspieces().resetMoves();
            }
            if (tile.hasChesspiece()) { // check if tile has a chesspiece
                checkSelectedPiece(tile, mouseRectangle);
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
                            clickedOnChesspiece = false;
                            for (iTile tileRemove : bord) {
                                if (tileRemove.getX() == selectedChessPiece.getX() && tileRemove.getY() == selectedChessPiece.getY()) {
                                    tileRemove.getChesspieces().resetMoves();
                                    canMovePosition = new ArrayList<>();
                                    tileRemove.removeChestpiece();
                                    if (gsm.isSinglePlayer()) {
                                        checkKings();
                                        checkWin();
                                        bot.updateBord(bord);
                                        bot.act();
                                    } else if (localPlay) {
                                        turn = !turn;
                                    } else {
                                        //multiplayer lol
                                    }
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
        for (iTile tile : bord) {
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
        settingsButton.update(dt);
        handleInput();
        if (settingsOpen) {
            settings.update(dt);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.begin();
        for (iTile tile : bord) {
            tile.render(sb); //render all tiles and chesspieces if they are on the tile
        }
        settingsButton.render(sb);
        if (settingsOpen) {
            settings.render(sb);
        }
        sb.end();
    }

    @Override
    public void dispose() {
        for (iTile tile : bord) {
            tile.dispose();
        }
    }

    private void checkWin() {
        boolean white = false;
        boolean black = false;
        for (iTile tile : bord) {
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
        for (iTile tile : bord) {
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

    private void checkSelectedPiece(iTile tile, Rectangle mouseRectangle) {
        if (!localPlay && tile.getChesspieces().getRectangle().intersects(mouseRectangle) && (youAreWhite == tile.getChesspieces().getColor())) { // check if mouse rectangle is on a chesspiece
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) { // if user clicks on the chesspiece
                tile.getChesspieces().calculateMoves(bord);
                canMovePosition = tile.getChesspieces().getPossibleMoves();
                clickedOnChesspiece = true;
                selectedChessPiece = tile;
            }
        }
        if (localPlay && tile.getChesspieces().getRectangle().intersects(mouseRectangle) && turn == tile.getChesspieces().getColor()) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) { // if user clicks on the chesspiece
                tile.getChesspieces().calculateMoves(bord);
                canMovePosition = tile.getChesspieces().getPossibleMoves();
                clickedOnChesspiece = true;
                selectedChessPiece = tile;
            }
        }
    }

    public void setSettingsOpen(boolean settingsOpen) { this.settingsOpen = settingsOpen; }
}
