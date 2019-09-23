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

public class C_Recharge extends Command {
  private SS_PneumaticSystem pneumaticSystem;

  public C_Recharge() {
    requires(Robot.getPneumaticSystem());
    pneumaticSystem = Robot.getPneumaticSystem();
  }

  @Override
  protected void initialize() {
    pneumaticSystem.openRechargeSolenoid();
  }

  
  @Override
  protected boolean isFinished() {
    return false;
  }

  @Override
  protected void end() {
    pneumaticSystem.closeRechargeSolenoid();
  }

  @Override
  protected void interrupted() {
    end();
  }
}
