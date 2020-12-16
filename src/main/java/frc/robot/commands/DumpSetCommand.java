package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.Robot;

public class DumpSetCommand extends Command {
	double actuatorSpeed;
	
	public DumpSetCommand(final double speed) {
		//Speed = position for actuators 
		this.actuatorSpeed = speed;
	}
	
	@Override
	protected void initialize() {
		//I do not know what setBounds does, but you need it
		//TODO: find out what setbounds does
		Robot.robotInstance.oi.dumpActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
		Robot.robotInstance.oi.dumpActuator.setSpeed(this.actuatorSpeed);
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