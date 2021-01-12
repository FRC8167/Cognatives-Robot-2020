package frc.robot;

//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

public class OI {
	//TODO: these should all be in subsystems
	//TODO: this class probably should not exist at all
	public final Joystick stick = new Joystick(RobotMap.joystickPort);
	//TODO: this one REALLLY needs to be in a subsystem
	public final Servo colorServo = new Servo(RobotMap.colorServoPort);
	
	public final Button 
		dumpButton, loadButton, 
		wheelClockwiseButton, wheelAntiClockwiseButton, 
		colorChooseButton, 
		turn90DegreesButton,
		servoCameraButton;

	public OI() {
		//TODO: put these into a joystick subsystem/class?
		//This is where you instantiate new buttons, the ports are just the numbers on the Joystick
		dumpButton = new JoystickButton(stick, RobotMap.dumpButtonNumber);
		loadButton = new JoystickButton(stick, RobotMap.loadButtonNumber);//added by nick (thanks nick -tyler)
		wheelClockwiseButton = new JoystickButton(stick, RobotMap.wheelClockwiseButtonNumber);
		wheelAntiClockwiseButton = new JoystickButton(stick, RobotMap.wheelAntiClockwiseButtonNumber);
		colorChooseButton = new JoystickButton(stick, RobotMap.colorDetectButtonNumber);
		//ultrasonicSensorButton = new JoystickButton(stick, RobotMap.ultrasonicSensorButtonNumber);	
		turn90DegreesButton = new JoystickButton(stick, RobotMap.turn90DegreesButtonNumber);
		servoCameraButton = new JoystickButton(stick, RobotMap.servoCameraButtonNumber);
	}
	
	public void makeButtonsDoStuff() {
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
}
