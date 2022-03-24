// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.FeedbackDevice;
import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

  private final WPI_TalonFX armMotor;
  /** Creates a new Arm. */
  public Arm() {
    armMotor = new WPI_TalonFX(19);
    armMotor.configFactoryDefault();
    armMotor.setNeutralMode(NeutralMode.Brake);
    armMotor.set(ControlMode.PercentOutput, 0);
    armMotor.configSelectedFeedbackSensor(FeedbackDevice.IntegratedSensor);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
    SmartDashboard.putNumber("Arm Motor", armMotor.getSelectedSensorPosition());
  }

  public void runArm(double speed){
    
    if(speed > 0 && getArmEncoder() <= 65000){
      armMotor.set(ControlMode.PercentOutput, speed);
    } else if(speed < 0 && getArmEncoder() >= 0){
      armMotor.set(ControlMode.PercentOutput, speed);
    } else {
      armMotor.set(ControlMode.PercentOutput, 0);
    }
    
  }

  public void stopArm(){
    armMotor.set(ControlMode.PercentOutput, 0);
  }

  public void resetArmEncoder(){
    armMotor.setSelectedSensorPosition(0);
  }

  public double getArmEncoder(){
    return armMotor.getSelectedSensorPosition();
  }
}
