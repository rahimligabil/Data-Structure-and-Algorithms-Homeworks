package HWSystem.Device.Sensor;

import HWSystem.Protocol.*;

/**
 * Abstract base class for temperature sensor devices.
 * 
 * This class provides a common structure for all temperature sensors,
 * requiring them to implement a method to retrieve the temperature value.
 * It also defines how the sensor data should be formatted as a string.
 */
public abstract class TempSensor extends Sensor {

    /**
     * Constructs a temperature sensor with the specified protocol and device ID.
     *
     * @param protocol the communication protocol used by the sensor
     * @param devID the unique ID of the device
     */
    public TempSensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Returns the temperature value measured by the sensor.
     *
     * @return temperature in degrees Celsius
     */
    public abstract float getTemp();

    /**
     * Returns a string representation of the temperature value.
     * Format: "Temperature:XX.XXC"
     *
     * @return formatted temperature string
     */
    @Override
    public String data2String() {
        float temp = getTemp();
        return String.format("Temperature:%.2fC", temp);
    }

    /**
     * Returns the sensor type.
     *
     * @return "TempSensor"
     */
    @Override
    public String getSensType() {
        return "TempSensor";
    }
}
