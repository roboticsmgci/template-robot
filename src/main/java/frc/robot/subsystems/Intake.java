package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Intake extends SubsystemBase {
    private CANSparkMax m_motor = new CANSparkMax(6, MotorType.kBrushed);

    public Intake() {
        m_motor.restoreFactoryDefaults();
        setName("Intake");
    }

    public void rotate(double speed) {
        m_motor.set(speed);
    }

    public void stop() {
        m_motor.set(0);
    }
}
