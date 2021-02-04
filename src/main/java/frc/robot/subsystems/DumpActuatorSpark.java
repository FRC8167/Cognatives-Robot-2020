package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.PWMSpeedController;
import edu.wpi.first.hal.FRCNetComm.tResourceType;
import edu.wpi.first.hal.HAL;
import edu.wpi.first.wpilibj.smartdashboard.SendableRegistry;
import frc.robot.RobotMap;

/**
 * this class only exists because some idiot at RevRobotics didnt set the constructor to public
 * so i had to copy paste this class from com.revrobotics.SparkMax into here and change it
 */
class SparkMax extends PWMSpeedController {
    public SparkMax(final int channel) {
      super(channel);

      setBounds(2.003, 1.55, 1.50, 1.46, 0.999);
      setPeriodMultiplier(PeriodMultiplier.k1X);
      setSpeed(0.0);
      setZeroLatch();

       HAL.report(tResourceType.kResourceType_RevSparkMaxPWM, getChannel());

      SendableRegistry.setName(this, "SparkMax", channel);
    }
  }

public class DumpActuatorSpark extends Subsystem {
    private final SparkMax actuatorController = new SparkMax(RobotMap.dumpActuatorPort);
    
    @Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}