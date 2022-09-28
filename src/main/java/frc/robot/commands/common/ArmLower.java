package frc.robot.commands.common;

import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.ArmMove;
import frc.robot.subsystems.Arm;

public class ArmLower extends SequentialCommandGroup {

    private Arm m_arm;

    public ArmLower(Arm arm) {

        m_arm = arm;

        setName("ArmLower");
        addCommands(
                // Break tape and lower
                new ArmMove(arm, -0.3, 0.4),
                // Stop, let it drop by gravity
                new ArmMove(arm, 0, 0.2));

    }

}
