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
		requires(Robot.robotInstance.colorSensor);
	}
	
	@Override
	protected void initialize() {
		//Adds RGB values from ColorSensorSubsystem
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kBlueTarget);
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kGreenTarget);
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kRedTarget);
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kYellowTarget);
	}
	
	@Override
	protected void execute() {
		Color detectedColor = Robot.robotInstance.outputs.m_colorSensor.getColor();
		Colors color;
		ColorMatchResult match = Robot.robotInstance.colorSensor.m_colorMatcher.matchClosestColor(detectedColor);

		if (match.color == Robot.robotInstance.colorSensor.kBlueTarget) {
			color = Colors.Blue;
		} else if (match.color == Robot.robotInstance.colorSensor.kRedTarget) {
			color = Colors.Red;
		} else if (match.color ==Robot.robotInstance.colorSensor. kGreenTarget) {
			color = Colors.Green;
		} else if (match.color == Robot.robotInstance.colorSensor.kYellowTarget) {
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
