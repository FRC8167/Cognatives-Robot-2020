package frc.robot;

import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.ADXRS450_Gyro;
//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

public class OI {
	//TODO: these should all be in subsystems
	public final Joystick stick = new Joystick(RobotMap.joystickPort);
	public final ColorSensorV3 m_colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
	//public final AnalogInput ultrasonicSensor = new AnalogInput(RobotMap.ultrasonicPort);
	//public final Servo dumpActuator = new Servo(RobotMap.dumpActuatorPort);
	public final ADXRS450_Gyro gyro = new ADXRS450_Gyro();
	public final Servo colorServo = new Servo(RobotMap.colorServoPort);
	
	public OI() {
		//TODO: shouldnt these be class variables???
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
		
		wheelClockwiseButton.whileHeld(new ColorWheelMotorCommand(.40)); //NEO brushless motor stuff
		wheelAntiClockwiseButton.whileHeld(new ColorWheelMotorCommand(-.40));
		colorChooseButton.whenReleased(new ColorChooseCommand());
		turn90DegreesButton.whenPressed(new QuickTurnCommand());
		
		servoCameraButton.whenPressed(new CameraServoCommand());
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
}
