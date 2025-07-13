package HWSystem.Device.MotorDriver;

import HWSystem.Protocol.*;

/**
 * Represents the PCA9685 motor driver device.
 * <p>
 * This driver uses the I2C protocol to communicate with the system.
 * It allows turning the motor on/off and setting its speed.
 * </p>
 * <p>
 * The PCA9685 is a widely used PWM controller for controlling servos or DC motors.
 * </p>
 */
public class PCA9685 extends MotorDriver {

    /**
     * Constructs a PCA9685 motor driver with the specified protocol and device ID.
     *
     * @param protocol the communication protocol (should be I2C)
     * @param devID the unique ID of the motor driver
     */
    public PCA9685(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the motor driver and updates the internal state.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the motor driver and updates the internal state.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of the motor driver.
     *
     * @return "PCA9685"
     */
    @Override
    public String getName() {
        return "PCA9685";
    }

    /**
     * Returns the general type of this device.
     *
     * @return "MotorDriver"
     */
    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    /**
     * Returns the current ON/OFF state of the motor driver.
     *
     * @return the current state of the device
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks whether the given protocol is compatible with PCA9685.
     * PCA9685 only supports the I2C protocol.
     *
     * @param protocol the protocol to check
     * @return true if protocol is I2C, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("I2C");
    }

    /**
     * Sets the motor speed to the given value.
     * Updates the internal speed and prints the change.
     *
     * @param speed the new motor speed
     */
    @Override
    public void setMotorSpeed(int speed) {
        this.currentSpeed = speed;
        System.out.println(getName() + ": Setting speed to " + speed + ".");
    }
}
