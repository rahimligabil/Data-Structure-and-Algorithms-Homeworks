package HWSystem.Protocol;

public class OneWire implements Protocol{
    @Override
    public String read(){
        return "OneWire: Reading.";
    }
    @Override
    public void write(String data){
        System.out.println("OneWire : Writing " + data  + ".");
    }
    @Override
    public String getProtocolName(){
        return "OneWire";
    }
}
