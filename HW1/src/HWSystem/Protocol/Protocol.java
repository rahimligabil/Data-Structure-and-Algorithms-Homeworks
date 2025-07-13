package HWSystem.Protocol;

public interface Protocol {
    public String read();
    public void write(String data);
    public String getProtocolName();
}
