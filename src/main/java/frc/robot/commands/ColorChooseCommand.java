package frc.robot.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.Robot;

public class ColorChooseCommand extends Command {
  private Timer timer = new Timer();
  private Timer safetyTimer = new Timer();
  private Timer startTimer = new Timer();
  private Timer endTimer = new Timer();
  int test = 0;
  public ColorChooseCommand() {
    requires(Robot.colorSensorSubsystem);
  }

  @Override
  protected void initialize() {
      timer.start();

    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kBlueTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kGreenTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kRedTarget);
    Robot.colorSensorSubsystem.m_colorMatcher.addColorMatch(Robot.colorSensorSubsystem.kYellowTarget);   
    Robot.oi.colorServo.setPosition(.80);
    startTimer.reset();
    startTimer.start();
  }

  @Override
  protected void execute() {
    if (startTimer.get() >= 1.5) {
      if (endTimer.get() <= 0.0) {
        if (safetyTimer.get() == 0.0) safetyTimer.start();
        Color detectedColor = Robot.oi.m_colorSensor.getColor();
        String colorString;
        ColorMatchResult match = Robot.colorSensorSubsystem.m_colorMatcher.matchClosestColor(detectedColor);

        if (Robot.colorChoice.getSelected()=="Red" || Robot.colorChoice.getSelected()=="Blue") {
          Robot.wheelMotorSubsystem.wheelControl(0.28);
        } else {
          Robot.wheelMotorSubsystem.wheelControl(0.35);
        }
        

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
      
        
        if (colorString == Robot.colorChoice.getSelected()) {
          safetyTimer.stop();
          if (safetyTimer.get() < .1) {
            Robot.wheelMotorSubsystem.wheelControl(0.0);
            endTimer.start();  
          }
          else {
            Robot.wheelMotorSubsystem.wheelControl(0.0);
            endTimer.start();
          }
        }

      } 
    }
    if (safetyTimer.get() >= 3.0) {
      Robot.wheelMotorSubsystem.wheelControl(0.0);
    }
  }


  @Override
  protected boolean isFinished() {
    //Stops the motor
    if (startTimer.get() > 1.5){
      if (Robot.wheelMotorSubsystem.getSpeed() == (0.0)) {
        endTimer.stop();
        endTimer.reset();
        return true;
      }
      else if (Robot.wheelMotorSubsystem.getSpeed() == (-0.3) && endTimer.get() > .30){
        endTimer.stop();
        endTimer.reset();
        return true;
      }
  }
    return false;
  }

  @Override
  protected void end() {
    Robot.wheelMotorSubsystem.wheelControl(0.0);
    Robot.oi.colorServo.setPosition(.1);
    startTimer.stop();
    safetyTimer.stop();
    safetyTimer.reset();
    timer.stop();
    timer.reset();
  }

  @Override
  protected void interrupted() {
    timer.stop();
    timer.reset();
  }
}
