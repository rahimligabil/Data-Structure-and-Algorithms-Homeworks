package Main;


import Tree.Tree;
import Tree.Tree.Node;
import java.util.Scanner;
import java.util.Stack;
import java.util.List;

/**
 * This class is the entry point for the application. It initializes the tree structure
 * for the planetary system and handles user input through the terminal.
 * Users can interact with the system to create a planetary system, add planets and satellites,
 * generate mission reports, and analyze radiation anomalies.
 */
public class Main {
    
    /**
     * The main method initializes the planetary system and continuously listens for user input.
     * It processes various commands like creating a planet system, adding planets, and printing reports.
     * 
     * @param args Command-line arguments (not used in this version).
     */
    public static void main(String[] args) {
        // Tree'yi oluştur
        Tree solarSystem = new Tree();

        // Terminalden komut almak için Scanner kullanıyoruz
        Scanner scanner = new Scanner(System.in);
        
        // Kullanıcıdan komut alıp işleme
        while (true) {
            // Kullanıcıdan bir komut al
            String line = scanner.nextLine().trim();
            
            if (line.equals("exit")) {
                System.out.println("Exiting...");
                break; // Çıkış komutu girildiğinde döngüyü sonlandır
            }
            
            String[] commandParts = line.split(" ");
            String command = commandParts[0];

            switch (command) {
                /**
                 * Creates a planetary system with a given star name and sensor data.
                 * 
                 * @param systemName The name of the star.
                 * @param temp The temperature of the star.
                 * @param pressure The pressure of the star.
                 * @param humidity The humidity of the star.
                 * @param radiation The radiation of the star.
                 */
                case "createPlanetSystem":
                    String systemName = commandParts[1];
                    double temp = Double.parseDouble(commandParts[2]);
                    double pressure = Double.parseDouble(commandParts[3]);
                    double humidity = Double.parseDouble(commandParts[4]);
                    double radiation = Double.parseDouble(commandParts[5]);
                    solarSystem.createPlanetSystem(systemName, temp, pressure, humidity, radiation);
                    break;

                /**
                 * Adds a planet to the system, specifying its name, parent planet, and sensor data.
                 * 
                 * @param planetName The name of the planet.
                 * @param parentPlanet The name of the parent planet.
                 * @param planetTemp The temperature of the planet.
                 * @param planetPressure The pressure of the planet.
                 * @param planetHumidity The humidity of the planet.
                 * @param planetRadiation The radiation of the planet.
                 */
                case "addPlanet":
                    String planetName = commandParts[1];
                    String parentPlanet = commandParts[2];
                    double planetTemp = Double.parseDouble(commandParts[3]);
                    double planetPressure = Double.parseDouble(commandParts[4]);
                    double planetHumidity = Double.parseDouble(commandParts[5]);
                    double planetRadiation = Double.parseDouble(commandParts[6]);
                    solarSystem.addPlanet(planetName, parentPlanet, planetTemp, planetPressure, planetHumidity, planetRadiation);
                    break;

                /**
                 * Adds a satellite to a specified planet, specifying the satellite's name and sensor data.
                 * 
                 * @param satelliteName The name of the satellite.
                 * @param satelliteParent The name of the parent planet.
                 * @param satTemp The temperature of the satellite.
                 * @param satPressure The pressure of the satellite.
                 * @param satHumidity The humidity of the satellite.
                 * @param satRadiation The radiation of the satellite.
                 */
                case "addSatellite":
                    String satelliteName = commandParts[1];
                    String satelliteParent = commandParts[2];
                    double satTemp = Double.parseDouble(commandParts[3]);
                    double satPressure = Double.parseDouble(commandParts[4]);
                    double satHumidity = Double.parseDouble(commandParts[5]);
                    double satRadiation = Double.parseDouble(commandParts[6]);
                    solarSystem.addSatellite(satelliteName, satelliteParent, satTemp, satPressure, satHumidity, satRadiation);
                    break;

                /**
                 * Prints the mission report for a specific node (planet).
                 * 
                 * @param planetToPrint The name of the planet to print the mission report for.
                 */
                case "printMissionReport":
                if (commandParts.length == 2) {
                    String planetToPrint = commandParts[1];  
                    boolean foundPlanet = solarSystem.printMissionReportNode(solarSystem.root, planetToPrint);
                    if (!foundPlanet) {
                        System.out.println("Planet not found.");
                    }
                } else {
                    solarSystem.printMissionReport(solarSystem.root); 
                }
                break;
            
                case "getPathTo":
                    String planetToFind = commandParts[1];
                    Stack<Tree.Node> path = solarSystem.getPathTo(planetToFind);
                    if (path.isEmpty()) {
                        System.out.println("Planet not found.");
                    } else {
                        System.out.println("Yol: ");
                        while (!path.isEmpty()) {
                            Node planet = path.pop();
                            System.out.println("--" + planet.name);
                        }
                    }
                    break;

                /**
                 * Finds radiation anomalies in the system by comparing the radiation levels with the given threshold.
                 * 
                 * @param threshold The threshold value to compare radiation levels.
                 */
                case "findRadiationAnomalies":
                    double threshold = Double.parseDouble(commandParts[1]);
                    List<Tree.Node> anomalies = solarSystem.findRadiationAnomalies(solarSystem.root, threshold);
                    if (anomalies.isEmpty()) {
                        System.out.println("No Radiation Anomalies found.");
                    } else {
                        System.out.println("Radiation Anomalies planets:");
                        for (Node anomaly : anomalies) {
                            System.out.println(anomaly.name + " -> Radiation: " + anomaly.sensordata.getRadiation());
                        }
                    }
                    break;

                /**
                 * Handles invalid or unrecognized input.
                 */
                default:
                    System.out.println("ERROR: Wrong input: " + line);
                    break;
            }
        }

        scanner.close(); // Scanner'ı kapatıyoruz
    }
}
