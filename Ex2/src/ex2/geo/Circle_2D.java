package ex2.geo;

/**
 * This class represents a 2D circle in the plane.
 * Please make sure you update it according to the GeoShape interface.
 * Ex2: you should update this class!
 * @author boaz.benmoshe
 *
 */
public class Circle_2D implements GeoShape{
	private Point_2D _center;
	private double _radius;
	private double originRadius;
	private final Point_2D originCenter;
	private int countForMinusOne=0;
	public Circle_2D(Point_2D cen, double rad) {
		this._center = new Point_2D(cen);
		this._radius = rad;
		this.originCenter = new Point_2D(_center);
		this.originRadius = _radius;
	}
	public Circle_2D(Circle_2D c) {this(c.getCenter(), c.getRadius());}
	public double getRadius() {return this._radius;}
	public Point_2D getCenter(){ return _center;}
	@Override
	public String toString()
	{
		return _center.toString()+", "+_radius;
	}
	@Override
	public boolean contains(Point_2D ot) {
		if(this._radius>=this._center.distance(ot))
			return true;
		return false;
	}

	@Override
	public double area() {
		return Math.PI*Math.pow(this._radius,2);
	}
	@Override
	public double perimeter() {
		return 2*Math.PI*this._radius;
	}
	@Override
	public void translate(Point_2D vec) {
		this._center.move(vec);
	}
	@Override
	public GeoShape copy() {
		return new Circle_2D(this);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		if(ratio==-1){
			countForMinusOne++;
			if(countForMinusOne==2){
				this._radius=originRadius;
				this._center.set_x(originCenter.x());
				this._center.set_y(originCenter.y());
				countForMinusOne=0;
				return;
			}
		}
		else
			countForMinusOne=0;

		_center.scale(center,ratio);
		_radius*=ratio;
	}
	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this._center.rotate(center,angleDegrees);
	}

	@Override
	public boolean equals(Object p){
		if(p==null || !(p instanceof Circle_2D)) {return false;}
		Circle_2D c2 = (Circle_2D) p;
		if(c2.getCenter().equals(this._center) && this._radius == c2.getRadius())
			return true;
		return false;
	}
}