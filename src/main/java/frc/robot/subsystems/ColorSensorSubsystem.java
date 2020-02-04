package frc.robot.subsystems;

import com.revrobotics.ColorMatch;
import edu.wpi.first.wpilibj.util.Color;

import frc.robot.commands.ColorMatchCommand;
import edu.wpi.first.wpilibj.command.Subsystem;

public class ColorSensorSubsystem extends Subsystem {
  public final ColorMatch m_colorMatcher = new ColorMatch();
  public final Color kBlueTarget = ColorMatch.makeColor(0.143, 0.427, 0.429);
  public final Color kGreenTarget = ColorMatch.makeColor(0.197, 0.561, 0.240);
  public final Color kRedTarget = ColorMatch.makeColor(0.561, 0.232, 0.114);
  public final Color kYellowTarget = ColorMatch.makeColor(0.361, 0.524, 0.113);

  @Override
  public void initDefaultCommand() {
    setDefaultCommand(new ColorMatchCommand());
  }
}
