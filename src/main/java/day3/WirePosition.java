package day3;

public class WirePosition {
    private int x;
    private int y;

    private WirePosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    public static WirePosition defaultPosition() {
        return new WirePosition(0,0);
    }
    public WirePosition moveUp() {
        return new WirePosition(x, y + 1);
    }

    public WirePosition moveDown() {
        return new WirePosition(x, y - 1);
    }

    public WirePosition moveLeft() {
        return new WirePosition(x - 1, y);
    }

    public WirePosition moveRight() {
        return new WirePosition(x + 1, y);
    }
}
