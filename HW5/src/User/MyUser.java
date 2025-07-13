package src.User;

/**
 * The MyUser class represents a user with a unique ID and a priority value.
 * This class allows access to a user's ID and priority.
 */
public class MyUser {
    private Integer id;  // The unique ID of the user
    private Integer priority;  // The priority of the user, where lower values indicate higher priority

    /**
     * Constructs a MyUser object with the given ID and priority.
     *
     * @param id The unique ID of the user.
     * @param priority The priority of the user.
     */
    public MyUser(Integer id, Integer priority) {
        this.id = id;
        this.priority = priority;
    }

    /**
     * Returns the unique ID of the user.
     * 
     * @return The ID of the user.
     * 
     * Time Complexity: O(1)
     * This method simply returns the value of the `id` field, which is a direct access operation.
     */
    public Integer getId() {
        return this.id;
    }

    /**
     * Returns the priority of the user.
     * 
     * @return The priority of the user.
     * 
     * Time Complexity: O(1)
     * This method simply returns the value of the `priority` field, which is a direct access operation.
     */
    public Integer getPriority() {
        return this.priority;
    }
}
