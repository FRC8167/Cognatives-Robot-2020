package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DumpSetCommand extends Command {
	final double actuatorPosition;
	
	public DumpSetCommand(double position) {
		//NOTE: position should be in range [-1.0, 1.0]
		this.actuatorPosition = position;
	}
	
	@Override
	protected void initialize() {
		// NOTE: `setSpeed` changes position for some reason
		Robot.robotInstance.oi.dumpActuator.setSpeed(this.actuatorPosition);
	}
	
	@Override
	protected void execute() {
	}
	
	@Override
	protected boolean isFinished() {
		return true;
	}
	
	@Override
	protected void end() {
	}
	
	@Override
	protected void interrupted() {
	}
}