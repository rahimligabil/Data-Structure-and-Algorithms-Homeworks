package HWSystem.Device;

import HWSystem.Protocol.Protocol;

/**
 * Abstract base class for all hardware devices in the system.
 * 
 * This class defines the common structure and behavior that every device must follow.
 * Each device is associated with a communication protocol and has a unique device ID.
 * It can also be assigned to a specific port.
 */
public abstract class Device {

    /** Unique ID for this device within its type. */
    protected int devID;

    /**
 * Represents the operational state of a device.
 */
public enum State {
    /** Device is powered on */
    ON,
    
    /** Device is powered off */
    OFF
}

    /** Current state of the device. Default is OFF. */
    protected State state = State.OFF;

    /** The communication protocol used by the device. */
    protected Protocol protocol;

    /** The port ID to which the device is connected. Default is -1 (not assigned). */
    protected int portID = -1;

    /**
     * Sets the port ID that this device is connected to.
     *
     * @param portID the port number
     */
    public void setPortID(int portID) {
        this.portID = portID;
    }

    /**
     * Returns the port ID this device is connected to.
     *
     * @return the port ID
     */
    public int getPortID() {
        return portID;
    }

    /**
     * Constructs a device with the given protocol and device ID.
     *
     * @param protocol the communication protocol used by the device
     * @param devID the device's unique ID
     */
    public Device(Protocol protocol, int devID) {
        this.protocol = protocol;
        this.devID = devID;
    }

    /**
     * Turns the device on.
     * Must be implemented by all subclasses.
     */
    public abstract void turnOn();

    /**
     * Turns the device off.
     * Must be implemented by all subclasses.
     */
    public abstract void TurnOff();

    /**
     * Returns the name of the device.
     *
     * @return device name
     */
    public abstract String getName();

    /**
     * Returns the general type of the device (e.g. Display, MotorDriver).
     *
     * @return device type as a string
     */
    public abstract String getDevType();

    /**
     * Returns the current ON/OFF state of the device.
     *
     * @return current state
     */
    public abstract State getState();

    /**
     * Checks if the given protocol is compatible with this device.
     *
     * @param protocol the protocol to check
     * @return true if compatible, false otherwise
     */
    public abstract boolean isProtocolCompatible(Protocol protocol);

    /**
     * Returns the device ID.
     *
     * @return device ID
     */
    public int getDevID() {
        return devID;
    }
}
