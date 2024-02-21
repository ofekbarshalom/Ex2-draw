package ex2.geo;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Polygon_2D implements GeoShape{
	private ArrayList<Point_2D> vertex;
	private int countForMinusOne=0;
	private ArrayList<Point_2D> originalVertex;
	public Polygon_2D() {
		this.vertex = new ArrayList<>();
		this.originalVertex = new ArrayList<>();
	}
	public Polygon_2D(Polygon_2D po) {
		this.vertex = new ArrayList<>(po.vertex);
		this.originalVertex = new ArrayList<>(po.vertex);
	}
	public Point_2D[] getAllPoints() {
		Point_2D[] allPoints = new Point_2D[this.vertex.size()];
		for (int i = 0; i < this.vertex.size(); i++)
			allPoints[i]= this.vertex.get(i);
		return allPoints;
	}

	public void add(Point_2D p) {this.vertex.add(p); this.originalVertex.add(p);}
	@Override
	public String toString() {
		return vertex.toString();
	}
	@Override
	public boolean contains(Point_2D ot) {
		int i, j;
		boolean result = false;
		for (i = 0, j = vertex.size() - 1; i < vertex.size(); j = i++) {
			if ((vertex.get(i).y() > ot.y()) != (vertex.get(j).y() > ot.y()) &&
					(ot.x() < (vertex.get(j).x() - vertex.get(i).x()) * (ot.y() - vertex.get(i).y()) / (vertex.get(j).y() - vertex.get(i).y()) + vertex.get(i).x())) {
				result = !result;
			}
		}
		return result;
	}

	@Override
	public double area() {
		double area=0;
		for (int i = 0; i < this.vertex.size()-1; i++) {
			double x1= this.vertex.get(i).x();
			double y1= this.vertex.get(i).y();
			double x2= this.vertex.get(i+1).x();
			double y2= this.vertex.get(i+1).y();
			area+=(x1*y2)-(x2*y1);
		}
		return Math.abs(area/2.0);
	}
	@Override
	public double perimeter() {
		double preimeter=0;
		for (int i = 0; i < this.vertex.size()-1; i++)
			preimeter+=this.vertex.get(i).distance(this.vertex.get(i+1));
		preimeter+=this.vertex.get(this.vertex.size()-1).distance(this.vertex.get(0));
		return preimeter;
	}
	@Override
	public void translate(Point_2D vec) {
		for (int i = 0; i < this.vertex.size(); i++)
			this.vertex.get(i).move(vec);
	}
	@Override
	public GeoShape copy() { //improve scale
		Polygon_2D copiedPolygon = new Polygon_2D();
		for (int i=0;i<this.vertex.size();i++)
			copiedPolygon.add(new Point_2D(this.vertex.get(i)));
		return copiedPolygon;
	}

	@Override
	public void scale(Point_2D center, double ratio) {
		if(ratio==-1){
			countForMinusOne++;
			if(countForMinusOne==2){
				this.vertex.clear();
				for (int i = 0; i < originalVertex.size(); i++) {
					this.vertex.add(new Point_2D(originalVertex.get(i)));
				}
				countForMinusOne=0;
				return;
			}
		}
		else
			countForMinusOne=0;

		for (int i = 0; i < this.vertex.size(); i++)
			this.vertex.get(i).scale(center,ratio);
	}

	@Override
	public void rotate(Point_2D center, double angleDegrees) {
		for (int i = 0; i < this.vertex.size(); i++)
			this.vertex.get(i).rotate(center,angleDegrees);
	}

	@Override
	public boolean equals(Object p){
		if(p==null || !(p instanceof Polygon_2D)) {return false;}
		Polygon_2D p2 = (Polygon_2D) p;
		Point_2D[] actual = this.getAllPoints();
		Point_2D[] excepted = p2.getAllPoints();
		int count=0;
		if(actual.length==excepted.length) {
			for (int i = 0; i < actual.length; i++) {
				for (int j = 0; j < actual.length; j++) {
					if (actual[i].equals(excepted[j]))
						count++;
				}
			}
			return(count==actual.length);
		}
		return false;
	}
}
