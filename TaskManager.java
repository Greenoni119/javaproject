import javax.swing.*;
import java.awt.*;
import java.util.LinkedHashMap;

// Main class for the task manager application. Extends JFrame to create a GUI window.
public class TaskManager extends JFrame {
    private final DefaultListModel<Task> taskListModel; // Model for the JList component that displays tasks
    private final JList<Task> taskList; // Component to display tasks
    private final JTextField taskInputField; // Input field for task descriptions
    private final JTextField assigneeInputField; // Input field for task assignees
    private final LinkedHashMap<Integer, Task> tasks; // Stores tasks using their ID for quick access
    private final TaskFileHandler fileHandler; // Handles file operations for tasks

    // Constructor for the TaskManager, sets up the GUI and loads tasks from the file
    public TaskManager() {
        super("Task Manager"); // Set the window title
        fileHandler = new TaskFileHandler("tasks.txt"); // Initialize the file handler with the file name
        tasks = fileHandler.loadTasks(); // Load tasks from file into the LinkedHashMap

        taskListModel = new DefaultListModel<>();
        tasks.values().forEach(taskListModel::addElement); // Add loaded tasks to the GUI model
        taskList = new JList<>(taskListModel); // Initialize the JList with the model
        taskInputField = new JTextField(20); // Set up the text field for entering task descriptions
        assigneeInputField = new JTextField(20); // Set up the text field for entering task assignees

        // Set up the 'Add Task' button with an action listener to create and add new tasks
        JButton addButton = new JButton("Add Task");
        addButton.addActionListener(e -> addTask());

        // Set up the 'Delete Task' button with an action listener to remove selected tasks
        JButton deleteButton = new JButton("Delete Task");
        deleteButton.addActionListener(e -> deleteTask());

        // Custom renderer for displaying tasks in the JList more readably
        taskList.setCellRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                if (value instanceof Task) {
                    Task task = (Task) value;
                    String text = String.format("ID: %d - %s - Assigned to: %s - Complete: %s",
                            task.getId(), task.getDescription(), task.getAssignee(), task.isComplete() ? "Yes" : "No");
                    return super.getListCellRendererComponent(list, text, index, isSelected, cellHasFocus);
                }
                return super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            }
        });

        // Arrange components in the window using a FlowLayout
        setLayout(new FlowLayout());
        add(new JScrollPane(taskList)); // Allows the task list to be scrollable
        add(taskInputField);
        add(assigneeInputField);
        add(addButton);
        add(deleteButton);

        // Configure window settings
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits when the window is closed
        setSize(800, 400); // Set the window size
        setLocationRelativeTo(null); // Center the window on the screen
    }

    // Method to add a new task based on the input fields
    private void addTask() {
        String description = taskInputField.getText();
        String assignee = assigneeInputField.getText();
        if (!description.isEmpty() && !assignee.isEmpty()) {
            Task task = new Task(description, assignee); // Create a new task
            tasks.put(task.getId(), task); // Add it to the LinkedHashMap
            taskListModel.addElement(task); // And to the GUI model for display
            taskInputField.setText(""); // Clear the input fields for the next entry
            assigneeInputField.setText("");
            fileHandler.saveTasks(tasks); // Save the updated task list to the file
        }
    }

     // Method to delete the selected task from both the LinkedHashMap and the GUI model
     private void deleteTask() {
        int selectedIndex = taskList.getSelectedIndex();
        if (selectedIndex != -1) { // Ensure a task is selected
            Task selectedTask = taskListModel.getElementAt(selectedIndex);
            tasks.remove(selectedTask.getId()); // Remove the task from the LinkedHashMap
            taskListModel.remove(selectedIndex); // And from the GUI model
            fileHandler.saveTasks(tasks); // Save the updated task list to the file
        }
    }

    // Main method to run the application
    public static void main(String[] args) {
        // Ensure the GUI is created on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> new TaskManager().setVisible(true));
    }
}