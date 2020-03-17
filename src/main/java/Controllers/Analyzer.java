package Controllers;

import models.Rectangle;

/**
 * This class consists of all the methods to analyze the relationships betweeen given rectangles.
 */
public class Analyzer {
    //stores the type of adjacency such as Proper, Partial or Sub-line.
    public static String adjacencyType = "";

    /**
     * This method is invoked from Execute class, which further calls specific methods to check containment,
     * intersection or adjacency. Before checking above properties, it checks a special case of only checking corners.
     * @param firstRectangle object of first rectangle.
     * @param secondRectangle object of second rectangle.
     * @return boolean array informing what relationships exists.
     */
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

    /**
     * This is an special case where only one corner of one rectangle touches one corner of another rectangle.
     * Considering this case to not fall in any one of to-check properties, it has been handled separately.
     * If this condition of only one corner touching another corner is met, it means no other properties can be
     * possible and therefore should not be checked.
     * @param firstRectangle object of first rectangle.
     * @param secondRectangle object of second rectangle.
     * @return boolean value if the above condition is satisfied or not. When it returns true, other properties
     * are possible and should be checked.
     */
    public boolean checkCorners(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean isPossible = true;
        //following are the 4 conditions checking 4 possible combinations where corners can touch each other.
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

    /**
     * This method checks if either of two given rectangles contains another one. It starts by comparing
     * if the topLeft and bottomRight of one rectangle are inside another rectangle.
     * @param firstRectangle object of first Rectangle.
     * @param secondRectangle object of second Rectangle.
     * @return returns true if either one contains another, else false.
     */
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

    /**
     * This method checks if the given two rectangles intersect. It does so by comparing coordinates of both rectangles:
     * a) if rightmost point of one rectangle lies on left side of another.
     * b) if leftmost point of one rectangle lies on right side of another.
     * c) if bottommost point of one rectangle lies on above another.
     * d) if topmost point of one rectangle lies below another.
     * With if any of these checks is true, it means rectangles do not intersect.
     * @param firstRectangle object of first rectangle.
     * @param secondRectangle object of second rectangle.
     * @return true if the rectangles intersect, else false.
     */
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

    /**
     * This method checks if the given two rectangles are adjacent. It further checks if the they are proper, partial
     * or sub-line adjacency.
     * It starts by checking if rectangles have same x-coordinate/y-coordinate. If this is true,
     * it it verified if the rectangle lies in the range of another rectangle's length. After that,
     * it is established that rectangles are adjacent, as they share a common co-ordinate and are
     * in range of each other. Further, it is checked if they have same height and start from same position,
     * if so, they are properly adjacent, if they are same height only they are partially adjacent, else they are
     * sub-line. These types are updated in static variable adjacencyType.
     * Moreover, cases such as containment, where the edge is adjacent internally is handled too.
     * For the assignment, it is assumed that partially adjacent rectangles have same height.
     * @param firstRectangle object of first Rectangle.
     * @param secondRectangle object of second rectangle.
     * @return true if they are adjacent, else false.
     */
    public boolean checkAdjacent(Rectangle firstRectangle, Rectangle secondRectangle) {
        boolean adjacent = false;
        boolean compareHeight = false;
        char axis = 'y';
        //checks if they share a x co-ordinate, externally and internally.
        if(firstRectangle.getBottomRight().getX() == secondRectangle.getTopLeft().getX() ||
            firstRectangle.getTopLeft().getX() == secondRectangle.getBottomRight().getX() ||
                firstRectangle.getTopLeft().getX() == secondRectangle.getTopLeft().getX() ||
                firstRectangle.getBottomRight().getX() == secondRectangle.getBottomRight().getX())
        {
            //verifies if the other rectangle in range of y.
            if(verifyRange(firstRectangle, secondRectangle, axis ))
            {
                adjacent = true;
                compareHeight = true;
            }
        }
        //checks if they share a y co-ordinate, externally and internally.
        else if (firstRectangle.getBottomRight().getY() == secondRectangle.getTopLeft().getY() ||
                secondRectangle.getBottomRight().getY() == firstRectangle.getTopLeft().getY() ||
                firstRectangle.getTopLeft().getY() == secondRectangle.getTopLeft().getY() ||
                firstRectangle.getBottomRight().getY() ==  secondRectangle.getBottomRight().getY())
        {
            axis = 'x';
            //verifies if other rectangle in range of x.
            if(verifyRange(firstRectangle, secondRectangle, axis))
            {
                adjacent = true;
                compareHeight = false;
            }
        }
        if(adjacent)
            //now since they are adjacent, checks if they have same height/width and start from same position.
            if(equalDimension(firstRectangle, secondRectangle, compareHeight))
                //checks if they start from same position.
                if(sameStartPosition(firstRectangle, secondRectangle, compareHeight))
                {
                    //equal dimension and same start position
                    adjacencyType ="Proper";
                    adjacent = true;
                }
                else
                {
                    //equal dimension only.
                    adjacencyType="Partial";
                    adjacent = true;
                }
            else
            {
                // neither equal dimension nor same start position, hence sub-line.
                adjacencyType="Sub-line";
                adjacent = true;
            }
        return adjacent;
    }

    /**
     * This method is called from checkAdjacent method if either rectangle share a same co-ordinate. It checks
     * if they share a x-coordinate, it is checked if rectangle lay in range of y of another.
     * if they share a y-coordinate, it is checked if rectangle lay in range of x of another.
     * @param firstRectangle object of first rectangle.
     * @param secondRectangle object of second rectangle.
     * @param axis axis for range is to be checked.
     * @return
     */
    public boolean verifyRange(Rectangle firstRectangle, Rectangle secondRectangle, char axis) {
        boolean inRange = false;
        if(axis == 'y'){
            if((firstRectangle.getTopLeft().getY() >= secondRectangle.getBottomRight().getY() &&
                    secondRectangle.getBottomRight().getY() >= firstRectangle.getBottomRight().getY()) ||
                    firstRectangle.getTopLeft().getY() >= secondRectangle.getTopLeft().getY() &&
                            secondRectangle.getTopLeft().getY() >= firstRectangle.getBottomRight().getY())
                inRange = true;
        }
        else {
            if((firstRectangle.getTopLeft().getX() <= secondRectangle.getBottomRight().getX() &&
                    secondRectangle.getBottomRight().getX() <= firstRectangle.getBottomRight().getX()) ||
                    (firstRectangle.getBottomRight().getX() <= secondRectangle.getTopLeft().getX() &&
                            secondRectangle.getTopLeft().getX() <= firstRectangle.getBottomRight().getX()))
                inRange = true;
        }
        return inRange;
    }

    /**
     * This method checks if the rectangles have same starting point.
     * @param firstRectangle object of first rectangle.
     * @param secondRectangle object of second rectangle.
     * @param compareHeight should check on Y or X.
     * @return true, if they start from same point, else false.
     */
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

    /**
     * The method checks if the the rectangles have same height/width.
     * @param firstRectangle object of first rectangle.
     * @param secondRectangle object of second rectangle.
     * @param compareHeight should check height or width.
     * @return true, if they have same dimension length , else false.
     */
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
}
