package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DumpSetCommand extends Command {
  double actuatorSpeed;
  Timer timer = new Timer();
  
  public DumpSetCommand(final double speed) {
    //Speed = position for actuators 
    this.actuatorSpeed = speed;
  }

  @Override
  protected void initialize() {
    //I do not know what setBounds does, but you need it
    Robot.oi.dumpActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
    Robot.oi.dumpActuator.setSpeed(this.actuatorSpeed);
    timer.start();
  }

  @Override
  protected void execute() {
    SmartDashboard.putNumber("Dump Set Command", timer.get());
  }

  @Override
  protected boolean isFinished() {
    return true;
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