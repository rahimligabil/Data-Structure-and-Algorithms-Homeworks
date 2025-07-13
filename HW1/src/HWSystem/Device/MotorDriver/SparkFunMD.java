package HWSystem.Device.MotorDriver;

import HWSystem.Protocol.Protocol;

public class SparkFunMD extends MotorDriver{

    public SparkFunMD(Protocol protocol,int devID){
        super(protocol,devID);
    }

    @Override
    public void turnOn() {
        state = State.ON;
        System.out.println(getName() + ": Turning ON");
        protocol.write("Turn ON");
    }

    @Override
    public void TurnOff() {
        state = State.OFF;
        System.out.println(getName() + ": Turning OFF");
        protocol.write("Turn OFF");
    }

    @Override
    public String getName() {
        return "SparkFunMD";
    }

    @Override
    public String getDevType() {
       return "MotorDriver";
    }

    @Override
    public State getState() {
        return state;
    }
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
    return protocol.getProtocolName().equals("SPI");
    }
    @Override
    public void setMotorSpeed(int speed) {
        this.currentSpeed = speed; 
        protocol.write("Speed: " + speed); 
    }

    
   
    
}
