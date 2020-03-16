package models;

public class Rectangle {
    private Coordinate topLeft;
    private Coordinate bottomRight;

    public Rectangle()
    {

    }
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
