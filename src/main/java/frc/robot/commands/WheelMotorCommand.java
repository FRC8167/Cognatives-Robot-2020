package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class WheelMotorCommand extends Command {
	private double wheelSpeed;
	
	public WheelMotorCommand(double wheelSpeed) {
		requires(Robot.robotInstance.wheelMotorSubsystem);
		this.wheelSpeed = wheelSpeed;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.robotInstance.wheelMotorSubsystem.wheelControl(this.wheelSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.robotInstance.wheelMotorSubsystem.wheelControl(0.0);
	}

	@Override
	protected void interrupted() {
		Robot.robotInstance.wheelMotorSubsystem.wheelControl(0.0);
	}

}
