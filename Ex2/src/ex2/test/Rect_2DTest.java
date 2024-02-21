package ex2.test;

import ex2.ex2.Ex2_Const;
import ex2.geo.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Rect_2DTest {
    Point_2D p1 = new Point_2D(1,5);
    Point_2D p2 = new Point_2D(3,2);
    Rect_2D r1=new Rect_2D(p1,p2);

    @Test
    void get_p1() {
        assertEquals(r1.get_p1().x(),1.0,"p1 X should be 1.0");
        assertEquals(r1.get_p1().y(),5.0,"p1 Y should be 5.0");
    }

    @Test
    void get_p2() {
        assertEquals(r1.get_p2().x(),3.0,"p2 X should be 3.0");
        assertEquals(r1.get_p2().y(),2.0,"p2 Y should be 2.0");
    }

    @Test
    void get_p3() {
        assertEquals(r1.get_p3().x(),1.0,"p3 X should be 1.0");
        assertEquals(r1.get_p3().y(),2.0,"p3 Y should be 2.0");
    }

    @Test
    void get_p4() {
        assertEquals(r1.get_p4().x(),3.0,"p4 X should be 3.0");
        assertEquals(r1.get_p4().y(),5.0,"p4 Y should be 5.0");
    }

    @Test
    void contains() {
        Point_2D bottomLeft1 = new Point_2D(1,2);  // p1 is the bottom left corner
        Point_2D topRight1 = new Point_2D(5,7);    // p2 is the top right corner
        Rect_2D r1 = new Rect_2D(bottomLeft1,topRight1);

        Point_2D inRectangle = new Point_2D(2,3);
        assertTrue(r1.contains(inRectangle),"The Point should be in the rectangle");
        Point_2D onRectangle = new Point_2D(1,2);
        assertTrue(r1.contains(onRectangle),"The Point should be on the rectangle");
        Point_2D notInRectangle = new Point_2D(9,9);
        assertFalse(r1.contains(notInRectangle),"The Point should not be on the rectangle");


        Point_2D topLeft2 = new Point_2D(1,7);       // p1 is the top left corner
        Point_2D bottomRight2 = new Point_2D(5,2);   // p2 is the bottom right corner
        Rect_2D r2 = new Rect_2D(topLeft2,bottomRight2);

        assertTrue(r2.contains(inRectangle),"The Point should be in the rectangle");
        assertTrue(r2.contains(onRectangle),"The Point should be on the rectangle");


        Point_2D topRight3 = new Point_2D(5,7);     // p1 is the top right corner
        Point_2D bottomLeft3 = new Point_2D(1,2);   // p2 is the bottom left corner
        Rect_2D r3 = new Rect_2D(topRight3,bottomLeft3);

        assertTrue(r3.contains(inRectangle),"The Point should be in the rectangle");
        assertTrue(r3.contains(onRectangle),"The Point should be on the rectangle");


        Point_2D bottomRight4 = new Point_2D(5,2);  // p1 is the bottom right corner
        Point_2D topLeft4 = new Point_2D(1,7);      // p2 is the top left corner
        Rect_2D r4 = new Rect_2D(bottomRight4,topLeft4);

        assertTrue(r4.contains(inRectangle),"The Point should be in the rectangle");
        assertTrue(r4.contains(onRectangle),"The Point should be on the rectangle");
    }

    @Test
    void area() {
        double r1Area=r1.area();
        double expectedArea=6.0;    // 3*2 = 6.0
        assertEquals(r1Area,expectedArea,"area should be 6.0");
    }

    @Test
    void perimeter() {
        double r1Perimeter=r1.perimeter();
        double expectedArea=10.0;    // 3*2+2*2 = 10.0
        assertEquals(r1Perimeter,expectedArea,"perimeter should be 10.0");
    }

    @Test
    void translate() {
        Point_2D p1 = new Point_2D(1,2);
        Point_2D p2 = new Point_2D(5,7);
        Rect_2D r1 = new Rect_2D(p1,p2);
        Point_2D vec = new Point_2D(3,3);

        r1.translate(vec);
        assertEquals(r1.get_p1().x(),4.0,"p1 X should be 4.0");
        assertEquals(r1.get_p1().y(),5.0,"p1 Y should be 5.0");

        assertEquals(r1.get_p2().x(),8.0,"p2 X should be 8.0");
        assertEquals(r1.get_p2().y(),10.0,"p2 Y should be 10.0");

        assertEquals(r1.get_p3().x(),4.0,"p3 X should be 4.0");
        assertEquals(r1.get_p3().y(),10.0,"p3 Y should be 10.0");

        assertEquals(r1.get_p4().x(),8.0,"p4 X should be 8.0");
        assertEquals(r1.get_p4().y(),5.0,"p4 Y should be 5.0");

        Point_2D vec2 = new Point_2D(-3,-3);
        r1.translate(vec2);
        assertEquals(r1.get_p1().x(),1.0,"p1 X should be 4.0");
        assertEquals(r1.get_p1().y(),2.0,"p1 Y should be 5.0");

        assertEquals(r1.get_p2().x(),5.0,"p2 X should be 8.0");
        assertEquals(r1.get_p2().y(),7.0,"p2 Y should be 10.0");

        assertEquals(r1.get_p3().x(),1.0,"p3 X should be 4.0");
        assertEquals(r1.get_p3().y(),7.0,"p3 Y should be 10.0");

        assertEquals(r1.get_p4().x(),5.0,"p4 X should be 8.0");
        assertEquals(r1.get_p4().y(),2.0,"p4 Y should be 5.0");
    }

    @Test
    void copy() {
        Rect_2D r1Copy=(Rect_2D) r1.copy();
        assertTrue(r1.equals(r1Copy),"the rectangles should be equal");
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(3,3);
        Rect_2D r1 = new Rect_2D(p1,p2);
        Rect_2D originR1 = new Rect_2D(p1,p2);
        Point_2D center = new Point_2D(2,2);

        r1.scale(center,2);         // Scaled the rectangle by 2;
        assertEquals(r1.get_p1().x(),0.0,"p1 X should be 0.0");
        assertEquals(r1.get_p1().y(),0.0,"p1 Y should be 0.0");
        assertEquals(r1.get_p2().x(),4.0,"p2 X should be 4.0");
        assertEquals(r1.get_p2().y(),4.0,"p2 Y should be 4.0");

        r1.scale(p1,-1);       // Scaled the rectangle by -1 to return to the original size
        r1.scale(p1,-1);
        assertTrue(r1.equals(originR1),"the size should go back to the original size of r1");

        r1.scale(p1,1);        // Scaled the rectangle by 1, the rectangle need to stay in the same size
        assertTrue(r1.equals(originR1),"the size shouldn't change");
    }

    @Test
    void rotate() {
        Point_2D p1 = new Point_2D(1.0,2.0);
        Point_2D p2 = new Point_2D(7.0,3.0);
        Rect_2D r1 = new Rect_2D(p1,p2);
        Point_2D center = new Point_2D(3.0,3.0);
        r1.rotate(center,90);

        Point_2D expectedP1 = new Point_2D(4,1);
        assertTrue(r1.get_p1().close2equals(expectedP1,Ex2_Const.EPS),"p1 should be close to equal to expectedP1");

        Point_2D expectedP2 = new Point_2D(3,7);
        assertTrue(r1.get_p2().close2equals(expectedP2,Ex2_Const.EPS),"p2 should be close to equal to expectedP2");

        Point_2D expectedP3 = new Point_2D(3,1);
        assertTrue(r1.get_p3().close2equals(expectedP3,Ex2_Const.EPS),"p3 should be close to equal to expectedP3");

        Point_2D expectedP4 = new Point_2D(4,7);
        assertTrue(r1.get_p4().close2equals(expectedP4,Ex2_Const.EPS),"p4 should be close to equal to expectedP4");
    }

    @Test
    void testToString() {
        assertEquals(r1.toString(),1.0+","+5.0+","+3.0+","+2.0+","+1.0+","+2.0+","+3.0+","+5.0);
    }

    @Test
    void testEquals(){
       Rect_2D r1 = new Rect_2D(p1,p2);
       Rect_2D r2 = new Rect_2D(p1,p2);
       Rect_2D r3 = new Rect_2D(p2,p1);

        assertTrue(r1.equals(r2),"r1 should be equal to r2");
        assertTrue(r1.equals(r3),"r1 should be equal to r3");

        assertFalse(r3.equals(null),"the object is null");

        Circle_2D c1 = new Circle_2D(p1,4.0);
        assertFalse(r1.equals(c1),"c1 is not a rectangle");
    }
}