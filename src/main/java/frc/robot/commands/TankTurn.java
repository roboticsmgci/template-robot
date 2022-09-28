package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankTurn extends CommandBase {

    private Drivetrain m_drivetrain;
    private double m_angleTarget;

    private final double m_error = 12;
    private final double m_speed = 0.4;

    private double m_angleCurrent;
    private boolean m_clockwise;

    public TankTurn(Drivetrain drivetrain, double angleTarget) {

        m_drivetrain = drivetrain;
        m_angleTarget = angleTarget;

        setName("TankTurn");
        addRequirements(m_drivetrain);

    }

    @Override
    public void initialize() {

        m_drivetrain.m_navX.reset();
        m_drivetrain.drive(0, 0);

        // If negative (CCW), turn positive and note CCW
        if (m_angleTarget < 0) {
            m_angleTarget = m_angleTarget * -1;
            m_clockwise = false;
        }

    }

    @Override
    public void execute() {

        m_angleCurrent = m_drivetrain.m_navX.getYaw();
        // If negative (CCW), absolute value
        if (m_angleCurrent < 0) {
            m_angleCurrent = m_angleCurrent * -1;
        }

        // Increase by 1 for gyro inconsistencies
        m_angleCurrent++;

        if (m_clockwise) {
            m_drivetrain.drive(-m_speed, m_speed);
        } else {
            m_drivetrain.drive(m_speed, -m_speed);
        }

    }

    @Override
    public boolean isFinished() {
        return m_angleCurrent >= m_angleTarget - m_error;
    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(0, 0);
    }

}
