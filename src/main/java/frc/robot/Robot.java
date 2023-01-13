// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.RobotController;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;

public class Robot extends TimedRobot {

    private Command m_autonomousCommand;
    private RobotContainer m_container;

    @Override
    public void robotInit() {
        // frc::CameraServer::GetInstance()->StartAutomaticCapture(0);
        // frc::CameraServer::GetInstance()->StartAutomaticCapture(1);
        RobotController.setBrownoutVoltage(6);
    }

    @Override
    public void robotPeriodic() {
        CommandScheduler.getInstance().run();
    }

    @Override
    public void disabledInit() {
    }

    @Override
    public void disabledPeriodic() {
    }

    @Override
    public void autonomousInit() {

        // m_container.m_drivetrain.setIdleMode(rev::CANSparkMax::IdleMode::kBrake);

        m_autonomousCommand = m_container.getAutonomousCommand();

        if (m_autonomousCommand != null) {
            m_autonomousCommand.schedule();
        }

    }

    @Override
    public void autonomousPeriodic() {
    }

    @Override
    public void teleopInit() {

        // m_container.m_drivetrain.SetIdleMode(rev::CANSparkMax::IdleMode::kCoast);

        if (m_autonomousCommand != null) {
            m_autonomousCommand.cancel();
            m_autonomousCommand = null;
        }

    }

    @Override
    public void teleopPeriodic() {
    }

    @Override
    public void testPeriodic() {
    }
}

/*
 * #ifndef RUNNING_FRC_TESTS
 * int main() {
 * return frc::StartRobot<Robot>();
 * }
 * #endif
 */
