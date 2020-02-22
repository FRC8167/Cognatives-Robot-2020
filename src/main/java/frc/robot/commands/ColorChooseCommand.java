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
    requires(Robot.colorSensorSubsystem);
  }

  @Override
  protected void initialize() {
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kBlueTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kGreenTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kRedTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kYellowTarget);   
  }

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
  
    //If color equals chosen color, stop after .4 seconds (the time it takes to center the color)
    if (colorString == Robot.colorChoice.getSelected() && timer.get() <= 0.0) {
      timer.start();
    }
    if (timer.get() > (0.4) && colorString == Robot.colorChoice.getSelected()) {
      timer.stop();
      timer.reset();
      Robot.wheelMotorSubsystem.wheelControl(0.0);    }
    else if (timer.get() > 0.4 && colorString != Robot.colorChoice.getSelected()) {
      timer.stop();
      timer.reset();
    }
  }


  @Override
  protected boolean isFinished() {
    //Stops the motor
    if (Robot.wheelMotorSubsystem.getSpeed() == (0.0)){
      return true;
    }
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
