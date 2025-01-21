// Declare the package where this class resides
package problem_1;

// Define the `LightOffEvent` class, which extends the `Event` class
public class LightOffEvent extends Event {

  // Constructor to initialize the event with a delay time
  public LightOffEvent(long delayTime) {
    // Call the superclass constructor to set the delay time
    super(delayTime);
  }

  // Override the `performAction` method to define the event's behavior
  @Override
  public void performAction() {
    // Print a message indicating that the lights are turned off
    System.out.println("Lights turned OFF.");
  }

  // Override the `getDescription` method to provide a description of the event
  @Override
  public String getDescription() {
    // Return the name of the event
    return "LightOffEvent";
  }
}
