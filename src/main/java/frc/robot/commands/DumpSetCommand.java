package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DumpSetCommand extends Command {
  double actuatorSpeed;
  
  public DumpSetCommand(final double speed) {
    this.actuatorSpeed = speed;
  }

  @Override
  protected void initialize() {
    Robot.oi.dumpActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    Robot.oi.dumpActuator.setSpeed(this.actuatorSpeed);
  }

  @Override
  protected void execute() {
  }

  @Override
  protected boolean isFinished() {
    return true;
  }

  @Override
  protected void end() {
  }
  
  @Override
  protected void interrupted() {
  }
}