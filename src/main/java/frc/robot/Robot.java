package frc.robot;

import edu.wpi.first.cameraserver.CameraServer;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.ColorSensorSubsystem;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.WheelMotorSubsystem;

public class Robot extends TimedRobot {

  private final Timer m_timer = new Timer();
  public static DriveSubsystem driveSubsystem = new DriveSubsystem();
  public static ColorSensorSubsystem colorSensorSubsystem = new ColorSensorSubsystem();
  public static WheelMotorSubsystem wheelMotorSubsystem = new WheelMotorSubsystem();
  public static OI oi = new OI();

  public static SendableChooser<String> colorChoice = new SendableChooser<String>();

  @Override
  public void robotInit() {
    //adds color chooices to smart dashboard
    colorChoice.setDefaultOption("Yellow", "Yellow");
    colorChoice.addOption("Green", "Green");
    colorChoice.addOption("Red", "Red");
    colorChoice.addOption("Blue", "Blue");
    SmartDashboard.putData("Choose Your Color", colorChoice);
    SmartDashboard.updateValues();
    
    //starts the webcam
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
    if (m_timer.get() < 5.0) {
      // drive forwards half speed
      Robot.driveSubsystem.m_robotDrive.arcadeDrive(0.5, 0.0);
    } else {
      // stop robot
      Robot.driveSubsystem.m_robotDrive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
  }

  @Override
  public void testPeriodic() {
  }

}
