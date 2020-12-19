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
	//Establish Motor Control Variables
	private SpeedController m_frontLeft = new PWMVictorSPX(RobotMap.frontLeftPort);
	private SpeedController m_rearLeft = new PWMVictorSPX(RobotMap.rearLeftPort);
	
	private SpeedController m_frontRight = new PWMVictorSPX(RobotMap.frontRightPort);
	private SpeedController m_rearRight = new PWMVictorSPX(RobotMap.rearRightPort);
	
	//group each side's motors together and use those
	private SpeedControllerGroup m_left = new SpeedControllerGroup(m_frontLeft, m_rearLeft);
	private SpeedControllerGroup m_right = new SpeedControllerGroup(m_frontRight, m_rearRight);
	
	//Instate Differential Drive
	//TODO: should this be public? it really shouldnt be directly accessed outside of the DifferentialDrive class
	public DifferentialDrive robotDifferentialDrive = new DifferentialDrive(m_left, m_right);
	
	/**
	 * Drives the robot.
	 * 
	 * @param forwardSpeed	the robot's forward speed, between -1.0 (full backwards speed) 
	 * 						and 1.0 (full forwards speed).
	 * @param rotationSpeed	the robot's rotation speed, between -1.0 and 1.0, 
	 * 						with positive values being clockwise.
	 */
	public void drive(double forwardSpeed, double rotationSpeed) {
		robotDifferentialDrive.arcadeDrive(forwardSpeed, rotationSpeed, false);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ManualDriveCommand());
	}
}
