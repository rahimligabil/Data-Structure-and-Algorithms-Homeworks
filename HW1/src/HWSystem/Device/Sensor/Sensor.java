package HWSystem.Device.Sensor;
import HWSystem.Protocol.*;
import HWSystem.Device.Device;

public abstract class Sensor extends Device{
    protected int devID;
    public Sensor(Protocol protocol,int devID){
        super(protocol,devID);
    }

    public abstract String getSensType();
    public abstract String data2String();
    @Override
    public String getDevType() {
    return getSensType() + " Sensor";
}


    
}
