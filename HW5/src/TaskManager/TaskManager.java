package src.TaskManager;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import src.PriorityQueue.MyPriorityQueue;
import src.PriorityQueue.TaskScheduler;
import src.Task.MyTask;
import src.User.MyUser;

/**
 * The TaskManager class manages the task queue and the users associated with each task.
 * It allows loading user data from a configuration file, adding tasks for users, and executing the tasks in priority order.
 * Tasks are stored in a priority queue, which ensures that the highest priority tasks are executed first.
 * 
 * @see MyTask
 * @see MyUser
 */
public class TaskManager {
    private MyPriorityQueue<MyTask> taskQueue = new TaskScheduler<>();  // A priority queue to hold tasks
    private ArrayList<MyUser> users = new ArrayList<>();  // A list of users

    /**
     * Loads the configuration file containing user priorities and creates a user list.
     * Each user is assigned a unique ID, and their priority is read from the file.
     *
     * @param filename The path to the configuration file containing user priorities.
     * @throws FileNotFoundException If the specified configuration file is not found.
     * 
     * Time Complexity: O(n)
     * The method iterates through each line in the file and parses it. 
     * Therefore, the complexity is linear with respect to the number of lines in the file (n).
     */
    public void loadConfig(String filename) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File(filename));
        int id = 0;
        // Read the file line by line
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;  // Skip empty lines
            try {
                // Parse the priority for each user
                int priority = Integer.parseInt(line);
                users.add(new MyUser(id++, priority));  // Add the user with their ID and priority
            } catch (NumberFormatException e) {
                System.err.println("Invalid priority format: " + line);  // Handle invalid priority format
            }
        }
        scanner.close();  // Close the scanner after reading the file
    }

    /**
     * Adds a task for the specified user. The task is placed in the priority queue.
     * The task will be processed based on the user's priority.
     *
     * @param userID The ID of the user who owns the task.
     * @param taskID The unique ID of the task.
     * 
     * Time Complexity: O(log n)
     * The add operation for a priority queue (min-heap) requires maintaining the heap property, 
     * which has a logarithmic time complexity in relation to the number of tasks in the queue (n).
     */
    public void addTask(int userID, int taskID) {
        if (userID < 0 || userID >= users.size()) {
            System.err.println("Invalid user ID: " + userID);  // Handle invalid user ID
            return;
        }
        MyTask task = new MyTask(users.get(userID), taskID);  // Create a new task for the user
        taskQueue.add(task);  // Add the task to the priority queue
    }

    /**
     * Executes tasks in the priority order, printing each task as it is executed.
     * Tasks are processed by removing them from the priority queue, starting with the highest priority task.
     * 
     * Time Complexity: O(n log n)
     * The method calls poll() until the queue is empty, which has a complexity of O(log n) for each poll.
     * Since it performs this operation for each task, the overall complexity is O(n log n).
     */
    public void executeTasks() {
        while (!taskQueue.isEmpty()) {
            System.out.println(taskQueue.poll());  // Poll the task with the highest priority and print it
        }
    }
}
