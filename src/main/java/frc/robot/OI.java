/*----------------------------------------------------------------------------*/
/* Copyright (c) 2017-2018 FIRST. All Rights Reserved.                        */
/* Open Source Software - may be modified and shared by FRC teams. The code   */
/* must be accompanied by the FIRST BSD license file in the root directory of */
/* the project.                                                               */
/*----------------------------------------------------------------------------*/

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.buttons.*;
import edu.wpi.first.wpilibj.*;
import frc.robot.commands.*;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
  //// CREATING BUTTONS
  // One type of button is a joystick button which is any button on a
  //// joystick.
  // You create one by telling it which joystick it's on and which button
  // number it is.
  // Joystick stick = new Joystick(port);
  // Button button = new JoystickButton(stick, buttonNumber);

  // There are a few additional built in buttons you can use. Additionally,
  // by subclassing Button you can create custom triggers and bind those to
  // commands the same as any other Button.

  //// TRIGGERING COMMANDS WITH BUTTONS
  // Once you have a button, it's trivial to bind it to a button in one of
  // three ways:

  // Start the command when the button is pressed and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenPressed(new ExampleCommand());

  // Run the command while the button is being held down and interrupt it once
  // the button is released.
  // button.whileHeld(new ExampleCommand());

  // Start the command when the button is released and let it run the command
  // until it is finished as determined by it's isFinished method.
  // button.whenReleased(new ExampleCommand());

  public static final int GAMEPAD = 0;
  //josyticks
  public static final int LSTICK_X_AXIS = 0;
  public static final int LSTICK_Y_AXIS = 1;
  public static final int RSTICK_X_AXIS = 4;
  public static final int RSTICK_Y_AXIS = 5;
  //triggers
  public static final int LTRIGGER_AXIS = 2;
  public static final int RTRIGGER_AXIS = 3;


  public static final int BUTTON_A = 1;

  private Joystick gamepad;

  public void registerControls() 
  {
    gamepad = new Joystick(RobotMap.GAMEPAD);

    final Button AButton = new JoystickButton(gamepad, BUTTON_A);
    AButton.whenPressed(new C_Recharge());
  }
  public Joystick getGamepad() 
  {
    return gamepad;
  }
}

/*
 * 1 = A
 *
 * 2 = B
 *
 * 3 = X
 *
 * 4 = Y
 *
 * 5 = LEFT-BUMPER
 *
 * 6 = RIGHT-BUMPER
 *
 * 7 = BACK
 *
 * 8 = START
 *
 * 9 = LEFT-STICK
 *
 * 10 = RIGHT-STICK
 *
 * as a side note please refrain from using 9-10 because the can cause issues
 * with commands using the stick axis AXIS ARE AS FOLLOWS
 *
 * 0 = LEFT-X-AXIS
 *
 * 1 = LEFT-Y-AXIS
 *
 * 2 = LEFT-TRIGGER
 *
 * 3 = RIGHT-TRIGGER
 *
 * 4 = RIGHT-X-AXIS
 *
 * 5 = RIGHT-Y-AXIS
 */