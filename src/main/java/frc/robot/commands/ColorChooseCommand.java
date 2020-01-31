/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
//import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class ColorChooseCommand extends Command {
  private Timer timer = new Timer();
  public ColorChooseCommand() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    requires(Robot.colorSensorSubsystem);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kBlueTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kGreenTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kRedTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kYellowTarget);   
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() {

    Robot.wheelMotorSubsystem.wheelControl(1.0);

    Color detectedColor = Robot.oi.m_colorSensor.getColor();
    String colorString;
    ColorMatchResult match = Robot.colorSensorSubsystem.m_colorMatcher.matchClosestColor(detectedColor);

    if (match.color == Robot.colorSensorSubsystem.kBlueTarget) {
      colorString = "Blue";
    } else if (match.color == Robot.colorSensorSubsystem.kRedTarget) {
      colorString = "Red";
    } else if (match.color ==Robot.colorSensorSubsystem. kGreenTarget) {
      colorString = "Green";
    } else if (match.color == Robot.colorSensorSubsystem.kYellowTarget) {
      colorString = "Yellow";
    } else {
      colorString = "Unknown";
    }

    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);
  
    //SmartDashboard.putNumber("Time Non Interacted", timer.get());
    if (colorString == Robot.colorChoice.getSelected() && timer.get() <= 0.0) {
      timer.start();
      //SmartDashboard.putNumber("Time", timer.get());
    }
    if (timer.get() > (0.4) && colorString == Robot.colorChoice.getSelected()) {
      //SmartDashboard.putNumber("Time 3", timer.get());
      timer.stop();
      timer.reset();
      Robot.wheelMotorSubsystem.wheelControl(0.0);    }
    else if (timer.get() > 0.4 && colorString != Robot.colorChoice.getSelected()) {
      //SmartDashboard.putNumber("Time 2", timer.get());
      timer.stop();
      timer.reset();
    }
  }


  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    if (Robot.wheelMotorSubsystem.getSpeed() == (0.0)){
      return true;
    }
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }
}
