package frc.robot.subsystems;

import edu.wpi.first.wpilibj.Spark;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
//import com.revrobotics.CANSparkMax;
//import com.revrobotics.CANSparkMaxLowLevel.MotorType;

//TODO: is "wheelmotor" a good name? (not imo, but ask walsh/taber/people)
public class WheelMotorSubsystem extends Subsystem {
	public Spark wheelMotor = new Spark(RobotMap.wheelMotorPort);
	//public PWMSparkMax wheelMotor2 = new PWMSparkMax(RobotMap.conveyerMotorPort);
	
	//TODO: rename, i dont like this method name
	public void wheelControl(double wheelSpeed) {
		wheelMotor.set(wheelSpeed);	
	}
	
	public double getSpeed() {
		//Returns current speed
		return wheelMotor.getSpeed();
	}
	
	@Override
	public void initDefaultCommand() {
	}
}
