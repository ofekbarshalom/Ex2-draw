package ex2.geo;

import ex2.ex2.Ex2_Const;

/**
 * This class represents a 2D Triangle in the plane.
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class Triangle_2D implements GeoShape{
	private Point_2D p1;
	private Point_2D p2;
	private Point_2D p3;
	private final Point_2D originP1;
	private final Point_2D originP2;
	private final Point_2D originP3;
	private int countForMinusOne=0;


	public Triangle_2D(Point_2D p1, Point_2D p2, Point_2D p3) {
		this.p1= new Point_2D(p1);
		this.p2= new Point_2D(p2);
		this.p3= new Point_2D(p3);
		this.originP1=new Point_2D(p1);
		this.originP2=new Point_2D(p2);
		this.originP3=new Point_2D(p3);
	}
	public Triangle_2D(Triangle_2D t1) {
		this(t1.p1,t1.p2,t1.p3);
	}
	public Point_2D[] getAllPoints() {
		return new Point_2D[]{this.p1,this.p2,this.p3};
	}
	@Override
	public boolean contains(Point_2D ot) {
		double area1=new Triangle_2D(ot,p2,p3).area();
		double area2=new Triangle_2D(p1,ot,p3).area();
		double area3=new Triangle_2D(p1,p2,ot).area();
		double barycentric1 = area1 / this.area();
		double barycentric2 = area2 / this.area();
		double barycentric3 = area3 / this.area();
		return barycentric1 >= 0 && barycentric2 >= 0 && barycentric3 >= 0 &&
				(barycentric1 + barycentric2 + barycentric3) <= 1;
	}

	@Override
	public double area() {
		double a=this.p1.distance(p2);
		double b=this.p2.distance(p3);
		double c=this.p3.distance(p1);
		double s=this.perimeter()/2;
		return Math.sqrt(s*(s-a)*(s-b)*(s-c)); //using Heron's formula;
	}

	@Override
	public double perimeter() {
		return this.p1.distance(p2)+this.p2.distance(p3)+this.p3.distance(p1);
	}

	@Override
	public void translate(Point_2D vec) {
		this.p1.move(vec);
		this.p2.move(vec);
		this.p3.move(vec);
	}

	@Override
	public GeoShape copy() {
		return new Triangle_2D(this);
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
				countForMinusOne=0;
				return;
			}
		}
		else
			countForMinusOne=0;

		this.p1.scale(center,ratio);
		this.p2.scale(center,ratio);
		this.p3.scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		this.p1.rotate(center,angleDegrees);
		this.p2.rotate(center,angleDegrees);
		this.p3.rotate(center,angleDegrees);
	}
	@Override
	public String toString() {
		return p1.toString()+","+p2.toString()+","+p3.toString();
	}

	@Override
	public boolean equals(Object p){
		if(p==null || !(p instanceof Triangle_2D)) {return false;}
		Triangle_2D s2 = (Triangle_2D) p;
		Point_2D[] actual = this.getAllPoints();
		Point_2D[] excepted = s2.getAllPoints();
		int count=0;

		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if(actual[i].equals(excepted[j])){
					count++;
				}
			}
		}
		return (count==3);
	}
}
