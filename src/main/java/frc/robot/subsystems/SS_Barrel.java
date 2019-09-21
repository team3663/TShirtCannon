/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/
package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.TalonSRX;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.RobotMap;
import frc.robot.commands.C_RotateBarrel;
import edu.wpi.first.wpilibj.Encoder;

public class SS_Barrel extends Subsystem 
{
    //0 degrees is forwards, 90 degrees is up
    //positive power is upwards
    private static final int DEFAULT_UPPER_SOFT_LIMIT = 90;
    private static final int DEFAULT_LOWER_SOFT_LIMIT = 0;
    private static final double SAFE_CURRENT = 3;
    private static final int ANGLE_GEAR_RATIO = 1; //TODO
    private static final double DEFAULT_SPEED = 0.25;

    //motors
    private TalonSRX leadMotor;
    private TalonSRX followMotor;

    //encoder
    private final Encoder encoder = new Encoder(RobotMap.BARREL_ENCODER_DIO_ONE, RobotMap.BARREL_ENCODER_DIO_TWO);

    //limit switches
    private DigitalInput upperLimit;
    private DigitalInput lowerLimit;

    //Encoder
    //private ;

    //software limits
    private int upperSoftLimit;
    private int lowerSoftLimit;

    public SS_Barrel()
    {
        //init motors
        leadMotor = new TalonSRX(RobotMap.LEAD_BARREL_ANGLE_MOTOR);
        followMotor = new TalonSRX(RobotMap.FOLLOW_BARREL_ANGLE_MOTOR);
        leadMotor.setNeutralMode(NeutralMode.Brake);
        followMotor.setNeutralMode(NeutralMode.Brake);

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
        setDefaultCommand(new C_RotateBarrel());
    }

    public void moveConstant(double speed)
    {
        leadMotor.set(ControlMode.PercentOutput, Math.signum(speed) * DEFAULT_SPEED);
    }

    public void move(double speed)
    {
        leadMotor.set(ControlMode.PercentOutput, speed);
    }

    public void setBrakeMode(boolean isBrakeMode)
    {
        if(isBrakeMode) 
        {
            leadMotor.setNeutralMode(NeutralMode.Brake);
        } 
        else 
        {
            leadMotor.setNeutralMode(NeutralMode.Coast);
        }
    }

    public void stop()
    {
        moveConstant(0);
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
        // SmartDashboard.putNumber("Barrel Encoder", encoder.get());
        // return encoder.get() * ANGLE_GEAR_RATIO;
        return 20; //TODO
    }
    
    public boolean isSafeCurrent()
    {
        //take the avarage current of the two motors and see if it is a safe current
        SmartDashboard.putNumber("Barrel Current", (leadMotor.getOutputCurrent() + followMotor.getOutputCurrent()) / 2);
        return (leadMotor.getOutputCurrent() + followMotor.getOutputCurrent()) / 2 <= SAFE_CURRENT;
    }
}
