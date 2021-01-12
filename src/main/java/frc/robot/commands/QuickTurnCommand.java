/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.														 */
/* Open Source Software - may be modified and shared by FRC teams. The code	 */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.																															 */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.Timer;

//import java.text.DecimalFormat;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.RobotMap;

//TODO: completely redo this whole thing
public class QuickTurnCommand extends Command {
	double initAngle;
	int direction;
	private static Timer timer = new Timer();
	
	public QuickTurnCommand() {
		// Use requires() here to declare subsystem dependencies
		// eg. requires(chassis);
		requires(Robot.getRobot().getDriveSubsystem());
	}
	
	// Called just before this Command runs the first time
	@Override
	protected void initialize() {
		initAngle = Robot.getRobot().getOutputs().gyro.getAngle();
		timer.start();
	}
	
	// Called repeatedly when this Command is scheduled to run
	@Override
	protected void execute() {
		//Finds what direction you want and sets the motors to turn that direction
		
		//NOTE: by default (not pressed) the pov is set to -1
		direction = Robot.getRobot().getOutputs().stick.getPOV();
		
		if (direction == 0) {
			// how would you 'quickturn forwards'?
		} else if (direction == 90) {
			Robot.getRobot().getDriveSubsystem().drive(0, RobotMap.turnRightValue);
		} else if (direction == 180) {
			Robot.getRobot().getDriveSubsystem().drive(0, RobotMap.turn180Value);
		} else if (direction == 270) {
			Robot.getRobot().getDriveSubsystem().drive(0, RobotMap.turnLeftValue);
		} else {
			// this is the default case, if we want to do something when the pov stick isnt moved it goes here
		}
	}

	// Make this return true when this Command no longer needs to run execute()
	@Override
	protected boolean isFinished() {
		//If the code has been running for more than two seconds, stop
		if (timer.get() >= 2.0) return true;
		
		//get the current angle of the robot
		double currentAngle = Robot.getRobot().getOutputs().gyro.getAngle();
		
		//Checks what direction you chose by using the speed and ends if the desired angle is met
		//TODO: test to make sure this still works
		return	  (direction == 90  && currentAngle >= initAngle + 40 )
				||(direction == 180 && currentAngle >= initAngle + 125)
				||(direction == 270 && currentAngle <= initAngle - 45 );
	}
	
	// Called once after isFinished returns true
	@Override
	protected void end() {
		//Returns motors to stopped and ends the timer
		Robot.getRobot().getDriveSubsystem().drive(0, 0);
		timer.stop();
		timer.reset(); //TODO: idk if this is necessary
	}
	
	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	@Override
	protected void interrupted() {
		
	}
}
