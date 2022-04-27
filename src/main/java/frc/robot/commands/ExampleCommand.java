package frc.robot.commands;

import frc.robot.subsystems.ExampleSubsystem;
import edu.wpi.first.wpilibj2.command.CommandBase;

public class ExampleCommand extends CommandBase {

    @SuppressWarnings({"PMD.UnusedPrivateField", "PMD.SingularField"})

    private final ExampleSubsystem subsystem;


    public ExampleCommand(ExampleSubsystem _subsystem) {

        subsystem = _subsystem;
        addRequirements(_subsystem);

    }


    @Override
    public void initialize() {}

    
    @Override
    public void execute() {}

    
    @Override
    public void end(boolean interrupted) {}

    
    @Override
    public boolean isFinished() {
        return false;
    }

}
