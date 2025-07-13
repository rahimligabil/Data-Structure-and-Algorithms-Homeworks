package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

/**
 * Represents an MPU6050 IMU (Inertial Measurement Unit) sensor device.
 * 
 * This sensor provides acceleration and rotation data via the I2C protocol.
 * It extends the IMUSensor class and implements required sensor behaviors.
 */
public class MPU6050 extends IMUSensor {

    /**
     * Constructs an MPU6050 sensor with the given protocol and device ID.
     *
     * @param protocol the communication protocol (must be I2C)
     * @param devID the unique ID of the device
     */
    public MPU6050(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the sensor and sets its state to ON.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the sensor and sets its state to OFF.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of the sensor.
     *
     * @return "MPU6050"
     */
    @Override
    public String getName() {
        return "MPU6050";
    }

    /**
     * Returns the current ON/OFF state of the sensor.
     *
     * @return the sensor's state
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks whether the given protocol is supported by this sensor.
     * MPU6050 supports only I2C communication.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is I2C, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("I2C");
    }

    /**
     * Returns the current acceleration value from the sensor.
     * 
     * @return acceleration value (dummy: 1.00)
     */
    public float getAccel() {
        return 1.00f;
    }

    /**
     * Returns the current rotation value from the sensor.
     * 
     * @return rotation value (dummy: 0.50)
     */
    public float getRot() {
        return 0.50f;
    }
}
