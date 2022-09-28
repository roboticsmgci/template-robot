package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankMoveGyro extends CommandBase {

    private Drivetrain m_drivetrain;
    private double m_distance;
    private double m_speed;

    private double m_distanceCounter;
    private double m_correction;

    public TankMoveGyro(Drivetrain drivetrain, double distance, double speed) {

        m_drivetrain = drivetrain;
        m_distance = distance;
        m_speed = speed;

        setName("TankMove");
        addRequirements(m_drivetrain);

    }

    @Override
    public void initialize() {

        m_drivetrain.drive(0, 0);
        m_drivetrain.m_leftLeadEncoder.setPosition(0);
        m_drivetrain.m_rightLeadEncoder.setPosition(0);
        m_drivetrain.m_navX.reset();
        m_drivetrain.m_PIDMove.setSetpoint(0);

        m_distanceCounter = 0;

    }

    @Override
    public void execute() {

        m_distanceCounter = (-m_drivetrain.m_leftLeadEncoder.getPosition()
                + m_drivetrain.m_rightLeadEncoder.getPosition())
                / 2;
        m_correction = m_drivetrain.m_PIDMove.calculate(m_drivetrain.m_navX.getAngle());

        if (m_distance >= 0 && m_distanceCounter < m_distance) {
            m_drivetrain.drive(m_speed - m_correction, m_speed + m_correction);
        } else if (m_distance < 0 && m_distanceCounter > m_distance) {
            m_drivetrain.drive(-m_speed - m_correction, -m_speed + m_correction);
        }
    }

    @Override
    public boolean isFinished() {

        m_distanceCounter = (-m_drivetrain.m_leftLeadEncoder.getPosition()
                + m_drivetrain.m_rightLeadEncoder.getPosition())
                / 2;

        if (m_distance >= 0) {
            return (m_distanceCounter >= m_distance);
        } else {
            return (m_distanceCounter <= m_distance);
        }

    }

    @Override
    public void end(boolean interrupted) {
        m_drivetrain.drive(0, 0);
    }

}
