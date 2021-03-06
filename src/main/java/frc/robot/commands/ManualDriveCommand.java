package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ManualDriveCommand extends Command {
	public ManualDriveCommand() {
		requires(Robot.getRobot().getDriveSubsystem());
	}
	
	@Override
	protected void initialize() {
	}
	
	@Override
	protected void execute() {
		//uses the slider to get the sensitivity of the robot and make sensitivity into the range [0, 1].
		double sensitivity = (Robot.getRobot().getJoystick().getThrottle()-1)/2;
		//getY is negative because the joystick reads the opposite way for some reason
		double forwardSpeed = Robot.getRobot().getJoystick().getY() * sensitivity;
		double rotationSpeed = Robot.getRobot().getJoystick().getX() * sensitivity;
		Robot.getRobot().getDriveSubsystem().drive(forwardSpeed, -rotationSpeed);
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
