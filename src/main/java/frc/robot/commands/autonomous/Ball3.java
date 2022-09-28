package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeMove;
import frc.robot.commands.TankMove;
import frc.robot.commands.TankTurn;
import frc.robot.commands.common.GrabBall;
import frc.robot.commands.common.ScoreBall;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class Ball3 extends SequentialCommandGroup {
    private Arm m_arm;
    private Drivetrain m_drivetrain;
    private Intake m_intake;

    public Ball3(Arm arm, Drivetrain drivetrain, Intake intake) {

        m_arm = arm;
        m_drivetrain = drivetrain;
        m_intake = intake;

        setName("Ball3");
        addCommands(
                // Remove ball
                new IntakeMove(intake, -1, 0.2),
                // Taxi out
                new TankMove(drivetrain, -0.5, 0.4),
                // Break tape
                // ArmLower(arm),
                // Turn to face ball 1
                new TankTurn(drivetrain, -120),
                // Move to ball 1
                new TankMove(drivetrain, 1.10, 0.4),
                // Get ball 1
                new GrabBall(arm, drivetrain, intake),
                // Turn to face ball 2
                new TankTurn(drivetrain, -115),
                // Move to ball 2
                new TankMove(drivetrain, 1.4, 0.4),
                // Get ball 2
                new GrabBall(arm, drivetrain, intake),
                // Turn to hub
                new TankTurn(drivetrain, -100),
                // Move to hub
                new TankMove(drivetrain, 2, 0.4),
                // Realign perpendicular to hub
                // TankTurn(drivetrain, -10),
                // Score ball
                new ScoreBall(arm, drivetrain, intake));

    }
}
