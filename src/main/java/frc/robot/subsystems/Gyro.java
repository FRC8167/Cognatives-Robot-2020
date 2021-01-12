package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;

public class Gyro extends Subsystem {
	private final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	
	public Gyro() {
		gyro.calibrate();
	}
	
	public double getAngle() {
		return gyro.getAngle();
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
