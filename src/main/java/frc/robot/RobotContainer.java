// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.GenericHID;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.XboxController.Button;
import frc.robot.commands.DriveManual;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.ExampleSubsystem;
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
  private final ExampleSubsystem m_exampleSubsystem = new ExampleSubsystem();

  private final ExampleCommand m_autoCommand = new ExampleCommand(m_exampleSubsystem);

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
   
  //Shooter
    
  new JoystickButton(m_driver2Controller, Button.kLeftBumper.value)
  .whileHeld(new ShootLow());

new JoystickButton(m_driver2Controller, Button.kRightBumper.value)
.whileHeld(new ShootHigh());

new JoystickButton(m_driverController, Button.kB.value)
.whileHeld(new CrazyShot());


//Intake

new JoystickButton(m_driver2Controller, Button.kA.value)
  .whileHeld(new IntakeCommand());

new JoystickButton(m_driverController, Button.kA.value)
  .whileHeld(new FireBalls());

new JoystickButton(m_driver2Controller, Button.kB.value)
  .whileHeld(new ClearHopper());      

new XboxController(m_driverController, Button.kX.value)
  .whenPressed(() -> m_intake.extendIntake());

new XboxController(m_driverController, Button.kY.value)
  .whenPressed(() -> m_intake.retractIntake());        

//Climber

new JoystickButton(m_driver3Controller, Button.kY.value)
.whileHeld(new ClimberUp());

new JoystickButton(m_driver3Controller, Button.kA.value)
.whileHeld(new ClimberDown());

new JoystickButton(m_driver3Controller, Button.kX.value)
.whileHeld(new ClimberOut());

new JoystickButton(m_driver3Controller, Button.kB.value)
.whileHeld(new ClimberIn());

new JoystickButton(m_driver3Controller, Button.kLeftBumper.value)
.whenPressed(() -> m_Climber.climberLock());

new JoystickButton(m_driver3Controller, Button.kRightBumper.value)
.whenPressed(() -> m_Climber.climberUnlock());

//Arm

new XboxController(Button.kB.value))
.whileHeld(new Armup());

new XboxController(Button.kA.value)
.whileHeld(Armdown());


  }

  private Object Armdown() {
    return null;
  }

  /**
   * Use this to pass the autonomous command to the main {@link Robot} class.
   *
   * @return the command to run in autonomous
   */
  public Command getAutonomousCommand() {
    // An ExampleCommand will run in autonomous
    return m_autoCommand;
  }
}
