/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
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

  //Joystick
  public static int joystickPort = (0);

  //Motors
  public static int frontLeftPort = (1);
  public static int rearLeftPort = (2);
  public static int frontRightPort = (3);
  public static int rearRightPort = (4);
  
  public static int wheelMotorPort = (5);

  //Servos
  public static int dumpActuatorPort = (6);


  //Buttons
  public static int dumpButtonNumber = (2);
  public static int wheelClockwiseButtonNumber = (7);
  public static int wheelAntiClockwiseButtonNumber = (8);
  public static int colorDetectButtonNumber = (9);
}

 
