package frc.robot;

import com.revrobotics.ColorSensorV3;

import edu.wpi.first.wpilibj.ADXRS450_Gyro;
import edu.wpi.first.wpilibj.AnalogInput;
import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.buttons.Button;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
//import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ColorChooseCommand;
import frc.robot.commands.DumpSetCommand;
import frc.robot.commands.QuickTurnCommand;
//import frc.robot.commands.UltrasonicSensorCommand;
//import frc.robot.commands.WheelMotorCommand;

public class OI {
  public Joystick stick = new Joystick(RobotMap.joystickPort);
  public final I2C.Port i2cPort = I2C.Port.kOnboard;
  public final ColorSensorV3 m_colorSensor = new ColorSensorV3(i2cPort);
  public final AnalogInput ultrasonicSensor = new AnalogInput(RobotMap.ultrasonicPort);
  public Servo dumpActuator = new Servo(RobotMap.dumpActuatorPort);
  public Servo dumpServo = new Servo(RobotMap.dumpServoPort);
  public ADXRS450_Gyro gyro = new ADXRS450_Gyro();

  public OI() {
    Button dumpButton = new JoystickButton(stick, RobotMap.dumpButtonNumber);
    Button loadButton = new JoystickButton(stick, RobotMap.loadButtonNumber);//added by nick
    //Button wheelClockwiseButton = new JoystickButton(stick, RobotMap.wheelClockwiseButtonNumber); NIO brushless motor stuff
    //Button wheelAntiClockwiseButton = new JoystickButton(stick, RobotMap.wheelAntiClockwiseButtonNumber); ^
    Button colorDetectButton = new JoystickButton(stick, RobotMap.colorDetectButtonNumber);
    //Button ultrasonicSensorButton = new JoystickButton(stick, RobotMap.ultrasonicSensorButtonNumber);  
    Button turn90DegreesButton = new JoystickButton(stick, RobotMap.turn90DegreesButtonNumber);
    
    dumpButton.whenPressed(new DumpSetCommand(-1.0));
    dumpButton.whenReleased(new DumpSetCommand(.4));
    loadButton.whenPressed(new DumpSetCommand(-0.4));
    loadButton.whenReleased(new DumpSetCommand(.4));

    //wheelClockwiseButton.whileHeld(new WheelMotorCommand(.3)); //NEO brushless motor stuff
    //wheelAntiClockwiseButton.whileHeld(new WheelMotorCommand(-.3));
    colorDetectButton.whenPressed(new ColorChooseCommand());
    turn90DegreesButton.whenPressed(new QuickTurnCommand());
  }

/**
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
*/



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
