package _02_robot_graffiti;

public class Point {
	
	public float x;
	public float y;
	public float xOffset;
	public float yOffset;
	
	public Point(float x, float y, float xOffset, float yOffset) {
		this.x = xOffset + x;
		this.y = yOffset + y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
	
	public Point() {
		this.x = 0;
		this.y = 0;
		this.xOffset = 0;
		this.yOffset = 0;
	}

	
}
