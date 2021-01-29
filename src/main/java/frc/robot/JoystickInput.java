package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.JoystickButton;

public class JoystickInput {
	private final Joystick stick;
	
	public JoystickInput(int portNumber) {
		stick = new Joystick(portNumber);
	}
	
	public double getX() {return stick.getX();}
	public double getY() {return stick.getY();}
	public double getThrottle() {return stick.getThrottle();}
	public int getPOV() {return stick.getPOV();}
	
	public JoystickButton getButton(int buttonNumber) {
		return new JoystickButton(stick, buttonNumber);
	}
	
	
}
