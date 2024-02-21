package ex2.test;

import ex2.geo.Point_2D;
import ex2.geo.Rect_2D;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Point_2DTest {
    Point_2D p1=new Point_2D(3.0,0.5);

    @Test
    void x() {
        double ActualX=p1.x();
        assertEquals(ActualX,3.0,"p1 X should be 3.0");
    }

    @Test
    void y() {
        double ActualY=p1.y();
        assertEquals(ActualY,0.5,"p1 Y should be 0.5");
    }

    @Test
    void set_x() {
        Point_2D p1 = new Point_2D(4.0,1.5);
        p1.set_x(3.0);
        double changedX = p1.x();

        assertEquals(changedX,3.0,"p1 X should be 3.0");
    }

    @Test
    void set_y() {
        Point_2D p1 = new Point_2D(4.0,1.5);
        p1.set_y(0.5);
        double changedY = p1.y();

        assertEquals(changedY,0.5,"p1 Y should be 0.5");
    }

    @Test
    void ix() {
        Point_2D p1=new Point_2D(3.77,0.3);
        int ActualX = p1.ix();
        assertEquals(ActualX,3,"p1 X in int should be 3");
    }

    @Test
    void iy() {
        Point_2D p1=new Point_2D(3.77,0.3);
        int ActualY = p1.iy();
        assertEquals(ActualY,0,"p1 Y in int should be 0");
    }

    @Test
    void add() {
        Point_2D p1=new Point_2D(3.0,0.5);
        Point_2D p2=new Point_2D(2.3,1.5);
        Point_2D a = p1.add(p2);
        assertEquals(a.x(),5.3,"p1 X should be 5.3");
        assertEquals(a.y(),2.0,"p1 Y should be 2.0");
    }

    @Test
    void testToString() {
        assertEquals(p1.toString(),3.0+","+0.5);
    }

    @Test
    void distance() {
        Point_2D p1=new Point_2D(3.0,4.0);
        double originDistance= p1.distance();
        assertEquals(originDistance,5.0,"the distance from origin should be 5.0");
    }

    @Test
    void testDistance() {
        Point_2D p1=new Point_2D(3.0,4.0);
        Point_2D p2=new Point_2D(6.0,8.0);
        double distance= p1.distance(p2);
        assertEquals(distance,5.0,"the distance should be ");
    }

    @Test
    void testEquals() {
        Point_2D p2=new Point_2D(6.0,8.0);
        assertFalse(p1.equals(p2),"p1 should not be equal to p2");

        assertFalse(p1.equals(null),"p2 is null");

        Rect_2D r1=new Rect_2D(p1,p2);
        assertFalse(p1.equals(r1),"p2 is not a point");

        Point_2D p3=new Point_2D(3.0,0.5);
        assertTrue(p1.equals(p3),"p1 should be equal to p2");
    }

    @Test
    void close2equals() {
        Point_2D p2=new Point_2D(6.0,8.0);
        assertFalse(p1.close2equals(p2,0.1),"p1 should not be close to equal to p2");

        Point_2D p3=new Point_2D(3.01,0.501);
        assertTrue(p1.close2equals(p3,0.1),"p1 should be close to equal to p2");
    }

    @Test
    void vector() {
        Point_2D p1 = new Point_2D(1.0,2.0);
        Point_2D p2 = new Point_2D(7.0,3.0);
        Point_2D expectedVector = new Point_2D(6.0,1.0);
        assertEquals(p1.vector(p2),expectedVector,"the vector should be (6.0,1.0)");
    }

    @Test
    void move() {
        Point_2D p1 = new Point_2D(1.0,2.0);
        Point_2D p2 = new Point_2D(7.0,3.0);
        Point_2D vector = new Point_2D(6.0,1.0);

        p1.move(vector);
        assertEquals(p1.x(),p2.x(),"p1 X should be equal to p2 X");
        assertEquals(p1.y(),p2.y(),"p1 Y should be equal to p2 Y");
    }

    @Test
    void scale() {
        Point_2D p1 = new Point_2D(1.0,2.0);
        Point_2D center = new Point_2D(1.0,2.0);

        p1.scale(center,2);
        assertEquals(p1.x(),1.0,"X should stay the same");
        assertEquals(p1.y(),2.0,"Y should stay the same");

        Point_2D center2 = new Point_2D(0.0,1.0);
        p1.scale(center2,2);
        assertEquals(p1.x(),2.0,"X should be 2.0");
        assertEquals(p1.y(),3.0,"Y should be 3.0");
    }

    @Test
    void rotate() {
        double EPS=0.000000000000001;
        Point_2D p1 = new Point_2D(3.0,6.0);
        Point_2D center = new Point_2D(1.5,3.0);

        p1.rotate(p1,90);
        assertEquals(p1.x(),3.0,"X should be the same");
        assertEquals(p1.y(),6.0,"Y should be the same");

        p1.rotate(center,90);
        assertEquals(p1.x(),-1.5,"X should be -1.5");
        assertEquals(p1.y(),4.5+EPS,"Y should be 4.5");
    }
}