package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualDriveCommand extends Command {
	public ManualDriveCommand() {
		requires(Robot.robotInstance.driveSubsystem);
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		//uses the slider to get the sensitivity of the robot. sensitivity is in range [0.3, 1].
		double throttle = Robot.robotInstance.oi.stick.getThrottle();
		double sensitivity =	(1 - (throttle + 1) * 0.35);
		//getY is negative because the joystick reads backwards
		double yValue = -Robot.robotInstance.oi.stick.getY() * sensitivity;
		double xValue = Robot.robotInstance.oi.stick.getX() * sensitivity;
		if (Robot.robotInstance.oi.reverseButton.get() == true) {
			Robot.robotInstance.driveSubsystem.teleopDrive(-yValue, xValue);
		}
		else {
			Robot.robotInstance.driveSubsystem.teleopDrive(yValue, xValue);
		}
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
