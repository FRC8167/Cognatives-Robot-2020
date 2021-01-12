package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.Colors;
import frc.robot.subsystems.*;

public class Robot extends TimedRobot {
	private final Timer autonomousTimer;
	private final SendableChooser<String> colorChoice;
	private final OI outputs;
	
	private final DriveSubsystem driveSubsystem;
	private final ColorSensor colorSensor;
	private final ColorWheelMotor colorWheelMotor;
	private final DumpActuator dumpActuator;
	private final Camera camera;
	
	private static Robot robotInstance = null;
	public synchronized static Robot getRobot() {
		if (robotInstance==null) robotInstance = new Robot();
		return robotInstance;
	}
	
	/**
	 * Constructs the Robot instance.
	 */
	private Robot() {
		// calls the TimedRobot constructor with 0.02 seconds between periodic methods, DO NOT CHANGE 0.02!
		super(0.02d);
		
		this.outputs = new OI();
		this.colorChoice = new SendableChooser<String>();
		
		// instantiates all the stuff
		this.autonomousTimer = new Timer();
		
		this.driveSubsystem = new DriveSubsystem();
		this.colorSensor = new ColorSensor();
		this.colorWheelMotor = new ColorWheelMotor();
		this.dumpActuator = new DumpActuator();
		this.camera = new Camera(RobotMap.cameraPort);
	}
	
	//GETTERS
	public OI getOutputs() {return outputs;}
	public DriveSubsystem getDriveSubsystem() {return driveSubsystem;}
	public DumpActuator getDumpActuator() {return dumpActuator;}
	public Camera getCamera() {return camera;}
	public ColorWheelMotor getColorWheelMotor() {return colorWheelMotor;}
	public ColorSensor getColorSensor() {return colorSensor;}
	
	public Colors getSelectedColor() {
		//TODO
		String color = colorChoice.getSelected();
		return Colors.Unknown;
	} 
	
	@Override
	public void robotInit() {
		//TODO: make gyro subsystem?
		//Calibrate the Gyro
		/* 
		wow what an incredibly helpful comment thank 
		you so much i would have no idea what this 
		did if that wasnt there
		*/
		this.outputs.gyro.calibrate();
		
		//adds color chooices to smart dashboard
		//TODO: what the frick does this DO? why is it yellow/green and red/blue??
		colorChoice.setDefaultOption("Yellow", "Green");
		colorChoice.addOption("Green", "Yellow");
		colorChoice.addOption("Red", "Blue");
		colorChoice.addOption("Blue", "Red");
		SmartDashboard.putData("Choose Your Color", colorChoice);
		SmartDashboard.updateValues();
	}
	
	
	@Override
	public void autonomousInit() {
		// resets and restarts the autonomous timer (wow thanks tyler for the helpful comment)
		autonomousTimer.reset();
		autonomousTimer.start();
	}
	
	@Override
	public void autonomousPeriodic() {
		//TODO: use the command abstraction, don't directly set all of this stuff, or at least the robotDifferentialDrive.drive()
		if (autonomousTimer.get() < 2.5) {
			//drive forward at 0.75x speed for 2.5 seconds
			this.driveSubsystem.drive(0.75, 0.0);
		} else if (autonomousTimer.get() >= 4.0 && autonomousTimer.get() < 4.5) {
			//TODO: why is there a 1.5 second gap in the code here?
			//dump the balls into the hole? idk
			this.dumpActuator.setPosition(-0.9);
		} else if (autonomousTimer.get() >= 4.5 && autonomousTimer.get() < 5.0) {
			//TODO: figure out what the frick this stuff does
			this.driveSubsystem.drive(0.7, 0.0);
		} else if (autonomousTimer.get() >= 5.0 && autonomousTimer.get() < 12.5) {
			this.driveSubsystem.stopMotors();
		} else if (autonomousTimer.get() >= 12.5 && autonomousTimer.get() < 14.0){
			this.driveSubsystem.drive(-0.7, 0.0);
		} else if (autonomousTimer.get() >= 14.0 && autonomousTimer.get() < 15.0) {
			this.driveSubsystem.drive(0.0, 0.65);
		} else {
			// stops the robot
			this.driveSubsystem.stopMotors();
		}
	}
	
	
	@Override
	public void teleopInit() {
		this.dumpActuator.setPosition(0.6);
	}
	
	@Override
	public void teleopPeriodic() {
		/*
		NOTE TO PEOPLE WHO DON'T KNOW WHAT THIS DOES:
			this basically gets a list of all the stuff that the robot needs to do, and then runs it,
			so DONT CHANGE THIS UNLESS YOU KNOW WHAT YOURE DOING.
		*/
		Scheduler.getInstance().run();
	}
	
	
	@Override
	public void testInit() {
	}
	
	@Override
	public void testPeriodic() {
	}
}
