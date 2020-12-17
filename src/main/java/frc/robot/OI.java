package frc.robot;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

public class OI {
	public final Joystick stick = new Joystick(RobotMap.joystickPort);
	public final I2C.Port i2cPort = I2C.Port.kOnboard;
	public final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
	public final AnalogInput ultrasonicSensor = new AnalogInput(RobotMap.ultrasonicPort);
	public final Servo dumpActuator = new Servo(RobotMap.dumpActuatorPort);
	public final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public final Servo colorServo = new Servo(RobotMap.colorServoPort);
	public final Servo cameraServo = new Servo(RobotMap.cameraServoPort);
	
	//This button is outside of public OI() becuase it doesn't excecute a command, but is checked in ManualDriveCommand
	//TODO: what is this? should this be here?
	public Button reverseButton = new JoystickButton(stick, RobotMap.reverseButtonNumber);
	
	public OI() {
		//This is where you instantiate new buttons, the ports are just the numbers on the Joystick
		Button dumpButton = new JoystickButton(stick, RobotMap.dumpButtonNumber);
		Button loadButton = new JoystickButton(stick, RobotMap.loadButtonNumber);//added by nick (thanks nick -tyler)
		Button wheelClockwiseButton = new JoystickButton(stick, RobotMap.wheelClockwiseButtonNumber);
		Button wheelAntiClockwiseButton = new JoystickButton(stick, RobotMap.wheelAntiClockwiseButtonNumber);
		Button colorChooseButton = new JoystickButton(stick, RobotMap.colorDetectButtonNumber);
		//Button ultrasonicSensorButton = new JoystickButton(stick, RobotMap.ultrasonicSensorButtonNumber);	
		Button turn90DegreesButton = new JoystickButton(stick, RobotMap.turn90DegreesButtonNumber);
		Button servoCameraButton = new JoystickButton(stick, RobotMap.servoCameraButtonNumber);
		
		//This is where you tell the buttons to excecute commands
		dumpButton.whenPressed(new DumpSetCommand(-0.9));
		dumpButton.whenReleased(new DumpSetCommand(.6));
		loadButton.whenPressed(new DumpSetCommand(-0.5));
		loadButton.whenReleased(new DumpSetCommand(.6));
		
		wheelClockwiseButton.whileHeld(new WheelMotorCommand(.40)); //NEO brushless motor stuff
		wheelAntiClockwiseButton.whileHeld(new WheelMotorCommand(-.40));
		colorChooseButton.whenReleased(new ColorChooseCommand());
		turn90DegreesButton.whenPressed(new QuickTurnCommand());
		
		servoCameraButton.whenPressed(new ServoCameraCommand());
	}

	/**
	 * TODO: um what? is this old code or something?
	Button D1 = new JoystickButton(driverController, 1);
	Button D2 = new JoystickButton(driverController, 2);
	Button D3 = new JoystickButton(driverController, 3);
	Button D4 = new JoystickButton(driverController, 4);
	Button D5 = new JoystickButton(driverController, 5);
	Button D6 = new JoystickButton(driverController, 6);
	Button D7 = new JoystickButton(driverController, 7);
	Button D8 = new JoystickButton(driverController, 8);
	Button D9 = new JoystickButton(driverController, 9);
	Button D10 = new JoystickButton(driverController, 10);
	
	public OI() {
		D1.whenPressed(new ShooterUp());
		D2.whenPressed(new ShooterDown());
	}
	
	// Button ButtonName = new JoystickButton(stick, buttonNumber);
	
	// There are a few additional built in buttons you can use. Additionally,
	// by subclassing Button you can create custom triggers and bind those to
	// commands the same as any other Button.
	
	//// TRIGGERING COMMANDS WITH BUTTONS
	// Once you have a button, it's trivial to bind it to a button in one of
	// three ways:
	
	// Start the command when the button is pressed and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenPressed(new ExampleCommand());
	
	// Run the command while the button is being held down and interrupt it once
	// the button is released.
	// button.whileHeld(new ExampleCommand());
	
	// Start the command when the button is released and let it run the command
	// until it is finished as determined by it's isFinished method.
	// button.whenReleased(new ExampleCommand());
	*/
}
