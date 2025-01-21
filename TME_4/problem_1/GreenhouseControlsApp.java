/*
Documentation for GreenhouseControlsApp Class

<h2>Overview</h2>
<p>The GreenhouseControlsApp class serves as the entry point for the application, initializing the graphical user interface (GUI) by 
invoking the GreenhouseGUI class's main method. This class does not contain any complex logic and is mainly used to launch the application.</p>

<h2>Features</h2>
<p>Launch the GUI: The class contains a main method which serves to launch the application by invoking the main method of the GreenhouseGUI class.</p>
 */

// Declare the package where this class resides
package problem_1;

// Define the `GreenhouseControlsApp` class, which serves as the entry point for the application
public class GreenhouseControlsApp {

  // The main method serves as the starting point for the Java application
  public static void main(String[] args) {
    // Call the main method of the `GreenhouseGUI` class, passing along any command-line arguments
    GreenhouseGUI.main(args);
  }
}
