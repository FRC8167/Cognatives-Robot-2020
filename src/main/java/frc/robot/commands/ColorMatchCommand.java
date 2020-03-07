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
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class ColorMatchCommand extends Command {
  Timer timer = new Timer();
  public ColorMatchCommand() {
    requires(Robot.colorSensorSubsystem);
  }

  @Override
  protected void initialize() {
    timer.start();
    //Adds RGB values from ColorSensorSubsystem
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kBlueTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kGreenTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kRedTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kYellowTarget);    

  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Color Match Command", timer.get());
    
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

    //Adds data to SmartDashboard
    SmartDashboard.putNumber("Red", detectedColor.red);
    SmartDashboard.putNumber("Green", detectedColor.green);
    SmartDashboard.putNumber("Blue", detectedColor.blue);
    SmartDashboard.putNumber("Confidence", match.confidence);
    SmartDashboard.putString("Detected Color", colorString);

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    timer.stop();
    timer.reset();
  }

  @Override
  protected void interrupted() {
    timer.stop();
    timer.reset();
  }
}
