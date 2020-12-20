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
		Robot.robotInstance.camera.togglePosition();
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
