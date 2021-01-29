package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;

import frc.robot.util.Colors;
import frc.robot.Robot;

//TODO: COMPLETELY REDO THIS, WHY ARE THERE LIKE 5 TIMERS THOSE USE CPU THREADS
public class ColorChooseCommand extends Command {
	private static Timer safetyTimer = new Timer();
	private static Timer startTimer = new Timer();
	private static Timer endTimer = new Timer();
	
	public ColorChooseCommand() {
		requires(Robot.getRobot().getColorSensor());
	}
	
	@Override
	protected void initialize() {
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kBlueTarget);
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kGreenTarget);
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kRedTarget);
		Robot.getRobot().getColorSensor().m_colorMatcher.addColorMatch(Robot.getRobot().getColorSensor().kYellowTarget);	 
		Robot.getRobot().getColorSensor().setServoPosition(.70);
		startTimer.reset();
		startTimer.start();
	}

	@Override
	protected void execute() {
		if (startTimer.get() >= 0.75) {
			if (endTimer.get() <= 0.0) {
				if (safetyTimer.get() == 0.0) safetyTimer.start();
				
				Colors color = Robot.getRobot().getColorSensor().getClosestColor();
				
				if (color.equals(Robot.getRobot().getSelectedColor())) {
					safetyTimer.stop();
					if (safetyTimer.get() < .3) {
						Robot.getRobot().getColorWheelMotor().wheelMotorControl(0.0);
						endTimer.start();
					}
					else {
						Robot.getRobot().getColorWheelMotor().wheelMotorControl(-0.15);
						endTimer.start();
					}
				}
			} 
		}
		if (safetyTimer.get() >= 3.0) {
			Robot.getRobot().getColorWheelMotor().wheelMotorControl(0.0);
		}
	}


	@Override
	protected boolean isFinished() {
		//Stops the motor
		if (startTimer.get() > 1.5){
			if (Robot.getRobot().getColorWheelMotor().getWheelMotorSpeed() == (0.0))	{
				endTimer.stop();
				endTimer.reset();
				return true;
			}
			else if (Robot.getRobot().getColorWheelMotor().getWheelMotorSpeed() == -0.15 && endTimer.get() > 0.2){
				endTimer.stop();
				endTimer.reset();
				return true;
			}
		}
		return false;
	}

	@Override
	protected void end() {
		Robot.getRobot().getColorWheelMotor().wheelMotorControl(0.0);
		Robot.getRobot().getColorSensor().setServoPosition(.1);
		startTimer.stop();
		safetyTimer.stop();
		safetyTimer.reset();
	}

	@Override
	protected void interrupted() {
		
	}
}
