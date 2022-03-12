// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DriveTrain;

public class DriveManual extends CommandBase {
  private final DriveTrain m_drive;
  private final DoubleSupplier m_speed;
  private final DoubleSupplier m_rotation;
  /** Creates a new DriveManual. */
  public DriveManual(DriveTrain subsystem, DoubleSupplier forward, DoubleSupplier rotation) {
    // Use addRequirements() here to declare subsystem dependencies.
    m_drive = subsystem;
    m_speed = forward;
    m_rotation = rotation;
    addRequirements(m_drive);
  }

  // Called when the command is initially scheduled.
  @Override
  public void initialize() {}

  // Called every time the scheduler runs while the command is scheduled.
  @Override
  public void execute() {
    m_drive.arcadeDrive(m_speed.getAsDouble(), m_rotation.getAsDouble());
  }

  // Called once the command ends or is interrupted.
  @Override
  public void end(boolean interrupted) {}

  // Returns true when the command should end.
  @Override
  public boolean isFinished() {
    return false;
  }
}
