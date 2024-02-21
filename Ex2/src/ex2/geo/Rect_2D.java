package ex2.geo;

/**
 * This class represents a 2D axis parallel rectangle.
 * Ex4: you should implement this class!
 * @author I2CS
 *
 */
public class Rect_2D implements GeoShape {
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	private Point_2D p4;
	private final Point_2D originP1;
	private final Point_2D originP2;
	private final Point_2D originP3;
	private final Point_2D originP4;
	private int countForMinusOne=0;

	public Rect_2D(Point_2D p1, Point_2D p2) {
		this.p1= new Point_2D(p1);
		this.p2= new Point_2D(p2);
		this.p3=new Point_2D(p1.x(),p2.y());
		this.p4=new Point_2D(p2.x(),p1.y());
		this.originP1=new Point_2D(this.p1);
		this.originP2=new Point_2D(this.p2);
		this.originP3=new Point_2D(this.p3);
		this.originP4=new Point_2D(this.p4);
	}
	public Rect_2D(Rect_2D t1) {
		this(t1.get_p1(),t1.get_p2());
	}
	public Point_2D get_p1() {return this.p1;}
	public Point_2D get_p2() {return this.p2;}
	public Point_2D get_p3() {return this.p3;}
	public Point_2D get_p4() {return this.p4;}

	@Override
	public boolean contains(Point_2D ot) {
		if (this.p1.y() < this.p2.y()) {
			if (this.p1.y() <= ot.y() && ot.y() <= this.p2.y()) {
				if (this.p1.x() < this.p2.x()) {
					if (this.p1.x() <= ot.x() && ot.x() <= this.p2.x())
						return true;
				} else if (this.p2.x() <= ot.x() && ot.x() <= this.p1.x())
					return true;
			}
		}
		else {
			if (this.p2.y() <= ot.y() && ot.y() <= this.p1.y()) {
				if (this.p1.x() < this.p2.x()) {
					if (this.p1.x() <= ot.x() && ot.x() <= this.p2.x())
						return true;
				} else {
					if (this.p2.x() <= ot.x() && ot.x() <= this.p1.x())
						return true;
				}
			}
		}
		return false;
	}
	@Override
	public double area() {
		return this.p2.distance(p3)* this.p1.distance(p3);
	}

	@Override
	public double perimeter() {
		return 2*this.p2.distance(p3)+2*this.p1.distance(p3);
	}

	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
		this.p3.move(vec);
		this.p4.move(vec);
	}

	@Override
	public GeoShape copy() {
		Rect_2D copiedRec = new Rect_2D(new Point_2D(this.p1), new Point_2D(this.p2));
		copiedRec.p3 = new Point_2D(this.p3);
		copiedRec.p4 = new Point_2D(this.p4);
		return copiedRec;
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
				this.p3.set_x(originP3.x());
				this.p3.set_y(originP3.y());
				this.p4.set_x(originP4.x());
				this.p4.set_y(originP4.y());
				countForMinusOne=0;
				return;
			}
		}
		else
			countForMinusOne=0;

		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
		this.p3.scale(center,ratio);
		this.p4.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {

		this.p1.rotate(center,angleDegrees);
		this.p2.rotate(center,angleDegrees);
		this.p3.rotate(center,angleDegrees);
		this.p4.rotate(center,angleDegrees);
	}
	@Override
	public String toString() {
		return p1.toString()+","+p2.toString()+","+p3.toString()+","+p4.toString();
	}

	@Override
	public boolean equals(Object p){
		if(p==null || !(p instanceof Rect_2D)) {return false;}
		Rect_2D r2 = (Rect_2D) p;
		Point_2D[] actual = new Point_2D[]{p1,p2,p3,p4};
		Point_2D[] excepted = new Point_2D[]{r2.get_p1(),r2.get_p2(),r2.get_p3(),r2.get_p4()};
		int count=0;
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				if(actual[i].equals(excepted[j])){
					count++;
				}
			}
		}
		return (count==4);
	}
}