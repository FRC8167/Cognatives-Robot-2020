/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDriveCommand;

/**
 * Add your docs here.
 */
public class DriveSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  //Establish Motor Control Methods
  public SpeedController m_frontLeft = new PWMVictorSPX(RobotMap.frontLeftPort);
  public SpeedController m_rearLeft = new PWMVictorSPX(RobotMap.rearLeftPort);
  public SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);

  public SpeedController m_frontRight = new PWMVictorSPX(RobotMap.frontRightPort);
  public SpeedController m_rearRight = new PWMVictorSPX(RobotMap.rearRightPort);
  public SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);

  //Instate Differential Drive
  public DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);

  //teleopDrive() method
  public void teleopDrive(double yValue, double xValue) {
    m_robotDrive.arcadeDrive(yValue, xValue);
  }

  @Override
  public void initDefaultCommand() {
    // Set the default command for a subsystem here.
    setDefaultCommand(new ManualDriveCommand());
  }
}
