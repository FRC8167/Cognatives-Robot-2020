package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class WheelMotorSubsystem extends Subsystem {
  public Spark wheelMotor = new Spark(RobotMap.wheelMotorPort);

  public void wheelControl(double wheelSpeed) {
    wheelMotor.set(wheelSpeed);
  }

  public double getSpeed() {
    return wheelMotor.getSpeed();
  }


  @Override
  public void initDefaultCommand() {
  }
}
