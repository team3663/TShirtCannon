/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import javax.lang.model.util.ElementScanner6;

import edu.wpi.first.wpilibj.command.Command;
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


    if(!safeCurrent() || speed == 0 //current is unsafe or speed is 0
      || (barrel.getUpperLimitHit() && speed > 0) //at upper limit and speed is going up
      || (barrel.getLowerLimitHit() && speed < 0)) //at lower limit and speed is going down
    {
      barrel.stop();
    }
    else
    {
      barrel.move(speed);
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
      cutPower = false;
      return 0;
    }
    return input;
  }

  private boolean safeCurrent()
  {
    if(cutPower)
    {
      return false;
    }
    else if(barrel.isSafeVoltage())
    {
      currentTimer.reset();
      return true;
    }
    else if(currentTimer.getElapsedSeconds() <= 1)
    {
      return true;
    }
    cutPower = true;
    return false;
  }
}
