/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj.command.Command;
import frc.robot.Robot;
import frc.robot.subsystems.SS_PneumaticSystem;
import frc.robot.util.ElapsedTime;;

public class C_Fire extends Command {

  private SS_PneumaticSystem pneumaticSystem;
  private ElapsedTime timer;
  public C_Fire() {
    requires(Robot.getPneumaticSystem());
    pneumaticSystem = Robot.getPneumaticSystem();
    timer = new ElapsedTime();
  }

  @Override
  protected void initialize() {
    pneumaticSystem.openShooterSolenoid();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() {
    return timer.getElapsedSeconds() > 1;
  }

  @Override
  protected void end() {
    pneumaticSystem.closeShooterSolenoid();
  }

  @Override
  protected void interrupted() {
    end();
  }
}