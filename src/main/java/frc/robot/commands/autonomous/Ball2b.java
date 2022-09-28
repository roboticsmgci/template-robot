package frc.robot.commands.autonomous;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.TankMoveGyro;
import frc.robot.commands.TankTurn;
import frc.robot.commands.common.GrabBall;
import frc.robot.commands.common.ScoreBall;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class Ball2b extends SequentialCommandGroup {
    private Arm m_arm;
    private Drivetrain m_drivetrain;
    private Intake m_intake;

    public Ball2b(Arm arm, Drivetrain drivetrain, Intake intake) {

        m_arm = arm;
        m_drivetrain = drivetrain;
        m_intake = intake;

        setName("Ball2b");
        addCommands(
                new TankMoveGyro(drivetrain, 0.6, 0.5),
                new GrabBall(arm, drivetrain, intake),
                new TankMoveGyro(drivetrain, -2.35, 0.5),
                new TankTurn(drivetrain, 158),
                new TankMoveGyro(drivetrain, 0.25, 0.45),
                // Score ball
                new ScoreBall(arm, drivetrain, intake));

    }
}
