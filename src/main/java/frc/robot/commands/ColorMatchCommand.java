/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.														 */
/* Open Source Software - may be modified and shared by FRC teams. The code	 */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.																															 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

//TODO: THIS ENTIRE COMMAND LITERALLY DOES NOTHING EXCEPT UPDATe THE SHUFFLEBOARD WITH DEBUG VALUES WHAT THE HELL
public class ColorMatchCommand extends Command {
	public ColorMatchCommand() {
		requires(Robot.getRobot().getColorSensor());
	}
	
	@Override
	protected void initialize() {
		//Adds RGB values from ColorSensorSubsystem
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kBlueTarget);
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kGreenTarget);
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kRedTarget);
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kYellowTarget);
	}
	
	@Override
	protected void execute() {
		Robot.getRobot().getColorSensor().getClosestColor();
		
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		
	}

	@Override
	protected void interrupted() {

	}
}
