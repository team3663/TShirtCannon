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

/**
 * An example command.  You can replace me with your own command.
 */
public class C_RotateBarrel extends Command
{
  private SS_Barrel barrel;
  private static final double DEADZONE = 0.05;
  private ElapsedTime currentTimer;

  public C_RotateBarrel() 
  {
    requires(Robot.ss_Barrel);
    barrel = Robot.ss_Barrel;
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
    double speed = deadzone(Robot.oi.gamepadOne.getRawAxis(OI.RSTICK_Y_AXIS));

    if(!safeCurrent() || speed == 0)
    {
      barrel.stop();
    }
    else if(barrel.getUpperLimitHit() && speed < 0)
    {
      barrel.move(speed);
    }
    else if(barrel.getLowerLimitHit() && speed > 0)
    {
      barrel.move(speed);
    }

  }

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
    if(barrel.isSafeVoltage())
    {
      currentTimer.reset();
      return true;
    }
    else if(currentTimer.getElapsedSeconds() <= 1)
    {
      return true;
    }
    return false;
  }
}
