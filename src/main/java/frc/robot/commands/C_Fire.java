/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.SS_PneumaticSystem;

public class C_Fire extends CommandBase {

  private SS_PneumaticSystem pneumaticSystem;
  public C_Fire() {
    addRequirements(Robot.getPneumaticSystem());
    pneumaticSystem = Robot.getPneumaticSystem();
  }

  @Override
  public void initialize() {
    pneumaticSystem.openShooterSolenoid();
  }

  @Override
  public void end(boolean interrupted) {
    pneumaticSystem.closeShooterSolenoid();
  }
}
