package frc.robot.commands;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.*;



public class C_Drive extends CommandBase 
{
  private Joystick gamepad;
  
  public C_Drive() 
  {
    // Use requires() here to declare subsystem dependencies
    //requires(Robot.m_subsystem);
    addRequirements(Robot.getDriveTrain());
    gamepad = Robot.getOI().getGamepad();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    //tank drive code   
    // double leftSpeed = Math.pow(gamepad.getRawAxis(OI.LSTICK_Y_AXIS) , 2) * Math.signum(gamepad.getRawAxis(OI.LSTICK_Y_AXIS)); // make forward stick positive
    // double rightSpeed = Math.pow(gamepad.getRawAxis(OI.RSTICK_Y_AXIS) , 2) * Math.signum(gamepad.getRawAxis(OI.RSTICK_Y_AXIS));

    shift();

    //arcade drive
    double steer = gamepad.getRawAxis(OI.LSTICK_X_AXIS);
    double speed = gamepad.getRawAxis(OI.RTRIGGER_AXIS) - gamepad.getRawAxis(OI.LTRIGGER_AXIS);
    double leftSpeed = 0;
    double rightSpeed = 0;

    //calculations for arcade drive
    if(steer > 0.2)
    {
      leftSpeed = speed;
      // rightSpeed = (0.5 - Math.abs(steer)) * speed * 2;
      rightSpeed = -speed;
    }
    else if(steer < -0.2)
    {
      //leftSpeed = (0.5 - Math.abs(steer)) * speed * 2;
      rightSpeed = speed;
      leftSpeed = -speed;
    }
    else
    {
      leftSpeed = speed;
      rightSpeed = speed;
    }

    Robot.getDriveTrain().drive(leftSpeed, rightSpeed);
  }

  private void shift()
  {
    //the order is intentional since if both bumpers are pressed, it will shift down
    if(gamepad.getRawButton(OI.BUTTON_R_BUMPER))
    {
      Robot.getDriveTrain().shift(true);
    }
    if(gamepad.getRawButton(OI.BUTTON_L_BUMPER))
    {
      Robot.getDriveTrain().shift(false);
    }
  }
}
