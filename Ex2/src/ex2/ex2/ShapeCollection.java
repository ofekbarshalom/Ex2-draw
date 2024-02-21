package ex2.ex2;
import ex2.geo.*;
import ex2.gui.GUIShape;
import ex2.gui.GUI_Shape;

import javax.imageio.IIOException;
import java.awt.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Comparator;

/**
 * This class represents a collection of GUI_Shape.
 * Ex2: you should implement this class!
 * @author I2CS
 *
 */
public class ShapeCollection implements GUI_Shape_Collection {
	private ArrayList<GUI_Shape> _shapes;
	
	public ShapeCollection() {
		_shapes = new ArrayList<GUI_Shape>();
	}
	@Override
	public GUI_Shape get(int i) {
		return _shapes.get(i);
	}

	@Override
	public int size() {
		return _shapes.size();
	}

	@Override
	public GUI_Shape removeElementAt(int i) {
		GUI_Shape removedShape=_shapes.get(i);
		_shapes.remove(i);
		return removedShape;
	}

	@Override
	public void addAt(GUI_Shape s, int i) {
		_shapes.add(i,s);
	}
	@Override
	public void add(GUI_Shape s) {
		if(s!=null && s.getShape()!=null) {
			_shapes.add(s);
		}
	}
	@Override
	public GUI_Shape_Collection copy() {
		ShapeCollection copiedShapeCollection=new ShapeCollection();
		for (int i = 0; i < _shapes.size(); i++)
			copiedShapeCollection.add(_shapes.get(i));
		return copiedShapeCollection;
	}

	@Override
	public void sort(Comparator<GUI_Shape> comp) {
		_shapes.sort(comp);
	}

	@Override
	public void removeAll() {
		_shapes.clear();
	}

	@Override
	public void save(String file_name) {
		String content = this._shapes.toString();
		content = content.replace("[","").replace("]","").replaceAll("(?<!^)(GUIShape)", "\n$1");
		String path = file_name + ".txt";
		try {
			Files.writeString(Paths.get(path),content);
			System.out.println("File saved successfully.");
		} catch (Exception e) {
			System.err.println("Error writing to file: " + e.getMessage());
		}
	}

	@Override
	public void load(String file) {
		try {
			String path = file;
			String content = new String(Files.readAllBytes(Paths.get(path)));
			String[] shapes = content.split("\n");
			for (int i = 0; i < shapes.length; i++) {
				String shape=shapes[i];
				GUIShape gs=new GUIShape(shape);
				this._shapes.add(gs);
			}
		}
		catch (IOException e){
			System.err.println("file is not readable"+e.getMessage());
		}
	}

	@Override
	public String toString() {
		String ans = "";
		for(int i=0;i<size();i=i+1) {
			ans += this.get(i);
		}
		return ans;
	}
	

}
