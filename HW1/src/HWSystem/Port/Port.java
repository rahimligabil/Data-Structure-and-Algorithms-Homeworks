package HWSystem.Port;
import HWSystem.Device.Device;
import HWSystem.Protocol.Protocol;

public class Port {
    private int portID;
    private Protocol protocol;
    private Device device;

    public Port(int id,Protocol p){
        this.portID = id;
        this.protocol = p;
        this.device = null;
    }
    public int  getportID(){
        return portID;
    }
    public String getPortName() {
        return "Port" + portID;
    }
    public Protocol getProtocol(){
        return protocol;
    }
    public Boolean isEmpty(){
        return device == null;
    }
    public void setDevice(Device device){
        this.device = device;
    }
    public void removeDevice(){
        this.device = null;
    }
    public Device getDevice(){
        return device;
    }
}
