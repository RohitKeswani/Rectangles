import Controllers.Analyzer;
import models.Coordinate;
import models.Rectangle;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/***
 * This class consists of main method, which read the txt file in /src/main/resources line by line and gets the
 * coordinates to create Rectangle objects, which are then passed on to Analyzer Class methods to analyze relations
 * amongst them.
 */
public class Execute {
    public static void main(String[] args) {
        String pathToFile =  args[0]; //"src/main/resources/RectangleDimensions";
        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader
                    (pathToFile));
            String line = "";
            int count=1;
            while((line = bufferedReader.readLine()) !=null) {
                String[] coordinates = line.split(",");
                //if any line of resources file, does not have exact 8 dimensions.
                if(coordinates.length != 8){
                    System.err.println("Given "+coordinates.length+" dimensions. Expected 8 dimensions; " +
                            "Check dimensions file");
                    break;
                }
                Rectangle firstRectangle = null, secondRectangle=null;
                boolean first = true;
                //reads 4 coordinates and creates one rectangle in each run.
                for(int i=0;i<coordinates.length;i+=4) {
                    Coordinate topLeft = new Coordinate(Integer.parseInt(coordinates[i]) ,
                            Integer.parseInt(coordinates[i+1]));
                    Coordinate bottomRight = new Coordinate(Integer.parseInt(coordinates[i+2]) ,
                            Integer.parseInt(coordinates[i+3]));
                    if(first)
                        firstRectangle = new Rectangle(topLeft, bottomRight);
                    else
                        secondRectangle = new Rectangle(topLeft, bottomRight);
                    first = false;
                }
                Analyzer analyzer = new Analyzer();
                //analyzer.analyze method accepts two created rectangles and returns types of relations between them.
                boolean[] status = analyzer.analyze(firstRectangle, secondRectangle);
                //prints the well-formatted output.
                printOutput(count, status, firstRectangle, secondRectangle);
                count++;
            }
        } catch (FileNotFoundException e) {
            System.err.println("Cannot find file at given path");
            e.printStackTrace();
        } catch (IOException e) {
            System.err.println("Exception occurred while reading the file");
            e.printStackTrace();
        } catch (NullPointerException e) {
            System.err.println("Rectangles objects did not get the coordinates assigned");
            e.printStackTrace();
        }
    }

    /**
     * This method prints the output in pretty and readable format.
     * @param count line number from resources file to keep a track.
     * @param status the boolean array returned by analyze method of Analyzer Class.
     * @param firstRectangle object of first rectangle
     * @param secondRectangle object of second rectangle.
     */
    private static void printOutput(int count, boolean[] status,
                                    Rectangle firstRectangle, Rectangle secondRectangle) {
        System.out.println("Count: "+count);
        System.out.println("First Rectangle Coordinates: ("+firstRectangle.getTopLeft().getX()+","+
                firstRectangle.getTopLeft().getY()+"),("+firstRectangle.getBottomRight().getX()+","
                +firstRectangle.getBottomRight().getY()+")");
        System.out.println("Second Rectangle Coordinates: ("+secondRectangle.getTopLeft().getX()+","+
                secondRectangle.getTopLeft().getY()+"),("+secondRectangle.getBottomRight().getX()+","
                +secondRectangle.getBottomRight().getY()+")");
        System.out.println("-------------------RESULTS-------------------");
        //boolean array at index[0] informs if containment is possible, index[1] tells if adjacency is possible and
        //index[2] informs if intersection is possible.
        if(status[0])
            System.out.println("Containment");
        if(status[1])
            System.out.println("Adjacent ("+Analyzer.adjacencyType+")");
        if(status[2])
            System.out.println("Intersection - No Containment");
        if(!status[0] && !status[1] && !status[2])
            System.out.println("No Intersection, No Containment, Not Adjacent");
        System.out.println("*********************************************");
        System.out.println();
    }
}
