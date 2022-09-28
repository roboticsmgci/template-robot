// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import frc.robot.commands.ArmDrive;
import frc.robot.commands.IntakeDrive;
import frc.robot.commands.TankDrive;
import frc.robot.commands.autonomous.Ball1;
import frc.robot.commands.autonomous.Ball1sus;
import frc.robot.commands.autonomous.Ball2;
import frc.robot.commands.autonomous.Ball2b;
import frc.robot.commands.autonomous.Ball3;
import frc.robot.commands.autonomous.OnlyTaxi;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.button.JoystickButton;

/**
 * This class is where the bulk of the robot should be declared. Since
 * Command-based is a
 * "declarative" paradigm, very little robot logic should actually be handled in
 * the {@link Robot}
 * periodic methods (other than the scheduler calls). Instead, the structure of
 * the robot (including
 * subsystems, commands, and button mappings) should be declared here.
 */
public class RobotContainer {

    Drivetrain m_drivetrain;

    private Intake m_intake;
    private Arm m_arm;

    // Autonomous
    SendableChooser<Command> m_chooser;
    OnlyTaxi m_onlyTaxi = new OnlyTaxi(m_drivetrain);
    Ball1sus m_ball1sus = new Ball1sus(m_drivetrain);
    Ball1 m_ball1 = new Ball1(m_drivetrain, m_intake);
    Ball2 m_ball2 = new Ball2(m_arm, m_drivetrain, m_intake);
    Ball2b m_ball2b = new Ball2b(m_arm, m_drivetrain, m_intake);
    Ball3 m_ball3 = new Ball3(m_arm, m_drivetrain, m_intake);

    // Joysticks
    Joystick m_stick1 = new Joystick(0);

    Joystick m_stick2 = new Joystick(0);

    Joystick m_stick3 = new Joystick(0);

    public RobotContainer() {
        m_chooser.setDefaultOption("1 Ball??? sus", m_ball1sus);
        m_chooser.addOption("Only Taxi", m_onlyTaxi);
        m_chooser.setDefaultOption("1 Ball", m_ball1);
        m_chooser.addOption("2 Ball", m_ball2);
        m_chooser.addOption("2 Ball v2", m_ball2b);
        m_chooser.addOption("3 Ball", m_ball3);

        SmartDashboard.putData(m_chooser);

        // Initialize all of your commands and subsystems here
        // ((-m_stick2.getThrottle() + 2) / 3)

        m_drivetrain.setDefaultCommand(
                new TankDrive(
                        m_drivetrain,
                        () -> {
                            return (-(
                            // tank
                            (m_stick2.getY()
                                    * (!(m_stick2.getRawButton(1) || m_stick2.getRawButton(1)) ? 1 : 0)
                                    + (m_stick1.getY() + m_stick2.getY()) / 2
                                            * ((m_stick2.getRawButton(1) || m_stick2.getRawButton(1)) ? 1 : 0))
                                    * (Math.round((-m_stick1.getThrottle() + 1) / 2) == 0 ? 1 : 0)
                                    // tank
                                    +
                            // arcade
                                    (m_stick2.getY()
                                            * (1 - Math.abs(
                                                    m_stick2.getZ()
                                                            * (!m_stick2.getRawButton(1) ? 1 : 0)))
                                            + (m_stick2.getZ()
                                                    * 0.5
                                                    * (!m_stick2.getRawButton(1) ? 1 : 0)
                                                    + m_stick2.getZ()
                                                            * 0.5
                                                            * (m_stick2.getRawButton(2) ? 1 : 0)))
                                            * (int) Math.round((-m_stick1.getThrottle() + 1) / 2)
                            // arcade
                            ) * ((-m_stick2.getThrottle() + 2) / 3));
                        },
                        () -> {
                            return (-(
                            // tank
                            (m_stick1.getY()
                                    * (!(m_stick2.getRawButton(1) || m_stick2.getRawButton(1)) ? 1 : 0)
                                    + (m_stick1.getY() + m_stick2.getY()) / 2
                                            * (m_stick2.getRawButton(1) || m_stick2.getRawButton(1) ? 1 : 0))
                                    * (Math.round((-m_stick1.getThrottle() + 1) / 2) == 0 ? 1 : 0)
                                    // tank
                                    +
                            // arcade
                                    (m_stick2.getY()
                                            * (1 - Math.abs(
                                                    m_stick2.getZ()
                                                            * (!m_stick2.getRawButton(1) ? 1 : 0)))
                                            - (m_stick2.getZ()
                                                    * 0.5
                                                    * (!m_stick2.getRawButton(1) ? 1 : 0)
                                                    + m_stick2.getZ()
                                                            * 0.5
                                                            * (m_stick2.getRawButton(2) ? 1 : 0)))
                                            * (int) Math.round((-m_stick1.getThrottle() + 1) / 2)
                            // arcade
                            ) * ((-m_stick2.getThrottle() + 2) / 3));
                        }));

        m_arm.setDefaultCommand(new ArmDrive(m_arm, () -> m_stick3.getY()));

        // Configure the button bindings
        configureButtonBindings();

    }

    private void configureButtonBindings() {
        new JoystickButton(m_stick3, 3).whenHeld(
                // intake
                new IntakeDrive(m_intake, -0.5));
        new JoystickButton(m_stick3, 4).whenHeld(
                // shooter
                new IntakeDrive(m_intake, 1));
    }

    public Command getAutonomousCommand() {
        return m_chooser.getSelected();
    }
}
