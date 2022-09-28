package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.TankMove;
import frc.robot.subsystems.Drivetrain;

public class OnlyTaxi extends SequentialCommandGroup {
    private Drivetrain m_drivetrain;

    public OnlyTaxi(Drivetrain drivetrain) {
        m_drivetrain = drivetrain;

        setName("OnlyTaxi");
        addCommands(new TankMove(drivetrain, -3, 0.5));
    }
}
