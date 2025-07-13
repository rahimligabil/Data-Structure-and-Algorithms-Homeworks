package HWSystem.Device.WirellesIO;

import HWSystem.Protocol.Protocol;

public class Bluetooth extends WirellesIO {

    public Bluetooth(Protocol protocol,int devID) {
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
        return "Bluetooth";
    }

    @Override
    public String getDevType() {
       return "WirellesIO";
    }

    @Override
    public State getState() {
        return state;
    }
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
    return protocol.getProtocolName().equals("UART");
    }
    @Override
    public  void sendData(String data){
        protocol.write(data);
    }
    @Override
    public String recvData(){
        return protocol.read();
    }

    
    
}
