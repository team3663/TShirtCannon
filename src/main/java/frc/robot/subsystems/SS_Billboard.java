/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.DigitalOutput;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.RobotMap;

/**
 * Add your docs here.
 */
public class SS_Billboard extends SubsystemBase {
  // Put methods for controlling this subsystem
  // here. Call these from Commands.

  public DigitalOutput FlashyLineOne = new DigitalOutput(RobotMap.FLASHY_LINE_ONE);
  public DigitalOutput FlashyLineTwo = new DigitalOutput(RobotMap.FLASHY_LINE_TWO);
}
