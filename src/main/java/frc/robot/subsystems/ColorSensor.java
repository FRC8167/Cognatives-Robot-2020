package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.commands.ColorMatchCommand;
import edu.wpi.first.wpilibj.I2C;
import com.revrobotics.ColorSensorV3;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ColorSensor extends Subsystem {
	private final ColorSensorV3 colorSensor = new ColorSensorV3(I2C.Port.kOnboard);
	
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
	public final ColorMatch m_colorMatcher = new ColorMatch();
	//TODO: why the frick are we using ColorMatch.makeColor when CONSTRUCTORS EXIST FOR A REASON, AND THIS JUST CALLS THE Color() CONSTRUCTOR ANYWAYS
	public final Color kBlueTarget = ColorMatch.makeColor(0.156, 0.454, 0.389);
	public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
	public final Color kRedTarget = ColorMatch.makeColor(0.471, 0.382, 0.147);
	public final Color kYellowTarget = ColorMatch.makeColor(0.325, 0.552, 0.123);
	
	public Color getColor() {
		return colorSensor.getColor();
	}
	
	@Override
	public void initDefaultCommand() {
		setDefaultCommand(new ColorMatchCommand());
	}
}
