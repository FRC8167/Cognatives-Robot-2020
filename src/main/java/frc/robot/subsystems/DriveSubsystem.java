package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.ManualDriveCommand;
import frc.robot.RobotMap;

public class DriveSubsystem extends Subsystem {
	//TODO: rename these variables, there will be no underscores in MY good christian java variable names
	//Establish Motor Control Methods
	private SpeedController m_frontLeft = new PWMVictorSPX(RobotMap.frontLeftPort);
	private SpeedController m_rearLeft = new PWMVictorSPX(RobotMap.rearLeftPort);
	
	private SpeedController m_frontRight = new PWMVictorSPX(RobotMap.frontRightPort);
	private SpeedController m_rearRight = new PWMVictorSPX(RobotMap.rearRightPort);
	
	//group each side's motors together and use those
	//TODO: do we need these public? they shouldnt be directly accessed outside of the DifferentialDrive
	public SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
	public SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	
	//Instate Differential Drive
	public DifferentialDrive m_robotDrive = new DifferentialDrive(m_left, m_right);
	
	public void teleopDrive(double yValue, double xValue) {
		//TODO: use better parameter names, how tf is "xValue" a good variable name
		m_robotDrive.arcadeDrive(yValue, xValue);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ManualDriveCommand());
	}
}
