package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DoubleSolenoid;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.C_Drive;

public class SS_DriveTrain extends Subsystem 
{
  //drive motors
  private TalonSRX leftMotorFront;
  private TalonSRX leftMotorRear;
  private TalonSRX rightMotorFront;
  private TalonSRX rightMotorRear;
  //shifting solenoids
  private DoubleSolenoid rightShift;
  private DoubleSolenoid leftShift;
  
  public SS_DriveTrain()
  {
    //init drive motors
    leftMotorFront = new TalonSRX(RobotMap.LF_MOTOR);     // CAN Bus DeviceID 0 is the left drive motor
    leftMotorRear = new TalonSRX(RobotMap.LR_MOTOR);
    rightMotorFront = new TalonSRX(RobotMap.RF_MOTOR);    // CAN Bus DeviceID 1 is the right drive motor
    rightMotorRear = new TalonSRX(RobotMap.RR_MOTOR);
    //init shifting solenoids
    rightShift = new DoubleSolenoid(RobotMap.RIGHT_SHIFT_SOLENOID_FORWARD, RobotMap.RIGHT_SHIFT_SOLENOID_REVERSE);
    leftShift = new DoubleSolenoid(RobotMap.LEFT_SHIFT_SOLENOID_FORWARD, RobotMap.LEFT_SHIFT_SOLENOID_REVERSE);

    //set the shifters to the lower gear
    shift(false);
  }

  @Override
  public void initDefaultCommand() 
  {
    setDefaultCommand(new C_Drive());
  }

  public void timeDrive() {}

  public void drive(double leftSpeed, double rightSpeed)
  {
    leftMotorFront.set(ControlMode.PercentOutput,leftSpeed);
    leftMotorRear.set(ControlMode.PercentOutput, leftSpeed);
    rightMotorFront.set(ControlMode.PercentOutput, -1 * rightSpeed);
    rightMotorRear.set(ControlMode.PercentOutput, -1 * rightSpeed);
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