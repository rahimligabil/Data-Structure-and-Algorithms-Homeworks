package Tree;

import SensorData.SensorData;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

/**
 * Represents a tree data structure for modeling a planetary system. 
 * Each node in the tree represents a celestial body (Star, Planet, or Satellite).
 */
public class Tree {
    
    /**
     * Represents a node (Star, Planet, or Satellite) in the planetary system.
     */
    static public class Node {
        public String name;
        public String type; // Star, Planet, Satellite
        public SensorData sensordata;
        public ArrayList<Node> children;

        /**
         * Default constructor for initializing a node with default values.
         */
        public Node() {
            this.name = "";
            this.type = "";
            this.sensordata = null;
            this.children = new ArrayList<>();
        }

        /**
         * Constructor to initialize a node with a specified name and type.
         * @param name The name of the celestial body (e.g., "Earth").
         * @param type The type of the node (e.g., "Planet").
         */
        public Node(String name, String type) {
            this.name = name;
            this.type = type;
            this.sensordata = null;
            this.children = new ArrayList<>();
        }

        /**
         * Constructor to initialize a node with a specified name, type, and sensor data.
         * @param name The name of the celestial body (e.g., "Earth").
         * @param type The type of the node (e.g., "Planet").
         * @param sensorData The sensor data (e.g., temperature, pressure, etc.).
         */
        public Node(String name, String type, SensorData sensorData) {
            this.name = name;
            this.type = type;
            this.sensordata = sensorData;
            this.children = new ArrayList<>();
        }

        /**
         * Adds a child node to the current node.
         * @param child The child node to add.
         * @return True if the child node is added successfully.
         */
        public boolean addChildren(Node child) {
            this.children.add(child);
            return true;
        }
    }

    public Node root;

    /**
     * Constructor to initialize the tree with a null root node.
     */
    public Tree() {
        root = null;
    }

    /**
     * Creates a planetary system with a star as the root node.
     * @param name The name of the star.
     * @param temp The temperature of the star.
     * @param pressure The pressure of the star.
     * @param humidity The humidity of the star (must be 0).
     * @param radiation The radiation level of the star.
     */
    public void createPlanetSystem(String name, double temp, double pressure, double humidity, double radiation) {
        if (root != null) {
            System.err.println("Error: A planetary system already exists.");
            return;
        }

        if (humidity != 0) {
            System.err.println("Error: Stars must have 0 humidity.");
            return;
        }

        if (!isValidSensorData(temp, pressure, humidity, radiation)) {
            System.err.println("Error: Invalid sensor data.");
            return;
        }

        root = new Node(name, "Star");
        root.sensordata = new SensorData(temp, pressure, humidity, radiation);
    }

    /**
     * Adds a planet node under a parent node.
     * @param name The name of the planet.
     * @param parentName The name of the parent node (either star or planet).
     * @param temp The temperature of the planet.
     * @param pressure The pressure of the planet.
     * @param humidity The humidity of the planet.
     * @param radiation The radiation level of the planet.
     * @return True if the planet is added successfully.
     */
    public boolean addPlanet(String name, String parentName, double temp, double pressure, double humidity, double radiation) {
        if (!isValidSensorData(temp, pressure, humidity, radiation)) {
            System.err.println("Error: Invalid sensor data.");
            return false;
        }

        if (findElement(name) != null) {
            System.err.println("Error: A node with this name already exists.");
            return false;
        }

        Node parent = findElement(parentName);
        if (parent == null) {
            System.err.println("Error: Parent node not found.");
            return false;
        }

        Node planet = new Node(name, "Planet");
        planet.sensordata = new SensorData(temp, pressure, humidity, radiation);
        parent.addChildren(planet);
        System.out.println("Planet added successfully: " + name);
        return true;
    }

    /**
     * Adds a satellite node under a planet node.
     * @param name The name of the satellite.
     * @param parentName The name of the planet node.
     * @param temp The temperature of the satellite.
     * @param pressure The pressure of the satellite.
     * @param humidity The humidity of the satellite.
     * @param radiation The radiation level of the satellite.
     * @return True if the satellite is added successfully.
     */
    public boolean addSatellite(String name, String parentName, double temp, double pressure, double humidity, double radiation) {
        if (!isValidSensorData(temp, pressure, humidity, radiation)) {
            System.err.println("Error: Invalid sensor data.");
            return false;
        }

        if (findElement(name) != null) {
            System.err.println("Error: A node with this name already exists.");
            return false;
        }

        Node parent = findElement(parentName);
        if (parent == null) {
            System.err.println("Error: Parent node not found.");
            return false;
        }

        if (!parent.type.equals("Planet")) {
            System.err.println("Error: Satellites can only be added to planets.");
            return false;
        }

        Node satellite = new Node(name, "Satellite");
        satellite.sensordata = new SensorData(temp, pressure, humidity, radiation);
        parent.addChildren(satellite);
        System.out.println("Satellite added successfully: " + name);
        return true;
    }

    /**
     * Validates the sensor data for a node.
     * @param temp Temperature value.
     * @param pressure Pressure value.
     * @param humidity Humidity value.
     * @param radiation Radiation value.
     * @return True if the data is valid, otherwise false.
     */
    private boolean isValidSensorData(double temp, double pressure, double humidity, double radiation) {
        return temp >= 0 && pressure >= 0 && humidity >= 0 && humidity <= 100 && radiation >= 0;
    }

    /**
     * Finds an element (node) by its name.
     * @param name The name of the node to search for.
     * @return The node if found, otherwise null.
     */
    public Node findElement(String name) {
        return bfs(root, name);
    }

    /**
     * Performs a breadth-first search to find a node by its name.
     * @param root The root node to start the search.
     * @param name The name of the node to find.
     * @return The node if found, otherwise null.
     */
    private Node bfs(Node root, String name) {
        if (root == null) return null;

        List<Node> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            Node current = queue.remove(0);
            if (name.equals(current.name)) {
                return current;
            }
            queue.addAll(current.children);
        }

        return null;
    }

    /**
     * Finds nodes with radiation levels above the given threshold.
     * @param root The root node to start searching from.
     * @param threshold The radiation threshold.
     * @return A list of nodes with radiation levels above the threshold.
     */
    public List<Node> findRadiationAnomalies(Node root, double threshold) {
        List<Node> anomalies = new ArrayList<>();
        findAnomaliesRecursive(root, threshold, anomalies);
        return anomalies;
    }

    /**
     * Recursive method to find radiation anomalies in the tree.
     * @param root The current node.
     * @param threshold The radiation threshold.
     * @param anomalies The list to store nodes with anomalies.
     */
    private void findAnomaliesRecursive(Node root, double threshold, List<Node> anomalies) {
        if (root == null) return;

        if (root.sensordata.getRadiation() > threshold) {
            anomalies.add(root);
        }

        for (Node child : root.children) {
            findAnomaliesRecursive(child, threshold, anomalies);
        }
    }

    /**
     * Finds the path to a node by its name.
     * @param name The name of the node.
     * @return A stack representing the path from the root to the node.
     */
    public Stack<Node> getPathTo(String name) {
        Stack<Node> pathStack = new Stack<>();
        if (root == null) {
            System.err.println("Error: Root is null.");
            return pathStack;
        }

        boolean found = getPathToRecursive(root, pathStack, name);
        if (!found) {
            System.err.println("Node not found: " + name);
        }

        // Reverse the stack to get the path from root to the node.
        Stack<Node> reversed = new Stack<>();
        while (!pathStack.isEmpty()) {
            reversed.push(pathStack.pop());
        }

        return reversed;
    }

    /**
     * Recursive helper method to find the path to a node.
     * @param root The current node to check.
     * @param pathStack The stack to store the path.
     * @param name The name of the node.
     * @return True if the node is found, otherwise false.
     */
    private boolean getPathToRecursive(Node root, Stack<Node> pathStack, String name) {
        if (root == null) return false;

        pathStack.push(root);
        if (root.name.equals(name)) return true;

        for (Node child : root.children) {
            if (getPathToRecursive(child, pathStack, name)) {
                return true;
            }
        }

        pathStack.pop();
        return false;
    }

    /**
     * Prints the mission report for the entire planetary system.
     * @param root The root node of the system.
     */
    public void printMissionReport(Node root) {
        if (root == null) return;
    
        // Çıktıyı daha görsel bir şekilde bastırmak için her seviyede girinti ekliyoruz
        printNodeReport(root, 0); // 0, root'un derinliği olduğu için ilk seviye
    }
    
    private void printNodeReport(Node node, int level) {
        if (node == null) return;
    
        // Derinliğe göre uygun bir girinti ekliyoruz
        String indentation = "  ".repeat(level); // Her seviyede 2 boşluk girintisi ekliyoruz
    
        // Gezegen veya uydu bilgilerini daha düzenli bastırıyoruz
        System.out.println(indentation + node.name + " (" + node.type + ") -> " +
                "Temperature: " + node.sensordata.getTemperature() + " K, " +
                "Pressure: " + node.sensordata.getPressure() + " Pascal, " +
                "Humidity: " + node.sensordata.getHumidity() + " %, " +
                "Radiation: " + node.sensordata.getRadiation() + " Sv");
    
        // Çocukları (alt gezegenler ve uydular) için aynı işlemi tekrarlıyoruz
        for (Node child : node.children) {
            printNodeReport(child, level + 1);  // Çocuklar bir seviye derinde olacak
        }
    }
    

    /**
     * Prints the mission report for a specific node by its name.
     * @param root The root node of the system.
     * @param name The name of the node to print the report for.
     * @return True if the report for the node is printed, otherwise false.
     */
    public boolean printMissionReportNode(Node root, String name) {
        if (root == null) return false;

        if (root.name.equals(name)) {
            System.out.println(
                root.name + " (" + root.type + ") -> " +
                "Temperature: " + root.sensordata.getTemperature() + " K, " +
                "Pressure: " + root.sensordata.getPressure() + " Pascal, " +
                "Humidity: " + root.sensordata.getHumidity() + " %, " +
                "Radiation: " + root.sensordata.getRadiation() + " Sv"
            );
            return true;
        }

        for (Node child : root.children) {
            if (printMissionReportNode(child, name)) {
                return true;
            }
        }

        return false;
    }
}
