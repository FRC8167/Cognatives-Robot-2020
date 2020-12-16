/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.												*/
/* Open Source Software - may be modified and shared by FRC teams. The code	 */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.																															 */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap {
	//I have no idea why we have parentheses around the numbers, but whatever
	//Stop judging me it looks cool -Conner
	//TODO: remove parenthesis, conner is not here -Tyler
	//TODO: make these all final, maybe even into separate enums or sm
	
	//Joystick
	public static int joystickPort = (0);
	
	//Motors
	public static int frontLeftPort = (1);
	public static int rearLeftPort = (2);
	public static int frontRightPort = (3);
	public static int rearRightPort = (4);
	
	public static int wheelMotorPort = (9);
	public static int colorServoPort = (5);
	
	//Servos
	public static int dumpActuatorPort = (6);
	public static final int ultrasonicPort = (0);
	public static int cameraServoPort = (7);
	
	//Buttons
	public static int dumpButtonNumber = (1);
	public static int loadButtonNumber = (3); //Added by nick to make it goto the load point
	public static int reverseButtonNumber = (4);
	public static int ultrasonicSensorButtonNumber = (5); //TODO: do we need this? didnt the UltS break?
	public static int wheelClockwiseButtonNumber = (7);
	public static int wheelAntiClockwiseButtonNumber = (8);
	public static int colorDetectButtonNumber = (9);
	//public static int servoButtonNumber = (5);
	public static int turn90DegreesButtonNumber = (11);
	public static int servoCameraButtonNumber = (10);
	
	//Speed Values for QuickTurnCommand
	public static double turnLeftValue = (-.75);
	public static double turnRightValue = (.75);
	public static double turn180Value = (.76);
	
	//TODO: what is this?
	public static final double kValueToCM = 9.77;
	public static final double kValueToInches = .125;
}


 
