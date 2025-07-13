package HWSystem.Protocol;

/**
 * Defines a communication protocol interface for hardware devices.
 * 
 * All protocol types (such as I2C, SPI, UART, OneWire) must implement this interface.
 * It provides the basic operations required for device communication.
 */
public interface Protocol {

    /**
     * Reads data using the protocol.
     *
     * @return a string representing the read operation result
     */
    public String read();

    /**
     * Writes data using the protocol.
     *
     * @param data the data to be written
     */
    public void write(String data);

    /**
     * Returns the name of the protocol.
     *
     * @return protocol name as a string (e.g., "SPI", "I2C")
     */
    public String getProtocolName();
}
