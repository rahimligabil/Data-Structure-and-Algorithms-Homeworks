package HWSystem.Device.WirellesIO;

import HWSystem.Protocol.*;
import HWSystem.Device.Device;

/**
 * Abstract base class for all wireless communication devices.
 * 
 * This class defines the common structure for wireless devices such as Bluetooth and Wifi.
 * Each device must implement how it sends and receives data over a communication protocol.
 */
public abstract class WirellesIO extends Device {

    /**
     * Constructs a wireless I/O device with the specified protocol and device ID.
     *
     * @param protocol the communication protocol used by the device
     * @param devID the unique ID of the device
     */
    public WirellesIO(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Sends data using the wireless communication interface.
     *
     * @param data the data to send
     */
    public abstract void sendData(String data);

    /**
     * Receives data from the wireless communication interface.
     *
     * @return the data received
     */
    public abstract String recvData();
}
