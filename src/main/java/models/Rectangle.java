package models;

/**
 * This class is for the Rectangle object. A rectangle can be identified with a pair of coordinates.
 * The coordinates in consideration are topLeft and bottomRight. topLeft and bottomRight are both
 * objects of class Coordinate.It has a constructor to set the values of topLeft and bottomRight while
 * initialization.
 */
public class Rectangle {
    private Coordinate topLeft;
    private Coordinate bottomRight;

    public Rectangle(Coordinate topLeft, Coordinate bottomRight)
    {
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }
    public Coordinate getTopLeft() {
        return topLeft;
    }

    public void setTopLeft(Coordinate topLeft) {
        this.topLeft = topLeft;
    }

    public Coordinate getBottomRight() {
        return bottomRight;
    }

    public void setBottomRight(Coordinate bottomRight) {
        this.bottomRight = bottomRight;
    }
}
