package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.IntakeMove;
import frc.robot.commands.TankMove;
import frc.robot.commands.TankMoveGyro;
import frc.robot.commands.TankTurn;
import frc.robot.commands.common.ArmLower;
import frc.robot.commands.common.GrabBall;
import frc.robot.commands.common.ScoreBall;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class Ball2 extends SequentialCommandGroup {
    private Arm m_arm;
    private Drivetrain m_drivetrain;
    private Intake m_intake;

    public Ball2(Arm arm, Drivetrain drivetrain, Intake intake) {

        m_arm = arm;
        m_drivetrain = drivetrain;
        m_intake = intake;

        setName("Ball2");
        addCommands(
                // Remove ball
                new IntakeMove(intake, 1, 0.3),
                // Taxi out
                new TankMoveGyro(drivetrain, -1.4, 0.63),
                // Break tape
                new ArmLower(arm),
                // Turn around to face the ball
                new TankTurn(drivetrain, 180),
                // Get the ball
                new GrabBall(arm, drivetrain, intake),
                // Turn back around to face hub
                new TankTurn(drivetrain, 180),
                // Move towards hub
                new TankMove(drivetrain, 1.9, 0.63),
                // Score ball
                new ScoreBall(arm, drivetrain, intake));

    }
}
