package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

/**
 * Represents a DHT11 temperature sensor device.
 * 
 * This sensor communicates using the OneWire protocol and provides temperature readings.
 * It extends the TempSensor class and overrides necessary device behavior.
 */
public class DHT11 extends TempSensor {

    /**
     * Constructs a DHT11 sensor with the specified protocol and device ID.
     *
     * @param protocol the communication protocol (must be OneWire)
     * @param devID the unique ID of the device
     */
    public DHT11(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the sensor and updates its state.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the sensor and updates its state.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of the sensor device.
     *
     * @return "DHT11"
     */
    @Override
    public String getName() {
        return "DHT11";
    }

    /**
     * Returns the current ON/OFF state of the sensor.
     *
     * @return the current state
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks if the sensor supports the given protocol.
     * DHT11 only works with OneWire protocol.
     *
     * @param protocol the protocol to check
     * @return true if protocol is OneWire, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("OneWire");
    }

    /**
     * Returns the current temperature reading from the sensor.
     * 
     * @return temperature value in Celsius (dummy value: 24.0)
     */
    public float getTemp() {
        return 24f;
    }
}
