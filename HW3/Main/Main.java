package Main;

import java.util.Queue;
import java.util.LinkedList;
import java.util.Scanner;

import HWSystem.HWSystem;

/**
 * Entry point of the embedded hardware system simulator.
 * <p>
 * This class initializes the system using a configuration file and takes user input
 * as commands through the terminal. Commands are stored in a queue and processed
 * one by one. After processing all commands, the system writes logs to the given path.
 * </p>
 * <p>
 * <b>Usage:</b><br>
 * <code>java Main.Main &lt;configFile&gt; &lt;logOutputPath&gt;</code>
 * </p>
 */
public class Main {

    /**
     * Main method that runs the system.
     *
     * @param args command-line arguments: [0] = config file path, [1] = log output path
     */
    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java Main.Main <configFile> <logOutputPath>");
            return;
        }

        String configFile = args[0];
        String logPath = args[1];

        HWSystem system = new HWSystem();

        try {
            system.loadConfig(configFile);
        } catch (Exception e) {
            System.err.println("ERROR: Configuration file not found.");
            return;
        }

        Scanner scanner = new Scanner(System.in);
        Queue<String> commandQueue = new LinkedList<>();

        // Read commands from user input
        while (true) {
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("exit")) {
                break;
            }

            commandQueue.add(input);
        }

        // Process commands in the order they were entered
        while (!commandQueue.isEmpty()) {
            String command = commandQueue.poll();
            system.runCommands(command);
        }

        System.out.println("Exiting...");
        system.writeLogs(logPath);
        scanner.close();
    }
}
