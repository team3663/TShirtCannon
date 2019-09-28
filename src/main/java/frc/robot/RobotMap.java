/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

/**
 * The RobotMap is a mapping from the ports sensors and actuators are wired into
 * to a variable name. This provides flexibility changing wiring, makes checking
 * the wiring easier and significantly reduces the number of magic numbers
 * floating around.
 */
public class RobotMap 
{
  // For example to map the left and right motors, you could define the
  // following variables to use with your drivetrain subsystem.
  // public static int leftMotor = 1;
  // public static int rightMotor = 2;

  //gamepads
  public static final int GAMEPAD = 0;

  //motors
  public static final int LR_MOTOR = 0;
  public static final int LF_MOTOR = 1;
  public static final int RR_MOTOR = 2;
  public static final int RF_MOTOR = 3;

  public static final int LEAD_BARREL_ANGLE_MOTOR = 15; //TODO
  public static final int FOLLOW_BARREL_ANGLE_MOTOR = 16; //TODO

  //DIO
  public static final int UPPER_ARM_SWITCH = 0;
  public static final int LOWER_ARM_SWITCH = 1;
  public static final int BARREL_ENCODER_A = 5; //TODO
  public static final int BARREL_ENCODER_B = 6; //TODO
  public static final int FLASHY_LINE_ONE = 7; //TODO
  public static final int FLASHY_LINE_TWO = 8; //TODO


  //RELAY
  public static final int SHOOTER_SOLENOID_RELAY = 0;
  public static final int RECHARGE_SOLENOID_RELAY = 1;

  //pneumatic shifters
  public static final int LEFT_SHIFT_SOLENOID_FORWARD = 0;
  public static final int LEFT_SHIFT_SOLENOID_REVERSE = 1;
  public static final int RIGHT_SHIFT_SOLENOID_FORWARD = 2;
  public static final int RIGHT_SHIFT_SOLENOID_REVERSE = 3;

  // If you are using multiple modules, make sure to define both the port
  // number and the module. For example you with a rangefinder:
  // public static int rangefinderPort = 1;
  // public static int rangefinderModule = 1;
}
