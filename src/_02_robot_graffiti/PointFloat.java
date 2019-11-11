package _02_robot_graffiti;

public class PointFloat extends Point {
	
	public float x;
	public float y;
	public float xOffset;
	public float yOffset;
	
	public PointFloat(float x, float y, float xOffset, float yOffset) {
		this.x = xOffset + x;
		this.y = yOffset + y;
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
