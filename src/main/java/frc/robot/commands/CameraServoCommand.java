/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.														 */
/* Open Source Software - may be modified and shared by FRC teams. The code	 */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.																															 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class CameraServoCommand extends Command {
	/**
	 * Creates a new CameraServoCommand, which toggles the current camera direction.
	 */
	public CameraServoCommand() {
		
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		//TODO: make a camera subsystem
		if (Robot.robotInstance.outputs.cameraServo.getPosition() > 0.9){
			Robot.robotInstance.outputs.cameraServo.setPosition(0);
		}
		else if (Robot.robotInstance.outputs.cameraServo.getPosition() <= .1){
			Robot.robotInstance.outputs.cameraServo.setPosition(1);
		}
	}
	
	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
	}
	
	@Override
	protected void interrupted() {
	}
}
