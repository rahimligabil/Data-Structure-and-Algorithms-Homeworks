package HWSystem.Device.Display;
import HWSystem.Protocol.*;
import HWSystem.Device.Device;

public abstract class Display extends Device {
    public abstract void printData(String data);
    public Display(Protocol protocol,int devID){
        super(protocol,devID);
    }
   
    
}
