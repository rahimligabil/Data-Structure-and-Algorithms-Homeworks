package HWSystem.Device.Display;
import HWSystem.Protocol.*;
import HWSystem.Device.Device;

/**
 * Abstract class for display devices in the system.
 * Each display must be linked to a protocol and have a device ID.
 * Subclasses should implement how data is printed on the display.
 */
public abstract class Display extends Device {

    /**
     * Constructor for a display device.
     *
     * @param protocol The protocol used to communicate with the display.
     * @param devID The ID of the display device.
     */
    public Display(Protocol protocol, int devID){
        super(protocol, devID);
    }

    /**
     * Prints the given data to the display.
     *
     * @param data The string to be printed on the screen.
     */
    public abstract void printData(String data);
}

