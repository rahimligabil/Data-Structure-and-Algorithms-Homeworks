package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

/**
 * Represents a GY951 IMU (Inertial Measurement Unit) sensor device.
 * 
 * This sensor provides acceleration and rotation data.
 * It supports both SPI and UART protocols for communication and 
 * extends the IMUSensor class.
 */
public class GY951 extends IMUSensor {

    /**
     * Constructs a GY951 sensor with the given protocol and device ID.
     *
     * @param protocol the communication protocol (SPI or UART)
     * @param devID the unique device ID
     */
    public GY951(Protocol protocol, int devID) {
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
     * @return "GY951"
     */
    @Override
    public String getName() {
        return "GY951";
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
     * Checks whether the given protocol is compatible with this sensor.
     * GY951 supports SPI and UART protocols.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is SPI or UART, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("SPI") || protocol.getProtocolName().equals("UART");
    }

    /**
     * Returns the current acceleration value measured by the sensor.
     *
     * @return acceleration value (dummy value: 1.00)
     */
    public float getAccel() {
        return 1.00f;
    }

    /**
     * Returns the current rotation value measured by the sensor.
     *
     * @return rotation value (dummy value: 0.50)
     */
    public float getRot() {
        return 0.50f;
    }
}
