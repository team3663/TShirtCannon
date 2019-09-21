/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;

public class SS_Arm extends Subsystem 
{
    //0 degrees is forwards, 90 degrees is up
    private static final int DEFAULT_UPPER_SOFT_LIMIT = 90;
    private static final int DEFAULT_LOWER_SOFT_LIMIT = 0;

    //motors
    private TalonSRX leadMotor;
    private TalonSRX followMotor;

    //limit switches
    private DigitalInput upperLimit;
    private DigitalInput lowerLimit;

    //Encoder
    //private ;

    //software limits
    private int upperSoftLimit;
    private int lowerSoftLimit;

    public SS_Arm()
    {
        //init motors
        leadMotor = new TalonSRX(RobotMap.LEAD_ARM_MOTOR);
        followMotor = new TalonSRX(RobotMap.FOLLOW_ARM_MOTOR);
        followMotor.follow(leadMotor);

        //init limit switches
        upperLimit = new DigitalInput(RobotMap.UPPER_ARM_SWITCH);
        lowerLimit = new DigitalInput(RobotMap.LOWER_ARM_SWITCH);

        //init software limits
        upperSoftLimit = DEFAULT_UPPER_SOFT_LIMIT;
        lowerSoftLimit = DEFAULT_LOWER_SOFT_LIMIT;
    }

    @Override
    public void initDefaultCommand() 
    {
    // Set the default command for a subsystem here.
    // setDefaultCommand(new MySpecialCommand());
    }

    public void move(double speed)
    {
        leadMotor.set(ControlMode.PercentOutput, speed);
    }

    public void stop()
    {
        move(0);
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
        return 0; //TODO
    }
}
