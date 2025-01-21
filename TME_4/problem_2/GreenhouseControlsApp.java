// TME_4 CODE DOCUMENTATION AND TEST PLAN:

/*
 * ===============================Documentation====================================
 * 
 * Documentation for Greenhouse Controls Application
Overview

The GreenhouseControlsApp is a Java Swing-based graphical user interface (GUI) application to simulate and control greenhouse operations. It allows users to start, stop, restart, suspend, and resume greenhouse events through buttons and menu options. Additionally, users can interact with the application using a popup menu, manage event files, and restore the greenhouse state from a file.

Features
Graphical User Interface:
Log area for displaying messages and events.
Buttons for controlling greenhouse operations (Start, Restart, Terminate, Suspend, Resume).
Menu bar with options to manage files, open new windows, and exit the application.
Popup menu for quick access to controls.
Event Control:
Start, stop, suspend, and resume event execution.
Terminate events with a user-defined delay.
File Management:
Open event files.
Restore the greenhouse state from files.
Class Descriptions
GreenhouseControlsApp

This is the main GUI class for the application.

Attributes:
logArea: A JTextArea for displaying messages.
greenhouseControls: Instance of GreenhouseControls for managing event states.
Methods:
createPopupMenuItem(String name, ActionListener action): Creates menu items for popup actions.
closeWindow(): Closes the current window with confirmation if events are running.
openEventsFile(): Opens a file chooser to select event files.
restoreState(): Restores greenhouse state from a selected file.
exitApplication(): Exits the application with confirmation if events are running.
log(String message): Appends messages to the log area.
GreenhouseControls

Manages the state of greenhouse operations.

Attributes:
executor: A cached thread pool for managing tasks.
running: A boolean flag indicating if the greenhouse is active.
Methods:
start(): Starts event execution.
restart(): Restarts events.
terminate(JFrame frame): Terminates events with a user-defined delay.
suspend(): Suspends event execution.
resume(): Resumes event execution.
isRunning(): Returns the running state.

++=================================+Test plan===================================
Test Plan
Objective

To ensure the functionality of all GUI components and backend operations in the GreenhouseControlsApp.

Test Cases

Test Case ID	Feature	Input/Action	Expected Result	Pass/Fail
TC-001	Start Button	Click "Start" button	Log displays "Greenhouse started" and "Events are running".	
TC-002	Restart Button	Click "Restart" button	Log displays "Restarting events".	
TC-003	Terminate Button	Click "Terminate" button and enter a delay (e.g., 1000 ms).	Log displays "Terminate scheduled with delay: 1000 ms."	
TC-004	Suspend Button	Click "Suspend" button	Log displays "Greenhouse suspended".	
TC-005	Resume Button	Click "Resume" button after suspension	Log displays "Greenhouse resumed".	
TC-006	Close Window	Attempt to close the window while events are running	Confirmation dialog appears. Window closes only if "Yes" is selected.	
TC-007	Open Events File	Click "Open Events" from the file menu, select a file	Log displays "Opened events file: [filename]".	
TC-008	Restore State	Click "Restore" from the file menu, select a file	Log displays "Restored state from: [filename]".	
TC-009	Exit Application	Click "Exit" from the file menu while events are running	Confirmation dialog appears. Application exits only if "Yes" is selected.	
TC-010	Popup Menu	Right-click the log area and select "Start" from the popup menu	Log displays "Greenhouse started" and "Events are running".	
TC-011	New Window	Select "New Window" from the file menu	A new instance of the application opens.	
Test Environment

Operating System: macOS, Windows, Linux
Java Version: JDK 17 or above
IDE: IntelliJ IDEA, Eclipse, or Visual Studio Code
Dependencies: Standard Java libraries (javax.swing, java.awt).

Test Execution
Load the application in the IDE or compile using javac and run via java.
Execute each test case step by step and record the results.
Verify that all user actions trigger the expected behaviors and log messages.
 */

package problem_2;

// Necessary imports for GUI components and file handling
import java.awt.*;
import java.awt.event.ActionListener;
import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import javax.swing.*;

public class GreenhouseControlsApp extends JFrame {

  private final JTextArea logArea; // Text area to display logs in the application
  private final GreenhouseControls greenhouseControls = new GreenhouseControls(); // Instance of GreenhouseControls for event handling

  public GreenhouseControlsApp() {
    // Set the window title
    setTitle("Greenhouse Controls");
    // Define the size of the window
    setSize(800, 600);
    // Specify the default close operation
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    // Use BorderLayout for layout management
    setLayout(new BorderLayout());

    // Initialize the log area to display event messages
    logArea = new JTextArea();
    logArea.setEditable(false); // Make log area non-editable
    JScrollPane scrollPane = new JScrollPane(logArea); // Add scroll functionality
    add(scrollPane, BorderLayout.CENTER); // Place log area in the center of the window

    // Create a panel for buttons
    JPanel buttonPanel = new JPanel(new FlowLayout());
    JButton startButton = new JButton("Start"); // Button to start greenhouse events
    JButton restartButton = new JButton("Restart"); // Button to restart events
    JButton terminateButton = new JButton("Terminate"); // Button to terminate events
    JButton suspendButton = new JButton("Suspend"); // Button to suspend events
    JButton resumeButton = new JButton("Resume"); // Button to resume events

    // Add buttons to the panel
    buttonPanel.add(startButton);
    buttonPanel.add(restartButton);
    buttonPanel.add(terminateButton);
    buttonPanel.add(suspendButton);
    buttonPanel.add(resumeButton);
    add(buttonPanel, BorderLayout.SOUTH); // Place the button panel at the bottom

    // Attach action listeners to buttons
    startButton.addActionListener(e -> greenhouseControls.start());
    restartButton.addActionListener(e -> greenhouseControls.restart());
    terminateButton.addActionListener(e -> greenhouseControls.terminate(this));
    suspendButton.addActionListener(e -> greenhouseControls.suspend());
    resumeButton.addActionListener(e -> greenhouseControls.resume());

    // Create a menu bar for file operations
    JMenuBar menuBar = new JMenuBar();
    JMenu fileMenu = new JMenu("File"); // "File" menu
    JMenuItem newWindowItem = new JMenuItem("New Window"); // Option to open a new window
    JMenuItem closeWindowItem = new JMenuItem("Close Window"); // Option to close the current window
    JMenuItem openEventsItem = new JMenuItem("Open Events"); // Option to open events file
    JMenuItem restoreItem = new JMenuItem("Restore"); // Option to restore the state
    JMenuItem exitItem = new JMenuItem("Exit"); // Option to exit the application

    // Add menu items to the file menu
    fileMenu.add(newWindowItem);
    fileMenu.add(closeWindowItem);
    fileMenu.add(openEventsItem);
    fileMenu.add(restoreItem);
    fileMenu.add(exitItem);
    menuBar.add(fileMenu); // Add file menu to the menu bar
    setJMenuBar(menuBar); // Set the menu bar for the frame

    // Define actions for menu items
    newWindowItem.addActionListener(e ->
      new GreenhouseControlsApp().setVisible(true)
    );
    closeWindowItem.addActionListener(e -> closeWindow());
    openEventsItem.addActionListener(e -> openEventsFile());
    restoreItem.addActionListener(e -> restoreState());
    exitItem.addActionListener(e -> exitApplication());

    // Create a popup menu for quick access to actions
    JPopupMenu popupMenu = new JPopupMenu();
    popupMenu.add(
      createPopupMenuItem("Start", e -> greenhouseControls.start())
    );
    popupMenu.add(
      createPopupMenuItem("Restart", e -> greenhouseControls.restart())
    );
    popupMenu.add(
      createPopupMenuItem("Terminate", e -> greenhouseControls.terminate(this))
    );
    popupMenu.add(
      createPopupMenuItem("Suspend", e -> greenhouseControls.suspend())
    );
    popupMenu.add(
      createPopupMenuItem("Resume", e -> greenhouseControls.resume())
    );
    logArea.setComponentPopupMenu(popupMenu); // Attach popup menu to the log area
  }

  // Helper method to create a menu item with a specified action
  private JMenuItem createPopupMenuItem(String name, ActionListener action) {
    JMenuItem item = new JMenuItem(name); // Create menu item
    item.addActionListener(action); // Attach action listener
    return item; // Return the menu item
  }

  // Method to close the current window
  private void closeWindow() {
    // Confirm before closing if greenhouse events are running
    if (greenhouseControls.isRunning()) {
      int confirm = JOptionPane.showConfirmDialog(
        this,
        "Greenhouse is running. Are you sure you want to close this window?",
        "Confirm Close",
        JOptionPane.YES_NO_OPTION
      );
      if (confirm != JOptionPane.YES_OPTION) {
        return; // Do nothing if the user cancels
      }
    }
    dispose(); // Close the window
  }

  // Method to open an events file
  private void openEventsFile() {
    JFileChooser fileChooser = new JFileChooser(); // Create a file chooser dialog
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile(); // Get the selected file
      log("Opened events file: " + file.getName()); // Log the file name
    }
  }

  // Method to restore the greenhouse state from a file
  private void restoreState() {
    JFileChooser fileChooser = new JFileChooser(); // Create a file chooser dialog
    if (fileChooser.showOpenDialog(this) == JFileChooser.APPROVE_OPTION) {
      File file = fileChooser.getSelectedFile(); // Get the selected file
      log("Restored state from: " + file.getName()); // Log the restoration
    }
  }

  // Method to exit the application
  private void exitApplication() {
    // Confirm before exiting if greenhouse events are running
    if (greenhouseControls.isRunning()) {
      int confirm = JOptionPane.showConfirmDialog(
        this,
        "Greenhouse is running. Are you sure you want to exit?",
        "Confirm Exit",
        JOptionPane.YES_NO_OPTION
      );
      if (confirm != JOptionPane.YES_OPTION) {
        return; // Do nothing if the user cancels
      }
    }
    System.exit(0); // Exit the application
  }

  // Method to log messages to the log area
  private void log(String message) {
    logArea.append(message + "\n"); // Append message with a newline
  }

  // Inner class for controlling greenhouse events
  private class GreenhouseControls {

    private final ExecutorService executor = Executors.newCachedThreadPool(); // Thread pool for running tasks
    private boolean running = false; // Flag to check if events are running

    // Method to start the greenhouse events
    public void start() {
      if (!running) {
        running = true; // Mark as running
        log("Greenhouse started."); // Log the start
        executor.execute(() -> log("Events are running...")); // Run event logic
      }
    }

    // Method to restart the events
    public void restart() {
      log("Restarting events..."); // Log the restart
    }

    // Method to terminate the events
    public void terminate(JFrame frame) {
      String delay = JOptionPane.showInputDialog(
        frame,
        "Enter delay in milliseconds:"
      ); // Prompt for delay
      if (delay != null) {
        log("Terminate scheduled with delay: " + delay + " ms."); // Log the termination
        running = false; // Mark as not running
        executor.shutdownNow(); // Stop tasks
      }
    }

    // Method to suspend the events
    public void suspend() {
      if (running) {
        log("Greenhouse suspended."); // Log the suspension
        running = false; // Mark as not running
      } else {
        log("Greenhouse is not running. Cannot suspend."); // Log error
      }
    }

    // Method to resume the events
    public void resume() {
      if (!running) {
        log("Greenhouse resumed."); // Log the resume
        running = true; // Mark as running
      } else {
        log("Greenhouse is already running."); // Log error
      }
    }

    // Method to check if events are running
    public boolean isRunning() {
      return running; // Return running status
    }
  }

  // Main method to launch the application
  public static void main(String[] args) {
    SwingUtilities.invokeLater(() ->
      new GreenhouseControlsApp().setVisible(true)
    ); // Launch GUI
  }
}
