/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import frc.robot.commands.WheelMotorCommand;

/**
 * Add your docs here.
 */
public class WheelMotorSubsystem extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.
  public Spark wheelMotor = new Spark(RobotMap.wheelMotorPort);

  public void wheelControl(double wheelSpeed) {
    wheelMotor.set(wheelSpeed);
  }

  public double getSpeed() {
    return wheelMotor.getSpeed();
  }


  @Override
  public void initDefaultCommand() {
    //setDefaultCommand(new WheelMotorCommand());
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
  }
}
