package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.ManualDriveCommand;

public class DriveSubsystem extends Subsystem {
	//Establish Motor Control Methods
	public SpeedController m_frontLeft = new PWMVictorSPX(RobotMap.frontLeftPort);
	public SpeedController m_rearLeft = new PWMVictorSPX(RobotMap.rearLeftPort);
	public SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
	
	public SpeedController m_frontRight = new PWMVictorSPX(RobotMap.frontRightPort);
	public SpeedController m_rearRight = new PWMVictorSPX(RobotMap.rearRightPort);
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
