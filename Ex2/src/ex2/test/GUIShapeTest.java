package ex2.test;

import ex2.ex2.ShapeCollection;
import ex2.geo.*;
import ex2.gui.GUIShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;

import static org.junit.jupiter.api.Assertions.*;

class GUIShapeTest {
    private Point_2D p1; private Point_2D p2; private Point_2D p3; private Point_2D p4; private Point_2D p5;
    private Polygon_2D v1; private GUIShape gs1;
    private Circle_2D c1; private GUIShape gs2;
    private Rect_2D r1; private GUIShape gs3;
    private Triangle_2D t1; private GUIShape gs4;
    private Segment_2D s1; private GUIShape gs5;

    @BeforeEach
    void setUp(){
        p1 = new Point_2D(1, 2);
        p2 = new Point_2D(3, 5);
        p3 = new Point_2D(9, 1);
        p4 = new Point_2D(4, 1);
        p5 = new Point_2D(1, 0);

        v1 = new Polygon_2D();
        v1.add(p1); v1.add(p2); v1.add(p3); v1.add(p4); v1.add(p5);
        gs1 = new GUIShape(v1, true, Color.blue, 1);

        c1 = new Circle_2D(p1,3);
        gs2 = new GUIShape(c1, false, Color.black, 2);

        r1=new Rect_2D(p1,p2);
        gs3 = new GUIShape(r1, true, Color.white, 3);

        t1=new Triangle_2D(p1,p2,p3);
        gs4 = new GUIShape(t1, false, Color.yellow, 4);

        s1=new Segment_2D(p1,p2);
        gs5 = new GUIShape(s1, true, Color.green, 5);
    }

    @Test
    void getShape() {
        GUIShape s1 = new GUIShape(gs1.getShape(),true,Color.BLUE,6);  // getShape test for polygon
        assertEquals(s1.getClass(),gs1.getClass());

        GUIShape s2 = new GUIShape(gs2.getShape(),true,Color.BLUE,6);  // getShape test for circle
        assertEquals(s2.getClass(),gs2.getClass());

        GUIShape s3 = new GUIShape(gs3.getShape(),true,Color.BLUE,6);  // getShape test for rectangle
        assertEquals(s3.getClass(),gs3.getClass());

        GUIShape s4 = new GUIShape(gs4.getShape(),true,Color.BLUE,6);  // getShape test for triangle
        assertEquals(s4.getClass(),gs4.getClass());

        GUIShape s5 = new GUIShape(gs5.getShape(),true,Color.BLUE,6);  // getShape test for segment
        assertEquals(s5.getClass(),gs5.getClass());
    }

    @Test
    void setShape() {
        GUIShape actual = new GUIShape(gs5.getShape(),true,Color.BLUE,6);
        actual.setShape(v1);
        assertEquals(actual.getClass(),gs1.getClass());  // setShape test for polygon

        actual.setShape(c1);
        assertEquals(actual.getClass(),gs2.getClass());  // setShape test for circle

        actual.setShape(r1);
        assertEquals(actual.getClass(),gs3.getClass());  // setShape test for rectangle

        actual.setShape(t1);
        assertEquals(actual.getClass(),gs4.getClass());  // setShape test for triangle

        actual.setShape(s1);
        assertEquals(actual.getClass(),gs5.getClass());  // setShape test for segment
    }

    @Test
    void isFilled() {
        assertTrue(gs1.isFilled());
        assertFalse(gs2.isFilled());
    }

    @Test
    void setFilled() {
        gs1.setFilled(false);       // changing isFilled to false from true
        assertFalse(gs1.isFilled());

        gs2.setFilled(true);        // changing isFilled to true from false
        assertTrue(gs2.isFilled());
    }

    @Test
    void getColor() {
        Color actual = gs1.getColor();
        Color expected = Color.BLUE;
        assertEquals(actual,expected);
    }

    @Test
    void setColor() {
        gs1.setColor(Color.BLACK);
        Color actual = gs1.getColor();
        Color expected = Color.BLACK;
        assertEquals(actual,expected);
    }

    @Test
    void getTag() {
        int actual = gs1.getTag();
        int expected = 1;
        assertEquals(actual,expected);
    }

    @Test
    void setTag() {
        gs1.setTag(2);
        int actual = gs1.getTag();
        int expected = 2;
        assertEquals(actual,expected);
    }

    @Test
    void copy() {
        GUIShape copiedShape = (GUIShape) gs1.copy();
        assertEquals(copiedShape.getTag(),gs1.getTag());
        assertEquals(copiedShape.getClass(),gs1.getClass());
        assertEquals(copiedShape.isFilled(),gs1.isFilled());
        assertEquals(copiedShape.isSelected(),gs1.isSelected());
        assertEquals(copiedShape.getColor(),gs1.getColor());
    }

    @Test
    void testToString() {
        String actual = gs1.toString();
        String expected = "GUIShape,-16776961,true,1,Polygon_2D,[1.0,2.0, 3.0,5.0, 9.0,1.0, 4.0,1.0, 1.0,0.0]";
        assertEquals(actual,expected);
    }

    @Test
    void isSelected() {
        assertFalse(gs1.isSelected());
    }

    @Test
    void setSelected() {
        gs1.setSelected(true);
        assertTrue(gs1.isSelected());
    }
}