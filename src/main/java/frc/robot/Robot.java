package frc.robot;

import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.Colors;
import frc.robot.subsystems.*;
import frc.robot.commands.*;

public class Robot extends TimedRobot {
	private final Timer autonomousTimer;
	private final SendableChooser<Colors> colorChoice;
	
	private final DriveSubsystem driveSubsystem;
	private final ColorSensor colorSensor;
	private final ColorWheelMotor colorWheelMotor;
	private final ClimbMotor climbMotor;
	private final DumpActuatorSpark dumpActuatorSpark;
	private final Gyro gyro;
	private final JoystickInput joystick;
	//private final Camera camera;
	
	private static Robot robotInstance = null;
	/**
	 * gets the current Robot() instance, and if one does not exist yet, 
	 * creates one. This is to make the Robot class effectively a singleton type.
	 * 
	 * @return the current Robot() instance.
	 */
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
		
		this.colorChoice = new SendableChooser<Colors>();
		
		// instantiates all the stuff
		this.autonomousTimer = new Timer();
		
		this.driveSubsystem = new DriveSubsystem();
		this.colorSensor = new ColorSensor();
		this.colorWheelMotor = new ColorWheelMotor();
		this.climbMotor = new ClimbMotor();
		this.dumpActuatorSpark = new DumpActuatorSpark();
		this.joystick = new JoystickInput(RobotMap.joystickPort);
		this.gyro = new Gyro();
		//this.camera = new Camera(RobotMap.cameraPort);
	}
	
	//GETTERS
	public DriveSubsystem getDriveSubsystem() {return driveSubsystem;}
	public DumpActuatorSpark getDumpActuator() {return dumpActuatorSpark;}
	public Camera getCamera() {return null;}
	public ColorWheelMotor getColorWheelMotor() {return colorWheelMotor;}
	public ClimbMotor getClimbMotor() {return climbMotor;}
	public Gyro getGyro() {return gyro;}
	public JoystickInput getJoystick() {return joystick;}
	public ColorSensor getColorSensor() {return colorSensor;}
	
	public Colors getSelectedColor() {return colorChoice.getSelected();}
	
	private void makeButtonsDoStuff() { //TODO: change crappy name
		JoystickButton 
			dumpButton, loadButton, 
			//wheelClockwiseButton, wheelAntiClockwiseButton, 
			//turn90DegreesButton,
			//servoCameraButton;
			colorChooseButton;
		
		//This is where you instantiate new buttons, the ports are just the numbers on the Joystick
		dumpButton = joystick.getButton(RobotMap.dumpButtonNumber);
		loadButton = joystick.getButton(RobotMap.loadButtonNumber);//added by nick (thanks nick -tyler)
		//wheelClockwiseButton = joystick.getButton(RobotMap.wheelClockwiseButtonNumber);
		//wheelAntiClockwiseButton = joystick.getButton(RobotMap.wheelAntiClockwiseButtonNumber);
		colorChooseButton = joystick.getButton(RobotMap.colorDetectButtonNumber);
		//ultrasonicSensorButton = new JoystickButton(stick, RobotMap.ultrasonicSensorButtonNumber);	
		//turn90DegreesButton = joystick.getButton(RobotMap.turn90DegreesButtonNumber);
		//servoCameraButton = joystick.getButton(RobotMap.servoCameraButtonNumber);
		
		//This is where you tell the buttons to excecute commands
		dumpButton.whenPressed(new DumpSetCommand(-0.9));
		dumpButton.whenReleased(new DumpSetCommand(.6));
		loadButton.whenPressed(new DumpSetCommand(-0.5));
		loadButton.whenReleased(new DumpSetCommand(.6));
		
		//wheelClockwiseButton.whileHeld(new ColorWheelMotorCommand(.40)); //NEO brushless motor stuff
		//wheelAntiClockwiseButton.whileHeld(new ColorWheelMotorCommand(-.40));
		colorChooseButton.whenReleased(new ColorChooseCommand());
		//turn90DegreesButton.whenPressed(new QuickTurnCommand());
		
		//servoCameraButton.whenPressed(new CameraServoCommand());

		joystick.getButton(RobotMap.dumpActuatorDownButtonNumber).whileHeld(new Command() {
			@Override public boolean isFinished() {return false;}
			@Override protected void execute() {dumpActuatorSpark.setSpeed(-0.4);}
			@Override protected void end() {dumpActuatorSpark.setSpeed(0.0);}
		});
		joystick.getButton(RobotMap.dumpActuatorUpButtonNumber).whileHeld(new Command() {
			@Override public boolean isFinished() {return false;}
			@Override protected void execute() {dumpActuatorSpark.setSpeed(0.4);}
			@Override protected void end() {dumpActuatorSpark.setSpeed(0.0);}
		});

		joystick.getButton(7).whileHeld(new Command() {
			@Override public boolean isFinished() {return false;}
			@Override protected void execute() {climbMotor.setSpeed(1.0);}
			@Override protected void end() {climbMotor.setSpeed(0.0);}
		});
		joystick.getButton(8).whileHeld(new Command() {
			@Override public boolean isFinished() {return false;}
			@Override protected void execute() {climbMotor.setSpeed(-1.0);}
			@Override protected void end() {climbMotor.setSpeed(0.0);}
		});
		
	}

	@Override
	public void robotInit() {
		makeButtonsDoStuff();
		//adds color chooices to smart dashboard
		//TODO: what the frick does this DO? why is it yellow/green and red/blue??
		colorChoice.setDefaultOption("Yellow", Colors.Green);
		colorChoice.addOption("Green", Colors.Yellow);
		colorChoice.addOption("Red", Colors.Blue);
		colorChoice.addOption("Blue", Colors.Red);
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
		//TODO: COMPLETELY redo this autonomous stuff
		/*
		//TODO: use the command abstraction, don't directly set all of this stuff, or at least the robotDifferentialDrive.drive()
		if (autonomousTimer.get() < 2.5) {
			//drive forward at 0.75x speed for 2.5 seconds
			this.driveSubsystem.drive(0.2, 0.0);
		} else if (autonomousTimer.get() >= 4.0 && autonomousTimer.get() < 4.5) {
			//TODO: why is there a 1.5 second gap in the code here?
			//dump the balls into the hole? idk
			this.dumpActuator.setPosition(-0.9);
		} else if (autonomousTimer.get() >= 4.5 && autonomousTimer.get() < 5.0) {
			//TODO: figure out what the frick this stuff does
			this.driveSubsystem.drive(0.2, 0.0);
		} else if (autonomousTimer.get() >= 5.0 && autonomousTimer.get() < 12.5) {
			this.driveSubsystem.stopMotors();
		} else if (autonomousTimer.get() >= 12.5 && autonomousTimer.get() < 14.0){
			this.driveSubsystem.drive(-0.2, 0.0);
		} else if (autonomousTimer.get() >= 14.0 && autonomousTimer.get() < 15.0) {
			this.driveSubsystem.drive(0.0, 0.3);
		} else {
			// stops the robot
			this.driveSubsystem.stopMotors();
		}//*/
	}
	
	@Override
	public void teleopInit() {
		
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
