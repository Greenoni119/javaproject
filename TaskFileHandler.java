import java.io.*;
import java.util.LinkedHashMap;
import java.util.Scanner;

// Handles reading from and writing tasks to a text file.
public class TaskFileHandler {
    private final String filePath; // The path to the file where tasks are saved

    // Constructor that sets the file path for saving and loading tasks
    public TaskFileHandler(String filePath) {
        this.filePath = filePath;
    }

    // Saves the current tasks to the file. Tasks are formatted and written as plain text.
    public void saveTasks(LinkedHashMap<Integer, Task> tasks) {
        try (PrintWriter out = new PrintWriter(new FileWriter(filePath))) {
            for (Task task : tasks.values()) {
                out.println(task.getId() + "," + task.getDescription() + "," + task.getAssignee() + "," + task.isComplete());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Loads tasks from the file. Parses the plain text data back into Task objects and adds them to a LinkedHashMap.
    public LinkedHashMap<Integer, Task> loadTasks() {
        LinkedHashMap<Integer, Task> tasks = new LinkedHashMap<>();
        File file = new File(filePath);
        if (!file.exists()) {
            return tasks; // If the file doesn't exist, return an empty list
        }
        try (Scanner scanner = new Scanner(file)) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                // Extract task details from the line
                int id = Integer.parseInt(parts[0]);
                String description = parts[1];
                String assignee = parts[2];
                boolean isComplete = Boolean.parseBoolean(parts[3]);
                // Create a new Task object and add it to the LinkedHashMap
                Task task = new Task(description, assignee, id, isComplete);
                tasks.put(task.getId(), task);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return tasks;
    }
}
