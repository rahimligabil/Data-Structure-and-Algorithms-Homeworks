package HWSystem.Protocol;

/**
 * Represents the I2C communication protocol.
 * 
 * This class provides basic read and write functionality 
 * following the I2C communication standard.
 * It implements the Protocol interface used across the system.
 */
/**
 * Implementation of the I2C communication protocol.
 */
public class I2C implements Protocol {

    /**
     * Reads data from the I2C interface.
     *
     * @return a string indicating a read operation
     */
    @Override
    public String read() {
        return "I2C: Reading.";
    }

    /**
     * Writes data using the I2C interface.
     *
     * @param data the data to be written
     */
    @Override
    public void write(String data) {
        System.out.println("I2C: Writing " + data + ".");
    }

    /**
     * Returns the name of the protocol.
     *
     * @return "I2C"
     */
    @Override
    public String getProtocolName() {
        return "I2C";
    }
}
