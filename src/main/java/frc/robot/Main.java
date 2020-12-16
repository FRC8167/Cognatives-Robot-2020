package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

//DO NOT CHANGE THIS FILE UNLESS YOU KNOW WHAT YOURE DOING!
//ONLY MODIFY THE ROBOT CLASS AND OTHER FILES. DONT TOUCH THIS ONE
public final class Main {
	private Main() {
	}
	
	public static void main(String... args) {
		RobotBase.startRobot(Robot::new);
	}
}
