/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import frc.robot.commands.C_Billboard;

/**
 * Add your docs here.
 */
public class SS_Billboard extends Subsystem {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DigitalOutput FlashyLineOne = new DigitalOutput(RobotMap.FLASHY_LINE_ONE);
  public DigitalOutput FlashyLineTwo = new DigitalOutput(RobotMap.FLASHY_LINE_TWO);

  @Override
  public void initDefaultCommand() 
  {
    setDefaultCommand(new C_Billboard());
  }
}
