// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;


import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DriveManual;
import frc.robot.commands.DriveOffTarmac;
import frc.robot.commands.IntakeShootHigh;
import frc.robot.commands.IntakeShootLow;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Climber;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {
  // The robot's subsystems and commands are defined here...

  public final static DriveTrain m_drivetrain = new DriveTrain();
  public final static Arm m_arm = new Arm();
  public final static Intake m_intake = new Intake();
  public final static Climber m_climber = new Climber();
  
  public final static XboxController m_driverController = new XboxController(0);
  public final Joystick m_driver2Controller = new Joystick(1);
  

  /** The container for the robot. Contains subsystems, OI devices, and commands. */
  public RobotContainer() {
    // Configure the button bindings
    configureButtonBindings();

    m_drivetrain.setDefaultCommand(
      new DriveManual(m_drivetrain, m_driverController::getLeftY, m_driverController::getRightX)
    );
  }

  /**
   * Use this method to define your button->command mappings. Buttons can be created by
   * instantiating a {@link GenericHID} or one of its subclasses ({@link
   * edu.wpi.first.wpilibj.Joystick} or {@link XboxController}), and then passing it to a {@link
   * edu.wpi.first.wpilibj2.command.button.JoystickButton}.
   */
  private void configureButtonBindings() {
   
  //Intake
    //shoot high
    new JoystickButton(m_driverController, Button.kLeftBumper.value)
      .whileHeld(new IntakeShootLow());
    //shoot low
    new JoystickButton(m_driverController, Button.kRightBumper.value)
      .whileHeld(new IntakeShootHigh());
    //intake balls
    new JoystickButton(m_driverController, Button.kA.value)
      .whileHeld(() -> m_intake.runIntake(.5))
      .whenReleased(() -> m_intake.stopIntake());  
  
  //Arm
    //arm up
    new JoystickButton(m_driverController, Button.kX.value)
      .whileHeld(() -> m_arm.runArm(.25))
      .whenReleased(() -> m_arm.stopArm());
    //arm down
    new JoystickButton(m_driverController, Button.kY.value)
      .whileHeld(() -> m_arm.runArm(-.25))
      .whenReleased(() -> m_arm.stopArm());  

  //Climber
    //climber 1 up
    new JoystickButton(m_driver2Controller, 5)
      .whileHeld(() -> m_climber.climber1Elevate(.25))
      .whenReleased(() -> m_climber.climber1ElevateStop());
    //climber 1 down
    new JoystickButton(m_driver2Controller, 3)
      .whileHeld(() -> m_climber.climber1Elevate(-.25))
      .whenReleased(() -> m_climber.climber1ElevateStop());
    //climber 1 in
    new JoystickButton(m_driver2Controller, 7)
      .whileHeld(() -> m_climber.climber1Rotate(-.25))
      .whenReleased(() -> m_climber.climber1RotateStop());
    //climber 1 out
    new JoystickButton(m_driver2Controller, 8)
      .whileHeld(() -> m_climber.climber1Rotate(.25))
      .whenReleased(() -> m_climber.climber1RotateStop());
    
    //climber 2 up
    new JoystickButton(m_driver2Controller, 6)
      .whileHeld(() -> m_climber.climber2Elevate(.25))
      .whenReleased(() -> m_climber.climber2ElevateStop());
    //climber 2 down
    new JoystickButton(m_driver2Controller, 4)
      .whileHeld(() -> m_climber.climber2Elevate(-.25))
      .whenReleased(() -> m_climber.climber2ElevateStop());
    //climber 2 in
    new JoystickButton(m_driver2Controller, 11)
      .whileHeld(() -> m_climber.climber2Rotate(-.25))
      .whenReleased(() -> m_climber.climber2RotateStop());
    //climber 2 out
    new JoystickButton(m_driver2Controller, 12)
      .whileHeld(() -> m_climber.climber2Rotate(.25))
      .whenReleased(() -> m_climber.climber2RotateStop());

  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    return new DriveOffTarmac().withTimeout(3);
  }
}
