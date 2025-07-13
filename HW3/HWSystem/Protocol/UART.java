package HWSystem.Protocol;

/**
 * Represents the UART communication protocol.
 * 
 * This class provides basic read and write operations over the UART interface.
 * Commonly used in wireless devices such as Bluetooth and Wifi modules.
 */
/**
 * Implementation of the UART communication protocol.
 */
public class UART implements Protocol {

    /**
     * Reads data using the UART protocol.
     *
     * @return a string indicating a read operation
     */
    @Override
    public String read() {
        return "UART: Reading.";
    }

    /**
     * Writes data using the UART protocol.
     *
     * @param data the data to be written
     */
    @Override
    public void write(String data) {
        System.out.println("UART: Writing " + data + ".");
    }

    /**
     * Returns the name of the protocol.
     *
     * @return "UART"
     */
    @Override
    public String getProtocolName() {
        return "UART";
    }
}
