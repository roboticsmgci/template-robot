package frc.robot.commands.common;

import edu.wpi.first.wpilibj2.command.ParallelCommandGroup;
import edu.wpi.first.wpilibj2.command.ParallelRaceGroup;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmMove;
import frc.robot.commands.IntakeMove;
import frc.robot.commands.TankMove;
import frc.robot.subsystems.Arm;
import frc.robot.subsystems.Drivetrain;
import frc.robot.subsystems.Intake;

public class ScoreBall extends SequentialCommandGroup {

    private Arm m_arm;
    private Drivetrain m_drivetrain;
    private Intake m_intake;

    public ScoreBall(Arm arm, Drivetrain drivetrain, Intake intake) {

        m_arm = arm;
        m_drivetrain = drivetrain;
        m_intake = intake;

        setName("ScoreBall");
        addCommands(
                // Raise arm
                new ArmMove(arm, 0.3, 0.7),
                // Move forward while keeping arm up
                new ParallelRaceGroup(
                        new TankMove(drivetrain, 0.4, 0.4),
                        new ArmMove(arm, 0.1, 5)),
                // Dump ball while keeping arm up
                new ParallelCommandGroup(
                        new IntakeMove(intake, 1, 0.3),
                        new ArmMove(arm, 0.1, 0.3)),
                // Move back
                new TankMove(drivetrain, -3, 0.65),
                new ArmLower(arm));

    }

}
