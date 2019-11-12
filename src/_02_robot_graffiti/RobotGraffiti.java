package _02_robot_graffiti;

import java.awt.Color;
import java.lang.reflect.Method;
import java.util.HashMap;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;
import org.jointheleague.graphical.robot.curves.Cubic;

@SuppressWarnings("deprecation")

public class RobotGraffiti {

	// negative number here makes the letters right side up
	public static int lineHeight = -200;
	public static int charWidth = 100;
	public static int speed = 6000;
	public static Robot robot;
	
	// rotates canvas angle to make 0 deg point "east"
	public static int angleTransform = 90;
	
	public static int xOffset = 400;
	public static int yOffset = 400;
	
	public static Point lowerLeft = new Point(0,0, xOffset, yOffset);
	public static Point topLeft = new Point(0,lineHeight, xOffset, yOffset);
	public static Point topRight = new Point(charWidth,lineHeight, xOffset, yOffset);
	public static Point middleLeft = new Point(0,lineHeight / 2, xOffset, yOffset);
	public static Point middleRight = new Point(charWidth,lineHeight / 2, xOffset, yOffset);
	public static Point lowerRight = new Point(charWidth,0, xOffset, yOffset);
	public static Point lowerMiddle = new Point(charWidth / 2,0, xOffset, yOffset);
	public static Point topMiddle = new Point(charWidth / 2,lineHeight, xOffset, yOffset);

	public static void redefineStandardPoints(int xOffset, int yOffset) throws Exception { 
		lowerLeft = new Point(0,0, xOffset, yOffset);
		topLeft = new Point(0,lineHeight, xOffset, yOffset);
		topRight = new Point(charWidth,lineHeight, xOffset, yOffset);
		middleLeft = new Point(0,lineHeight / 2, xOffset, yOffset);
		middleRight = new Point(charWidth,lineHeight / 2, xOffset, yOffset);
		lowerRight = new Point(charWidth,0, xOffset, yOffset);
		lowerMiddle = new Point(charWidth / 2,0, xOffset, yOffset);
		topMiddle = new Point(charWidth / 2,lineHeight, xOffset, yOffset);
	}
	
    public static void main(String[] args) throws Exception {
    	 
    	// 1. Make a new Robot
    	robot = new Robot();

    	robot.setSpeed(speed);
    	
    	String alphabet = "abcdefghijklmnopqrstuvwxyz";
    	String sentence = "the quick brown fox jumped over the lazy dog";
    	printWord(sentence);

    	robot.hide();
    }
    
    public static void printWord(String word) throws Exception {
    	
    	int windowWidth = 1500;
    	int windowHeight = 500;
    	Robot.setWindowSize(windowWidth,windowHeight);
    	
    	int numLetters = word.length();
    	char[] letters = new char[numLetters];
    	
    	for(int i = 0; i < numLetters; i++) {
    		letters[i] = word.charAt(i);
    	}
    	
    	// decide on letter sizing
    	int borderWidth = (int) windowWidth * 1/20;
//    	int border = 100;
    	System.out.println("border: " + borderWidth);
    	int availableSpace = windowWidth - (2 * borderWidth);
    	int spacePerLetter = (int) availableSpace / numLetters;
    	int letterToLetterGap = (int) (spacePerLetter * 0.25);
    	
    	// set global variables
    	charWidth = spacePerLetter - letterToLetterGap;
    	 
    	// neagtive lineHeight makes characters upright
    	lineHeight = - charWidth * 2;
    	
    	// initial X offset is the border
		xOffset = borderWidth;
		
		// vertically center the line
		yOffset = windowHeight / 2 - lineHeight / 2;
		
    	for(int i = 0; i < letters.length; i++) {
    		// reset global offsets before drawing each letter
    		redefineStandardPoints(xOffset, yOffset);
    		
    		char currentLetter =  letters[i];
    		if (currentLetter != ' ') {
        		// draw the letter
        		String methodName = "draw_" + currentLetter;
        		Method method = RobotGraffiti.class.getDeclaredMethod(methodName);
        		method.invoke(null);
    		}
    		
    		// increment border after drawing first letter
    		xOffset += spacePerLetter;

    	}
    }
    
    private static void draw_z() throws Exception {
    	drawLine(topLeft, topRight);
    	drawLine(topRight, lowerLeft);
    	drawLine(lowerLeft, lowerRight);
    }
    private static void draw_y() throws Exception {
    	Point yCrux = new Point(charWidth / 2,lineHeight * 5/8, xOffset, yOffset);
    	
    	drawLine(topLeft, yCrux);
    	drawLine(yCrux, topRight);
    	drawLine(yCrux, lowerMiddle);
    }
    private static void draw_x() throws Exception {
    	drawLine(topLeft, lowerRight);
    	drawLine(topRight, lowerLeft);
    }
    private static void draw_w() throws Exception {
    	Point bottomWLeft = new Point(charWidth * 1/4,0, xOffset, yOffset);
    	Point bottomWRight = new Point(charWidth * 3/4,0, xOffset, yOffset);
    	
    	drawLine(topLeft, bottomWLeft);
    	drawLine(bottomWLeft, topMiddle);
    	drawLine(topMiddle, bottomWRight);
    	drawLine(bottomWRight, topRight);
    }
    private static void draw_v() throws Exception {
    	drawLine(topLeft, lowerMiddle);
    	drawLine(lowerMiddle, topRight);
    }
	private static void draw_u() throws Exception {
		Point bottomUCurveStart = new Point(0,lineHeight*1/4, xOffset, yOffset);
		Point bottomUCurveEnd = new Point(charWidth,lineHeight*1/4, xOffset, yOffset);
		
    	Point[] bottomUCurveLeft = {bottomUCurveStart, lowerLeft, lowerLeft, lowerMiddle};
    	Point[] bottomUCurveRight = {lowerMiddle, lowerRight, lowerRight, bottomUCurveEnd};
    	
    	// draw lines and curves
    	drawLine(topLeft, bottomUCurveStart);
    	drawCurve(bottomUCurveLeft);
    	drawCurve(bottomUCurveRight);
    	drawLine(bottomUCurveEnd, topRight);
    }
    private static void draw_t() throws Exception {
		drawLine(topLeft, topRight);
		drawLine(topMiddle, lowerMiddle);
    }
	private static void draw_s() throws Exception {
		// 2 / \ 1
		// 3 \
		//     \
		// 5 \ / 4
		
		Point sCurveOneStart = new Point(charWidth,lineHeight*3/4, xOffset, yOffset);
		Point sCurveTwoEnd = new Point(0,lineHeight*3/4, xOffset, yOffset);
		Point sCurveFourStart = new Point(charWidth,lineHeight*1/4, xOffset, yOffset);
		Point sCurveFiveEnd = new Point(0,lineHeight*1/4, xOffset, yOffset);

    	Point[] curveOne = {sCurveOneStart, topRight, topRight, topMiddle};
    	Point[] curveTwo = {topMiddle, topLeft, topLeft, sCurveTwoEnd};
    	Point[] curveThree = {sCurveTwoEnd, middleLeft, middleRight, sCurveFourStart};
    	Point[] curveFour = {sCurveFourStart, lowerRight, lowerRight, lowerMiddle};
    	Point[] curveFive = {lowerMiddle, lowerLeft, lowerLeft, sCurveFiveEnd};
    	
    	// draw lines and curves
    	drawCurve(curveOne);
    	drawCurve(curveTwo);
    	drawCurve(curveThree);
    	drawCurve(curveFour);
    	drawCurve(curveFive);
    }
	
	private static void draw_r() throws Exception {
		Point topLobeApex = new Point(charWidth, lineHeight * 3/4, xOffset, yOffset);
		
    	Point[] topLobeTopCurve = {topLeft, topRight, topRight, topLobeApex};
    	Point[] topLobeBottomCurve = {topLobeApex, middleRight, middleRight, middleLeft};
    	
    	drawCurve(topLobeTopCurve);
    	drawCurve(topLobeBottomCurve);
    	drawLine(lowerLeft, topLeft);
    	drawLine(middleLeft, lowerRight);
    }
    
	private static void draw_q() throws Exception {
		Point qLineTop = new Point(charWidth * 3/4, lineHeight * 1/4, xOffset, yOffset);
		
		// define curves
    	Point[] topRightCurve = {middleRight, topRight, topRight, topMiddle};
    	Point[] topLeftCurve = {topMiddle, topLeft, topLeft, middleLeft};
    	Point[] bottomLeftCurve = {middleLeft, lowerLeft, lowerLeft, lowerMiddle};
    	Point[] bottomRightCurve = {lowerMiddle, lowerRight, lowerRight, middleRight};
    	
    	// draw lines and curves
    	drawCurve(topRightCurve);
    	drawCurve(topLeftCurve);
    	drawCurve(bottomLeftCurve);
    	drawCurve(bottomRightCurve);
    	drawLine(qLineTop, lowerRight);
    }
    
	private static void draw_p() throws Exception {
		Point topLobeApex = new Point(charWidth, lineHeight * 3/4, xOffset, yOffset);
		
    	Point[] topLobeTopCurve = {topLeft, topRight, topRight, topLobeApex};
    	Point[] topLobeBottomCurve = {topLobeApex, middleRight, middleRight, middleLeft};
    	
    	drawCurve(topLobeTopCurve);
    	drawCurve(topLobeBottomCurve);
    	drawLine(lowerLeft, topLeft);
    }
	
	private static void draw_o() throws Exception {
    	Point[] topRightCurve = {middleRight, topRight, topRight, topMiddle};
    	Point[] topLeftCurve = {topMiddle, topLeft, topLeft, middleLeft};
    	Point[] bottomLeftCurve = {middleLeft, lowerLeft, lowerLeft, lowerMiddle};
    	Point[] bottomRightCurve = {lowerMiddle, lowerRight, lowerRight, middleRight};
    	
    	drawCurve(topRightCurve);
    	drawCurve(topLeftCurve);
    	drawCurve(bottomLeftCurve);
    	drawCurve(bottomRightCurve);
    }
    
    private static void draw_n() throws Exception {
    	drawLine(lowerLeft, topLeft);
    	drawLine(topLeft, lowerRight);
    	drawLine(lowerRight, topRight);
    }
    
    private static void draw_m() throws Exception {
    	Point topMLeft = new Point(charWidth * 1/4,lineHeight, xOffset, yOffset);
    	Point topMRight = new Point(charWidth * 3/4,lineHeight, xOffset, yOffset);
    	drawLine(lowerLeft, topMLeft);
    	drawLine(topMLeft, lowerMiddle);
    	drawLine(lowerMiddle, topMRight);
    	drawLine(topMRight, lowerRight);
    }
    
    private static void draw_l() throws Exception {
    	drawLine(lowerLeft, topLeft);
    	drawLine(lowerLeft, lowerRight);
    }
    
    private static void draw_k() throws Exception {
    	drawLine(lowerLeft, topLeft);
    	drawLine(middleLeft, topRight);
    	drawLine(middleLeft, lowerRight);
    }
    
    private static void draw_j() throws Exception {
		float jHookHeight = lineHeight * 1/3;
		Point jHookMiddle = new Point(charWidth / 2,jHookHeight, xOffset, yOffset);
		Point jHookBottom = new Point(charWidth / 4, 0, xOffset, yOffset);
		Point jHookTip = new Point(0, jHookHeight, xOffset, yOffset);
		
		// draw lines and curves
		drawLine(topLeft, topRight);
		drawLine(topMiddle, jHookMiddle);
		
    	Point[] jHookCurveRight = {jHookMiddle, lowerMiddle, lowerMiddle, jHookBottom};
    	Point[] jHookCurveLeft = {jHookBottom, lowerLeft, lowerLeft, jHookTip};
    	drawCurve(jHookCurveRight);
    	drawCurve(jHookCurveLeft);
    }
    private static void draw_i() throws Exception {
    	drawLine(lowerLeft, lowerRight);
    	drawLine(topLeft, topRight);
    	drawLine(lowerMiddle, topMiddle);
    }
    
    private static void draw_h() throws Exception {
    	drawLine(lowerLeft, topLeft);
    	drawLine(middleLeft, middleRight);
    	drawLine(lowerRight, topRight);
    }
    
    private static void draw_g() throws Exception {
		float topTipHeight = lineHeight * 3/4;
		float bottomTipHeight = lineHeight * 1/4;
		Point topTipRight = new Point(charWidth,topTipHeight, xOffset, yOffset);
		Point bottomTipRight = new Point(charWidth,bottomTipHeight, xOffset, yOffset);
    	
    	Point[] topRightCurve = {topTipRight, topRight, topRight, topMiddle};
    	Point[] topLeftCurve = {topMiddle, topLeft, topLeft, middleLeft};
    	Point[] bottomLeftCurve = {middleLeft, lowerLeft, lowerLeft, lowerMiddle};
    	Point[] bottomRightCurve = {lowerMiddle, lowerRight, lowerRight, bottomTipRight};
    	
		Point innerGCorner = new Point(charWidth * 3/4,bottomTipHeight, xOffset, yOffset);
		Point innerGHookTip = new Point(charWidth * 3/4,bottomTipHeight * 3/4, xOffset, yOffset);
		
		// draw lines and curves
    	drawCurve(topRightCurve);
    	drawCurve(topLeftCurve);
    	drawCurve(bottomLeftCurve);
    	drawCurve(bottomRightCurve);
    	
		drawLine(innerGCorner, bottomTipRight);
		drawLine(innerGCorner, innerGHookTip);
    }
    
    private static void draw_f() throws Exception {
    	drawLine(lowerLeft, topLeft);
    	drawLine(topLeft, topRight);
    	drawLine(middleLeft, middleRight);
    }
    
    private static void draw_e() throws Exception {
    	drawLine(lowerLeft, topLeft);
    	drawLine(topLeft, topRight);
    	drawLine(middleLeft, middleRight);
    	drawLine(lowerLeft, lowerRight);
    }
    
	private static void draw_d() throws Exception {
		Point[] topRightCurve = {topLeft, topRight, topRight, middleRight};
		Point[] lowerRightCurve = {middleRight, lowerRight, lowerRight, lowerLeft};
    	
    	drawLine(lowerLeft, topLeft);
    	drawCurve(topRightCurve);
    	drawCurve(lowerRightCurve);
    }
	
	private static void draw_c() throws Exception {
		Point topTipRight = new Point(charWidth,lineHeight * 3/4, xOffset, yOffset);
		Point bottomTipRight = new Point(charWidth,lineHeight * 1/4, xOffset, yOffset);
    	
    	Point[] topRightCurve = {topTipRight, topRight, topRight, topMiddle};
    	Point[] topLeftCurve = {topMiddle, topLeft, topLeft, middleLeft};
    	Point[] bottomLeftCurve = {middleLeft, lowerLeft, lowerLeft, lowerMiddle};
    	Point[] bottomRightCurve = {lowerMiddle, lowerRight, lowerRight, bottomTipRight};
    	
    	drawCurve(topRightCurve);
    	drawCurve(topLeftCurve);
    	drawCurve(bottomLeftCurve);
    	drawCurve(bottomRightCurve);
    }
	
	private static void draw_b() throws Exception {
		Point topLobeApex = new Point(charWidth, lineHeight * 3/4, xOffset, yOffset);
		Point bottomLobeApex = new Point(charWidth, lineHeight * 1/4, xOffset, yOffset);
		
    	Point[] topLobeTopCurve = {topLeft, topRight, topRight, topLobeApex};
    	Point[] topLobeBottomCurve = {topLobeApex, middleRight, middleRight, middleLeft};
    	
    	Point[] bottomLobeTopCurve = {middleLeft, middleRight, middleRight, bottomLobeApex};
    	Point[] bottomLobeBottomCurve = {bottomLobeApex, lowerRight, lowerRight, lowerLeft};
    	
    	drawLine(lowerLeft, topLeft);
    	drawCurve(topLobeTopCurve);
    	drawCurve(topLobeBottomCurve);
    	drawCurve(bottomLobeTopCurve);
    	drawCurve(bottomLobeBottomCurve);
    }
    
    private static void draw_a() throws Exception {
		Point middleALeft = new Point(charWidth / 4,lineHeight / 2, xOffset, yOffset);
		Point middleARight = new Point(charWidth * 3/4, lineHeight/2, xOffset, yOffset);
		
    	drawLine(lowerLeft, topMiddle);
    	drawLine(lowerRight, topMiddle);
    	drawLine(middleLeft, middleRight);
    }
    
    private static void drawCurve(Point[] controlPoints) throws Exception {
    	Point first = controlPoints[0];
    	Point second = controlPoints[1];
    	Point third = controlPoints[2];
    	Point fourth = controlPoints[3];
    	
    	robot.moveTo(first.x, first.y);
    	robot.penDown();
    	robot.cubicTo(second.x,second.y,third.x,third.y,fourth.x,fourth.y, false);
    }
    
    
    private static void drawLine(Point start, Point end) throws Exception {
    	// teleport to start of line
    	robot.moveTo((int) start.x, (int) start.y);
    	
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
    	int verticalDistance = (int) end.y - (int) start.y;
    	int horizontalDistance = (int) end.x - (int) start.x;
    	
//    	System.out.println("verticalDistance: " + verticalDistance);
//    	System.out.println("horizontalDistance: " + horizontalDistance);

    	// use atan2 to capture full 360 deg of rotation
    	double angleInRadians = Math.atan2(verticalDistance,horizontalDistance);
    	double angleInDegrees = Math.toDegrees(angleInRadians);
    	
    	double transformedAngle = angleInDegrees + angleTransform;
    	
    	return transformedAngle;
    }
    
    private static double calculateDistance(Point start, Point end) throws Exception {
    	int verticalDistance = (int) end.y - (int) start.y;
    	int horizontalDistance = (int) end.x - (int) start.x;
    	
    	double aSquared = Math.pow(verticalDistance, 2);
    	double bSquared = Math.pow(horizontalDistance, 2);
    	
    	double cSquared = aSquared + bSquared;
    	
    	double distance = Math.sqrt(cSquared);
    	
    	return distance;
    }
	
}



;
