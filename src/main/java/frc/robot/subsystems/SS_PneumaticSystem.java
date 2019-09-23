/*----------------------------------------------------------------------------*/
/* Copyright (c) 2018 FIRST. All Rights Reserved.                             */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot.subsystems;

import edu.wpi.first.wpilibj.command.Subsystem;
import frc.robot.RobotMap;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.Relay.Value;

/**
 * Add your docs here.
 */
public class SS_PneumaticSystem extends Subsystem {

  Relay shooterSolenoid = new Relay(RobotMap.SHOOTER_SOLENOID_RELAY);
  Relay rechargeSolenoid = new Relay(RobotMap.RECHARGE_SOLENOID_RELAY);

  public void openShooterSolenoid()
  {
    shooterSolenoid.set(Value.kForward);
  }

  public void closeShooterSolenoid() 
  {
    shooterSolenoid.set(Value.kOff);
  }

  public void openRechargeSolenoid() 
  {
    rechargeSolenoid.set(Value.kForward);
  }

  public void closeRechargeSolenoid() 
  {
    rechargeSolenoid.set(Value.kOff);
  }
  
  @Override
  public void initDefaultCommand() {
    
  }
}
