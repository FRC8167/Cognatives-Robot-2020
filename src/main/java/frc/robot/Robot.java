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
import frc.robot.subsystems.WheelMotorSubsystem;

public class Robot extends TimedRobot {
	public static Robot robotInstance; //TODO: add some sort of getInstance (or getRobot) method for this
	
	public final Timer autonomousTimer;
	public DriveSubsystem driveSubsystem;
	public ColorSensorSubsystem colorSensorSubsystem;
	public WheelMotorSubsystem wheelMotorSubsystem;
	public SendableChooser<String> colorChoice;
	public UsbCamera camera;
	public OI oi;
	
	Robot() {
		// calls the TimedRobot constructor with 0.02 seconds between periodic methods, DO NOT CHANGE 0.02!
		super(0.02d);
		
		// checks if a Robot() has already been made
		if (robotInstance == null) robotInstance = this;
		else {
			//TODO: throw an error here or something
			System.out.println("CRITICAL ERROR: there has already been a Robot() instantiated!");
		}
		
		// instantiates all the stuff
		this.autonomousTimer = new Timer();
		this.driveSubsystem = new DriveSubsystem();
		this.colorSensorSubsystem = new ColorSensorSubsystem();
		this.wheelMotorSubsystem = new WheelMotorSubsystem();
		this.oi = new OI(); //TODO: maybe rename OI to something better? maybe just un-abbreviate it idk
		this.colorChoice = new SendableChooser<String>();
		this.camera = new UsbCamera("crappy webcam on slot 0", 0);
	}
	
	private void cameraInit() {
		camera.setFPS(20); // omg the camera is so bad LOL
		camera.setResolution(320, 240); // HAHAHA 240P!
		CameraServer.getInstance().startAutomaticCapture();
	}
	private void actuatorInit() {
		this.oi.dumpActuator.setSpeed(1);
		this.oi.cameraServo.setPosition(1);
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
		
		this.oi.gyro.calibrate();
		
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
		if (autonomousTimer.get() < 2.5) {
			//drive forward for 2.5 seconds
			this.driveSubsystem.m_robotDrive.arcadeDrive(0.75, 0.0);
		} else if (autonomousTimer.get() >= 4.0 && autonomousTimer.get() < 4.5) {
			//dump the balls into the hole? idk
			this.oi.dumpActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
			this.oi.dumpActuator.setSpeed(-0.9);
		} else if (autonomousTimer.get() >= 4.5 && autonomousTimer.get() < 5.0) {
			//TODO: figure out wtf this stuff does
			this.driveSubsystem.m_robotDrive.arcadeDrive(0.7, 0.0);
		} else if (autonomousTimer.get() >= 5.0 && autonomousTimer.get() < 12.5) {
			this.driveSubsystem.m_robotDrive.stopMotor();
		} else if (autonomousTimer.get() >= 12.5 && autonomousTimer.get() < 14.0){
			this.driveSubsystem.m_robotDrive.arcadeDrive(-0.7, 0.0);
		} else if (autonomousTimer.get() >= 14.0 && autonomousTimer.get() < 15.0) {
			this.driveSubsystem.m_robotDrive.arcadeDrive(0.0, 0.65);
		} else {
			// stops the robot
			this.driveSubsystem.m_robotDrive.stopMotor();
		}
	}
	
	@Override
	public void teleopInit() {
		//TODO: find out what this stuff actually DOES
		this.oi.dumpActuator.setBounds(2.0, 1.8, 1.5, 1.2, 1.0);
		this.oi.dumpActuator.setSpeed(0.6);
		this.driveSubsystem.m_robotDrive.setExpiration(1.0);
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
	public void testPeriodic() {
		
	}
}
