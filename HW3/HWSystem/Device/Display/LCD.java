package HWSystem.Device.Display;

import HWSystem.Protocol.Protocol;

/**
 * Represents an LCD display device in the system.
 * <p>
 * LCDs are compatible only with the I2C protocol. This class handles
 * turning the display on/off and printing data to the screen.
 * </p>
 */
public class LCD extends Display {

    /**
     * Constructs an LCD device with a given protocol and device ID.
     *
     * @param protocol the protocol used to communicate with this device (must be I2C)
     * @param devID the unique ID of the device
     */
    public LCD(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the LCD display.
     * Updates the state to ON and prints a message.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the LCD display.
     * Updates the state to OFF and prints a message.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of the display type.
     *
     * @return "LCD"
     */
    @Override
    public String getName() {
        return "LCD";
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
     * Returns the current state of the device (ON or OFF).
     *
     * @return the state of the LCD
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks if the given protocol is compatible with the LCD.
     * LCD only supports the I2C protocol.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is I2C, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("I2C");
    }

    /**
     * Prints the given data to the LCD screen.
     *
     * @param data the string data to print
     */
    @Override
    public void printData(String data) {
        System.out.println(getName() + ": Printing \"" + data + "\".");
    }
}
