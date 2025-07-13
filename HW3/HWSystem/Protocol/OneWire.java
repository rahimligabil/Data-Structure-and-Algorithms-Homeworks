package HWSystem.Protocol;

/**
 * Represents the OneWire communication protocol.
 * 
 * This class provides basic read and write operations for the OneWire protocol.
 * It implements the Protocol interface used for communication in the hardware system.
 */
/**
 * Implementation of the OneWire communication protocol.
 */
public class OneWire implements Protocol {

    /**
     * Reads data from the OneWire interface.
     *
     * @return a string indicating a read operation
     */
    @Override
    public String read() {
        return "OneWire: Reading.";
    }

    /**
     * Writes data using the OneWire interface.
     *
     * @param data the data to be written
     */
    @Override
    public void write(String data) {
        System.out.println("OneWire : Writing " + data + ".");
    }

    /**
     * Returns the name of the protocol.
     *
     * @return "OneWire"
     */
    @Override
    public String getProtocolName() {
        return "OneWire";
    }
}
