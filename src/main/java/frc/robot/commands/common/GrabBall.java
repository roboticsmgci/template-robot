package frc.robot.commands.common;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmMove;
import frc.robot.commands.IntakeMove;
import frc.robot.commands.TankMoveGyro;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class GrabBall extends SequentialCommandGroup {

    private Arm m_arm;
    private Drivetrain m_drivetrain;
    private Intake m_intake;

    public GrabBall(Arm arm, Drivetrain drivetrain, Intake intake) {

        m_arm = arm;
        m_drivetrain = drivetrain;
        m_intake = intake;

        setName("GrabBall");
        addCommands(
                new ArmMove(arm, -0.23, 0.6),
                // Move forward while intake in
                new ParallelCommandGroup(
                        new ArmMove(arm, -0.1, 1.3),
                        new IntakeMove(intake, -1, 1.3),
                        new TankMoveGyro(drivetrain, 0.5, 0.7)));

    }

}
