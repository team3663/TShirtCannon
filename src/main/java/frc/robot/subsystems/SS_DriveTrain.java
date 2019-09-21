package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.C_Drive;

public class SS_DriveTrain extends Subsystem 
{

  TalonSRX leftMotorFront = new TalonSRX(RobotMap.LF_MOTOR);     // CAN Bus DeviceID 0 is the left drive motor
  TalonSRX leftMotorRear = new TalonSRX(RobotMap.LR_MOTOR);
  TalonSRX rightMotorFront = new TalonSRX(RobotMap.RF_MOTOR);    // CAN Bus DeviceID 1 is the right drive motor
  TalonSRX rightMotorRear = new TalonSRX(RobotMap.RR_MOTOR); 

  @Override
  public void initDefaultCommand() 
  {
    setDefaultCommand(new C_Drive());
  }

  public void timeDrive() {}

  public void drive(double leftSpeed, double rightSpeed)
  {
    leftMotorFront.set(ControlMode.PercentOutput, -1 * leftSpeed);
    leftMotorRear.set(ControlMode.PercentOutput, -1 * leftSpeed);
    rightMotorFront.set(ControlMode.PercentOutput, rightSpeed);
    rightMotorRear.set(ControlMode.PercentOutput, rightSpeed);
  }
}