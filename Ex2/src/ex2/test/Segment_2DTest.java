package ex2.test;

import ex2.geo.Circle_2D;
import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import ex2.geo.Segment_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Segment_2DTest {

    @Test
    void get_p1() {
        Point_2D p1 = new Point_2D(1,6);
        Point_2D p2 = new Point_2D(2,2);
        Segment_2D s1 = new Segment_2D(p1,p2);

        assertEquals(s1.get_p1().x(),1,"p1 X should be 1.0");
        assertEquals(s1.get_p1().y(),6,"p1 Y should be 6.0");
    }

    @Test
    void get_p2() {
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(2,4);
        Segment_2D s1 = new Segment_2D(p1,p2);
        assertEquals(s1.get_p2().x(),2,"p2 X should be 2.0");
        assertEquals(s1.get_p2().y(),4,"p2 Y should be 4.0");
    }

    @Test
    void contains() {
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(3,3);
        Segment_2D s1 = new Segment_2D(p1,p2);

        Point_2D onSegemnet = new Point_2D(2,2);
        assertTrue(s1.contains(onSegemnet),"The Point should be on the segment");

        Point_2D notOnSegment = new Point_2D(7,6);
        assertFalse(s1.contains(notOnSegment),"The Point should not be on the segment");
    }

    @Test
    void area() {
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(3,3);
        Segment_2D s1 = new Segment_2D(p1,p2);

        double s1Area = s1.area();
        assertEquals(s1Area,0,"area should be 0");
    }

    @Test
    void perimeter() {
        Point_2D p1 = new Point_2D(1,1);
        Point_2D p2 = new Point_2D(1,3);
        Segment_2D s1 = new Segment_2D(p1,p2);

        double s1Perimeter = s1.perimeter();
        double expectedPerimeter=4.0;        // (3-1)*2 = 4.0
        assertEquals(s1Perimeter,expectedPerimeter,"perimeter should be 4.0");
    }

    @Test
    void translate() {
        Point_2D p1 = new Point_2D(1,3);
        Point_2D p2 = new Point_2D(1,1);
        Segment_2D s1 = new Segment_2D(p1,p2);
        Point_2D vec = new Point_2D(2,2);

        s1.translate(vec);
        assertEquals(s1.get_p1().x(),3.0,"p1 X should be 3.0");
        assertEquals(s1.get_p1().y(),5.0,"p1 Y should be 4.0");
        assertEquals(s1.get_p2().x(),3.0,"p2 X should be 3.0");
        assertEquals(s1.get_p2().y(),3.0,"p2 Y should be 2.0");

        Point_2D vec2 = new Point_2D(-2,-2);
        s1.translate(vec2);
        assertEquals(s1.get_p1().x(),1.0,"p1 X should be 1.0");
        assertEquals(s1.get_p1().y(),3.0,"p1 Y should be 3.0");
        assertEquals(s1.get_p2().x(),1.0,"p2 X should be 1.0");
        assertEquals(s1.get_p2().y(),1.0,"p2 Y should be 1.0");
    }

    @Test
    void copy() {
        Point_2D p1 = new Point_2D(1,3);
        Point_2D p2 = new Point_2D(1,1);
        Segment_2D s1 = new Segment_2D(p1,p2);

        Segment_2D s1Copy=(Segment_2D) s1.copy();
        assertTrue(s1.equals(s1Copy),"the segments should be equal");
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(1,3);
        Point_2D p2 = new Point_2D(1,1);
        Segment_2D s1 = new Segment_2D(p1,p2);
        Segment_2D originS1 =  (Segment_2D)s1.copy();
        Point_2D center=new Point_2D(1,2);

        s1.scale(center,2);         // Scaled the segment by 2;
        assertEquals(s1.get_p1().x(),1.0,"p1 X should be 1.0");
        assertEquals(s1.get_p1().y(),4.0,"p1 Y should be 4.0");
        assertEquals(s1.get_p2().x(),1.0,"p2 X should be 1.0");
        assertEquals(s1.get_p2().y(),0.0,"p2 Y should be 0.0");

        s1.scale(p1,-1);       // Scaled the segment by -1 to return to the original size
        s1.scale(p1,-1);
        assertTrue(s1.equals(originS1),"the size should go back to the original size of s1");

        s1.scale(p1,1);        // Scaled the segment by 1, the segment need to stay in the same size
        assertTrue(s1.equals(originS1),"the size shouldn't change");
    }

    @Test
    void rotate() {
        Point_2D p1 = new Point_2D(1,3);
        Point_2D p2 = new Point_2D(1,1);
        Segment_2D s1 = new Segment_2D(p1,p2);
        Point_2D center=new Point_2D(1,2);

        s1.rotate(center,90);
        assertEquals(s1.get_p1().x(),0.0,"p1 X should be 0.0");
        assertEquals(s1.get_p1().y(),2.0,"p1 Y should be 2.0");
        assertEquals(s1.get_p2().x(),2.0,"p2 X should be 2.0");
        assertEquals(s1.get_p2().y(),2.0,"p2 Y should be 2.0");
    }

    @Test
    void testToString() {
        Point_2D p1 = new Point_2D(1,3);
        Point_2D p2 = new Point_2D(0,5);
        Segment_2D s1 = new Segment_2D(p1,p2);

        String s1String = s1.toString();
        assertEquals(s1String,1.0+","+3.0+","+0.0+","+5.0,"the String should be (1.0,3.0,0.0,5.0)");
    }

    @Test
    void testEqual(){
        Point_2D p1 = new Point_2D(1,3);
        Point_2D p2 = new Point_2D(0,5);
        Segment_2D s1 = new Segment_2D(p1,p2);
        Segment_2D s2 = new Segment_2D(p1,p2);
        Segment_2D s3 = new Segment_2D(p2,p1);

        assertTrue(s1.equals(s2),"s1 should be equal to s2");
        assertTrue(s1.equals(s3),"s1 should be equal to s3");

        assertFalse(s3.equals(null),"the object is null");

        Rect_2D r1=new Rect_2D(p1,p2);
        assertFalse(s1.equals(r1),"r1 is not a segment");
    }
}