package _02_robot_graffiti;

public class Point {
	
	public int x;
	public int y;
	public int xOffset;
	public int yOffset;
	
	public Point(int x, int y, int xOffset, int yOffset) {
		System.out.println("x 😀: " + x);
		System.out.println("y: " + y);
		this.x = xOffset + x;
		this.y = yOffset + y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
		
	}
}
