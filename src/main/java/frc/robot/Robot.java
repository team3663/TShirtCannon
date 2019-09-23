/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.networktables.NetworkTableEntry;
import edu.wpi.first.wpilibj.DriverStation;
//import edu.wpi.first.wpilibj.Joystick;  // moved to OI.java
import edu.wpi.first.wpilibj.SpeedControllerGroup;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardTab;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.subsystems.*;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.*;


/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the TimedRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the build.gradle file in the
 * project.
 */
public class Robot extends TimedRobot 
{
  private static SS_DriveTrain ss_DriveTrain;
  private static SS_Barrel ss_Barrel;
  private static SS_Billboard ss_Billboard;
  private static SS_PneumaticSystem ss_PneumaticSystem;
  private static OI oi;

  SendableChooser<Command> chooser = new SendableChooser<>();

  //WPI_TalonSRX _talonL1 = new WPI_TalonSRX(RobotMap.L1_MOTOR);
  //WPI_TalonSRX _talonL2 = new WPI_TalonSRX(RobotMap.L2_MOTOR);
  //SpeedControllerGroup _motorL = new SpeedControllerGroup(_talonL1, _talonL2);
  //WPI_TalonSRX _talonR1 = new WPI_TalonSRX(RobotMap.R1_MOTOR);
  //WPI_TalonSRX _talonR2 = new WPI_TalonSRX(RobotMap.R2_MOTOR);
  //SpeedControllerGroup _motorR = new SpeedControllerGroup(_talonR1, _talonR2);
  //DifferentialDrive _drive = new DifferentialDrive(_motorL, _motorR);
  //Joystick _joystick = new Joystick(0);  // moved to OI.java
  //ShuffleboardTab tab = Shuffleboard.getTab("HelloWorld");
  //NetworkTableEntry distanceEntry = tab.add("Distance to target", 0).getEntry();


  /**
   * This function is run when the robot is first started up and should be
   * used for any initialization code.
   */
  @Override
  public void robotInit() 
  {
    
    ss_DriveTrain = new SS_DriveTrain();
    ss_Barrel = new SS_Barrel();
    ss_Billboard = new SS_Billboard();
    ss_PneumaticSystem = new SS_PneumaticSystem();

    oi = new OI(); // oi must be initilized last PLEASE
  }

  public static SS_DriveTrain getDriveTrain() 
  {
    return ss_DriveTrain;
  }

  public static SS_Barrel getBarrel()
  {
    return ss_Barrel;
  }

  public static SS_Billboard getBillboard() {
    return ss_Billboard;
  }

  public static SS_PneumaticSystem getPneumaticSystem() {
    return ss_PneumaticSystem;
  }

  public static OI getOI() 
  {
    return oi;
  }
 
  /**
   * This function is called every robot packet, no matter the mode. Use
   * this for items like diagnostics that you want ran during disabled,
   * autonomous, teleoperated and test.
   *
   * <p>This runs after the mode specific periodic functions, but before
   * LiveWindow and SmartDashboard integrated updating.
   */
  @Override
  public void robotPeriodic() {}

  /**
   * This function is called once each time the robot enters Disabled mode.
   * You can use it to reset any subsystem information you want to clear when
   * the robot is disabled.
   */
  @Override
  public void disabledInit() {}

  @Override
  public void disabledPeriodic() {
    Scheduler.getInstance().run();
  }

  /**
   * This autonomous (along with the chooser code above) shows how to select
   * between different autonomous modes using the dashboard. The sendable
   * chooser code works with the Java SmartDashboard. If you prefer the
   * LabVIEW Dashboard, remove all of the chooser code and uncomment the
   * getString code to get the auto name from the text box below the Gyro
   *
   * <p>You can add additional auto modes by adding additional commands to the
   * chooser code above (like the commented example) or additional comparisons
   * to the switch structure below with additional strings & commands.
   */
  @Override
  public void autonomousInit() 
  {
    //m_autonomousCommand = chooser.getSelected();

    /*
     * String autoSelected = SmartDashboard.getString("Auto Selector",
     * "Default"); switch(autoSelected) { case "My Auto": autonomousCommand
     * = new MyAutoCommand(); break; case "Default Auto": default:
     * autonomousCommand = new ExampleCommand(); break; }
     */

    // schedule the autonomous command (example)
    // if (m_autonomousCommand != null) {
    //   m_autonomousCommand.start();
    // }
  }

  /**
   * This function is called periodically during autonomous.
   */
  @Override
  public void autonomousPeriodic() 
  {
    //Scheduler.getInstance().run();
  }

  @Override
  public void teleopInit() 
  {
    // // This makes sure that the autonomous stops running when
    // // teleop starts running. If you want the autonomous to
    // // continue until interrupted by another command, remove
    // // this line or comment it out.
    // if(m_autonomousCommand != null) 
    // {
    //   m_autonomousCommand.cancel();
    // }

    // SmartDashboard.putData("Auto mode", chooser);
    // SmartDashboard.putNumber("time: ", System.currentTimeMillis());
    // SmartDashboard.putNumber("xSpeed: ", 0);
    // SmartDashboard.putNumber("zRotation: ", 0);

    // /* factory default values */
    // _talonL1.configFactoryDefault();
    // _talonL2.configFactoryDefault();
    // _talonR1.configFactoryDefault();
    // _talonR2.configFactoryDefault();

    // /* flip values so robot moves forward when stick-forward/LEDs-green */
    // _motorL.setInverted(true); // <<<<<< Adjust this
    // _motorR.setInverted(false); // <<<<<< Adjust this

    // /*
    //  * WPI drivetrain classes defaultly assume left and right are opposite. call
    //  * this so we can apply + to both sides when moving forward. DO NOT CHANGE
    //  */
    // _drive.setRightSideInverted(false);
  }

  /**
   * This function is called periodically during operator control.
   */
  @Override
  public void teleopPeriodic() {
    Scheduler.getInstance().run();

    /*  This section is moved to C_Drive.java
      
    double xSpeed = _joystick.getRawAxis(RobotMap.LSTICK_Y_AXIS) * -1; // make forward stick positive
    double zRotation = _joystick.getRawAxis(RobotMap.LSTICK_X_AXIS); // WPI Drivetrain uses positive=> right

    _drive.arcadeDrive(xSpeed, zRotation);

    SmartDashboard.putNumber("time: ", System.currentTimeMillis());


    // hold down btn1 to print stick values 
    if (_joystick.getRawButton(RobotMap.BUTTON_A)) {
      
      System.out.println("time: " + System.currentTimeMillis()
            + "     xSpeed: " + xSpeed + "     zRotation: " + zRotation);
      
      //SmartDashboard.putNumber("time: ", System.currentTimeMillis());
      //SmartDashboard.putNumber("xSpeed: ", xSpeed);
      //SmartDashboard.putNumber("zRotation: ", zRotation);
      
      tab.add("time", System.currentTimeMillis());
      tab.add("xSpeed", xSpeed);
      tab.add("zRotation",zRotation);
      distanceEntry.setDouble(System.currentTimeMillis());
    }
    */
  }

  /**
   * This function is called periodically during test mode.
   */
  @Override
  public void testPeriodic() {
  }
}
