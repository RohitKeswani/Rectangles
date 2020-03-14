package Controllers;

import models.Rectangle;

public class Analyzer {
    public boolean[] analyze(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean[] status = new boolean[3];
        boolean contained = checkContainment(firstRectangle, secondRectangle);
        boolean adjacent = checkAdjacent(firstRectangle, secondRectangle);
        boolean intersect = checkIntersection(firstRectangle, secondRectangle);
        status[0] = contained;
        status[1] = adjacent;
        status[2] = intersect;
        return status;
    }

    private boolean checkIntersection(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean contained = false;
        return contained;
    }

    private boolean checkAdjacent(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean adjacent = false;
        return adjacent;
    }

    private boolean checkContainment(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean intersect = false;
        return intersect;
    }
}
