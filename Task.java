import java.io.Serializable;

// Represents a task in the task manager. Implements Serializable to allow task objects to be saved to and loaded from a file.
public class Task implements Serializable {
    private static final long serialVersionUID = 1L; // Ensures compatibility between saved files and the class
    private static int nextId = 1; // Static counter to auto-increment task IDs
    private int id; // Unique identifier for each task
    private String description; // A text description of the task
    private String assignee; // The person assigned to the task
    private boolean isComplete; // Whether the task is completed

    // Constructor for creating new tasks. Initializes the task with a description, assignee, and sets the task as incomplete by default.
    public Task(String description, String assignee) {
        this.id = nextId++;
        this.description = description;
        this.assignee = assignee;
        this.isComplete = false;
    }

    // Additional constructor for loading tasks from a file. Allows setting all fields, including the task's unique ID and completion status.
    public Task(String description, String assignee, int id, boolean isComplete) {
        if (id >= nextId) {
            nextId = id + 1; // Update the ID counter if the loaded task has a higher ID
        }
        this.id = id;
        this.description = description;
        this.assignee = assignee;
        this.isComplete = isComplete;
    }

    // Getter methods for accessing the task's properties
    public int getId() { return id; }
    public String getDescription() { return description; }
    public String getAssignee() { return assignee; }
    public boolean isComplete() { return isComplete; }

    // Setter methods for updating the task's properties
    public void setDescription(String description) { this.description = description; }
    public void setAssignee(String assignee) { this.assignee = assignee; }
    public void setComplete(boolean complete) { isComplete = complete; }

    // Provides a string representation of the task, including its ID, description, assignee, and completion status. Useful for displaying in the GUI.
    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", assignee='" + assignee + '\'' +
                ", isComplete=" + isComplete +
                '}';
    }
}
