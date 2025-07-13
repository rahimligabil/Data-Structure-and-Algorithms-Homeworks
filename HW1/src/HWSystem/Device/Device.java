package HWSystem.Device;
import HWSystem.Protocol.Protocol;
public abstract class Device {
    protected int devID;
    public enum State {
        ON,
        OFF;
    }
    protected State state = State.OFF;

    protected Protocol protocol;
    public Device(Protocol protocol,int devID){
        this.protocol = protocol;
        this.devID = devID;
    }
    public abstract void turnOn();
    public abstract void TurnOff();
    public abstract String getName();
    public abstract String getDevType();
    public abstract State getState();
    public abstract boolean isProtocolCompatible(Protocol protocol);
    public int getDevID() {
        return devID;
    }
    

}
