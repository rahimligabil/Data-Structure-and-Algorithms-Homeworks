package HWSystem.Device.Sensor;
import HWSystem.Protocol.*;
public abstract class TempSensor extends Sensor{
    public abstract   float getTemp();
    public TempSensor(Protocol protocol,int devID){
    super(protocol,devID);
}
    public String data2String() {
        float temp = getTemp();
        return String.format("Temperature:%2fC", temp);
    }
    public  String getSensType(){
        return "TempSensor";
    }
    
}
