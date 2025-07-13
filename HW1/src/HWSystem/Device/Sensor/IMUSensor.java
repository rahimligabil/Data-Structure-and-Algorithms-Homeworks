package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

public abstract class IMUSensor extends Sensor{
    public abstract float getAccel();
    public abstract float getRot();
    public IMUSensor(Protocol protocol,int devID) {
        super(protocol,devID);
    }
    
    public String data2String() {
        Float accel = getAccel();
        Float rot = getRot();
        return String.format("Accel: %.2f, Rot: %.2f", accel, rot);
    }
    public  String getSensType(){
        return "IMUSensor";
    }
    
    

} 