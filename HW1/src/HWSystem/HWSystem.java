package HWSystem;
import java.io.File;
import java.util.ArrayList;
import java.util.Scanner;

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

public class HWSystem {
    private ArrayList<Port> ports = new ArrayList<>();
    private ArrayList<Device> devices = new ArrayList<>();
    
    private int maxSensor,maxDisplays,maxWirelles,maxMotorDrivers;


    public void addDevice(String devName, int portID, int devID) {
    if (portID < 0 || portID >= ports.size()) {
        System.out.println("Error: Invalid portID.");
        return;
    }

    Port port = ports.get(portID);

    if (!port.isEmpty()) {
        System.out.println("Error: Port already occupied.");
        return;
    }

 
    Protocol portProtocol = port.getProtocol();

    Device device = null;

    if (devName.equals("DHT11")) {
        device = new DHT11(portProtocol, devID);
    } else if (devName.equals("GY951")) {
        device = new GY951(portProtocol, devID);
    } else if (devName.equals("OLED")) {
        device = new OLED(portProtocol, devID);
    } else if (devName.equals("LCD")) {
        device = new LCD(portProtocol, devID);
    } else if (devName.equals("Wifi")) {
        device = new Wifi(portProtocol, devID);
    } else if (devName.equals("Bluetooth")) {
        device = new Bluetooth(portProtocol, devID);
    } else if (devName.equals("MPU6050")) {
        device = new MPU6050(portProtocol, devID);
    } else if (devName.equals("BME280")) {
        device = new BME280(portProtocol, devID);
    } else if (devName.equals("PCA9685")) {
        device = new PCA9685(portProtocol, devID);
    } else if (devName.equals("SparkFunMD")) {
        device = new SparkFunMD(portProtocol, devID);
    } else {
        System.out.println("Error: Invalid device.");
        return;
    }

    int maxLimit = 0;
    if (device.getDevType().equals("TempSensor Sensor")) {
        maxLimit = maxSensor;
    }
    else if (device.getDevType().equals("IMUSensor Sensor")) {
        maxLimit = maxSensor;
    }  
    else if (device.getDevType().equals("Display")) {
        maxLimit = maxDisplays;
    } 
    else if (device.getDevType().equals("WirelessAdapter")) {
        maxLimit = maxWirelles;
    } 
    else if (device.getDevType().equals("MotorDriver")) {
        maxLimit = maxMotorDrivers;
    } 
    else {
        maxLimit = 100000;
    }


   
    if (!device.isProtocolCompatible(portProtocol)) {
        System.out.println("Error: The device protocol is not same with port protocol");
        return;
    }

  
    for (Device d : devices) {
        if (d.getDevType().equals(device.getDevType()) && d.getDevID() == devID) {
            System.out.println("Error: This device is already used");
            return;
        }
    }

    if (count(device.getDevType()) >= maxLimit) {
        System.out.println("Errow : Max  number of " + device.getDevType() + " is reached.");
        return;
    }

   
    port.setDevice(device);
    devices.add(device);

    System.out.println(devName + " adding succesfullyy.");
}
    public int count(String devType) {
    int count = 0;
    for (Device d : devices) {
        if (d.getDevType().equals(devType)) {
            count++;
        }
    }
    return count;
}
    public void removeDevice(int portID){
    if (portID < 0 || portID >= ports.size()) {
        System.out.println("Error: Invalid portID.");
        return;
    }
    Port port = ports.get(portID);
    if(port.isEmpty()){
        System.out.println("The port is empty");
        return;
    }
    Device d = port.getDevice();
    if(d.getState() == State.ON){
        System.out.println("Error the device " + d.getName() + "is active");
        System.out.println("Commmand failed.");
        return;
    }
    devices.remove(d);
    port.removeDevice();
    System.out.println(d.getName() + " is removed from Port:" + portID);

   }
    public void turnOn(int portID){
    if (portID < 0 || portID >= ports.size()) {
        System.out.println("Error: Invalid portID.");
        return;
    }

    Port port = ports.get(portID);

    if(port.isEmpty()){
        System.out.println("Error: The port is empty, no device to turn ON.");
        return;
    }
    
    Device device = port.getDevice();

    if (device.getState() == State.ON) {
        System.out.println("Error: " + device.getName() + " is already ON.");
        return;
    }
    device.turnOn();
}
    public void turnOff(int portID){
        if (portID < 0 || portID >= ports.size()) {
            System.out.println("Error: Invalid portID.");
            return;
        }
        Port port = ports.get(portID);

        if(port.isEmpty()){
        System.out.println("Error: The port is empty, no device to turn OFF.");
        return;
        }

        Device device = port.getDevice();

        if (device.getState() == State.OFF) {
            System.out.println("Error: " + device.getName() + " is already OFF.");
            return;
        }

        device.TurnOff();
    }
    public void listPorts(){
        System.out.println("List of ports:");
        for(Port p : ports){
            if (p.isEmpty()) {
                System.out.println( p.getportID() + " " + p.getProtocol().getProtocolName()  + " is  empty");
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
    public void listDevicesByType(String devType){
        System.out.println("List of " + devType + " s:");
        int flag = 0;
        for(Port p : ports){
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
            System.out.println("No device finding " + devType + " type.");
            return;
        }
    }
    public void readSensor(int devID){
        for(Device d : devices){
            if(d.getDevID() == devID){
              if(d instanceof Sensor s){ 
                    if(s.getState() == State.OFF){
                        System.out.println("Error : Device is not active.");
                        System.out.println("Command failed.");
                        return;
                    }
                    String data = s.data2String();
                    System.out.println(s.getName() + " " + s.getDevType() + ": " + data);

                }
                else {
                    System.out.println("Error: This device is not a Sensor.");
                }
                return;
            }
        }
        System.out.println("Error: No device with given devID.");
      }
    public void  printDisplay(int devID,String str){
        for(Device d : devices){
            if(d.getDevID() == devID){
                if(d instanceof Display dev){
                    if(dev.getState() == State.OFF){
                        System.out.println("Error : The device is OFF");
                        return;
                    }
                    dev.printData(str);
                }
                else{
                    System.out.println("Error: This device is not a Display device");
                }
                return;
        }
    }
    System.out.println("Error: No device with given devID.");
}
    public void readWireless(int devID){
        for(Device d : devices){
            if(d.getDevID() == devID){
                if(d instanceof WirellesIO w){
                    if(w.getState() == State.OFF){
                        System.out.println("Error : The device is OFF");
                        return;
                    }
                    System.out.println(w.recvData());
                }
                else{
                    System.out.println("Error: This device is not a WirellessIO device");
                }
                return;
            }
        }
        System.out.println("Error: No device with given devID.");

    }
    public void writeWireless(int devID,String str){
        for(Device d : devices){
            if(d.getDevID() == devID){
                if(d instanceof WirellesIO w){
                    if(w.getState() == State.OFF){ 
                        System.out.println("Error : The device is OFF");
                        return;
                    }
                    w.sendData(str);
                }
                else{
                    System.out.println("Error: This device is not a WirellessIO device");
                }
                return;
            }
        }
        System.out.println("Error: No device with given devID.");

    }
    public void setMotorSpeed(int devID, int speed){
        if(speed < 0){
            System.out.println("The speed can not be a negative");
            return;
        }
        for(Device d : devices){
            if(d.getDevID() == devID){
                if(d instanceof MotorDriver m){
                    if(m.getState() == State.OFF){ 
                        System.out.println("Error : The device is OFF");
                        return;
                    }
                    m.setMotorSpeed(speed);
                }
                else{
                    System.out.println("Error: This device is not a MotorDriver device");
                }
                return;
            }
        }
        System.out.println("Error: No device with given devID.");

    }

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
                    System.out.println("Invalid protocol name: " + protoName);
                    continue; 
                }
    
                ports.add(new Port(i, protocol));
            }
    
            maxSensor = Integer.parseInt(scanner.nextLine().replace("# of sensors:", "").trim()); // yazi kismini boslukla replace edip bosluklari cikartip integer turune ceviriyo
    
            maxDisplays = Integer.parseInt(scanner.nextLine().replace("# of displays:", "").trim());
    
            maxWirelles = Integer.parseInt(scanner.nextLine().replace("# of wireless adapters:", "").trim());
    
            maxMotorDrivers = Integer.parseInt(scanner.nextLine().replace("# of motor drivers:", "").trim());
    
            scanner.close();
            System.out.println("Configuration file is installed");
        } catch (Exception e) {
            System.out.println("File reading error: " + e.getMessage()); // dosya hata firlatilirsa yakalar.
        }
    }
    public void runCommands(String fileName) {
        try {
            Scanner scanner = new Scanner(new File(fileName));
    
            while (scanner.hasNextLine()) { // dosyadaki lineleri aliyo tek tek
                String line = scanner.nextLine().trim();
                if (line.isEmpty()) continue;  
    
                if (line.startsWith("Command: ")) { // commmandi kaldiriyo basindan
                    line = line.substring(9).trim();  
                } else {
                    continue;  
                }
    
                String[] parts = line.split(" "); // boslukklari temizlioy
                String command = parts[0];
    
                try {
                    if (command.equals("turnON")) {
                        int portID = Integer.parseInt(parts[1]);
                        turnOn(portID);
    
                    } else if (command.equals("turnOFF")) {
                        int portID = Integer.parseInt(parts[1]);
                        turnOff(portID);
    
                    } else if (command.equals("addDev")) {
                        String devName = parts[1];
                        int portID = Integer.parseInt(parts[2]);
                        int devID = Integer.parseInt(parts[3]);
                        addDevice(devName, portID, devID);
    
                    } else if (command.equals("rmDev")) {
                        int portID = Integer.parseInt(parts[1]);
                        removeDevice(portID);
    
                    } else if (command.equals("list")) {
                        if (parts[1].equals("ports")) {
                            listPorts();
                        } else {
                            String devType = parts[1];
                            listDevicesByType(devType);
                        }
    
                    } else if (command.equals("readSensor")) {
                        int devID = Integer.parseInt(parts[1]);
                        readSensor(devID);
    
                    } else if (command.equals("printDisplay")) {
                        int devID = Integer.parseInt(parts[1]);
                        String str = "";
                        for (int i = 2; i < parts.length; i++) {
                            str += parts[i];
                            if (i != parts.length - 1) str += " ";
                        }
                        printDisplay(devID, str);
    
                    } else if (command.equals("readWireless")) {
                        int devID = Integer.parseInt(parts[1]);
                        readWireless(devID);
    
                    } else if (command.equals("writeWireless")) {
                        int devID = Integer.parseInt(parts[1]);
                        String str = "";
                        for (int i = 2; i < parts.length; i++) {
                            str += parts[i];
                            if (i != parts.length - 1) str += " ";
                        }
                        writeWireless(devID, str);
    
                    } else if (command.equals("setMotorSpeed")) {
                        int devID = Integer.parseInt(parts[1]);
                        int speed = Integer.parseInt(parts[2]);
                        setMotorSpeed(devID, speed);
    
                    } else if (command.equals("exit")) {
                        System.out.println("The program is finish.");
                        break;
    
                    } else {
                        System.out.println("Error invalid command " + command);
                    }
    
                } catch (Exception e) {
                    System.out.println("Invalid command " + line);
                }
            }
    
            scanner.close();
        } catch (Exception e) {
            System.out.println("File reading error: " + e.getMessage()); // hata olursa yakaliyo
        }
    }
    
    

}   
