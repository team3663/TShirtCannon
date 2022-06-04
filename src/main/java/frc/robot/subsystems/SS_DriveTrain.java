package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;

import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.PneumaticsModuleType;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class SS_DriveTrain extends SubsystemBase 
{
  //drive motors
  private TalonSRX leftMotorFront;
  private TalonSRX leftMotorRear;
  private TalonSRX rightMotorFront;
  private TalonSRX rightMotorRear;
  //shifting solenoids
  private DoubleSolenoid rightShift;
  private DoubleSolenoid leftShift;

  private int ampCap = 25;
  
  public SS_DriveTrain()
  {
    //init drive motors
    leftMotorFront = new TalonSRX(RobotMap.LF_MOTOR);     // CAN Bus DeviceID 0 is the left drive motor
    leftMotorRear = new TalonSRX(RobotMap.LR_MOTOR);
    rightMotorFront = new TalonSRX(RobotMap.RF_MOTOR);    // CAN Bus DeviceID 1 is the right drive motor
    rightMotorRear = new TalonSRX(RobotMap.RR_MOTOR);
    //init shifting solenoids
    rightShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.RIGHT_SHIFT_SOLENOID_FORWARD, RobotMap.RIGHT_SHIFT_SOLENOID_REVERSE);
    leftShift = new DoubleSolenoid(PneumaticsModuleType.CTREPCM, RobotMap.LEFT_SHIFT_SOLENOID_FORWARD, RobotMap.LEFT_SHIFT_SOLENOID_REVERSE);

    //set the shifters to the lower gear
    shift(false);
  }

  public void timeDrive() {}

  public void drive(double leftSpeed, double rightSpeed)
  {
    leftMotorFront.set(ControlMode.PercentOutput,leftSpeed/2);
    leftMotorRear.set(ControlMode.PercentOutput, leftSpeed/2);
    rightMotorFront.set(ControlMode.PercentOutput, -1 * rightSpeed/2);
    rightMotorRear.set(ControlMode.PercentOutput, -1 * rightSpeed/2);
  }

  public void shift(boolean shiftUp)
  {
    if(shiftUp)
    {
      rightShift.set(DoubleSolenoid.Value.kForward);
      leftShift.set(DoubleSolenoid.Value.kForward);
    }
    else
    {
      rightShift.set(DoubleSolenoid.Value.kReverse);
      leftShift.set(DoubleSolenoid.Value.kReverse);
    }
  }
}