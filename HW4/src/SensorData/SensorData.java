package SensorData;

/**
 * This class represents the sensor data for a celestial body, 
 * including temperature, pressure, humidity, and radiation.
 */
public class SensorData {
    private double temperature;
    private double pressure;
    private double humidity;
    private double radiation;

    /**
     * Constructor to initialize the sensor data with specific values.
     * @param temp The temperature of the celestial body.
     * @param pressure The pressure of the celestial body.
     * @param humidity The humidity of the celestial body (must be between 0 and 100).
     * @param radiation The radiation level of the celestial body.
     */
    public SensorData(double temp, double pressure, double humidity, double radiation){
        this.temperature = temp;
        this.pressure = pressure;
        this.radiation = radiation;
        if(humidity < 0 || humidity > 100){
            System.err.println("The humidity must be between 0 - 100");
            return;
        }
        this.humidity = humidity;
    }

    /**
     * Default constructor for the SensorData class.
     */
    public SensorData() {
    }

    /**
     * Sets the temperature for the celestial body.
     * @param t The temperature value.
     */
    public void setTemperature(double t){
        this.temperature = t;
    }

    /**
     * Retrieves the temperature of the celestial body.
     * @return The temperature of the celestial body.
     */
    public double getTemperature(){
        return temperature;
    }

    /**
     * Sets the pressure for the celestial body.
     * @param p The pressure value.
     */
    public void setPressure(double p){
        this.pressure = p;
    }

    /**
     * Retrieves the pressure of the celestial body.
     * @return The pressure of the celestial body.
     */
    public double getPressure(){
        return pressure;
    }

    /**
     * Sets the humidity for the celestial body.
     * Humidity must be between 0 and 100.
     * @param humidity The humidity value.
     */
    public void setHumidiy(double humidity){
        if(humidity < 0 || humidity > 100){
            System.err.println("Humidity must be between 0-100 percentage.");
            return;
        }
        this.humidity = humidity;
    }

    /**
     * Retrieves the humidity of the celestial body.
     * @return The humidity of the celestial body.
     */
    public double getHumidity(){
        return humidity;
    }

    /**
     * Sets the radiation level for the celestial body.
     * @param r The radiation value.
     */
    public void setRadiation(double r){
        this.radiation = r;
    }

    /**
     * Retrieves the radiation level of the celestial body.
     * @return The radiation level of the celestial body.
     */
    public double getRadiation(){
        return radiation;
    }
}
