package src.Task;

import src.User.MyUser;

/**
 * The MyTask class represents a task that is associated with a user and has a unique ID.
 * Tasks are comparable by the user's priority and the task's ID to determine the order in the queue.
 * The task is executed based on its priority, where lower priority values indicate higher importance.
 * 
 * @see MyUser
 */
public class MyTask implements Comparable<MyTask> {

    private MyUser user;  // The user who created this task
    private Integer id;   // The unique ID of the task

    /**
     * Constructs a new task with the given user and task ID.
     *
     * @param user The user associated with this task.
     * @param id The unique ID of the task.
     * 
     * Time Complexity: O(1)
     * This is a constructor, and constructing an object with fixed properties is a constant-time operation.
     */
    public MyTask(MyUser user, Integer id) {
        this.user = user;
        this.id = id;
    }

    /**
     * Returns a string representation of this task in the format "Task <id> User <userID>".
     * The task is represented by its unique ID and the ID of the associated user.
     * 
     * @return A string representing the task and its associated user.
     * 
     * Time Complexity: O(1)
     * The `toString()` method formats the string and returns it. This operation is constant time because it involves a simple string formatting,
     * which does not depend on the size of the task or the user, making it an O(1) operation.
     */
    public String toString() {
        return String.format("Task %d User %d", id, user.getId());
    }

    /**
     * Compares this task with another task based on the user's priority.
     * If the priorities are equal, the task IDs are compared to maintain order.
     *
     * @param o The task to compare this task with.
     * @return A negative integer, zero, or a positive integer as this task is less than,
     *         equal to, or greater than the specified task based on priority and ID.
     * 
     * Time Complexity: O(1)
     * The comparison involves two basic operations: comparing user priorities and task IDs. 
     * Both comparisons are constant time operations (O(1)).
     */
    @Override
    public int compareTo(MyTask o) {
        // Compare by user priority (lower priority value is higher importance)
        if (o.user.getPriority() < this.user.getPriority()) {
            return 1;  // This task has a higher priority
        } else if (o.user.getPriority() > this.user.getPriority()) {
            return -1; // The other task has a higher priority
        } else {
            // If priorities are the same, compare by task ID
            return this.id - o.id;
        }
    }
}
