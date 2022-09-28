package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.TankMove;
import frc.robot.subsystems.Drivetrain;

public class Ball1sus extends SequentialCommandGroup {
    private Drivetrain m_drivetrain;

    public Ball1sus(Drivetrain drivetrain) {

        m_drivetrain = drivetrain;

        setName("Ball1sus");
        addCommands(new TankMove(drivetrain, -0.8, 0.8), new TankMove(drivetrain, 3, 0.5));

    }
}
