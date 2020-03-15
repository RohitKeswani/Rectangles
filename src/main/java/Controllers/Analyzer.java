package Controllers;

import models.Rectangle;

public class Analyzer {
    public boolean[] analyze(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean[] status = new boolean[3];
        boolean contained, adjacent, intersect = false;
        contained = checkContainment(firstRectangle, secondRectangle);
        adjacent = checkAdjacent(firstRectangle, secondRectangle);
        if(!contained) {
            intersect = checkIntersection(firstRectangle, secondRectangle);
        }
        status[0] = contained;
        status[1] = adjacent;
        status[2] = intersect;
        return status;
    }

    private boolean checkIntersection(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean intersect = false;
        if(firstRectangle.getBottomRight().getX() < secondRectangle.getBottomRight().getX())
            System.out.println("java is mad");
        if((firstRectangle.getBottomRight().getX() > secondRectangle.getTopLeft().getX() &&
                firstRectangle.getBottomRight().getX() < secondRectangle.getBottomRight().getX()) ||
                //TODO following condition returns true for R1=25,60 and R2=40,20
                (firstRectangle.getBottomRight().getX() > secondRectangle.getTopLeft().getX() &&
                firstRectangle.getTopLeft().getX() < secondRectangle.getTopLeft().getX()))
            intersect = true;
        if((firstRectangle.getTopLeft().getY() > secondRectangle.getBottomRight().getY() &&
                firstRectangle.getBottomRight().getY() < secondRectangle.getBottomRight().getY())||
                (firstRectangle.getTopLeft().getY() < secondRectangle.getTopLeft().getY() &&
                        secondRectangle.getBottomRight().getY() < firstRectangle.getTopLeft().getY()))
            intersect = true;
        return intersect;
    }

    private boolean checkAdjacent(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean adjacent = false;
        return adjacent;
    }

    private boolean checkContainment(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean contained = false;
        if(firstRectangle.getTopLeft().getX() < secondRectangle.getTopLeft().getX() &&
                firstRectangle.getTopLeft().getY() > secondRectangle.getTopLeft().getY() &&
                firstRectangle.getBottomRight().getX() > secondRectangle.getBottomRight().getX() &&
                firstRectangle.getBottomRight().getY() < secondRectangle.getBottomRight().getY())
            contained = true;
        if(secondRectangle.getTopLeft().getX() < firstRectangle.getTopLeft().getX() &&
        secondRectangle.getTopLeft().getY() > firstRectangle.getTopLeft().getY() &&
        secondRectangle.getBottomRight().getX() > firstRectangle.getBottomRight().getX() &&
        secondRectangle.getBottomRight().getY() < firstRectangle.getBottomRight().getY())
            contained = true;
        return contained;
    }
}
