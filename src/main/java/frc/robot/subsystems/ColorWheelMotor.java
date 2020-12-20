package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

public class ColorWheelMotor extends Subsystem {
	private Spark colorWheelMotor = new Spark(RobotMap.wheelMotorPort);
	
	/**
	 * Controls the color wheel motor, and spins it at the given speed.
	 * 
	 * @param wheelSpeed	the speed of the color wheel motor, in 
	 * 						between -1.0 and 1.0, with 1.0 being full 
	 * 						speed clockwise. //TODO: verify
	 */
	public void wheelMotorControl(double wheelSpeed) {
		colorWheelMotor.set(wheelSpeed);	
	}
	
	/**
	 * gets the current color wheel motor speed.
	 * 
	 * @return	the current speed of the motor.
	 */
	public double getWheelMotorSpeed() {
		return colorWheelMotor.getSpeed();
	}
	
	@Override
	public void initDefaultCommand() {
	}
}
