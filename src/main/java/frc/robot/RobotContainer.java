package frc.robot;

import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.commands.ExampleCommand;
import frc.robot.subsystems.ExampleSubsystem;


public class RobotContainer {

    private final ExampleSubsystem exampleSubsystem = new ExampleSubsystem();
    private final ExampleCommand autoCommand = new ExampleCommand(exampleSubsystem);


    public RobotContainer() {
        configureButtonBindings();
    }


    private void configureButtonBindings() {}


    public Command getAutonomousCommand() {
        // An ExampleCommand will run in autonomous
        return autoCommand;
    }

}
