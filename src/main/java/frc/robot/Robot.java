package frc.robot;

// import edu.wpi.cscore.AxisCamera;
// import edu.wpi.cscore.CameraServerJNI;
// import edu.wpi.cscore.MjpegServer;
// import edu.wpi.cscore.UsbCamera;
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
  public static SendableChooser<String> cameraChoice = new SendableChooser<String>();

  //public static UsbCamera camera = new UsbCamera("Camera",0);
  


  //private String camera;

  @Override
  public void robotInit() {
//camera.setFPS(20);
//camera.setResolution(320, 240); //320 = width, 240 = height
    //Actuator Initialize
    Robot.oi.dumpActuator.setSpeed(1);
    Robot.oi.cameraServo.setPosition(.1);
    //Calibrate the Gyro
    Robot.oi.gyro.calibrate();
  


    //adds color chooices to smart dashboard
    colorChoice.setDefaultOption("Yellow", "Green");
    colorChoice.addOption("Green", "Yellow");
    colorChoice.addOption("Red", "Blue");
    colorChoice.addOption("Blue", "Red");
    SmartDashboard.putData("Choose Your Color", colorChoice);
    SmartDashboard.updateValues();
    
    //starts the webcam
    //serverOne.startAutomaticCapture();
    //serverOne.startAutomaticCapture(0);
    CameraServer.getInstance().startAutomaticCapture();
  }

  @Override
  public void autonomousInit() {
    m_timer.reset();
    m_timer.start();
  }

  @Override
  public void autonomousPeriodic() {
     if (m_timer.get() > 0.0 && m_timer.get() < 1.0){
      Robot.driveSubsystem.m_robotDrive.arcadeDrive(0, .7);
    }
    else if (m_timer.get() > 1.0 && m_timer.get() < 4.0){
      Robot.driveSubsystem.m_robotDrive.arcadeDrive(0.5, 0.0);
    }
    else {
      // stop robot
      Robot.driveSubsystem.m_robotDrive.stopMotor();
    }
  }

  @Override
  public void teleopInit() {
    Robot.driveSubsystem.m_robotDrive.setExpiration(1.0);
  }

  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();
    //int POV = Robot.oi.stick.getPOV();
    //SmartDashboard.putNumber("POV: ", POV);

    }

  @Override
  public void testPeriodic() {
  }
}
