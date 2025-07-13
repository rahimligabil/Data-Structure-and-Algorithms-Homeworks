package HWSystem.Port;

import HWSystem.Device.Device;
import HWSystem.Protocol.Protocol;
import java.util.Stack;

/**
 * Represents a hardware port in the system.
 * 
 * Each port is associated with a specific protocol and can host one device at a time.
 * It also maintains a stack-based log of actions and events.
 */
public class Port {

    /** Unique identifier of the port. */
    private int portID;

    /** The communication protocol assigned to this port. */
    private Protocol protocol;

    /** The device currently connected to this port (can be null). */
    private Device device;

    /** A stack storing log messages related to this port. */
    private Stack<String> log = new Stack<>();

    /**
     * Constructs a port with the given ID and protocol.
     * Initially, the port has no connected device.
     *
     * @param id the ID of the port
     * @param p the communication protocol used by this port
     */
    public Port(int id, Protocol p) {
        this.portID = id;
        this.protocol = p;
        this.device = null;
        log.push("Port Opened.");
    }

    /**
     * Returns the ID of the port.
     *
     * @return the port ID
     */
    public int getportID() {
        return portID;
    }

    /**
     * Returns a string representation of the port's name.
     *
     * @return the name of the port (e.g., "Port2")
     */
    public String getPortName() {
        return "Port" + portID;
    }

    /**
     * Returns the protocol used by this port.
     *
     * @return the port's protocol
     */
    public Protocol getProtocol() {
        return protocol;
    }

    /**
     * Checks whether the port is currently empty (i.e., no device is connected).
     *
     * @return true if no device is connected, false otherwise
     */
    public Boolean isEmpty() {
        return device == null;
    }

    /**
     * Connects a device to the port.
     *
     * @param device the device to connect
     */
    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * Disconnects any device currently connected to the port.
     */
    public void removeDevice() {
        this.device = null;
    }

    /**
     * Returns the device currently connected to this port.
     *
     * @return the connected device, or null if none
     */
    public Device getDevice() {
        return device;
    }

    /**
     * Adds a log entry to the port's log stack.
     *
     * @param entry the log message to add
     */
    public void addLog(String entry) {
        log.push(entry);
    }

    /**
     * Returns the entire log stack of the port.
     *
     * @return the stack of log messages
     */
    public Stack<String> getLogStack() {
        return log;
    }

    /**
     * Returns the file name used to store the port's log.
      * The format is "&lt;ProtocolName&gt;_&lt;PortID&gt;.log".
     *
     * @return the log file name for this port
     */
    public String getLogFileName() {
        return getProtocol().getProtocolName() + "_" + getportID() + ".log";
    }
}
