package frc.robot.commands;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.Speed;
import frc.robot.subsystems.Arm;

public class ArmDrive extends CommandBase {
    private Arm m_arm;
    private Speed m_speed;

    public ArmDrive(Arm arm, Speed speed) {

        m_arm = arm;
        m_speed = speed;

        setName("ArmDrive");
        addRequirements(m_arm);

    }

    @Override
    public void execute() {
        double speed = m_speed.speed();
        if (speed < 0) {
            // going down
            m_arm.rotate(m_speed.speed() / 10);
        } else {
            // going up
            m_arm.rotate(m_speed.speed() / 4);
        }
    }

    @Override
    public void end(boolean interrupted) {
        m_arm.rotate(0);
    }
}
