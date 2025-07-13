package HWSystem.Device.Display;

import HWSystem.Protocol.Protocol;

public class LCD extends Display {

    public LCD(Protocol protocol,int devID) {
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
        return "LCD";
    }

    @Override
    public String getDevType() {
       return "Display";
    }

    @Override
    public State getState() {
        return state;
    }
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
    return protocol.getProtocolName().equals("I2C");
    }
    @Override
    public void printData(String data){
        protocol.write(data);
    }
}
