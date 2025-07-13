package HWSystem;
import java.io.File;
import java.io.IOException;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedList; 

import HWSystem.Device.Device;
import HWSystem.Device.Device.State;
import HWSystem.Device.Display.Display;
import HWSystem.Device.Display.LCD;
import HWSystem.Device.Display.OLED;
import HWSystem.Device.MotorDriver.MotorDriver;
import HWSystem.Device.MotorDriver.PCA9685;
import HWSystem.Device.MotorDriver.SparkFunMD;
import HWSystem.Device.Sensor.BME280;
import HWSystem.Device.Sensor.DHT11;
import HWSystem.Device.Sensor.GY951;
import HWSystem.Device.Sensor.MPU6050;
import HWSystem.Device.Sensor.Sensor;
import HWSystem.Device.WirellesIO.Bluetooth;
import HWSystem.Device.WirellesIO.Wifi;
import HWSystem.Device.WirellesIO.WirellesIO;
import HWSystem.Port.Port;
import HWSystem.Protocol.I2C;
import HWSystem.Protocol.OneWire;
import HWSystem.Protocol.Protocol;
import HWSystem.Protocol.SPI;
import HWSystem.Protocol.UART;
/**
 * Central controller class that manages all hardware operations and protocols.
 */
public class HWSystem {
    private ArrayList<Port> ports = new ArrayList<>();
    private ArrayList<Device> devices = new ArrayList<>();
    private ArrayList<Display> display = new ArrayList<>();
    private ArrayList<Sensor> sensor = new ArrayList<>();
    private ArrayList<MotorDriver> motorDriver = new ArrayList<>();
    private ArrayList<WirellesIO> wirelles = new ArrayList<>();
    
    private int maxSensor,maxDisplays,maxWirelles,maxMotorDrivers;


        /**
     * Adds a new device to the system on the specified port and device slot.
     * 
     * Supports various types like sensors, displays, wireless adapters, and motor drivers.
     * Performs protocol compatibility and ID collision checks.
     *
     * @param devName the type/name of the device (e.g., "DHT11", "LCD")
     * @param portID the port ID where the device will be connected
     * @param devID the device ID for indexing within its category
     */

    public void addDevice(String devName, int portID, int devID) {
        if (portID < 0 || portID > ports.size() - 1) {
            System.err.println("Error: Invalid portID.");
            return;
        }
    
        Port port = ports.get(portID);
    
        if (!port.isEmpty()) {
            System.err.println("Error: Port already occupied.");
            return;
        }
    
        Protocol portProtocol = port.getProtocol();
    
        if (devName.equals("DHT11")) {
            if (devID > sensor.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            DHT11 device = new DHT11(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (sensor.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            sensor.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("GY951")) {
            if (devID > sensor.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            GY951 device = new GY951(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (sensor.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            sensor.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("OLED")) {
            if (devID > display.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            OLED device = new OLED(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (display.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            display.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("LCD")) {
            if (devID > display.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            LCD device = new LCD(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (display.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            display.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("Wifi")) {
            if (devID > wirelles.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            Wifi device = new Wifi(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (wirelles.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            wirelles.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("Bluetooth")) {
            if (devID > wirelles.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            Bluetooth device = new Bluetooth(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (wirelles.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            wirelles.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("MPU6050")) {
            if (devID > sensor.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            MPU6050 device = new MPU6050(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (sensor.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            sensor.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("BME280")) {
            if (devID > sensor.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            BME280 device = new BME280(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (sensor.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            sensor.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("PCA9685")) {
            if (devID > motorDriver.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            PCA9685 device = new PCA9685(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (motorDriver.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            motorDriver.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else if (devName.equals("SparkFunMD")) {
            if (devID > motorDriver.size() - 1) {
                System.err.println("Error: devID err of bounds.");
                return;
            }
            SparkFunMD device = new SparkFunMD(portProtocol, devID);
            if (!device.isProtocolCompatible(portProtocol)) {
                System.err.println("Error: Protocol mismatch.");
                return;
            }
            if (motorDriver.get(devID) != null) {
                System.err.println("Error: Device ID already used.");
                return;
            }
            motorDriver.set(devID, device);
            port.setDevice(device);
            device.setPortID(portID); 
            System.out.println("Device added.");
    
            return;
        }
    
        else {
            System.err.println("Error: Invalid device.");
        }
    } 
   
        /**
     * Removes the device connected to the given port.
     * 
     * The device must be turned OFF before it can be removed.
     *
     * @param portID the ID of the port to remove the device from
     */

    public void removeDevice(int portID){
    if (portID < 0 || portID >= ports.size()) {
        System.err.println("Error: Invalid portID.");
        return;
    }
    Port port = ports.get(portID);
    if(port.isEmpty()){
        System.err.println("The port is empty");
        return;
    }
    Device d = port.getDevice();
    if(d.getState() == State.ON){
        System.err.println("Error: the device " + d.getName() + " is active");
        System.err.println("Commmand failed.");
        return;
    }
    devices.remove(d);
    port.removeDevice();
    System.out.println("Device removed.");


   }
    /**
     * Turns ON the device connected to the specified port.
     *
     * @param portID the ID of the port where the device is located
     */

    public void turnOn(int portID){
    if (portID < 0 || portID >= ports.size()) {
        System.err.println("Error: Invalid portID.");
        return;
    }

    Port port = ports.get(portID);

    if(port.isEmpty()){
        System.err.println("Error: The port is empty, no device to turn ON.");
        return;
    }
    
    Device device = port.getDevice();

    if (device.getState() == State.ON) {
        System.err.println("Error: " + device.getName() + " is already ON.");
        return;
    }
    device.turnOn();
    port.addLog("Writing \"turnON\".");
}
        /**
     * Turns OFF the device connected to the specified port.
     *
     * @param portID the ID of the port where the device is located
     */

    public void turnOff(int portID){
        if (portID < 0 || portID >= ports.size()) {
            System.err.println("Error: Invalid portID.");
            return;
        }
        Port port = ports.get(portID);

        if(port.isEmpty()){
        System.err.println("Error: The port is empty, no device to turn OFF.");
        return;
        }

        Device device = port.getDevice();

        if (device.getState() == State.OFF) {
            System.err.println("Error: " + device.getName() + " is already OFF.");
            return;
        }

        device.TurnOff();

        port.addLog("Writing \"turnOFF\".");
    }

            /**
     * Lists all ports and their current states.
     * 
     * Shows protocol, occupancy status, and device details if present.
     */

    public void listPorts(){ 
        System.out.println("list of ports:");
        Iterator<Port> it = ports.iterator();
        while(it.hasNext()){
            Port p = it.next();
            if (p.isEmpty()) {
                System.out.println( p.getportID() + " " + p.getProtocol().getProtocolName()  + " empty");
            }
            else{
                Device device = p.getDevice();
                System.out.println(p.getportID() + " " + p.getProtocol().getProtocolName() + " occupied " 
                                   + device.getName() + " " 
                                   + device.getDevType() + " " 
                                   + device.getDevID() + " " 
                                   + device.getState() + " ");
            }

        }

    }
        /**
     * Lists all devices of the given type that are currently connected.
     * 
     * @param devType the device type to list (e.g., "Sensor", "Display")
     */

    public void listDevicesByType(String devType){ 
        System.out.println("list of " + devType + "s:");
        int flag = 0;
        Iterator<Port> it = ports.iterator();
        while(it.hasNext()){
        Port p = it.next();
          if(!p.isEmpty()){
            Device device = p.getDevice();
            if (device.getDevType().contains(devType)) {
                System.out.println(device.getName() + " " 
                                   + device.getDevID() + " " 
                                   + p.getportID() + " " 
                                   + p.getProtocol().getProtocolName() + ".");
                                   flag = 1;
            } 
        }
    }
      
        if(flag == 0){
            System.err.println("No device finding " + devType + " type.");
            return;
        }

    }
        /**
     * Reads data from a sensor by device ID.
     * 
     * The sensor must be turned ON. Displays formatted output using data2String().
     *
     * @param devID the ID of the sensor to read from
     */

    public void readSensor(int devID){
    Sensor dev = sensor.get(devID);
    if(dev != null){
        if(dev.getState() == State.OFF){
            System.err.println("Error : Device is not active.");
            System.err.println("Command failed.");
            return;
        }
        String data = dev.data2String();
        System.out.println(dev.getName() + " " + dev.getDevType() + ": " + data);

        ports.get(dev.getPortID()).addLog("Reading.");

    }
    else {
        System.err.println("Error: No device with given devID.");
    }
    return;
}
        /**
     * Sends a string message to a display device.
     * 
     * The display must be turned ON.
     *
     * @param devID the ID of the display
     * @param str the string message to display
     */

    public void  printDisplay(int devID,String str){
    Display dev = display.get(devID);
    if(dev != null){
    if(dev.getState() == State.OFF){
        System.err.println("Error : The device is OFF");
        return;
    }
    dev.printData(str);
    ports.get(dev.getPortID()).addLog("Writing \"" + str + "\"");
    }
    else{
        System.err.println("Error: No device with given devID.");
    }
    return;

}
        /**
     * Reads data from a wireless communication device.
     * 
     * The device must be turned ON. Output is printed to terminal.
     *
     * @param devID the ID of the wireless device
     */

    public void readWireless(int devID){ 
        WirellesIO dev = wirelles.get(devID);
        if(dev != null){
            if(dev.getState() == State.OFF){
                System.err.println("Error : The device is OFF");
                return;
            }
            String data = dev.recvData();
            System.out.println(dev.getName() + ": Received \"" + data + "\".");
    
            ports.get(dev.getPortID()).addLog("Reading.");
            return;
        }
        System.err.println("Error: No device with given devID.");
        return;

    }
        /**
     * Sends data to a wireless communication device.
     * 
     * The device must be turned ON.
     *
     * @param devID the ID of the wireless device
     * @param str the message to send
     */

    public void writeWireless(int devID,String str){
        WirellesIO dev = wirelles.get(devID);
        if(dev != null){
            if(dev.getState() == State.OFF){
                System.err.println("Error : The device is OFF");
                return;
            }
            dev.sendData(str);
            ports.get(dev.getPortID()).addLog("Writing \"" + str + "\"");
            return;
        }
        System.err.println("Error: No device with given devID.");
        return;

    }

        /**
     * Sets the speed of a motor driver device.
     * 
     * The motor must be turned ON and speed must be non-negative.
     *
     * @param devID the ID of the motor driver
     * @param speed the speed value to set
     */

    public void setMotorSpeed(int devID, int speed){ 
        if(speed < 0){
            System.err.println("The speed can not be a negative");
            return;
        }
        MotorDriver dev = motorDriver.get(devID);
        if(dev != null){
            if(dev.getState() == State.OFF){
                System.err.println("Error : The device is OFF");
                return;
            }
            dev.setMotorSpeed(speed);
    
            ports.get(dev.getPortID()).addLog("Writing \"" + speed + "\"");
            return;
        }
        System.err.println("Error: No device with given devID.");
        return;
        }
            /**
     * Loads the hardware system configuration from a file.
     * 
     * Initializes ports and device slots based on given limits and protocol setup.
     *
     * @param fileName the name of the configuration file
     */

    public void loadConfig(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName)); // bir scanner objesi olusturuyo file okumak icin
    
           
            String line = scanner.nextLine();
            line = line.replace("Port Configuration:", "").trim();
            String[] protocolStrings = line.split(","); // , leri temizliyo 
            for (int i = 0; i < protocolStrings.length; i++) {
                Protocol protocol = null;
                String protoName = protocolStrings[i].trim(); // basindaki ve sonundaki bosluklari temizliyo 
    
                if (protoName.equals("SPI")) {
                    protocol = new SPI();
                } else if (protoName.equals("I2C")) {
                    protocol = new I2C();
                } else if (protoName.equals("UART")) {
                    protocol = new UART();
                } else if (protoName.equals("OneWire")) {
                    protocol = new OneWire();
                } else {
                    System.err.println("Invalid protocol name: " + protoName);
                    continue; 
                }
    
                ports.add(new Port(i, protocol));
            }
    
            maxSensor = Integer.parseInt(scanner.nextLine().replace("# of sensors:", "").trim()); // yazi kismini boslukla replace edip bosluklari cikartip integer turune ceviriyo
    
            maxDisplays = Integer.parseInt(scanner.nextLine().replace("# of displays:", "").trim());
    
            maxWirelles = Integer.parseInt(scanner.nextLine().replace("# of wireless adapters:", "").trim());
    
            maxMotorDrivers = Integer.parseInt(scanner.nextLine().replace("# of motor drivers:", "").trim());
    
            scanner.close(); int i;
            i = 0;
            while (i < maxSensor) {
                sensor.add(null);
                i++;
            }
            i = 0;
            while (i < maxDisplays) {
                display.add(null);
                i++;
            }
            i = 0;
            while (i < maxMotorDrivers) {
                motorDriver.add(null);
                i++;
            }
            i = 0;
            while (i < maxWirelles) {
                wirelles.add(null);
                i++;
            }
        } catch (Exception e) {
            System.err.println("File reading error: " + e.getMessage()); // dosya hata firlatilirsa yakalar.
        }
    }
            /**
     * Parses and executes a single command line.
     * 
     * Supports all user-level commands like addDev, turnON, readSensor, etc.
     *
     * @param commandLine the full command line string entered by the user
     */

    public void runCommands(String commandLine) {
        LinkedList<String> parts = new LinkedList<>(Arrays.asList(commandLine.trim().split(" ")));
    
        if (parts.isEmpty()) return;
    
        String command = parts.removeFirst();
    
        try {
            if (command.equals("turnON")) {
                int portID = Integer.parseInt(parts.removeFirst());
                turnOn(portID);
    
            } else if (command.equals("turnOFF")) {
                int portID = Integer.parseInt(parts.removeFirst());
                turnOff(portID);
    
            } else if (command.equals("addDev")) {
                String devName = parts.removeFirst();
                int portID = Integer.parseInt(parts.removeFirst());
                int devID = Integer.parseInt(parts.removeFirst());
                addDevice(devName, portID, devID);
    
            } else if (command.equals("rmDev")) {
                int portID = Integer.parseInt(parts.removeFirst());
                removeDevice(portID);
    
            } else if (command.equals("list")) {
                String listType = parts.removeFirst();
                if (listType.equals("ports")) {
                    listPorts();
                } else {
                    listDevicesByType(listType);
                }
    
            } else if (command.equals("readSensor")) {
                int devID = Integer.parseInt(parts.removeFirst());
                readSensor(devID);
    
            } else if (command.equals("printDisplay")) {
                int devID = Integer.parseInt(parts.removeFirst());
                StringBuilder sb = new StringBuilder();
                while (!parts.isEmpty()) {
                    sb.append(parts.removeFirst());
                    if (!parts.isEmpty()) sb.append(" ");
                }
                printDisplay(devID, sb.toString());
    
            } else if (command.equals("readWireless")) {
                int devID = Integer.parseInt(parts.removeFirst());
                readWireless(devID);
    
            } else if (command.equals("writeWireless")) {
                int devID = Integer.parseInt(parts.removeFirst());
                StringBuilder sb = new StringBuilder();
                while (!parts.isEmpty()) {
                    sb.append(parts.removeFirst());
                    if (!parts.isEmpty()) sb.append(" ");
                }
                writeWireless(devID, sb.toString());
    
            } else if (command.equals("setMotorSpeed")) {
                int devID = Integer.parseInt(parts.removeFirst());
                int speed = Integer.parseInt(parts.removeFirst());
                setMotorSpeed(devID, speed);
    
            } else {
                System.err.println("Error: Invalid command → " + command);
            }
        } catch (Exception e) {
            System.err.println("Error: Invalid arguments in command → " + commandLine);
        }
    }

    
        /**
     * Writes all port logs to individual log files in the given directory.
     * 
     * Each port's log is saved as "&lt;Protocol&gt;_&lt;PortID&gt;.log".
     *
     * @param errputPath the folder path where log files will be saved
     */


    public void writeLogs(String errputPath) {
        Iterator<Port> portIt = ports.iterator();
    
        while (portIt.hasNext()) {
            Port port = portIt.next();
            String fileName = port.getLogFileName();
            File logFile = new File(errputPath + "/" + fileName);
    
            try {
                FileWriter writer = new FileWriter(logFile);
                Stack<String> logs = port.getLogStack();
    
                Stack<String> tempStack = new Stack<>();
                Iterator<String> logIt = logs.iterator();
    
                while (logIt.hasNext()) {
                    tempStack.push(logIt.next());
                }
    
                while (!tempStack.isEmpty()) {
                    writer.write(tempStack.pop() + "\n");
                }
    
                writer.close();
    
            } catch (IOException e) {
                System.err.println("Error writing log for port " + port.getportID());
            }
        }
    }
}
        

   
    
