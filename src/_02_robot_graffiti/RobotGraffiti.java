package _02_robot_graffiti;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class RobotGraffiti {

	// negative number here makes the letters right side up
	public static int lineHeight = -200;
	public static int charWidth = 100;
	public static int speed = 10000;
	public static Robot robot;
	
	// rotates canvas angle to make 0 deg point "east"
	public static int angleTransform = 90;
	
	public static int xOffset = 200;
	public static int yOffset = 300;
	
    public static void main(String[] args) throws Exception {
    	 
    	// 1. Make a new Robot
    	robot = new Robot();

    	robot.setSpeed(10000);
    	draw_e();
    	robot.hide();
    	
    }
    
    private static void draw_e() throws Exception {
    	// calculate points
    	Point lowerLeft = new Point(0,0, xOffset, yOffset);
    	Point topLeft = new Point(0,lineHeight, xOffset, yOffset);
    	Point topRight = new Point(charWidth,lineHeight, xOffset, yOffset);
    	Point middleLeft = new Point(0,lineHeight / 2, xOffset, yOffset);
    	Point middleRight = new Point(charWidth,lineHeight / 2, xOffset, yOffset);
    	Point lowerRight = new Point(charWidth,0, xOffset, yOffset);
    	
    	// draw lines
    	drawLine(lowerLeft, topLeft);
    	drawLine(topLeft, topRight);
    	drawLine(middleLeft, middleRight);
    	drawLine(lowerLeft, topLeft);
    	drawLine(lowerLeft, lowerRight);
    }
    
    private static void draw_a() throws Exception {
    	// calculate points
    	int lowerLeftX = 0;
    	int lowerLeftY = 0;
    	
    	int lowerRightX = charWidth;
    	int lowerRightY = 0;
    	
    	int topMiddleX = charWidth / 2;
    	int topMiddleY = lineHeight;
    	
    	int middleLeftX = charWidth / 4;
    	int middleLeftY = lineHeight / 2;
    	
    	int middleRightX = charWidth * 3 / 4;
    	int middleRightY = lineHeight / 2;
    	
    	Point lowerLeft = new Point(lowerLeftX,lowerLeftY, xOffset, yOffset);
    	Point lowerRight = new Point(lowerRightX,lowerRightY, xOffset, yOffset);
    	Point topMiddle = new Point(topMiddleX,topMiddleY, xOffset, yOffset);
    	Point middleLeft = new Point(middleLeftX,middleLeftY, xOffset, yOffset);
    	Point middleRight = new Point(middleRightX,middleRightY, xOffset, yOffset);
    	
    	// draw lines
    	drawLine(lowerLeft, topMiddle);
    	drawLine(lowerRight, topMiddle);
    	drawLine(middleLeft, middleRight);
    }
    
    private static void drawLine(Point start, Point end) throws Exception {
    	// teleport to start of line
    	robot.moveTo(start.x, start.y);
    	
    	// calculate move inputs from x,y coordinates
    	double angle = calculateAngle(start, end);
    	double distance = calculateDistance(start, end);
    	
    	// draw line
    	robot.setAngle((int) angle);
    	robot.penDown();
    	robot.move((int) distance);
    	robot.penUp();
    }
    
    private static double calculateAngle(Point start, Point end) throws Exception {
    	int verticalDistance = end.y - start.y;
    	int horizontalDistance = end.x - start.x;
    	
//    	System.out.println("verticalDistance: " + verticalDistance);
//    	System.out.println("horizontalDistance: " + horizontalDistance);

    	// use atan2 to capture full 360 deg of rotation
    	double angleInRadians = Math.atan2(verticalDistance,horizontalDistance);
    	double angleInDegrees = Math.toDegrees(angleInRadians);
    	
    	double transformedAngle = angleInDegrees + angleTransform;
    	
    	return transformedAngle;
    }
    
    private static double calculateDistance(Point start, Point end) throws Exception {
    	int verticalDistance = end.y - start.y;
    	int horizontalDistance = end.x - start.x;
    	
    	double aSquared = Math.pow(verticalDistance, 2);
    	double bSquared = Math.pow(horizontalDistance, 2);
    	
    	double cSquared = aSquared + bSquared;
    	
    	double distance = Math.sqrt(cSquared);
    	
    	return distance;
    }
    
	
}

;