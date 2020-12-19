package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class ColorWheelMotorCommand extends Command {
	private final double wheelSpeed;
	
	/**
	 * Constructs a new ColorWheelMotorCommand, with the given speed.
	 * 
	 * @param wheelSpeed	the speed of the colorwheel motor, between -1.0 and 1.0, 
	 * 						with 1.0 being clockwise. //TODO: VERIFY THIS
	 */
	public ColorWheelMotorCommand(double wheelSpeed) {
		requires(Robot.robotInstance.colorWheelMotorSubsystem);
		this.wheelSpeed = wheelSpeed;
	}

	@Override
	protected void initialize() {
	}

	@Override
	protected void execute() {
		Robot.robotInstance.colorWheelMotorSubsystem.wheelMotorControl(this.wheelSpeed);
	}
	
	@Override
	protected boolean isFinished() {
		return false;
	}

	@Override
	protected void end() {
		Robot.robotInstance.colorWheelMotorSubsystem.wheelMotorControl(0.0);
	}

	@Override
	protected void interrupted() {
		Robot.robotInstance.colorWheelMotorSubsystem.wheelMotorControl(0.0);
	}

}
