package HWSystem.Device.Sensor;

import HWSystem.Protocol.*;
import HWSystem.Device.Device;

/**
 * Abstract base class for all sensor devices.
 * 
 * Sensors are hardware devices that collect data such as temperature, motion, or orientation.
 * Each sensor must define its type and how its data is represented as a string.
 */
public abstract class Sensor extends Device {

    /**
 * Constructs a Sensor with the given protocol and device ID.
 *
 * @param protocol the communication protocol (e.g., I2C, SPI)
 * @param devID the unique ID of the sensor
 */
    public Sensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Returns the specific type of the sensor (e.g., "IMUSensor", "TempSensor").
     *
     * @return the sensor type as a string
     */
    public abstract String getSensType();

    /**
     * Returns a string representation of the sensor's measured data.
     *
     * @return sensor data as a string
     */
    public abstract String data2String();

    /**
     * Returns the general device type for this sensor.
     * Combines sensor type with the word "Sensor".
     *
     * @return device type string (e.g., "IMUSensor Sensor")
     */
    @Override
    public String getDevType() {
        return getSensType() + " Sensor";
    }
}
