package HWSystem.Protocol;

/**
 * Represents the SPI communication protocol.
 * 
 * This class provides basic read and write operations over the SPI bus.
 * It is used by devices that support SPI-based communication.
 */
/**
 * Implementation of the SPI communication protocol.
 */
public class SPI implements Protocol {

    /**
     * Reads data using the SPI protocol.
     *
     * @return a string indicating a read operation
     */
    @Override
    public String read() {
        return "SPI:Reading.";
    }

    /**
     * Writes data using the SPI protocol.
     *
     * @param data the data to be written
     */
    @Override
    public void write(String data) {
        System.out.println("SPI: Writing " + data + ".");
    }

    /**
     * Returns the name of the protocol.
     *
     * @return "SPI"
     */
    @Override
    public String getProtocolName() {
        return "SPI";
    }
}
