package Controllers;

import models.Rectangle;

public class Analyzer {
    public static String adjacencyType = "";

    public boolean[] analyze(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean[] status = new boolean[3];
        boolean contained = false, adjacent=false, intersect = false;
        boolean isPossible = checkCorners(firstRectangle, secondRectangle);
        if(isPossible){
            contained = checkContainment(firstRectangle, secondRectangle);
            adjacent = checkAdjacent(firstRectangle, secondRectangle);
            if(!contained && !adjacent) {
                intersect = checkIntersection(firstRectangle, secondRectangle);
            }
        }
        status[0] = contained;
        status[1] = adjacent;
        status[2] = intersect;
        return status;
    }

    //TODO returns false if corners shared, else true
    public boolean checkCorners(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean isPossible = true;
        if(firstRectangle.getTopLeft().getX() == secondRectangle.getBottomRight().getX() &&
        firstRectangle.getTopLeft().getY() == secondRectangle.getBottomRight().getY())
            isPossible = false;
        if(firstRectangle.getBottomRight().getX() == secondRectangle.getTopLeft().getX() &&
        firstRectangle.getBottomRight().getY() == secondRectangle.getTopLeft().getY())
            isPossible = false;
        if(firstRectangle.getTopLeft().getX() == secondRectangle.getBottomRight().getX() &&
        firstRectangle.getBottomRight().getY() == secondRectangle.getTopLeft().getY())
            isPossible = false;
        if(firstRectangle.getBottomRight().getX() == secondRectangle.getTopLeft().getX() &&
        firstRectangle.getTopLeft().getY() == secondRectangle.getBottomRight().getY())
            isPossible = false;
        return isPossible;
    }

    public boolean checkIntersection(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean intersect = true;
        if(firstRectangle.getTopLeft().getX() >= secondRectangle.getBottomRight().getX() ||
        secondRectangle.getTopLeft().getX() >= firstRectangle.getBottomRight().getX())
            intersect = false;
        if(firstRectangle.getTopLeft().getY() <= secondRectangle.getBottomRight().getY() ||
        secondRectangle.getTopLeft().getY() <= firstRectangle.getBottomRight().getY())
            intersect = false;
        return intersect;
    }

    public boolean checkAdjacent(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean adjacent = false;
        boolean compareHeight = false;
        if(firstRectangle.getBottomRight().getX() == secondRectangle.getTopLeft().getX() ||
            firstRectangle.getTopLeft().getX() == secondRectangle.getBottomRight().getX() ||
                firstRectangle.getTopLeft().getX() == secondRectangle.getTopLeft().getX() ||
                firstRectangle.getBottomRight().getX() == secondRectangle.getBottomRight().getX())
        {
            if(verifyY(firstRectangle, secondRectangle))
            {
                adjacent = true;
                compareHeight = true;
            }
        }
        else if (firstRectangle.getBottomRight().getY() == secondRectangle.getTopLeft().getY() ||
                secondRectangle.getBottomRight().getY() == firstRectangle.getTopLeft().getY() ||
                firstRectangle.getTopLeft().getY() == secondRectangle.getTopLeft().getY() ||
                firstRectangle.getBottomRight().getY() ==  secondRectangle.getBottomRight().getY())
        {
            if(verifyX(firstRectangle, secondRectangle))
            {
                adjacent = true;
                compareHeight = false;
            }
        }
        if(adjacent)
            if(equalDimension(firstRectangle, secondRectangle, compareHeight))
                //check for proper or partial
                if(sameStartPosition(firstRectangle, secondRectangle, compareHeight))
                {
                    adjacencyType ="Proper";
                    adjacent = true;
                }
                else
                {
                    adjacencyType="Partial";
                    adjacent = true;
                }
            else
            {
                adjacencyType="Sub-line";
                adjacent = true;
            }
        return adjacent;
    }

    public boolean verifyX(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean inRange = false;
        if((firstRectangle.getTopLeft().getX() <= secondRectangle.getBottomRight().getX() &&
        secondRectangle.getBottomRight().getX() <= firstRectangle.getBottomRight().getX()) ||
        (firstRectangle.getBottomRight().getX() <= secondRectangle.getTopLeft().getX() &&
        secondRectangle.getTopLeft().getX() <= firstRectangle.getBottomRight().getX()))
            inRange = true;
        return inRange;
    }

    public boolean verifyY(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean inRange = false;
        if((firstRectangle.getTopLeft().getY() >= secondRectangle.getBottomRight().getY() &&
                secondRectangle.getBottomRight().getY() >= firstRectangle.getBottomRight().getY()) ||
        firstRectangle.getTopLeft().getY() >= secondRectangle.getTopLeft().getY() && secondRectangle.getTopLeft().getY()
                >= firstRectangle.getBottomRight().getY())
                inRange = true;
        return inRange;
    }

    public boolean sameStartPosition(Rectangle firstRectangle, Rectangle secondRectangle, boolean compareHeight) {
        boolean sameStartPosition = false;
        if(compareHeight)
            if(firstRectangle.getBottomRight().getY() == secondRectangle.getBottomRight().getY())
            {
                sameStartPosition = true;
            }
        if(!compareHeight)
            if(firstRectangle.getBottomRight().getX() == secondRectangle.getBottomRight().getX())
            {
                sameStartPosition = true;
            }
        return sameStartPosition;
    }

    public boolean equalDimension(Rectangle firstRectangle, Rectangle secondRectangle, boolean compareHeight) {
        boolean equalDimension = false;
        if(compareHeight)
            if(firstRectangle.getTopLeft().getY()-firstRectangle.getBottomRight().getY() ==
            secondRectangle.getTopLeft().getY()-secondRectangle.getBottomRight().getY())
            {
                equalDimension = true;
            }
        if(!compareHeight)
            if(firstRectangle.getBottomRight().getX()-firstRectangle.getTopLeft().getX() ==
            secondRectangle.getBottomRight().getX() - secondRectangle.getTopLeft().getX())
            {
                equalDimension = true;
            }
        return equalDimension;
    }

    public boolean checkContainment(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean contained = false;
        if(firstRectangle.getTopLeft().getX() <= secondRectangle.getTopLeft().getX() &&
                firstRectangle.getTopLeft().getY() >= secondRectangle.getTopLeft().getY() &&
                firstRectangle.getBottomRight().getX() >= secondRectangle.getBottomRight().getX() &&
                firstRectangle.getBottomRight().getY() <= secondRectangle.getBottomRight().getY())
            contained = true;
        if(secondRectangle.getTopLeft().getX() <= firstRectangle.getTopLeft().getX() &&
        secondRectangle.getTopLeft().getY() >= firstRectangle.getTopLeft().getY() &&
        secondRectangle.getBottomRight().getX() >= firstRectangle.getBottomRight().getX() &&
        secondRectangle.getBottomRight().getY() <= firstRectangle.getBottomRight().getY())
            contained = true;
        return contained;
    }
}
