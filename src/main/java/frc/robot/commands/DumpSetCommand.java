package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;

public class DumpSetCommand extends Command {
	private final double actuatorPosition;
	
	/**
	 * Constructs a new DumpSetCommand, which sets the 
	 * linear actuator to a given position.
	 * 
	 * @param position	in range [-1.0, 1.0] 
	 * 					//TODO: test how position actually sets the position
	 */
	public DumpSetCommand(double position) {
		this.actuatorPosition = position;
	}
	
	@Override
	protected void initialize() {
		// NOTE: `setSpeed` changes the position for some reason 
		Robot.robotInstance.outputs.dumpActuator.setSpeed(this.actuatorPosition);
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