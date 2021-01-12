package frc.robot.subsystems;

import edu.wpi.first.wpilibj.PWMVictorSPX;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import frc.robot.commands.ManualDriveCommand;
import frc.robot.RobotMap;

public class DriveSubsystem extends Subsystem {
	//motor controllers
	private SpeedController frontLeftMotorController = new PWMVictorSPX(RobotMap.frontLeftPort);
	private SpeedController rearLeftMotorController = new PWMVictorSPX(RobotMap.rearLeftPort);
	
	private SpeedController frontRightMotorController = new PWMVictorSPX(RobotMap.frontRightPort);
	private SpeedController rearRightMotorController = new PWMVictorSPX(RobotMap.rearRightPort);
	
	//group each side's motors together and use those
	private SpeedControllerGroup leftMotors = new SpeedControllerGroup(frontLeftMotorController, rearLeftMotorController);
	private SpeedControllerGroup rightMotors = new SpeedControllerGroup(frontRightMotorController, rearRightMotorController);
	
	//Instate Differential Drive
	private DifferentialDrive robotDifferentialDrive = new DifferentialDrive(leftMotors, rightMotors);
	
	public DriveSubsystem() {
		this.robotDifferentialDrive.setExpiration(1.0);
	}
	
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
	
	/**
	 * Stops the robot's motors.
	 */
	public void stopMotors() {
		robotDifferentialDrive.stopMotor();
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ManualDriveCommand());
	}
}
