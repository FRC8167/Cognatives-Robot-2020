package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;


public class ManualDriveCommand extends Command {
  public ManualDriveCommand() {
    requires(Robot.driveSubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    //uses the slider to get the sensitivity of the robot. sensitivity is in range [0.3, 1].
    double throttle = Robot.oi.stick.getThrottle();
    double sensitivity =  (1 - (throttle + 1) * 0.35);
    //getY is negative because the joystick reads backwards
    double yValue = -Robot.oi.stick.getY() * sensitivity;
    double xValue = Robot.oi.stick.getX() * sensitivity;
    if (Robot.oi.reverseButton.get() == true) {
      Robot.driveSubsystem.teleopDrive(-yValue, xValue);
    }
    else {
      Robot.driveSubsystem.teleopDrive(yValue, xValue);
    }

  }

  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
  }

  @Override
  protected void interrupted() {
  }
}
