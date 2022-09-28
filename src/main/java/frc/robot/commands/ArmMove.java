package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Arm;

public class ArmMove extends CommandBase {

    private Arm m_arm;
    private double m_speed;
    private double m_duration;

    private int m_periods;
    private int m_periodTarget;

    public ArmMove(Arm arm, double speed, double duration) {

        m_arm = arm;
        m_speed = speed;
        m_duration = duration;

        setName("ArmMove");
        addRequirements(m_arm);

    }

    @Override
    public void initialize() {

        m_periods = 0;
        m_periodTarget = (int) (m_duration * 50);

        m_arm.stop();

    }

    @Override
    public void execute() {

        m_arm.rotate(m_speed);
        m_periods++;

    }

    @Override
    public boolean isFinished() {

        return (m_periods >= m_periodTarget);

    }

    @Override
    public void end(boolean interrupted) {

        m_arm.stop();

    }

}
