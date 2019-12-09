package saveLibraries;

public class Position {

    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object anObject) {
        if (!(anObject instanceof Position)) {
            return false;
        }
        Position otherMember = (Position) anObject;
        if (x == otherMember.getX() && y == otherMember.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
