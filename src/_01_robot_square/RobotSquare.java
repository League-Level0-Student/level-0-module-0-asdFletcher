package _01_robot_square;
/*
 *    Copyright (c) The League of Amazing Programmers 2013-2017
 *    Level 0
 */

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

// This recipe draws a square using the Robot

public class RobotSquare {
    public static void main(String[] args) throws Exception {
 
    	// 1. Make a new Robot
    	Robot myRobot = new Robot();

		
        // 3. Put the robot's pen down
    	myRobot.penDown();


        // 6. Make the robot move as fast as possible
    	myRobot.setSpeed(10000);
    	

        // 5. Do everything below here 4 times


        //         2. Move your robot 200 pixels


        //         4. Turn the robot 90 degrees to the right (90 degrees)
    	myRobot.move(200);
    	myRobot.turn(90);
    	
    	myRobot.move(200);
    	myRobot.turn(90);
    	
    	myRobot.move(200);
    	myRobot.turn(90);
    	
    	myRobot.move(200);
    	myRobot.turn(90);

    }
}
