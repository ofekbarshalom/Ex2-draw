package ex2.test;

import ex2.ex2.ShapeCollection;
import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.awt.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

class ShapeCollectionTest {
    private Point_2D p1;
    private Point_2D p2;
    private Point_2D p3;
    private Point_2D p4;
    private Point_2D p5;
    private Polygon_2D v1; private GUIShape gs1;
    private Circle_2D c1; private GUIShape gs2;
    private Rect_2D r1; private GUIShape gs3;
    private Triangle_2D t1; private GUIShape gs4;
    private Segment_2D s1; private GUIShape gs5;
    private ShapeCollection collection1;

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

        collection1=new ShapeCollection();
        collection1.add(gs1); collection1.add(gs2); collection1.add(gs3); collection1.add(gs4); collection1.add(gs5);
    }

    @Test
    void get() {
        GUIShape actual = (GUIShape) collection1.get(2);
        GUI_Shape excepted = gs3;
        assertEquals(excepted.getTag(),actual.getTag());
        assertEquals(excepted.getShape(),actual.getShape());
        assertEquals(excepted.isFilled(),actual.isFilled());
        assertEquals(excepted.isSelected(),actual.isSelected());
    }

    @Test
    void size() {assertEquals(collection1.size(),5);}

    @Test
    void removeElementAt() {
        collection1.removeElementAt(0);
        assertNotEquals(collection1.get(0).toString(),gs1.toString());

        assertEquals(collection1.get(0).getTag(),gs2.getTag());
        assertEquals(collection1.get(0).getShape(),gs2.getShape());
        assertEquals(collection1.get(0).getColor(),gs2.getColor());
        assertEquals(collection1.get(0).isSelected(),gs2.isSelected());
        assertEquals(collection1.get(0).isFilled(),gs2.isFilled());
    }

    @Test
    void addAt() {
        collection1.addAt(gs1,2);
        assertEquals(collection1.get(2).getTag(),gs1.getTag());
        assertEquals(collection1.get(2).getShape(),gs1.getShape());
        assertEquals(collection1.get(2).getColor(),gs1.getColor());
        assertEquals(collection1.get(2).isSelected(),gs1.isSelected());
        assertEquals(collection1.get(2).isFilled(),gs1.isFilled());

        assertEquals(collection1.get(3).getTag(),gs3.getTag());
        assertEquals(collection1.get(3).getShape(),gs3.getShape());
        assertEquals(collection1.get(3).getColor(),gs3.getColor());
        assertEquals(collection1.get(3).isSelected(),gs3.isSelected());
        assertEquals(collection1.get(3).isFilled(),gs3.isFilled());
    }

    @Test
    void add() {
        collection1.add(gs1);
        assertEquals(collection1.get(5).getTag(),gs1.getTag());
        assertEquals(collection1.get(5).getShape(),gs1.getShape());
        assertEquals(collection1.get(5).getColor(),gs1.getColor());
        assertEquals(collection1.get(5).isSelected(),gs1.isSelected());
        assertEquals(collection1.get(5).isFilled(),gs1.isFilled());
    }

    @Test
    void copy() {
        ShapeCollection copiedCollection1 = (ShapeCollection) collection1.copy();
        for (int i = 0; i < collection1.size(); i++) {
            assertEquals(collection1.get(i).getTag(),copiedCollection1.get(i).getTag());
            assertEquals(collection1.get(i).getShape(),copiedCollection1.get(i).getShape());
            assertEquals(collection1.get(i).getColor(),copiedCollection1.get(i).getColor());
            assertEquals(collection1.get(i).isSelected(),copiedCollection1.get(i).isSelected());
            assertEquals(collection1.get(i).isFilled(),copiedCollection1.get(i).isFilled());
        }
    }

    @Test
    void sort() {
        ShapeComp comp = new ShapeComp(0); // Sort by Tag
        collection1.sort(comp);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).getTag()<collection1.get(i+1).getTag());

        ShapeComp comp2 = new ShapeComp(1); // Sort by Anti-Tag
        collection1.sort(comp2);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).getTag()>collection1.get(i+1).getTag());

        ShapeComp comp3 = new ShapeComp(2); // Sort by area
        collection1.sort(comp3);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).getShape().area()<collection1.get(i+1).getShape().area());

        ShapeComp comp4 = new ShapeComp(3); // Sort by anti-area
        collection1.sort(comp4);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).getShape().area()>collection1.get(i+1).getShape().area());

        ShapeComp comp5 = new ShapeComp(4); // Sort by perimeter
        collection1.sort(comp5);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).getShape().perimeter()<collection1.get(i+1).getShape().perimeter());

        ShapeComp comp6 = new ShapeComp(5); // Sort by anti-perimeter
        collection1.sort(comp6);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).getShape().perimeter()>collection1.get(i+1).getShape().perimeter());

        ShapeComp comp7 = new ShapeComp(6); // Sort by toString
        collection1.sort(comp7);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).toString().compareTo(collection1.get(i+1).toString())<=0);

        ShapeComp comp8 = new ShapeComp(7); // Sort by anti-toString
        collection1.sort(comp8);
        for (int i = 0; i < collection1.size()-1; i++)
            assertTrue(collection1.get(i).toString().compareTo(collection1.get(i+1).toString())>=0);
    }

    @Test
    void removeAll() {
        collection1.removeAll();
        assertEquals(collection1.size(),0);
    }

    @Test
    void save() {
        ShapeCollection collection2 = new ShapeCollection();
        collection2.add(gs1); collection2.add(gs2);
        String fileName = "test_file";
        try {
            collection2.save(fileName);
            String actualContent = Files.readString(Paths.get(fileName + ".txt"));
            String expectedContent = "GUIShape,-16776961,true,1,Polygon_2D,1.0,2.0, 3.0,5.0, 9.0,1.0, 4.0,1.0, 1.0,0.0, \n" +
                    "GUIShape,-16777216,false,2,Circle_2D,1.0,2.0, 3.0";
            assertEquals(actualContent,expectedContent);
        } catch (Exception e) {
            System.err.println("the file is not valid");
        }
    }

    @Test
    void load() throws Exception{
        ShapeCollection collection2 = new ShapeCollection(); //didnt finished yet
        String expectedContent = "GUIShape,-16776961,true,1,Polygon_2D,1.0,2.0, 3.0,5.0, 9.0,1.0, 4.0,1.0, 1.0,0.0, \n" +
                "GUIShape,-16777216,false,2,Circle_2D,1.0,2.0, 3.0";
        String fileName = "test_file";
        Path path1 = Paths.get("textShape.txt");

        Files.writeString(path1,expectedContent);
        collection2.load(path1.toString());
        assertEquals(collection2.size(),2);
    }

    @Test
    void testToString() {
        ShapeCollection collection2 = new ShapeCollection();
        collection2.add(gs1); collection2.add(gs2);
        String actual = collection2.toString();
        String expected = "GUIShape,-16776961,true,1,Polygon_2D,[1.0,2.0, 3.0,5.0, 9.0,1.0, 4.0,1.0, 1.0,0.0]GUIShape,-16777216,false,2,Circle_2D,1.0,2.0, 3.0";
        assertEquals(actual,expected);
    }
}