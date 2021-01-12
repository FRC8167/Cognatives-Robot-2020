/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.														 */
/* Open Source Software - may be modified and shared by FRC teams. The code	 */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.																															 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;
import frc.robot.util.Colors;
import frc.robot.Robot;

//TODO: yes
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
		Color detectedColor = Robot.getRobot().getColorSensor().getColor();
		Colors color;
		ColorMatchResult match = Robot.getRobot().getColorSensor().m_colorMatcher.matchClosestColor(detectedColor);
		
		if (match.color == Robot.getRobot().getColorSensor().kBlueTarget) {
			color = Colors.Blue;
		} else if (match.color == Robot.getRobot().getColorSensor().kRedTarget) {
			color = Colors.Red;
		} else if (match.color ==Robot.getRobot().getColorSensor(). kGreenTarget) {
			color = Colors.Green;
		} else if (match.color == Robot.getRobot().getColorSensor().kYellowTarget) {
			color = Colors.Yellow;
		} else {
			color = Colors.Unknown;
		}

		//Adds data to SmartDashboard
		SmartDashboard.putNumber("Red", detectedColor.red);
		SmartDashboard.putNumber("Green", detectedColor.green);
		SmartDashboard.putNumber("Blue", detectedColor.blue);
		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", color.toString());

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
