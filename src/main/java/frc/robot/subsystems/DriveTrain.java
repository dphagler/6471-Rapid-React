// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_VictorSPX;

import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.motorcontrol.MotorControllerGroup;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class DriveTrain extends SubsystemBase {
  /** Creates a new DriveTrain. */

  private final WPI_VictorSPX rightMaster, leftMaster, rightSlave, leftSlave;
  private MotorControllerGroup left_motors, right_motors;
  private final DifferentialDrive m_drive;

  public DriveTrain() {
    rightMaster = new WPI_VictorSPX(10);
    rightSlave = new WPI_VictorSPX(12);

    leftMaster = new WPI_VictorSPX(11);
    leftSlave = new WPI_VictorSPX(13);

    rightMaster.configFactoryDefault();
    rightSlave.configFactoryDefault();
    leftMaster.configFactoryDefault();
    leftSlave.configFactoryDefault();

    rightMaster.setNeutralMode(NeutralMode.Brake);
    rightSlave.setNeutralMode(NeutralMode.Brake);
    leftMaster.setNeutralMode(NeutralMode.Brake);
    leftSlave.setNeutralMode(NeutralMode.Brake);

    right_motors = new MotorControllerGroup(rightMaster, rightSlave);
    left_motors = new MotorControllerGroup(leftMaster, leftSlave);

    m_drive = new DifferentialDrive(right_motors, left_motors);
    m_drive.setSafetyEnabled(false);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void arcadeDrive(double speed, double rotation){
    m_drive.arcadeDrive(speed, rotation);
  }

  public void tankDrive(double left, double right){
    m_drive.tankDrive(left, right);
  }


}
