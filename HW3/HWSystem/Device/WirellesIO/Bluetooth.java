package HWSystem.Device.WirellesIO;

import HWSystem.Protocol.Protocol;

/**
 * Represents a Bluetooth wireless communication device.
 * 
 * This device uses the UART protocol to send and receive data wirelessly.
 * It can be turned on or off, and supports basic data transfer functions.
 */
public class Bluetooth extends WirellesIO {

    /**
     * Creates a Bluetooth device with the given protocol and device ID.
     *
     * @param protocol the protocol used for communication (must be UART)
     * @param devID the ID of the device
     */
    public Bluetooth(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the Bluetooth device.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the Bluetooth device.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of this device.
     *
     * @return "Bluetooth"
     */
    @Override
    public String getName() {
        return "Bluetooth";
    }

    /**
     * Returns the type of this device.
     *
     * @return "WirellesIO"
     */
    @Override
    public String getDevType() {
        return "WirellesIO";
    }

    /**
     * Returns the current state (ON/OFF) of the device.
     *
     * @return the device state
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks whether the device is compatible with the given protocol.
     * Only the UART protocol is supported.
     *
     * @param protocol the protocol to check
     * @return true if the protocol is UART, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("UART");
    }

    /**
     * Sends the specified data through the Bluetooth connection.
     *
     * @param data the data to be sent
     */
    @Override
    public void sendData(String data) {
        System.out.println(getName() + ": Sending \"" + data + "\".");
    }

    /**
     * Receives data from the Bluetooth connection.
     * Currently returns a static test value.
     *
     * @return the received data
     */
    @Override
    public String recvData() {
        return "TestData";
    }
}
