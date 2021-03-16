package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class ClimbMotor extends Subsystem {
	private Spark colorWheelMotor = new Spark(RobotMap.climbMotorPort);
	//TODO: documentation

	/**
	 * @param speed 
	 */
	public void setSpeed(double speed) {
		colorWheelMotor.set(speed);	
	}
	
	/**
	 * @return 
	 */
	public double getSpeed() {
		return colorWheelMotor.getSpeed();
	}
	
	@Override
	public void initDefaultCommand() {
	}
}
