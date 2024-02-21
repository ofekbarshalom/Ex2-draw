package ex2.test;

import ex2.ex2.Ex2_Const;
import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.gui.GUIShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Circle_2DTest {

    @Test
    void getRadius() {
        Point_2D p1=new Point_2D(3,6);
        Circle_2D c1= new Circle_2D(p1, 5);

        double c1Radios=c1.getRadius();
        assertEquals(c1Radios,5,"Radios should be 5.0");
    }

    @Test
    void getCenter() {
        Point_2D p1=new Point_2D(3,6);
        Circle_2D c1= new Circle_2D(p1, 5.0);

        Point_2D c1Center=c1.getCenter();
        assertEquals(c1Center.x(),3,"The center X should be 3.0");
        assertEquals(c1Center.y(),6,"The centerY should be 6.0");
    }

    @Test
    void testToString() {
        Point_2D p1=new Point_2D(3,6);
        Circle_2D c1= new Circle_2D(p1, 5.0);

        String c1String = c1.toString();
        assertEquals(c1String,3.0+","+6.0+", "+5.0,"the String should be (3.0,6.0, 5.0)");
    }

    @Test
    void contains() {
        Point_2D p1=new Point_2D(3,6);
        Circle_2D c1= new Circle_2D(p1, 5.0);
        
        Point_2D inCircle = new Point_2D(4,6);                        // point in circle
        boolean checkContains = c1.contains(inCircle);                      // True
        assertTrue(checkContains,"Point should be in circle");

        Point_2D onCircle = new Point_2D(3,11);                       // point on circle perimeter
        checkContains = c1.contains(onCircle);                              // True
        assertTrue(checkContains,"Point should be in circle");

        Point_2D notInCircle = new Point_2D(12,12);                    // point not in the circle
        checkContains = c1.contains(notInCircle);                            // False
        assertFalse(checkContains,"Point should not be in circle");
    }

    @Test
    void area() {
        Point_2D p1 =new Point_2D(3,6);
        Circle_2D c1 = new Circle_2D(p1, 5.0);

        double c1Area = c1.area();
        double expectedArea = Math.PI*Math.pow(5.0,2);
        assertEquals(c1Area,expectedArea,"The area should be Equal");
    }

    @Test
    void perimeter() {
        Point_2D p1 =new Point_2D(3,6);
        Circle_2D c1 = new Circle_2D(p1, 5.0);

        double c1Perimeter = c1.perimeter();
        double expectedPerimeter = 2*Math.PI*5.0;
        assertEquals(c1Perimeter,expectedPerimeter,"The perimeter should be Equal");
    }

    @Test
    void translate() {
        Point_2D p1 = new Point_2D(3,6);
        Circle_2D c1 = new Circle_2D(p1, 5.0);
        Point_2D vec = new Point_2D(2,3);

        c1.translate(vec);       //Moved the circle by the vector
        double expectedX = 5;
        double expectedY = 9;
        assertEquals(c1.getCenter().x(),expectedX,"The center X should be 5.0");
        assertEquals(c1.getCenter().y(),expectedY,"The center X should be 5.0");
    }

    @Test
    void copy() {
        Point_2D p1 =new Point_2D(3,6);
        Circle_2D c1 = new Circle_2D(p1, 5.0);

        Circle_2D c1Copy = (Circle_2D) c1.copy();
        assertTrue(c1.equals(c1Copy),"the circles should be equal");
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(3,6);
        Circle_2D c1 = new Circle_2D(p1, 5.0);

        c1.scale(p1,2);         //Scaled the circle by 2;
        assertEquals(c1.getRadius(),10.0,"Radios should be 10.0");

        c1.scale(p1,-1);       //Scaled the circle by -1 to return to the original size
        c1.scale(p1,-1);
        assertEquals(c1.getRadius(),5.0,"Radios should return the the original size [5.0]");

        c1.scale(p1,1);        //Scaled the circle by 1, the circle need to stay in the same size
        assertEquals(c1.getRadius(),5.0,"Radios should stay the same [5.0]");
    }

    @Test
    void rotate() {
        Point_2D p1 = new Point_2D(1,1);
        Circle_2D c1 = new Circle_2D(p1, 5);

        Point_2D center = new Point_2D(2,2);
        c1.rotate(center,90);
        assertEquals(c1.getCenter().x(),3,"The center X should be 3.0");
        assertEquals(c1.getCenter().y(),1,"The center Y should be 1.0");
    }
}