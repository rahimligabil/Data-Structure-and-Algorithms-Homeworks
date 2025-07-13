package HWSystem.Device.MotorDriver;
import HWSystem.Protocol.*;
import HWSystem.Device.Device;

public abstract class MotorDriver extends Device {// Motorun son hızı
    protected int currentSpeed = 0;
    public abstract void setMotorSpeed(int speed);
    public MotorDriver(Protocol protocol,int devID) {
        super(protocol,devID);
        this.currentSpeed = 0; 
    }
}
