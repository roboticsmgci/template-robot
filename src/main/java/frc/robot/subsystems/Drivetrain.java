package frc.robot.subsystems;

import com.kauailabs.navx.frc.AHRS;
import com.revrobotics.CANSparkMax;
import com.revrobotics.SparkMaxRelativeEncoder;
import com.revrobotics.CANSparkMaxLowLevel.MotorType;

import edu.wpi.first.math.controller.PIDController;
import edu.wpi.first.wpilibj.SPI;
import edu.wpi.first.wpilibj.drive.DifferentialDrive;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivetrain extends SubsystemBase {
    private static final int leftLeadID = 1;
    private static final int leftFollowID = 2;
    private static final int rightLeadID = 3;
    private static final int rightFollowID = 4;

    private CANSparkMax m_leftLeadMotor = new CANSparkMax(leftLeadID, MotorType.kBrushless);
    private CANSparkMax m_leftFollowMotor = new CANSparkMax(leftFollowID, MotorType.kBrushless);
    private CANSparkMax m_rightLeadMotor = new CANSparkMax(rightLeadID, MotorType.kBrushless);
    private CANSparkMax m_rightFollowMotor = new CANSparkMax(rightFollowID, MotorType.kBrushless);

    private DifferentialDrive m_robotDrive = new DifferentialDrive(m_leftLeadMotor, m_rightLeadMotor);

    // Makeshift Slew Rate Limiter
    private double m_lastSpeedLeft;
    private double m_lastSpeedRight;
    private final double m_speedRateLimit = 0.08;

    public AHRS m_navX = new AHRS(SPI.Port.kMXP);
    public SparkMaxRelativeEncoder m_leftLeadEncoder = (SparkMaxRelativeEncoder) m_leftLeadMotor.getEncoder();
    public SparkMaxRelativeEncoder m_rightLeadEncoder = (SparkMaxRelativeEncoder) m_rightLeadMotor.getEncoder();
    public PIDController m_PIDMove = new PIDController(0.005, 0, 0);

    public Drivetrain() {

        // Configures the motors to follow each other
        m_leftFollowMotor.follow(m_leftLeadMotor);
        m_rightFollowMotor.follow(m_rightLeadMotor);

        // Conversion factor from rotations to meters
        m_leftLeadEncoder.setPositionConversionFactor(0.0443);
        m_rightLeadEncoder.setPositionConversionFactor(0.0443);

        setName("Drivetrain");

    }

    public void drive(double left, double right) {

        if (left > m_lastSpeedLeft + m_speedRateLimit) {
            left = m_lastSpeedLeft + m_speedRateLimit;
        } else if (left < m_lastSpeedLeft - m_speedRateLimit) {
            left = m_lastSpeedLeft - m_speedRateLimit;
        }

        if (right > m_lastSpeedRight + m_speedRateLimit) {
            right = m_lastSpeedRight + m_speedRateLimit;
        } else if (right < m_lastSpeedRight - m_speedRateLimit) {
            right = m_lastSpeedRight - m_speedRateLimit;
        }

        m_robotDrive.tankDrive(-left, right);

        m_lastSpeedLeft = left;
        m_lastSpeedRight = right;

    }

    public void periodic() {
        SmartDashboard.putNumber("Gyro", m_navX.getYaw());
    }

    public void setIdleMode(CANSparkMax.IdleMode mode) {
        m_leftLeadMotor.setIdleMode(mode);
        m_rightLeadMotor.setIdleMode(mode);
        m_leftFollowMotor.setIdleMode(mode);
        m_rightFollowMotor.setIdleMode(mode);
    }
}
