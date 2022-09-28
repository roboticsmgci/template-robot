package frc.robot.subsystems;

import com.revrobotics.CANSparkMax;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Arm extends SubsystemBase {

	private CANSparkMax m_arm = new CANSparkMax(5, MotorType.kBrushless);

	public Arm() {
		m_arm.restoreFactoryDefaults();
		setName("Arm");
	}

	public void rotate(double speed) {
		m_arm.set(speed);
	}

	public void stop() {
		m_arm.set(0);
	}
}
