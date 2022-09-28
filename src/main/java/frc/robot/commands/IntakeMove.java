package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeMove extends CommandBase {

    private Intake m_intake;
    private double m_speed;
    private double m_duration;

    private int m_periods;
    private int m_periodTarget;

    public IntakeMove(Intake intake, double speed, double duration) {

        m_intake = intake;
        m_speed = speed;
        m_duration = duration;

        setName("IntakeMove");
        addRequirements(m_intake);

    }

    @Override
    public void initialize() {

        m_periods = 0;
        m_periodTarget = (int) (m_duration * 50);

        m_intake.stop();

    }

    @Override
    public void execute() {

        m_intake.rotate(m_speed);
        m_periods++;

    }

    @Override
    public boolean isFinished() {

        return (m_periods >= m_periodTarget);

    }

    @Override
    public void end(boolean interrupted) {

        m_intake.stop();

    }

}
