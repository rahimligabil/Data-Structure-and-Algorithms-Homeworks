package Main;

import java.util.NoSuchElementException;
import java.util.Scanner;
import src.TaskManager.TaskManager;

/**
 * The Main class processes tasks based on user input and prioritizes them according to the user IDs.
 * It reads a configuration file to load users and their priorities, and then reads task IDs from the terminal.
 * When the "execute" command is given, it processes the tasks in priority order.
 */
public class Main {

    /**
     * The main entry point of the program.
     * It loads the configuration file, reads task IDs from the terminal, and processes them accordingly.
     * When the "execute" command is entered, the tasks are executed in priority order.
     *
     * @param args Command-line arguments, where the first argument is the path to the configuration file.
     * 
     * Time Complexity: O(n log n)
     * The method processes user input and adds tasks to the task manager (which takes O(log n) for each task). 
     * The "execute" command processes all tasks in the priority queue, which also takes O(n log n) overall.
     */
    public static void main(String[] args) {
        
        // Ensure that a valid configuration file path is provided as a command-line argument
        if (args.length != 1) {
            System.err.println("Usage: java Main <config_file>");
            return;
        }

        // Create a new TaskManager instance
        TaskManager tm = new TaskManager();

        // Load the configuration file
        try {
            tm.loadConfig(args[0]);
        } catch (Exception e) {
            System.err.println("ERROR: Configuration file could not be read.");
            return;
        }

        // Create a scanner object to read user input from the terminal
        Scanner scanner = new Scanner(System.in);
        int taskID = 0;

        // Loop to read task input from the terminal
        while (true) {
            String input;
            try {
                input = scanner.nextLine();
            } catch (NoSuchElementException e) {
                break;
            }

            // Skip empty inputs
            if (input.isEmpty()) continue;

            // Exit the loop and process tasks if "execute" command is given
            if (input.equalsIgnoreCase("execute")) {
                break;
            }

            try {
                // Parse the user ID and add the task
                int userID = Integer.parseInt(input);
                tm.addTask(userID, taskID++);
            } catch (NumberFormatException e) {
                // Print error message if the input is not a valid number
                System.err.println("Invalid input: " + input);
            }
        }

        // Close the scanner
        scanner.close();

        // Execute the tasks in priority order
        tm.executeTasks();
    }
}
