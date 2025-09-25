package design.patterns.structural.adapter;

/** Modified from https://sourcemaking.com/design_patterns/adapter/java/1 */

public class Rectangle {

	public void drawRec(int x, int y, int w, int h) {
		// x, y are the coordinates of the bottom left corner of the rectangle
		System.out.println("rectangle at (" + x + ',' + y + ") with width " + w + " and height " + h);
	}

}
