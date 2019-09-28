/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;
import javax.swing.text.StyleContext.SmallAttributeSet;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.OI;
import frc.robot.Robot;
import frc.robot.subsystems.SS_Barrel;
import frc.robot.util.ElapsedTime;


public class C_RotateBarrel extends Command
{
  private SS_Barrel barrel;
  private static final double DEADZONE = 0.15;
  private ElapsedTime currentTimer;
  private boolean cutPower = false;

  public C_RotateBarrel() 
  {
    requires(Robot.getBarrel());
    barrel = Robot.getBarrel();
    currentTimer = new ElapsedTime();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() 
  {
    currentTimer.reset();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    double speed = deadzone(Robot.getOI().getGamepad().getRawAxis(OI.RSTICK_Y_AXIS));

    //telemetry
    SmartDashboard.putBoolean("upper Limit unsafe", barrel.getUpperLimitHit() && speed > 0);
    SmartDashboard.putBoolean("lower Limit unsafe", barrel.getLowerLimitHit() && speed < 0);
    SmartDashboard.putBoolean("not moving barrel", speed == 0);
    SmartDashboard.putBoolean("barrel current unsafe", !safeCurrent());

    if(!safeCurrent() //current is unsafe
      || speed == 0 //speed is 0
      || (barrel.getUpperLimitHit() && speed > 0) //at upper limit and speed is going up
      || (barrel.getLowerLimitHit() && speed < 0)) //at lower limit and speed is going down
    {
      barrel.stop();
      SmartDashboard.putBoolean("moveBarrel", false);
    }
    else
    {
      barrel.moveConstant(speed);
      SmartDashboard.putBoolean("moveBarrel", true);
    }
  }

  // (condiion 1)
  // || (condition)

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {
  }

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {
  }

  private double deadzone(double input)
  {
    if(Math.abs(input) <= DEADZONE)
    {
      return 0;
    }
    return input;
  }

  private boolean safeCurrent()
  {
    //stop cutting the power if the joystick returns to zero
    if(deadzone(Robot.getOI().getGamepad().getRawAxis(OI.RSTICK_Y_AXIS)) == 0)
    {
      cutPower = false;
    }
    else if(cutPower)
    {
      return false;
    }

    //if the current is safe, reset the unsafe current timer
    if(barrel.isSafeCurrent())
    {
      currentTimer.reset();
      return true;
    }
    else if(currentTimer.getElapsedSeconds() >= 1.5)
    {
      //if the power is unsafe for too long, it will cut power to the barrel angle motor
      cutPower = true;
      return false;
    }
    return true;
  }
}
