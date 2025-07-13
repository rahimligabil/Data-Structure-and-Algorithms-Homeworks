package HWSystem.Device.MotorDriver;

import HWSystem.Protocol.Protocol;

/**
 * Represents the SparkFunMD motor driver device.
 * <p>
 * This motor driver communicates using the SPI protocol.
 * It supports turning on/off the motor and setting its speed.
 * </p>
 * <p>
 * SparkFunMD is a type of {@link MotorDriver} and follows the same interface
 * as other motor drivers in the system.
 * </p>
 */
public class SparkFunMD extends MotorDriver {

    /**
     * Constructs a SparkFunMD motor driver with the specified protocol and device ID.
     *
     * @param protocol the communication protocol used (must be SPI)
     * @param devID the unique ID of the motor driver
     */
    public SparkFunMD(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the motor driver.
     * Sets the internal state to ON and logs the action.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the motor driver.
     * Sets the internal state to OFF and logs the action.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of the motor driver device.
     *
     * @return "SparkFunMD"
     */
    @Override
    public String getName() {
        return "SparkFunMD";
    }

    /**
     * Returns the type of the device.
     *
     * @return "MotorDriver"
     */
    @Override
    public String getDevType() {
        return "MotorDriver";
    }

    /**
     * Returns the current ON/OFF state of the device.
     *
     * @return the current state
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks whether the given protocol is compatible with SparkFunMD.
     * SparkFunMD only works with the SPI protocol.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is SPI, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("SPI");
    }

    /**
     * Sets the motor speed.
     * Updates the current speed and prints the new value.
     *
     * @param speed the speed value to set
     */
    @Override
    public void setMotorSpeed(int speed) {
        this.currentSpeed = speed;
        System.out.println(getName() + ": Setting speed to " + speed + ".");
    }
}
