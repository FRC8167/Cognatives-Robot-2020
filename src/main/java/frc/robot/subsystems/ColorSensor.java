package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;
import com.revrobotics.ColorMatchResult;
import frc.robot.Robot;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.util.Colors;
import frc.robot.RobotMap;
import frc.robot.commands.ColorMatchCommand;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ColorSensor extends Subsystem {
	private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
	private final Servo colorServo = new Servo(RobotMap.colorServoPort);
	
	//TODO: i hate everything with this color sensor stuff, burn this to the ground and remake it
	
	//TODO: dont use this unreliable colorMatcher stuff, make one YOURSELF
	//it will probably be something like:
	//	mindistance = 0.0
	//	closest_color = null
	//	for Color color in colors:
	//		if dist(color, sensor_color) < mindistance:
	//			closest_color = color
	//			mindistance = dist(color, sensor_color)
	//	return closest_color
	
	//Establishes what RGB values are equal to what color
	//TODO: these are all AWFUL variable names
	public final ColorMatch m_colorMatcher = new ColorMatch();
	public final Color kBlueTarget = new Color(0.156, 0.454, 0.389);
	public final Color kGreenTarget = new Color(0.197, 0.561, 0.240);
	public final Color kRedTarget = new Color(0.471, 0.382, 0.147);
	public final Color kYellowTarget = new Color(0.325, 0.552, 0.123);
	
	public Color getColor() {
		return colorSensor.getColor();
	}
	
	public Colors getClosestColor() {
		//TODO: this is literal garbage; change this ASAP
		//TODO: specifically, use a non-garbage distance function to get the min color distance
		Color detectedColor = Robot.getRobot().getColorSensor().getColor();
		ColorMatchResult match = Robot.getRobot().getColorSensor().m_colorMatcher.matchClosestColor(detectedColor);
		Colors color;
		
		if (match.color == Robot.getRobot().getColorSensor().kBlueTarget) {
			color = Colors.Blue;
		} else if (match.color == Robot.getRobot().getColorSensor().kRedTarget) {
			color = Colors.Red;
		} else if (match.color ==Robot.getRobot().getColorSensor(). kGreenTarget) {
			color = Colors.Green;
		} else if (match.color == Robot.getRobot().getColorSensor().kYellowTarget) {
			color = Colors.Yellow;
		} else {
			color = Colors.Unknown;
		}

		//Adds data to SmartDashboard
		SmartDashboard.putNumber("Red", detectedColor.red);
		SmartDashboard.putNumber("Green", detectedColor.green);
		SmartDashboard.putNumber("Blue", detectedColor.blue);
		SmartDashboard.putNumber("Confidence", match.confidence);
		SmartDashboard.putString("Detected Color", color.toString());

		return color;
	}
	
	public void setServoPosition(double pos) {
		colorServo.setPosition(pos);
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ColorMatchCommand());
	}
}
