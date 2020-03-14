import Controllers.Analyzer;
import models.Coordinate;
import models.Rectangle;

public class Execute {
    public static void main(String[] args) {
        Coordinate topLeft = new Coordinate(30,50);
        Coordinate bottomRight = new Coordinate(50, 30);
        Rectangle firstRectangle = new Rectangle(topLeft, bottomRight);
        topLeft = new Coordinate(60, 40);
        bottomRight = new Coordinate(80,20);
        Rectangle secondRectangle = new Rectangle(topLeft, bottomRight);
        Analyzer analyzer = new Analyzer();
        boolean[] status = analyzer.analyze(firstRectangle, secondRectangle);
        if(status[0])
            System.out.println("Contained");
        if(status[1])
            System.out.println("Adjacent");
        if(status[2])
            System.out.println("Intersect");
        else
            System.out.println("Nothing");
    }
}
