/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

//import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class QuickTurnCommand extends Command {
  double initAngle;
  public QuickTurnCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //this.initAngle = initAngle;
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    initAngle = Robot.oi.gyro.getAngle();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    int pov = Robot.oi.stick.getPOV();
    if (pov == 270.000) {
      Robot.driveSubsystem.m_right.set(-0.55);
      Robot.driveSubsystem.m_left.set(-0.55);
    } else if (pov == 90.000) {
      Robot.driveSubsystem.m_right.set(0.55);
      Robot.driveSubsystem.m_left.set(0.55);
    } else if (pov == 180.000) {
      Robot.driveSubsystem.m_right.set(0.56);
      Robot.driveSubsystem.m_left.set(0.56);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    double currentAngle = (Robot.oi.gyro.getAngle());
    double scale = 100.0;
    double leftSpeed = Math.round(Robot.driveSubsystem.m_left.get() * scale) / scale;
    double rightSpeed = Math.round(Robot.driveSubsystem.m_right.get() * scale) / scale;
    if (rightSpeed == 0.56 && leftSpeed == 0.56){
      if (currentAngle >= initAngle + 125) {
        return true;
      }
    }

    else if (rightSpeed == -0.55 && leftSpeed == -0.55){
      if (currentAngle <= initAngle - 45) {
        return true;
      }
    }

    else if (rightSpeed == 0.55 && leftSpeed == 0.55){
      if (currentAngle >= initAngle + 40) {
        return true;
      }
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    Robot.driveSubsystem.m_left.set(0);
    Robot.driveSubsystem.m_right.set(0);
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
