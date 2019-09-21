package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.*;



public class C_Drive extends Command 
{
  private Joystick gamepad;
  public C_Drive() 
  {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    requires(Robot.getDriveTrain());
    gamepad = Robot.getOI().getGamepad();
  }

  // Called just before this Command runs the first time
  @Override
  protected void initialize() {}

  // Called repeatedly when this Command is scheduled to run
  @Override
  protected void execute() 
  {
    //tank drive code   
    // double leftSpeed = Math.pow(gamepad.getRawAxis(OI.LSTICK_Y_AXIS) , 2) * Math.signum(gamepad.getRawAxis(OI.LSTICK_Y_AXIS)); // make forward stick positive
    // double rightSpeed = Math.pow(gamepad.getRawAxis(OI.RSTICK_Y_AXIS) , 2) * Math.signum(gamepad.getRawAxis(OI.RSTICK_Y_AXIS));

    //arcade drive
    double steer = gamepad.getRawAxis(OI.LSTICK_X_AXIS);
    double speed = gamepad.getRawAxis(OI.RTRIGGER_AXIS) - gamepad.getRawAxis(OI.LTRIGGER_AXIS);
    double leftSpeed = 0;
    double rightSpeed = 0;

    //calculations for arcade drive
    if(steer > 0)
    {
      leftSpeed = speed;
      rightSpeed = (0.5 - Math.abs(steer)) * speed * 2;
    }
    else if(steer < 0)
    {
      leftSpeed = (0.5 - Math.abs(steer)) * speed * 2;
      rightSpeed = speed;
    }
    else
    {
      leftSpeed = speed;
      rightSpeed = speed;
    }

    Robot.getDriveTrain().drive(leftSpeed, rightSpeed);
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
