package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

/**
 * Represents a BME280 temperature sensor device.
 * 
 * This device supports both I2C and SPI protocols and can provide temperature readings.
 * It extends the TempSensor class and overrides necessary device behaviors.
 */
public class BME280 extends TempSensor {

    /**
     * Constructs a BME280 sensor with the given protocol and device ID.
     *
     * @param protocol the communication protocol (I2C or SPI)
     * @param devID the ID of the device
     */
    public BME280(Protocol protocol, int devID) {
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
     * @return "BME280"
     */
    @Override
    public String getName() {
        return "BME280";
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
     * BME280 works with both I2C and SPI.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is I2C or SPI, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("I2C") || protocol.getProtocolName().equals("SPI");
    }

    /**
     * Returns the current temperature reading from the sensor.
     * 
     * @return temperature value in Celsius (dummy value: 24)
     */
    public float getTemp() {
        return 24;
    }
}
