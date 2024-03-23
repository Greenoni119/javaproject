Task Manager Application
========================

Description:
------------
The Task Manager Application is a Java-based desktop application that allows users to create, delete, and view tasks. Each task has a unique ID, a description, an assignee, and a completion status. The application features a graphical user interface (GUI) for interactive task management and supports saving and loading tasks from a .txt file for persistence.

Setup Instructions:
-------------------
1. Ensure you have Java Development Kit (JDK) installed on your system. The application has been developed and tested on JDK 11, but it should be compatible with JDK 8 and newer versions. You can download JDK from https://www.oracle.com/java/technologies/javase-jdk11-downloads.html.

2. Download the project files to your local machine. The project includes the following main files:
   - Task.java: Defines the Task class.
   - TaskFileHandler.java: Handles reading from and writing to the tasks file.
   - TaskManager.java: Contains the main application window and logic.

3. Place all project files in the same directory to ensure the application runs correctly.

Running the Application:
------------------------
1. Open a terminal or command prompt window.

2. Navigate to the directory where you saved the project files.

3. Compile the Java files using the following command:


4. Once compiled, run the application with the command:


5. The Task Manager application window will open, allowing you to add, delete, and view tasks.

Usage:
------
- To add a task: Enter the task description and assignee in the respective text fields, then click the "Add Task" button.
- To delete a task: Select a task from the list and click the "Delete Task" button.
- Your tasks are automatically saved to a file named `tasks.txt` in the same directory as the application. They will be loaded from this file when the application starts.

Notes:
------
- The application uses a .txt file (`tasks.txt`) for task persistence. Do not delete this file if you wish to retain your tasks between application sessions.
- If you encounter any issues compiling or running the application, ensure you have the correct Java version installed and that all project files are in the same directory.

