package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;


/**
 * An example command.  You can replace me with your own command.
 */
public class C_Drive extends Command 
{
  
  public C_Drive() 
  {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    requires(Robot.ss_DriveTrain);
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    // move drive code from Robot.java here....
      
    double leftSpeed = Math.pow(Robot.oi.gamepadOne.getRawAxis(OI.LSTICK_Y_AXIS) , 2) * Math.signum(Robot.oi.gamepadOne.getRawAxis(OI.LSTICK_Y_AXIS)); // make forward stick positive
    double rightSpeed = Math.pow(Robot.oi.gamepadOne.getRawAxis(OI.RSTICK_Y_AXIS) , 2) * Math.signum(Robot.oi.gamepadOne.getRawAxis(OI.RSTICK_Y_AXIS));

    Robot.ss_DriveTrain.drive(leftSpeed, rightSpeed);
  }

  // Make this return true when this Command no longer needs to run execute()
  @Override
  protected boolean isFinished() 
  {
    return false;
  }

  // Called once after isFinished returns true
  @Override
  protected void end() {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  @Override
  protected void interrupted() {}
}
