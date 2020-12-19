package frc.robot;

// import edu.wpi.cscore.AxisCamera;
// import edu.wpi.cscore.CameraServerJNI;
// import edu.wpi.cscore.MjpegServer;
import java.lang.InstantiationException;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ColorWheelMotorSubsystem;

public class Robot extends TimedRobot {
	//TODO: add some sort of getInstance (or getRobot) method for this THAT DOESNT GET FLAGGED AS A FUCKING MEMORY LEAK AGHHHH
	public static Robot robotInstance;
	
	public final Timer autonomousTimer;
	public final DriveSubsystem driveSubsystem;
	public final ColorSensorSubsystem colorSensorSubsystem;
	public final ColorWheelMotorSubsystem colorWheelMotorSubsystem;
	public final SendableChooser<String> colorChoice;
	public final UsbCamera camera;
	public final OI outputs;
	
	/**
	 * Constructs a Robot instance, and sets the static Robot.robotInstance variable to it.
	 * 
	 * @throws InstantiationException if there is already a robot instance created
	 */
	Robot() {
		// calls the TimedRobot constructor with 0.02 seconds between periodic methods, DO NOT CHANGE 0.02!
		super(0.02d);
		
		// checks if a Robot() has already been made
		if (robotInstance == null) robotInstance = this;
		else {
			//TODO: how to throw an InstantiationException here
			System.out.println("CRITICAL ERROR: there has already been a Robot() instantiated!");
		}
		
		// instantiates all the stuff
		this.autonomousTimer = new Timer();
		this.driveSubsystem = new DriveSubsystem();
		this.colorSensorSubsystem = new ColorSensorSubsystem();
		this.colorWheelMotorSubsystem = new ColorWheelMotorSubsystem();
		this.outputs = new OI(); //TODO: maybe rename OI to something better? maybe just un-abbreviate it idk
		this.colorChoice = new SendableChooser<String>();
		this.camera = new UsbCamera("crappy webcam on slot 0", 0);
		
		// i dont know what this does but it is important, dont remove this
		this.outputs.dumpActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
	}
	
	private void cameraInit() {
		camera.setFPS(20); // omg the camera is so bad LOL
		camera.setResolution(320, 240); // HAHAHA 240P!
		CameraServer.getInstance().startAutomaticCapture();
	}
	private void actuatorInit() {
		this.outputs.dumpActuator.setSpeed(1);
		this.outputs.cameraServo.setPosition(1);
	}
	private void colorchoicesInit() {
		//adds color chooices to smart dashboard
		//TODO: wtf does this DO? why is it yellow/green and red/blue??
		colorChoice.setDefaultOption("Yellow", "Green");
		colorChoice.addOption("Green", "Yellow");
		colorChoice.addOption("Red", "Blue");
		colorChoice.addOption("Blue", "Red");
		SmartDashboard.putData("Choose Your Color", colorChoice);
		SmartDashboard.updateValues();
	}
	
	@Override
	public void robotInit() {
		//TODO: should this just go in the constructor? or should the constructor go in this? having both seems redundant
		//Calibrate the Gyro
		/* 
		wow what an incredibly helpful comment thank 
		you so much i would have no idea what this 
		did if that wasnt there
		*/
		this.outputs.gyro.calibrate();
		
		cameraInit();
		actuatorInit();
		colorchoicesInit();
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
			this.driveSubsystem.robotDifferentialDrive.arcadeDrive(0.75, 0.0);
		} else if (autonomousTimer.get() >= 4.0 && autonomousTimer.get() < 4.5) {
			//TODO: why is there a 1.5 second gap in the code here?
			//dump the balls into the hole? idk
			this.outputs.dumpActuator.setSpeed(-0.9);
		} else if (autonomousTimer.get() >= 4.5 && autonomousTimer.get() < 5.0) {
			//TODO: figure out wtf this stuff does
			this.driveSubsystem.robotDifferentialDrive.arcadeDrive(0.7, 0.0);
		} else if (autonomousTimer.get() >= 5.0 && autonomousTimer.get() < 12.5) {
			this.driveSubsystem.robotDifferentialDrive.stopMotor();
		} else if (autonomousTimer.get() >= 12.5 && autonomousTimer.get() < 14.0){
			this.driveSubsystem.robotDifferentialDrive.arcadeDrive(-0.7, 0.0);
		} else if (autonomousTimer.get() >= 14.0 && autonomousTimer.get() < 15.0) {
			this.driveSubsystem.robotDifferentialDrive.arcadeDrive(0.0, 0.65);
		} else {
			// stops the robot
			this.driveSubsystem.robotDifferentialDrive.stopMotor();
		}
	}
	
	
	@Override
	public void teleopInit() {
		this.outputs.dumpActuator.setSpeed(0.6);
		this.driveSubsystem.robotDifferentialDrive.setExpiration(1.0);
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
