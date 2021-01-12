package frc.robot.commands;

import com.revrobotics.ColorMatchResult;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.util.Colors;
import frc.robot.Robot;

//TODO: COMPLETELY REDO THIS, WHY ARE THERE LIKE 5 TIMERS THOSE USE CPU THREADS
public class ColorChooseCommand extends Command {
	private static Timer safetyTimer = new Timer();
	private static Timer startTimer = new Timer();
	private static Timer endTimer = new Timer();
	
	public ColorChooseCommand() {
		requires(Robot.getRobot().colorSensor);
	}
	
	@Override
	protected void initialize() {
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kBlueTarget);
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kGreenTarget);
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kRedTarget);
		Robot.robotInstance.colorSensor.m_colorMatcher.addColorMatch(Robot.robotInstance.colorSensor.kYellowTarget);	 
		Robot.robotInstance.outputs.colorServo.setPosition(.70);
		startTimer.reset();
		startTimer.start();
	}

	@Override
	protected void execute() {
		if (startTimer.get() >= 0.75) {
			if (endTimer.get() <= 0.0) {
				if (safetyTimer.get() == 0.0) safetyTimer.start();
				Color detectedColor = Robot.robotInstance.outputs.m_colorSensor.getColor();
				Colors color;
				ColorMatchResult match = Robot.robotInstance.colorSensor.m_colorMatcher.matchClosestColor(detectedColor);

				Robot.robotInstance.colorWheelMotor.wheelMotorControl(0.35);

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
				
				SmartDashboard.putNumber("Red", detectedColor.red);
				SmartDashboard.putNumber("Green", detectedColor.green);
				SmartDashboard.putNumber("Blue", detectedColor.blue);
				SmartDashboard.putNumber("Confidence", match.confidence);
				SmartDashboard.putString("Detected Color", color.toString());
				
				if (color.toString().equals(Robot.robotInstance.colorChoice.getSelected())) {
					safetyTimer.stop();
					if (safetyTimer.get() < .3) {
						Robot.robotInstance.colorWheelMotor.wheelMotorControl(0.0);
						endTimer.start();
					}
					else {
						Robot.robotInstance.colorWheelMotor.wheelMotorControl(-0.15);
						endTimer.start();
					}
				}
			} 
		}
		if (safetyTimer.get() >= 3.0) {
			Robot.robotInstance.colorWheelMotor.wheelMotorControl(0.0);
		}
	}


	@Override
	protected boolean isFinished() {
		//Stops the motor
		if (startTimer.get() > 1.5){
			if (Robot.robotInstance.colorWheelMotor.getWheelMotorSpeed() == (0.0))	{
				endTimer.stop();
				endTimer.reset();
				return true;
			}
			else if (Robot.robotInstance.colorWheelMotor.getWheelMotorSpeed() == -0.15 && endTimer.get() > 0.2){
				endTimer.stop();
				endTimer.reset();
				return true;
			}
		}
		return false;
	}

	@Override
	protected void end() {
		Robot.robotInstance.colorWheelMotor.wheelMotorControl(0.0);
		Robot.robotInstance.outputs.colorServo.setPosition(.1);
		startTimer.stop();
		safetyTimer.stop();
		safetyTimer.reset();
	}

	@Override
	protected void interrupted() {
		
	}
}
