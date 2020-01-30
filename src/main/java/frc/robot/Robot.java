/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WheelMotorSubsystem;

//Github Test

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends TimedRobot {

  private final Timer m_timer = new Timer();
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static ColorSensorSubsystem colorSensorSubsystem = new ColorSensorSubsystem();
  public static WheelMotorSubsystem wheelMotorSubsystem = new WheelMotorSubsystem();
  public static  OI oi = new OI();

  public static SendableChooser<String> colorChoice = new SendableChooser<String>();


    
  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() {
    colorChoice.setDefaultOption("Yellow", "Yellow");
    colorChoice.addOption("Green", "Green");
    colorChoice.addOption("Red", "Red");
    colorChoice.addOption("Blue", "Blue");
    SmartDashboard.putData("Choose Your Color", colorChoice);
    SmartDashboard.updateValues();
  }

  /**
   * This function is run once each time the robot enters autonomous mode.
   */
  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() {
	// Drive for 2 seconds
    if (m_timer.get() < 5.0) {
      Robot.driveSubsystem.m_robotDrive.arcadeDrive(0.5, 0.0); // drive forwards half speed
    } else {
      Robot.driveSubsystem.m_robotDrive.stopMotor(); // stop robot
    }
  }

  /**
   * This function is called once each time the robot enters teleoperated mode.
   */
  @Override
  public void teleopInit() {
  }

	/**
	 * This function is called periodically during operator control.
	 */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    
    //Color Sensor Code -----------------------------------------------------------------------------------------

    //Latch code------------------------------------------------------------------------------------------------
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
  
}
