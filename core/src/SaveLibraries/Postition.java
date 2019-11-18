package SaveLibraries;

public class Postition {

    private int x;
    private int y;

    public Postition(int x, int y) {
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
        if (!(anObject instanceof Postition)) {
            return false;
        }
        Postition otherMember = (Postition) anObject;
        if (x == otherMember.getX() && y == otherMember.getY()) {
            return true;
        } else {
            return false;
        }
    }
}
