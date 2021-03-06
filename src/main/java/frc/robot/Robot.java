package frc.robot;

import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;


public class Robot extends TimedRobot {

    private Command autoCommand;
    private RobotContainer robotContainer;


    @Override
    public void robotInit() {

        // Instantiate our RobotContainer.  This will perform all our button bindings, and put our
        // autonomous chooser on the dashboard.
        robotContainer = new RobotContainer();

    }

    @Override
    public void robotPeriodic() {

        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();

    }


    @Override
    public void disabledInit() {}

    @Override
    public void disabledPeriodic() {}

    
    @Override
    public void autonomousInit() {

        autoCommand = robotContainer.getAutonomousCommand();

        if (autoCommand != null) {
            autoCommand.schedule();
        }

    }
    
    @Override
    public void autonomousPeriodic() {}


    @Override
    public void teleopInit() {

        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autoCommand != null) {
            autoCommand.cancel();
        }

    }

    @Override
    public void teleopPeriodic() {}


    @Override
    public void testInit() {

        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();

    }

  
    @Override
    public void testPeriodic() {}


    @Override
    public void simulationInit() {}

    @Override
    public void simulationPeriodic() {}

}
