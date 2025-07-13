package HWSystem.Device.WirellesIO;
import HWSystem.Protocol.*;
import HWSystem.Device.Device;

public abstract  class WirellesIO extends Device {
    public abstract void sendData(String data);
    public abstract String recvData();
    public WirellesIO(Protocol protocol,int devID){
        super(protocol,devID);
    }
  
    
}
