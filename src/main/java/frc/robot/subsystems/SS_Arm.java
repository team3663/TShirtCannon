package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;
import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SS_Arm extends Subsystem 
{
    private static final int DEFAULT_UPPER_SOFT_LIMIT = 90;
    private static final int DEFAULT_LOWER_SOFT_LIMIT = 0;

    //motors
    private CANSparkMax mainMotor;
    private CANSparkMax followMotor;

    //limit switches
    private DigitalInput upperLimit;
    private DigitalInput lowerLimit;

    //angle potentiometer
    private AnalogPotentiometer anglePot;

    //software limits
    private int upperSoftLimit;
    private int lowerSoftLimit;

    public SS_Arm()
    {
        //init motors
        mainMotor = new CANSparkMax(RobotMap.LEAD_ARM_MOTOR, MotorType.kBrushless);
        followMotor = new CANSparkMax(RobotMap.FOLLOW_ARM_MOTOR, MotorType.kBrushless);
        followMotor.follow(mainMotor);

        //init limit switches
        upperLimit = new DigitalInput(RobotMap.UPPER_ARM_SWITCH);
        lowerLimit = new DigitalInput(RobotMap.LOWER_ARM_SWITCH);

        //init angle potentiometer
        anglePot = new AnalogPotentiometer(RobotMap.ARM_POTENTIOMETER, 360, 30); //TODO

        //init software limits
        upperSoftLimit = DEFAULT_UPPER_SOFT_LIMIT;
        lowerSoftLimit = DEFAULT_LOWER_SOFT_LIMIT;
    }

    public void moveArm(double speed)
    {
        mainMotor.set(speed);
    }

    public boolean getUpperLimitHit()
    {
        if(upperLimit.get())
        {
            upperSoftLimit = getAngle();
            return true;
        }
        else if(getAngle() >= upperSoftLimit)
        {
            return true;
        }
        return false;
    }

    public boolean getLowerLimitHit()
    {
        if(lowerLimit.get())
        {
            lowerSoftLimit = getAngle();
            return true;
        }
        else if(getAngle() <= lowerSoftLimit)
        {
            return true;
        }
        return false;
    }

    public int getAngle()
    {
        return (int)anglePot.get(); //TODO
    }

    @Override
    public void initDefaultCommand() 
    {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    }
}
