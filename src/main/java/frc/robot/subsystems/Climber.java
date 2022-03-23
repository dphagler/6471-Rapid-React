// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Climber extends SubsystemBase {

  private final CANSparkMax climber1Elevate, climber2Elevate;
  private final WPI_TalonFX climber1Rotate, climber2Rotate;
  /** Creates a new Climber. */
  public Climber() {

    climber1Elevate = new CANSparkMax(15, MotorType.kBrushed);
    climber2Elevate = new CANSparkMax(16, MotorType.kBrushed);
    climber1Elevate.restoreFactoryDefaults();
    climber2Elevate.restoreFactoryDefaults();
    climber1Elevate.setIdleMode(IdleMode.kBrake);
    climber2Elevate.setIdleMode(IdleMode.kBrake);

    climber1Rotate = new WPI_TalonFX(17);
    climber2Rotate = new WPI_TalonFX(18);
    climber1Rotate.configFactoryDefault();
    climber2Rotate.configFactoryDefault();
    climber1Rotate.setNeutralMode(NeutralMode.Brake);
    climber2Rotate.setNeutralMode(NeutralMode.Brake);
    climber1Rotate.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
    climber2Rotate.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);

  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Climber Rotate Motor 1", climber1Rotate.getSelectedSensorPosition());
    SmartDashboard.putNumber("Climber Rotate Motor 2", climber2Rotate.getSelectedSensorPosition());
  }

  public void climber1Elevate(double speed){
    climber1Elevate.set(speed);
  }

  public void climber2Elevate(double speed){
    climber2Elevate.set(speed);
  }

  public void climber1ElevateStop(){
    climber1Elevate.set(0);
  }

  public void climber2ElevateStop(){
    climber2Elevate.set(0);
  }

  public void climber1Rotate(double speed){

    if(speed > 0 && getRotateEncoder1() <= 5000){
      climber1Rotate.set(ControlMode.PercentOutput, speed);
    } else if(speed < 0 && getRotateEncoder1() >= 0){
      climber1Rotate.set(ControlMode.PercentOutput, speed);
    } else {
      climber1Rotate.set(ControlMode.PercentOutput, 0);
    }

  }

  public void climber2Rotate(double speed){

    if(speed > 0 && getRotateEncoder2() <= 5000){
      climber2Rotate.set(ControlMode.PercentOutput, speed);
    } else if(speed < 0 && getRotateEncoder2() >= 0){
      climber2Rotate.set(ControlMode.PercentOutput, speed);
    } else {
      climber2Rotate.set(ControlMode.PercentOutput, 0);
    }

  }

  public void climber1RotateStop(){
    climber1Rotate.set(ControlMode.PercentOutput, 0);
  }

  public void climber2RotateStop(){
    climber2Rotate.set(ControlMode.PercentOutput, 0);
  }

  public void resetRotateEncoder1(){
    climber1Rotate.setSelectedSensorPosition(0);
  }

  public void resetRotateEncoder2(){
    climber2Rotate.setSelectedSensorPosition(0);
  }

  public double getRotateEncoder1(){
    return climber1Rotate.getSelectedSensorPosition();
  }

  public double getRotateEncoder2(){
    return climber2Rotate.getSelectedSensorPosition();
  }
}
