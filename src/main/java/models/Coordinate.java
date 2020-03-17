package models;

/**
 * This class is for coordinates. Each coordinate has two attributes. X and Y attributes.
 * It has a constructor to set the values of coordinates while initialization, or later by using
 * getters and setters.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }
}
