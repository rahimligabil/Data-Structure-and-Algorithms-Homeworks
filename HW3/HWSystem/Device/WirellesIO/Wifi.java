package HWSystem.Device.WirellesIO;

import HWSystem.Protocol.Protocol;

/**
 * Represents a Wifi communication device.
 * 
 * This device supports both SPI and UART protocols for wireless data transfer.
 * It can be turned on or off, and provides basic methods for sending and receiving data.
 */
public class Wifi extends WirellesIO {

    /**
     * Creates a Wifi device with the given protocol and device ID.
     *
     * @param protocol the communication protocol (SPI or UART)
     * @param devID the ID of the device
     */
    public Wifi(Protocol protocol, int devID) {
        super(protocol, devID);
    }

    /**
     * Turns on the Wifi device.
     */
    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
    }

    /**
     * Turns off the Wifi device.
     */
    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
    }

    /**
     * Returns the name of this device.
     *
     * @return "Wifi"
     */
    @Override
    public String getName() {
        return "Wifi";
    }

    /**
     * Returns the type of the device.
     *
     * @return "WirellesIO"
     */
    @Override
    public String getDevType() {
        return "WirellesIO";
    }

    /**
     * Returns the current ON/OFF state of the Wifi device.
     *
     * @return the device state
     */
    @Override
    public State getState() {
        return state;
    }

    /**
     * Checks whether the given protocol is supported by the Wifi device.
     * Wifi supports both SPI and UART protocols.
     *
     * @param protocol the protocol to check
     * @return true if protocol is SPI or UART, false otherwise
     */
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
        return protocol.getProtocolName().equals("SPI") || protocol.getProtocolName().equals("UART");
    }

    /**
     * Sends the given data through the Wifi connection.
     *
     * @param data the data to send
     */
    @Override
    public void sendData(String data) {
        System.out.println(getName() + ": Sending \"" + data + "\".");
    }

    /**
     * Receives data from the Wifi connection.
     * Currently returns a static test value.
     *
     * @return the received data
     */
    @Override
    public String recvData() {
        return "TestData";
    }
}
