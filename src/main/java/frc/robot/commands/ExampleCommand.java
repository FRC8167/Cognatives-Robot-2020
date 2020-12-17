package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;

public class ExampleCommand extends Command {
	public ExampleCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis); or something like that
	}
	
	@Override
	protected void initialize() {
		// code that gets called right before the command gets run
	}
	
	public boolean isFinished() {
		// return `true` when the command is finished, and false otherwise
		return false;
	}
	
	@Override
	protected void execute() {
		// the code that runs ~50 times a second while the command is running
	}
	
	@Override
	protected void end() {
		// code that gets called after the command stops running
	}
	
	@Override
	protected void interrupted() {
		// code that gets called if the command gets interrupted somehow
	}
}
