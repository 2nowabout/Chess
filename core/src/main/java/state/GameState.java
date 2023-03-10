package state;

import checks.checkWin;
import checks.gameChecks;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import functions.generateBord;
import interfaces.iButtons;
import interfaces.iGameChecks;
import interfaces.iGenerateBord;
import interfaces.iTile;
import objects.Buttons;
import objects.chessPieces.Pawn;
import saveLibraries.Position;
import websockets.messageCreator.iJsonCreator;
import websockets.messageSender.MessageSender;
import websockets.websocket.Websocket;

import java.awt.*;
import java.util.ArrayList;

public class GameState extends State {

    private GameStateManager gsm;

    private boolean turn;
    private boolean clickedOnChesspiece = false;
    private boolean settingsOpen;
    private boolean youAreWhite;
    private boolean needToCheck;

    private iButtons settingsButton;
    private iGameChecks checks;
    private checkWin winCheck;

    private ArrayList<iTile> bord;
    private ArrayList<Position> canMovePosition;
    private iTile selectedChessPiece;
    private State settings;
    private iJsonCreator messageCreator;


    public GameState(GameStateManager gsm, boolean firstToPlay, iJsonCreator messageCreator) {
        super(gsm);
        this.gsm = gsm;
        needToCheck = false;
        winCheck = new checkWin();
        bord = new ArrayList<>();
        canMovePosition = new ArrayList<>();
        iGenerateBord bordGenerator = new generateBord();
        bord = bordGenerator.generate(); //generate start layout
        turn = true;
        checks = new gameChecks();
        settingsButton = new Buttons(Gdx.graphics.getWidth() - 200, Gdx.graphics.getHeight() - 100, "", 50, 50, "settings.png");
        settings = new Settings(gsm, this);
        this.messageCreator = messageCreator;
        if (firstToPlay) {
            youAreWhite = true;
        } else {
            youAreWhite = false;
            turn = false;
        }
    }

    @Override
    protected void handleInput() {
        checks.closeApp();
        Rectangle mouseRectangle = new Rectangle(Gdx.input.getX(), Gdx.input.getY(), 1, 1); //get mouse position
        mouseRectangle.y = Gdx.graphics.getHeight() - mouseRectangle.y; // invert y, this is already inverted in the game
        settingsOpen = checks.openSettingsCheck(settingsButton, mouseRectangle, settingsOpen);
        for (iTile tile : bord) {
            if (tile.hasChesspiece() && Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) // check if player clicked to reset possible tiles
            {
                tile.getChesspieces().resetMoves();
            }
            if (tile.hasChesspiece()) { // check if tile has a chesspiece
                checkSelectedPiece(tile, mouseRectangle);
            }
            if (clickedOnChesspiece) {
                chesspieceMoveActionCheck(tile, mouseRectangle);
            }
        }
    }

    @Override
    public void update(float dt) {
        for (iTile tile : bord) {
            tile.update(dt);
            tile.setCanMoveHere(false);
            checks.checkTile(tile, canMovePosition, dt);
            settingsButton.update(dt);
        }
        if(needToCheck) {
            checks.checkKings(gsm, bord);
            checks.checkKingsdead(gsm, bord);
            needToCheck = false;
        }
        handleInput();
        if (settingsOpen) {
            settings.update(dt);
        }
        if(checks.hasWhiteWon() || checks.hasBlackWon())
        {
            winCheck.checkIfWon(youAreWhite, messageCreator, checks);
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

    private void checkSelectedPiece(iTile tile, Rectangle mouseRectangle) {
        if (tile.getChesspieces().getRectangle().intersects(mouseRectangle) && youAreWhite == tile.getChesspieces().getColor() && turn) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) { // if user clicks on the chesspiece
                tile.getChesspieces().calculateMoves(bord);
                canMovePosition = tile.getChesspieces().getPossibleMoves();
                clickedOnChesspiece = true;
                selectedChessPiece = tile;
            }
        }
    }

    private void endTurn() {
        turn = !turn;
    }

    private void chesspieceMoveActionCheck(iTile tile, Rectangle mouseRectangle) {
        if (tile.getRectangle().intersects(mouseRectangle)) {
            if (Gdx.input.isButtonJustPressed(Input.Buttons.LEFT)) {
                if (tile.hasChesspiece()) {
                    if (selectedChessPiece.getChesspieces().getColor() != tile.getChesspieces().getColor() && tile.isCanMoveHere()) {
                        tile.removeChestpiece();
                        checks.checkKingsdead(gsm, bord);
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
                            MessageSender.broadcast(messageCreator.moveMessage(tileRemove, tile, youAreWhite));
                            needToCheck = true;
                            endTurn();
                        }
                    }
                }
            }
        }
    }

    public void enemyMove(Position newPos, Position oldPos) {
        iTile oldTile = null;
        iTile newTile = null;
        for (iTile tile : bord) {
            if (tile.getX() == oldPos.getX() && tile.getY() == oldPos.getY()) {
                oldTile = tile;
            } else if (tile.getX() == newPos.getX() && tile.getY() == newPos.getY()) {
                newTile = tile;
            }
        }
        newTile.setChesspieces(oldTile.getChesspieces());
        oldTile.removeChestpiece();
        needToCheck = true;
        turn = !turn;
    }

    public void setSettingsOpen(boolean settingsOpen) {
        this.settingsOpen = settingsOpen;
    }
}
