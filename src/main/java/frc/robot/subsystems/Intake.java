// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMax.IdleMode;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj.Servo;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {

  private static Servo shooterServo;

  private static double startAngle;
  private static double endAngle;
  private static double currentAngle;
  private static double startTime;

  public static final double WPILIB_MIN_SERVO_ANGLE = 0.0; //degrees
  public static final double WPILIB_MAX_SERVO_ANGLE = 360.0; //degrees
  private static final double TIME_TO_SERVO_FULL_EXTENSION = 3.48; //Avg time to move from retract to extend
  private static final double PERCENT_PER_SECOND = 1.00 / TIME_TO_SERVO_FULL_EXTENSION;
  private static final double DEGREES_PER_SECOND = (WPILIB_MAX_SERVO_ANGLE - WPILIB_MIN_SERVO_ANGLE)
      * PERCENT_PER_SECOND;

  private static final double HOOD_MAX_POSITION = 1.0; //percent servo travel to max hood position
  private static final double HOOD_MIN_POSITION = 0.0; //percent servo travel to min hood position
  
  //SERVO Parameters from https://s3.amazonaws.com/actuonix/Actuonix+L16+Datasheet.pdf
  private static final double MAX_SERVO_PWM = 2.0; //ms
  private static final double MIN_SERVO_PWM = 1.0; //ms
  private static final double SERVO_RANGE = MAX_SERVO_PWM - MIN_SERVO_PWM;
  private static final double CENTER_SERVO_PWM = 1.5; //ms
  private static final double SERVO_DEADBAND = 0.0; //ms - no deadband
  
  // pwm values in ms for the max and min angles of the shooter hood
  private static final double HOOD_MAX_PWM = MIN_SERVO_PWM + (SERVO_RANGE * HOOD_MAX_POSITION);
  private static final double HOOD_MIN_PWM = MIN_SERVO_PWM + (SERVO_RANGE * HOOD_MIN_POSITION);

  private final CANSparkMax intakeMotor;
  /** Creates a new Intake. */
  public Intake() {
    shooterServo = new Servo(0);
    shooterServo.setBounds(HOOD_MAX_PWM, CENTER_SERVO_PWM + SERVO_DEADBAND, 
        CENTER_SERVO_PWM, CENTER_SERVO_PWM - SERVO_DEADBAND, HOOD_MIN_PWM);
    
    intakeMotor = new CANSparkMax(14, MotorType.kBrushed);

    intakeMotor.restoreFactoryDefaults();
    intakeMotor.setIdleMode(IdleMode.kBrake);
  }

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }

  public void setAngle(double degrees){
    if(degrees <= WPILIB_MIN_SERVO_ANGLE)
      degrees = WPILIB_MIN_SERVO_ANGLE;
      
    if(degrees >= WPILIB_MAX_SERVO_ANGLE)
      degrees = WPILIB_MAX_SERVO_ANGLE;
      
    startAngle = shooterServo.getAngle();
    startTime = Timer.getFPGATimestamp();
      
    shooterServo.setAngle(degrees);
  }

  public void runIntake(double speed){
    intakeMotor.set(speed);
  }

  public void stopIntake(){
    intakeMotor.set(0);
  }
}
