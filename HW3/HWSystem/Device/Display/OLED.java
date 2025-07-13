package HWSystem.Device.Display;

import HWSystem.Protocol.Protocol;

/**
 * Represents an OLED display device in the system.
 * <p>
 * OLED displays support only the SPI protocol.
 * This class handles basic operations such as turning the screen on/off
 * and printing data to the display.
 * </p>
 */
public class OLED extends Display {

    /**
     * Constructs an OLED display device with a given protocol and device ID.
     *
     * @param protocol the protocol used to communicate with this device (must be SPI)
     * @param devID the unique ID of the device
     */
    public OLED(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the OLED display.
     * Sets the internal state to ON and prints a message.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the OLED display.
     * Sets the internal state to OFF and prints a message.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of the display type.
     *
     * @return "OLED"
     */
    @Override
    public String getName() {
        return "OLED";
    }

    /**
     * Returns the general device type.
     *
     * @return "Display"
     */
    @Override
    public String getDevType() {
        return "Display";
    }

    /**
     * Returns the current ON/OFF state of the OLED.
     *
     * @return the current device state
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks if the given protocol is compatible with this OLED.
     * OLED only supports the SPI protocol.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is SPI, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("SPI");
    }

    /**
     * Prints the given string data to the OLED screen.
     *
     * @param data the string to display
     */
    @Override
    public void printData(String data) {
        System.out.println(getName() + ": Printing \"" + data + "\".");
    }
}
