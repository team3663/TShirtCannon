/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Robot;
import frc.robot.subsystems.SS_Billboard;

public class C_Billboard extends CommandBase {

  private SS_Billboard billboard;
  public C_Billboard() {
    addRequirements(Robot.getBillboard());
    billboard = Robot.getBillboard();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() {
    
     billboard.FlashyLineTwo.set(Robot.getOI().getGamepad().getRawButton(4));
     billboard.FlashyLineOne.set(Robot.getOI().getGamepad().getRawButton(4));
  }

}
