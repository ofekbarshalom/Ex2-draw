package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D segment on the plane,
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Segment_2D implements GeoShape{
	private final Point_2D p1;
	private final Point_2D p2;
	private final Point_2D originP1;
	private final Point_2D originP2;
	private int countForMinusOne=0;
	public Segment_2D(Point_2D p1, Point_2D p2) {
		this.p1= new Point_2D(p1);
		this.p2= new Point_2D(p2);
		this.originP1=new Point_2D(p1);
		this.originP2=new Point_2D(p2);
	}
	public Segment_2D(Segment_2D t1) {
		this(t1.get_p1(),t1.get_p2());
	}
	public Point_2D get_p1() {return this.p1;}
	public Point_2D get_p2() {return this.p2;}

	@Override
	public boolean contains(Point_2D ot) {
        return this.p1.distance(ot) + this.p2.distance(ot) - this.p1.distance(this.p2) < Ex2_Const.EPS1;
	}
	@Override
	public double area() {
		return 0;
	}

	@Override
	public double perimeter() {
		return this.p1.distance(this.p2)*2;
	}

	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Segment_2D(this);
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		if(ratio==-1){
			countForMinusOne++;
			if(countForMinusOne==2){
				this.p1.set_x(originP1.x());
				this.p1.set_y(originP1.y());
				this.p2.set_x(originP2.x());
				this.p2.set_y(originP2.y());
				countForMinusOne=0;
				return;
			}
		}
		else
			countForMinusOne=0;

		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center,angleDegrees);
		this.p2.rotate(center,angleDegrees);
	}
	@Override
	public String toString() {
		return p1.toString()+","+p2.toString();
	}

	@Override
	public boolean equals(Object p){
		if(p==null || !(p instanceof Segment_2D)) {return false;}
        Segment_2D s2 = (Segment_2D) p;
		if(s2.p1.equals(this.p1) && s2.p2.equals(this.p2))
			return true;
		if(s2.p1.equals(this.p2) && s2.p2.equals(this.p1))
			return true;
		return false;
    }
}