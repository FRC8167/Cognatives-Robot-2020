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
		//uses the slider to get the sensitivity of the robot and make sensitivity into the range [0.35, 1].
		double throttle = Robot.robotInstance.oi.stick.getThrottle();
		double sensitivity = (1 - (throttle + 1) * 0.35);
		//getY is negative because the joystick reads the opposite way for some reason
		double forwardSpeed = -Robot.robotInstance.oi.stick.getY() * sensitivity;
		double rotationSpeed = Robot.robotInstance.oi.stick.getX() * sensitivity;
		if (!Robot.robotInstance.oi.reverseButton.get()) { //TODO: is there a reverse button anymore?
			Robot.robotInstance.driveSubsystem.teleopDrive(forwardSpeed, rotationSpeed);
		} else {
			Robot.robotInstance.driveSubsystem.teleopDrive(-forwardSpeed, rotationSpeed);
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
