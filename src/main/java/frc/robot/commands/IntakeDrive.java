package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.Intake;

public class IntakeDrive extends CommandBase {

    private Intake m_intake;
    private double m_speed;

    public IntakeDrive(Intake intake, double speed) {

        m_intake = intake;
        m_speed = speed;

        setName("IntakeDrive");
        addRequirements(m_intake);

    }

    @Override
    public void initialize() {
        m_intake.stop();
    }

    @Override
    public void execute() {
        m_intake.rotate(m_speed);
    }

    @Override
    public void end(boolean interrupted) {
        m_intake.stop();
    }

}
