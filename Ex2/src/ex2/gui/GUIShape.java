package ex2.gui;
/**
 * This class implements the GUI_shape.
 * Ex2: you should implement this class!
 * @author I2CS
 */
import ex2.geo.*;
import java.awt.*;


public class GUIShape implements GUI_Shape{
	private GeoShape _g = null;
	private boolean _fill;
	private Color _color;
	private int _tag;
	private boolean _isSelected;
	
	public GUIShape(GeoShape g, boolean f, Color c, int t) {
		_g = null;
		if (g!=null) {_g = g.copy();}
		_fill= f;
		_color = c;
		_tag = t;
		_isSelected = false;
	}
	public GUIShape(GUIShape ot) {
		this(ot._g, ot._fill, ot._color, ot._tag);
	}
	public GUIShape(String shape) {
		String[] quality = shape.split(",");
		if("Rect_2D".equals(quality[4])) {
			double x1 = Double.parseDouble(quality[5]);
			double y1 = Double.parseDouble(quality[6]);
			double x2 = Double.parseDouble(quality[9]);
			double y2 = Double.parseDouble(quality[10]);
			Point_2D p1 = new Point_2D(x1,y1);
			Point_2D p2 = new Point_2D(x2,y2);
			Rect_2D r=new Rect_2D(p1,p2);
			boolean isFilled = Boolean.parseBoolean(quality[2]);
			Color color = Color.decode(quality[1]);
			int tag = Integer.parseInt(quality[3]);
			_g = r; _fill= isFilled; _color = color; _tag = tag; _isSelected = false;
		}
		if("Triangle_2D".equals(quality[4])){
			double x1 = Double.parseDouble(quality[5]);
			double y1 = Double.parseDouble(quality[6]);
			double x2 = Double.parseDouble(quality[7]);
			double y2 = Double.parseDouble(quality[8]);
			double x3 = Double.parseDouble(quality[9]);
			double y3 = Double.parseDouble(quality[10]);
			Point_2D p1 = new Point_2D(x1,y1);
			Point_2D p2 = new Point_2D(x2,y2);
			Point_2D p3 = new Point_2D(x3,y3);
			Triangle_2D t =new Triangle_2D(p1,p2,p3);
			boolean isFilled = Boolean.parseBoolean(quality[2]);
			Color color = Color.decode(quality[1]);
			int tag = Integer.parseInt(quality[3]);
			_g = t; _fill= isFilled; _color = color; _tag = tag; _isSelected=false;
		}
		if("Circle_2D".equals(quality[4])){
			double x1 = Double.parseDouble(quality[5]);
			double y1 = Double.parseDouble(quality[6]);
			double rad = Double.parseDouble(quality[7]);
			Point_2D cen = new Point_2D(x1,y1);
			Circle_2D c = new Circle_2D(cen,rad);
			boolean isFilled = Boolean.parseBoolean(quality[2]);
			Color color = Color.decode(quality[1]);
			int tag = Integer.parseInt(quality[3]);
			_g = c; _fill= isFilled; _color = color; _tag = tag; _isSelected=false;
		}
		if("Segment_2D".equals(quality[4])){
			double x1 = Double.parseDouble(quality[5]);
			double y1 = Double.parseDouble(quality[6]);
			double x2 = Double.parseDouble(quality[7]);
			double y2 = Double.parseDouble(quality[8]);
			Point_2D p1 = new Point_2D(x1,y1);
			Point_2D p2 = new Point_2D(x2,y2);
			Segment_2D s = new Segment_2D(p1,p2);
			boolean isFilled = Boolean.parseBoolean(quality[2]);
			Color color = Color.decode(quality[1]);
			int tag = Integer.parseInt(quality[3]);
			_g = s; _fill= isFilled; _color = color; _tag = tag; _isSelected=false;
		}
		if("Polygon_2D".equals(quality[4])){
			Polygon_2D p = new Polygon_2D();
			for (int j = 5; j < quality.length-1; j+=2) {
				double x1 = Double.parseDouble(quality[j]);
				double y1 = Double.parseDouble(quality[j+1]);
				p.add(new Point_2D(x1,y1));
			}
			boolean isFilled = Boolean.parseBoolean(quality[2]);
			Color color = Color.decode(quality[1]);
			int tag = Integer.parseInt(quality[3]);
			_g = p; _fill= isFilled; _color = color; _tag = tag; _isSelected=false;
		}
	}
	@Override
	public GeoShape getShape() {
		return _g;
	}

	@Override
	public void setShape(GeoShape g) {
		_g = g;
	}

	@Override
	public boolean isFilled() {
		return _fill;
	}

	@Override
	public void setFilled(boolean filled) {
		_fill = filled;
	}

	@Override
	public Color getColor() {
		return _color;
	}

	@Override
	public void setColor(Color cl) {
		_color = cl;
	}

	@Override
	public int getTag() {
		return _tag;
	}

	@Override
	public void setTag(int tag) {
		_tag = tag;
	}

	@Override
	public GUI_Shape copy() {
		GUI_Shape cp = new GUIShape(this);
		return cp;
	}
	@Override
	public String toString() {
		String ans = ""+this.getClass().getSimpleName()+","+_color.getRGB()+","+_fill+","+_tag+","+this._g.getClass().getSimpleName()+","+_g.toString();
		return ans;
	}
	@Override
	public boolean isSelected() {
		return this._isSelected;
	}
	@Override
	public void setSelected(boolean s) {
		this._isSelected = s;
	}
}
