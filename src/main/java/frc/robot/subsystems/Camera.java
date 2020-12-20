package frc.robot.subsystems;

import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.Servo;

import frc.robot.RobotMap;

public class Camera extends Subsystem {
	private final UsbCamera usbCamera;
	private final Servo cameraServo;
	
	public Camera(int usbPortNumber) {
		this.usbCamera = new UsbCamera("crappy webcam", usbPortNumber);
		this.cameraServo = new Servo(RobotMap.cameraServoPort);
		
		setFPS(20);
		setResolution(320, 240);
		setPosition(1.0);
		CameraServer.getInstance().startAutomaticCapture();
	}
	
	public void togglePosition() {
		if (cameraServo.getPosition() <= 0.1){
			cameraServo.setPosition(1.0);
		} else cameraServo.setPosition(0.0);
	}
	
	public void setPosition(double position) {
		cameraServo.setPosition(position);
	}
	
	public double getPosition() {
		return cameraServo.getPosition();
	}
	
	public void setFPS(int FPS) {
		usbCamera.setFPS(FPS);
	}
	
	public void setResolution(int width, int height) {
		usbCamera.setResolution(width, height);
	}
	
	@Override
	public void initDefaultCommand() {
		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}
}
