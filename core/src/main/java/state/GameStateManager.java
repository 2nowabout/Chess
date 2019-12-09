package state;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import websockets.websocket.handlerContext;

import java.util.Stack;

public class GameStateManager {
    private Stack<State> states;
    private boolean singlePlayer = true;
    private boolean localPlay = false;
    private boolean multiPlayer = false;
    private handlerContext controller;
    private Texture background;

    public GameStateManager()
    {
        states = new Stack<State>();
        controller = new handlerContext(this);
    }

    public void push(State state)
    {
        states.push(state);
    }

    public void pop()
    {
        states.pop();
    }

    public void set(State state)
    {
        states.pop();
        states.push(state);
    }

    public void update(float dt) { states.peek().update(dt); }
    public void render(SpriteBatch sb) { states.peek().render(sb); }

    public boolean isSinglePlayer() { return singlePlayer; }
    public void setSinglePlayer(boolean singlePlayer) { this.singlePlayer = singlePlayer; }
    public boolean isLocalPlay() { return localPlay; }
    public void setLocalPlay(boolean localPlay) { this.localPlay = localPlay; }
    public boolean isMultiPlayer() { return multiPlayer; }
    public void setMultiPlayer(boolean multiPlayer) { this.multiPlayer = multiPlayer; }
    public State getCurrentState() { return states.peek(); }
    public Texture getBackground() { return background; }
    public void setBackground(Texture background) { this.background = background; }
}
