package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;
import frc.robot.RobotMap;

@Deprecated
public class DumpActuator extends Subsystem {
	private final Servo actuator = new Servo(RobotMap.dumpActuatorPort);
	
	public DumpActuator() {
		actuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
		actuator.setPosition(1.0);
	}
	
	/**
	 * TODO: test this
	 * 
	 * @param position - the position to set the actuator to, between -1 and 1
	 */
	public void setPosition(double position) {
		actuator.setSpeed(position);
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
