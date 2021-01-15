package frc.robot;

//import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

import frc.robot.commands.*;

public class OI {
	//TODO: this class probably should not exist at all
	public final JoystickInput joystick = new JoystickInput(RobotMap.joystickPort);
	
	private final Button 
		dumpButton, loadButton, 
		wheelClockwiseButton, wheelAntiClockwiseButton, 
		colorChooseButton, 
		turn90DegreesButton,
		servoCameraButton;

	public OI() {
		//This is where you instantiate new buttons, the ports are just the numbers on the Joystick
		dumpButton = joystick.getButton(RobotMap.dumpButtonNumber);
		loadButton = joystick.getButton(RobotMap.loadButtonNumber);//added by nick (thanks nick -tyler)
		wheelClockwiseButton = joystick.getButton(RobotMap.wheelClockwiseButtonNumber);
		wheelAntiClockwiseButton = joystick.getButton(RobotMap.wheelAntiClockwiseButtonNumber);
		colorChooseButton = joystick.getButton(RobotMap.colorDetectButtonNumber);
		//ultrasonicSensorButton = new JoystickButton(stick, RobotMap.ultrasonicSensorButtonNumber);	
		turn90DegreesButton = joystick.getButton(RobotMap.turn90DegreesButtonNumber);
		servoCameraButton = joystick.getButton(RobotMap.servoCameraButtonNumber);
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
