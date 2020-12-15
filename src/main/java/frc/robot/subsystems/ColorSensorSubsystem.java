package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.commands.ColorMatchCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ColorSensorSubsystem extends Subsystem {
  //Establishes what RGB values are equal to what color
  public final ColorMatch m_colorMatcher = new ColorMatch();
  public final Color kBlueTarget = ColorMatch.makeColor(0.156, 0.454, 0.389);
  public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public final Color kRedTarget = ColorMatch.makeColor(0.471, 0.382, 0.147);
  public final Color kYellowTarget = ColorMatch.makeColor(0.325, 0.552, 0.123);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ColorMatchCommand());
  }
}
