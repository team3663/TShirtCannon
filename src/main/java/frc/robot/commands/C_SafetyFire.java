/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.*;
import frc.robot.util.*;

public class C_SafetyFire extends CommandBase {
  private SS_PneumaticSystem pneumaticSystem;
  private ElapsedTime timer;
  public C_SafetyFire() {
    addRequirements(Robot.getPneumaticSystem());
    pneumaticSystem = Robot.getPneumaticSystem();
    timer = new ElapsedTime();
  }

  @Override
  public void initialize() {
    pneumaticSystem.openShooterSolenoid();
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  public boolean isFinished() {
    boolean timeOut = timer.getElapsedSeconds() > 1;

    //gets B and X buttons
    boolean safetyButtons = Robot.getOI().getGamepad().getRawButton(2) && Robot.getOI().getGamepad().getRawButton(3);
    
    return timeOut || !safetyButtons;
  }

  @Override
  public void end(boolean interrupted) {
    pneumaticSystem.closeShooterSolenoid();
  }
}
