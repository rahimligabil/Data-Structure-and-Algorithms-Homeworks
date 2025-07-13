package HWSystem.Device.Sensor;

import HWSystem.Protocol.Protocol;

public class DHT11 extends TempSensor {

     public DHT11(Protocol protocol, int devID)
    {
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
       return "DHT11";
    }

    @Override
    public State getState() {
      return state;
    }
    @Override
    public boolean isProtocolCompatible(Protocol protocol) {
    return protocol.getProtocolName().equals("OneWire");
    }
    
    public float getTemp(){
        protocol.read();  
        return (float)(Math.random() * 100);
    }
   
    
}
