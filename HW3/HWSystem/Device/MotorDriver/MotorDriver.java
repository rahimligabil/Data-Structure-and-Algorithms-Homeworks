package HWSystem.Device.MotorDriver;

import HWSystem.Protocol.*;
import HWSystem.Device.Device;

/**
 * Abstract class for motor driver devices in the system.
 * <p>
 * A MotorDriver is a type of {@link Device} that controls a motor and keeps track of its speed.
 * Subclasses must implement the {@link #setMotorSpeed(int)} method to define how the motor speed is set.
 * </p>
 * 
 * <p>
 * Each motor driver is linked to a specific communication protocol (e.g., UART, SPI).
 * The motor's current speed is tracked with {@code currentSpeed}.
 * </p>
 */
public abstract class MotorDriver extends Device {

    /** Current speed of the motor. Default is 0. */
    protected int currentSpeed = 0;

    /**
     * Constructs a motor driver with the given protocol and device ID.
     *
     * @param protocol the communication protocol used by the motor driver
     * @param devID the unique device ID
     */
    public MotorDriver(Protocol protocol, int devID) {
        super(protocol, devID);
        this.currentSpeed = 0;
    }

    /**
     * Sets the speed of the motor.
     * <p>
     * This method must be implemented by subclasses to define how motor speed is controlled.
     * </p>
     *
     * @param speed the speed value to set
     */
    public abstract void setMotorSpeed(int speed);
}
