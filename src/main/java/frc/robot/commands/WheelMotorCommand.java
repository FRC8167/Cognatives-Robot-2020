package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WheelMotorCommand extends Command {
  private double wheelSpeed;

  public WheelMotorCommand(double wheelSpeed) {
    this.wheelSpeed = wheelSpeed;
    requires(Robot.wheelMotorSubsystem);
  }

  @Override
  protected void initialize() {
  }

  @Override
  protected void execute() {
    Robot.wheelMotorSubsystem.wheelControl(this.wheelSpeed);
  }
  
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    Robot.wheelMotorSubsystem.wheelControl(0.0);
  }

  @Override
  protected void interrupted() {
    Robot.wheelMotorSubsystem.wheelControl(0.0);
  }

}
