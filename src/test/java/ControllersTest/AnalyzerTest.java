import Controllers.Analyzer;

import models.Coordinate;
import models.Rectangle;
import org.junit.*;

public class AnalyzerTest {
    Analyzer analyzer;

    public Rectangle initializeRectangle(int x1, int y1, int x2, int y2) {
        return new Rectangle(new Coordinate(x1, y1), new Coordinate(x2, y2));
    }

    @Before
    public void setUp()
    {
        analyzer = new Analyzer();
    }
    @After
    public void tearDown()
    {
        analyzer = null;
    }

    @Test
    public void testForContainment() {
        Rectangle firstRectangle = initializeRectangle(30,30,40,20);
        Rectangle secondRectangle = initializeRectangle(20,40,50,10);
        Assert.assertTrue(analyzer.checkContainment(firstRectangle, secondRectangle));
    }

    @Test
    public void testForContainmentRectangleReversed() {
        Rectangle firstRectangle = initializeRectangle(20,40,50,10);
        Rectangle secondRectangle = initializeRectangle(30,30,40,20);
        Assert.assertTrue(analyzer.checkContainment(firstRectangle, secondRectangle));
    }

    @Test
    public void testForNotContainment() {
        Rectangle firstRectangle = initializeRectangle(30,30,40,20);
        Rectangle secondRectangle = initializeRectangle(70,20,80,10);
        Assert.assertFalse(analyzer.checkContainment(firstRectangle, secondRectangle));
    }

    @Test
    public void testForIntersectionLeftRight(){
        Rectangle firstRectangle = initializeRectangle(20,20,40,10);
        Rectangle secondRectangle = initializeRectangle(30,40,60,0);
        Assert.assertTrue(analyzer.checkIntersection(firstRectangle, secondRectangle));
    }

    @Test
    public void testForIntersectionRightLeft(){
        Rectangle firstRectangle = initializeRectangle(30,40,60,0);
        Rectangle secondRectangle = initializeRectangle(20,20,40,10);
        Assert.assertTrue(analyzer.checkIntersection(firstRectangle, secondRectangle));
    }

    @Test
    public void testForIntersectionTopBottom(){
        Rectangle firstRectangle = initializeRectangle(30,70,60,50);
        Rectangle secondRectangle = initializeRectangle(40,60,50,30);
        Assert.assertTrue(analyzer.checkIntersection(firstRectangle, secondRectangle));
    }

    @Test
    public void testForIntersectionBottomTop(){
        Rectangle firstRectangle = initializeRectangle(40,60,50,30);
        Rectangle secondRectangle = initializeRectangle(30,70,60,50);
        Assert.assertTrue(analyzer.checkIntersection(firstRectangle, secondRectangle));
    }

    @Test
    public void testForNotIntersection(){
        Rectangle firstRectangle = initializeRectangle(40,60,30,30);
        Rectangle secondRectangle = initializeRectangle(85,40,95,20);
        Assert.assertFalse(analyzer.checkIntersection(firstRectangle, secondRectangle));
    }

    @Test
    public void testForAdjacentProperAlongXAxis(){
        Rectangle firstRectangle = initializeRectangle(10,20,30,10);
        Rectangle secondRectangle = initializeRectangle(30,20,50,10);
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
        Assert.assertTrue(analyzer.equalDimension(firstRectangle, secondRectangle, true));
        Assert.assertTrue(analyzer.sameStartPosition(firstRectangle, secondRectangle, true));
    }

    @Test
    public void testForAdjacentPartialAlongXAxis(){
        Rectangle firstRectangle = initializeRectangle(10,20,30,10);
        Rectangle secondRectangle = initializeRectangle(30,25,50,15);
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
        Assert.assertTrue(analyzer.equalDimension(firstRectangle, secondRectangle, true));
        Assert.assertFalse(analyzer.sameStartPosition(firstRectangle, secondRectangle, true));
    }

    @Test
    public void testForAdjacentSublineAlongXAxis(){
        Rectangle firstRectangle = initializeRectangle(10,20,30,10);
        Rectangle secondRectangle = initializeRectangle(30,18,50,12);
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
        Assert.assertFalse(analyzer.equalDimension(firstRectangle, secondRectangle, true));
        Assert.assertFalse(analyzer.sameStartPosition(firstRectangle, secondRectangle, true));
    }

    @Test
    public void testForAdjacentProperAlongYAxis(){
        Rectangle firstRectangle = initializeRectangle(10,20,30,10);
        Rectangle secondRectangle = initializeRectangle(10,40,30,20);
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
        Assert.assertTrue(analyzer.equalDimension(firstRectangle, secondRectangle, false));
        Assert.assertTrue(analyzer.sameStartPosition(firstRectangle, secondRectangle, false));
    }

    @Test
    public void testForAdjacentPartialAlongYAxis(){
        Rectangle firstRectangle = initializeRectangle(10,20,30,10);
        Rectangle secondRectangle = initializeRectangle(5,40,25,20);
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
        Assert.assertTrue(analyzer.equalDimension(firstRectangle, secondRectangle, false));
        Assert.assertFalse(analyzer.sameStartPosition(firstRectangle, secondRectangle, false));
    }

    @Test
    public void testForAdjacentSublineAlongYAxis(){
        Rectangle firstRectangle = initializeRectangle(10,20,30,10);
        Rectangle secondRectangle = initializeRectangle(15,40,25,20);
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
        Assert.assertFalse(analyzer.equalDimension(firstRectangle, secondRectangle, false));
        Assert.assertFalse(analyzer.sameStartPosition(firstRectangle, secondRectangle, false));
    }

    @Test
    public void testForNotAdjacentAlongXAxis(){
        Rectangle firstRectangle = initializeRectangle(30,60,60,30);
        Rectangle secondRectangle = initializeRectangle(60,80,80,70);
        Assert.assertFalse(analyzer.checkAdjacent(firstRectangle, secondRectangle));
    }

    @Test
    public void testForNotAdjacentAlongYAxis(){
        Rectangle firstRectangle = initializeRectangle(30,60,60,30);
        Rectangle secondRectangle = initializeRectangle(10,70,20,60);
        Assert.assertFalse(analyzer.checkAdjacent(firstRectangle, secondRectangle));
    }

    @Test
    public void testForContainmentAndAdjacent(){
        Rectangle firstRectangle = initializeRectangle(20,60,50,40);
        Rectangle secondRectangle = initializeRectangle(250,60,50,50);
        Assert.assertTrue(analyzer.checkContainment(firstRectangle,secondRectangle));
        Assert.assertTrue(analyzer.checkAdjacent(firstRectangle, secondRectangle));
    }
    @Test
    public void checkCorners() {
        Rectangle firstRectangle = initializeRectangle(10,80,30,60);
        Rectangle secondRectangle = initializeRectangle(30,60,60,30);
        Assert.assertFalse(analyzer.checkCorners(firstRectangle,secondRectangle));
    }
}