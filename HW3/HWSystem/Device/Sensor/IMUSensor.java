package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

/**
 * Abstract base class for IMU (Inertial Measurement Unit) sensor devices.
 * 
 * IMUSensor defines common functionality for sensors that provide motion-related data,
 * such as acceleration and rotation. Subclasses must implement the specific logic
 * for retrieving sensor readings.
 */
public abstract class IMUSensor extends Sensor {

    /**
     * Constructs an IMUSensor with the specified protocol and device ID.
     *
     * @param protocol the communication protocol used by the sensor
     * @param devID the unique ID of the device
     */
    public IMUSensor(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Returns the current acceleration value measured by the sensor.
     *
     * @return acceleration value
     */
    public abstract float getAccel();

    /**
     * Returns the current rotation value measured by the sensor.
     *
     * @return rotation value
     */
    public abstract float getRot();

    /**
     * Returns a formatted string representation of the sensor data.
     * Format: "Accel: XX.XX, Rot: YY.YY"
     *
     * @return a string containing both acceleration and rotation data
     */
    public String data2String() {
        Float accel = getAccel();
        Float rot = getRot();
        return String.format("Accel: %.2f, Rot: %.2f", accel, rot);
    }

    /**
     * Returns the sensor type as a string.
     *
     * @return "IMUSensor"
     */
    public String getSensType() {
        return "IMUSensor";
    }
}
