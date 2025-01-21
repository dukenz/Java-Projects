// Declare the package where this class resides
package problem_1;

// Import necessary classes for GUI components and layout management
import java.awt.*;
import javax.swing.*;

// Define the `GreenhouseGUI` class, extending `JFrame` to create a GUI window
public class GreenhouseGUI extends JFrame {

  // Create an instance of `GreenhouseControls` to manage greenhouse events
  private final GreenhouseControls greenhouseControls = new GreenhouseControls();

  // Constructor to set up the GUI components
  public GreenhouseGUI() {
    // Set the title of the GUI window
    setTitle("Greenhouse Controls");

    // Set the size of the window (width: 500 pixels, height: 400 pixels)
    setSize(500, 400);

    // Specify the behavior when the user closes the window
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    // Use a BorderLayout to organize components in the GUI
    setLayout(new BorderLayout());

    // Create a text area to display logs and make it non-editable
    JTextArea logArea = new JTextArea();
    logArea.setEditable(false);

    // Add the text area to a scroll pane for scrolling functionality
    JScrollPane scrollPane = new JScrollPane(logArea);

    // Place the scroll pane in the center of the window
    add(scrollPane, BorderLayout.CENTER);

    // Create a panel to hold control buttons
    JPanel buttonPanel = new JPanel();

    // Create buttons for adding events, suspending, and resuming
    JButton addEventButton = new JButton("Add Event");
    JButton suspendButton = new JButton("Suspend All");
    JButton resumeButton = new JButton("Resume All");

    // Add the buttons to the button panel
    buttonPanel.add(addEventButton);
    buttonPanel.add(suspendButton);
    buttonPanel.add(resumeButton);

    // Place the button panel at the bottom of the window
    add(buttonPanel, BorderLayout.SOUTH);

    // Add functionality to the "Add Event" button
    addEventButton.addActionListener(e -> {
      // Prompt the user to input the event class name
      String className = JOptionPane.showInputDialog(
        this,
        "Enter Event Class Name (e.g., LightOnEvent):"
      );

      // Prompt the user to input the delay time for the event
      String delayStr = JOptionPane.showInputDialog(
        this,
        "Enter Delay Time (ms):"
      );

      try {
        // Parse the delay time input into a long value
        long delay = Long.parseLong(delayStr);

        // Add the event to `GreenhouseControls`
        greenhouseControls.addEvent(className, delay);

        // Log the success message in the log area
        logArea.append(
          "Event " + className + " added with delay " + delay + " ms.\n"
        );
      } catch (Exception ex) {
        // Log any errors that occur during event creation
        logArea.append("Error: " + ex.getMessage() + "\n");
      }
    });

    // Add functionality to the "Suspend All" button
    suspendButton.addActionListener(e -> {
      // Suspend all events in `GreenhouseControls`
      greenhouseControls.suspendAll();

      // Log the suspension action in the log area
      logArea.append("All events suspended.\n");
    });

    // Add functionality to the "Resume All" button
    resumeButton.addActionListener(e -> {
      // Resume all events in `GreenhouseControls`
      greenhouseControls.resumeAll();

      // Log the resumption action in the log area
      logArea.append("All events resumed.\n");
    });
  }

  // Main method to launch the GUI application
  public static void main(String[] args) {
    // Use Swing's thread-safe method to start the GUI
    SwingUtilities.invokeLater(() -> {
      // Create an instance of `GreenhouseGUI`
      GreenhouseGUI gui = new GreenhouseGUI();

      // Make the GUI window visible
      gui.setVisible(true);
    });
  }
}
