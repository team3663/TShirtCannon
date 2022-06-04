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

import edu.wpi.first.wpilibj.AnalogPotentiometer;
import edu.wpi.first.wpilibj.DigitalInput;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

public class SS_Barrel extends SubsystemBase 
{
    //0 degrees is forwards, 90 degrees is up
    //positive power is upwards
    private static final int DEFAULT_UPPER_SOFT_LIMIT = 100; //TODO
    private static final int DEFAULT_LOWER_SOFT_LIMIT = -100; //TODO
    private static final double SAFE_CURRENT = 5;
    private static final double DEFAULT_SPEED = 0.15;
    //potentiometer constants
    private static final double POT_FULL_RANGE = 0;
    private static final double POT_OFFSET = 360;
    private static final int LIMIT_BUFFER = 2;

    //motors
    private TalonSRX leadMotor;
    private TalonSRX followMotor;

    //limit switches
    private DigitalInput upperLimit;
    private DigitalInput lowerLimit;

    //software limits
    private double upperSoftLimit;
    private double lowerSoftLimit;

    //Potentiometer
    private AnalogPotentiometer anglePot;

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

        //init potentiometer
        //first parameter: channel    The analog channel this potentiometer is plugged into.
        //second parameter: fullRange The scaling to multiply the fraction by to get a meaningful unit.
        //third parameter: offset     The offset to add to the scaled value for controlling the zero value
        anglePot = new AnalogPotentiometer(RobotMap.BARREL_ANGLE_POT, POT_FULL_RANGE, POT_OFFSET);
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
            followMotor.setNeutralMode(NeutralMode.Brake);
        } 
        else 
        {
            leadMotor.setNeutralMode(NeutralMode.Coast);
            followMotor.setNeutralMode(NeutralMode.Coast);
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
            upperSoftLimit = getAngle() - LIMIT_BUFFER;
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
            lowerSoftLimit = getAngle() + LIMIT_BUFFER;
            return true;
        }
        else if(getAngle() <= lowerSoftLimit)
        {
            return true;
        }
        return false;
    }

    public double getAngle()
    {
        return anglePot.get();
    }
    
    public boolean isSafeCurrent()
    {
        //take the avarage current of the two motors and see if it is a safe current
        SmartDashboard.putNumber("Barrel Current", (leadMotor.getOutputCurrent() + followMotor.getOutputCurrent()) / 2);
        return (leadMotor.getOutputCurrent() + followMotor.getOutputCurrent()) / 2 <= SAFE_CURRENT;
    }
}
