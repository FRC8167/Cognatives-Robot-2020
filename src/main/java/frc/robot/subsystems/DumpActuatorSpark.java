package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;

// import com.revrobotics.CANEncoder;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import frc.robot.RobotMap;

public class DumpActuatorSpark extends Subsystem {
    public final CANSparkMax actuatorController = new CANSparkMax(RobotMap.DumpActuatorCANId, MotorType.kBrushed);// RobotMap.dumpActuatorPort);
    //private final CANEncoder actuatorEncoder = new CANEncoder(actuatorController);

    public DumpActuatorSpark() {
        
    }

    public void setSpeed(double speed) {
        actuatorController.set(speed);
    }

    public void setPosition(double position) {
        //TODO
    }

    public double getSpeed() {
        return actuatorController.get();
    }

    @Override
    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        // setDefaultCommand(new MySpecialCommand());
    }
}