import Controllers.Analyzer;
import models.Coordinate;
import models.Rectangle;

public class Execute {
    public static void main(String[] args) {
        Coordinate topLeft = new Coordinate(70 ,70);
        Coordinate bottomRight = new Coordinate(80, 60);
        Rectangle firstRectangle = new Rectangle(topLeft, bottomRight);
        Coordinate left = new Coordinate(30, 60);
        Coordinate right = new Coordinate(60,30);
        Rectangle secondRectangle = new Rectangle(left, right);
        Analyzer analyzer = new Analyzer();
        boolean[] status = analyzer.analyze(firstRectangle, secondRectangle);
        if(status[0])
            System.out.println("Containment");
        if(status[1])
            System.out.println("Adjacent");
        if(status[2])
            System.out.println("Intersection - No Containment");
        if(!status[0] && !status[1] && !status[2])
            System.out.println("Nothing");
    }
}
