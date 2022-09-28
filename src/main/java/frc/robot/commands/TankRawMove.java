package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Drivetrain;

public class TankRawMove extends CommandBase {

    private Drivetrain m_drivetrain;
    private double m_speedl;
    private double m_speedr;
    private double m_duration;

    private int periods;
    private int periodTarget;

    public TankRawMove(Drivetrain drivetrain, double speedl, double speedr, double duration) {

        m_drivetrain = drivetrain;
        m_speedl = speedl;
        m_speedr = speedr;
        m_duration = duration;

        setName("TankRawMove");
        addRequirements(m_drivetrain);

    }

    @Override
    public void initialize() {

        periods = 0;
        periodTarget = (int) (m_duration * 50);

        m_drivetrain.drive(0, 0);

    }

    @Override
    public void execute() {

        m_drivetrain.drive(m_speedl, m_speedr);
        periods++;

    }

    @Override
    public boolean isFinished() {

        return (periods >= periodTarget);

    }

    @Override
    public void end(boolean interrupted) {

        m_drivetrain.drive(0, 0);

    }

}