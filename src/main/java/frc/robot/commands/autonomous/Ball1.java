package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeMove;
import frc.robot.commands.TankMove;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class Ball1 extends SequentialCommandGroup {
    private Drivetrain m_drivetrain;
    private Intake m_intake;

    public Ball1(Drivetrain drivetrain, Intake intake) {

        m_drivetrain = drivetrain;
        m_intake = intake;

        setName("Ball1");
        addCommands(
                // Remove ball
                new IntakeMove(intake, 1, 0.5),
                // Taxi out
                new TankMove(drivetrain, -3, 0.5));
    }
}
