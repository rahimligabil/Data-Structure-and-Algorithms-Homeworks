package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

public class GY951 extends IMUSensor{

    public GY951(Protocol protocol,int devID) {
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
        return "GY951";
    }
    @Override
    public State getState() {
       return state;
    }
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
    return protocol.getProtocolName().equals("SPI") || protocol.getProtocolName().equals("UART");
    }
    public float getAccel(){
        protocol.read();  
        return (float)(Math.random() * 100);
    }
    public float getRot(){
        protocol.read();  
        return (float)(Math.random() * 100);
    }
  
    
    
}
