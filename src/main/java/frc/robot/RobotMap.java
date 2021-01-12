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
	//remove parenthesis, conner is not here -Tyler
	
	//Joystick
	public static final int joystickPort = 0;
	
	//Motors
	public static final int frontLeftPort = 1;
	public static final int rearLeftPort = 2;
	public static final int frontRightPort = 3;
	public static final int rearRightPort = 4;
	
	public static final int colorServoPort = 5;
	public static final int wheelMotorPort = 9;
	
	//USB ports
	public static final int cameraPort = 0;
	
	//Servos
	public static final int dumpActuatorPort = 6;
	public static final int cameraServoPort = 7;
	
	//Buttons
	public static final int dumpButtonNumber = 1;
	public static final int loadButtonNumber = 3; //Added by nick to make it goto the load point
	public static final int reverseButtonNumber = 4;
	public static final int ultrasonicSensorButtonNumber = 5; //TODO: do we need this? didnt the Ult Sensor break?
	public static final int wheelClockwiseButtonNumber = 7;
	public static final int wheelAntiClockwiseButtonNumber = 8;
	public static final int colorDetectButtonNumber = 9;
	public static final int servoCameraButtonNumber = 10;
	public static final int turn90DegreesButtonNumber = 11;
	
	//Speed Values for QuickTurnCommand
	public static final double turnLeftValue = -.75;
	public static final double turnRightValue = .75;
	public static final double turn180Value = .76;
	
	//idk what this is but its never used
	//public static final double kValueToCM = 9.77;
	//public static final double kValueToInches = .125;
}


 
