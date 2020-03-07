/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

//import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

public class QuickTurnCommand extends Command {
  double initAngle;
  String direction;
  private Timer timer = new Timer();
  public QuickTurnCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //this.initAngle = initAngle;
    requires(Robot.driveSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    direction = "unestablished";
    initAngle = Robot.oi.gyro.getAngle();
    timer.reset();
    timer.start();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {
    //Finds what direction you want and sets the motors to turn that direction
    int pov = Robot.oi.stick.getPOV();
    if (pov == 270.000) {
      direction = "left";
      Robot.driveSubsystem.teleopDrive(0, RobotMap.turnLeftValue);
    }
    else if (pov == 90.000){
      direction = "right";
      Robot.driveSubsystem.teleopDrive(0, RobotMap.turnRightValue);
    } 
    else if (pov == 180.000) {
      direction = "back";
      Robot.driveSubsystem.teleopDrive(0, RobotMap.turn180Value);
    }
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    //If the code runs for two seconds, end
    if (timer.get() >= 2) return true;

    double currentAngle = (Robot.oi.gyro.getAngle());

    //double scale = 100.0;
    //Rounds speed to two decimals, I know there are more efficient ways to do this but this was easy
    //double leftSpeed = Math.round(Robot.driveSubsystem.m_left.get() * scale) / scale;
    //double rightSpeed = Math.round(Robot.driveSubsystem.m_right.get() * scale) / scale;

    //Checks what direction you chose by using the speed and ends if the desired angle is met
    if (direction == "back"){
      if (currentAngle >= initAngle + 125) {
        return true;
      }
    }

    else if (direction == "left"){
      if (currentAngle <= initAngle - 45) {
        return true;
      }
    }

    else if (direction == "right"){
      if (currentAngle >= initAngle + 40) {
        return true;
      }
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
    //Returns motors to stopped and ends the timer
    Robot.driveSubsystem.teleopDrive(0, 0);    
    timer.stop();
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
