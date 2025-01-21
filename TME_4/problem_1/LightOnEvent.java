// Declare the package where this class resides
package problem_1;

// Define the `LightOnEvent` class, which extends the abstract `Event` class
public class LightOnEvent extends Event {

  // Constructor to initialize the event with a delay time
  public LightOnEvent(long delayTime) {
    // Call the superclass constructor to set the delay time
    super(delayTime);
  }

  // Override the `performAction` method to define the behavior of this event
  @Override
  public void performAction() {
    // Print a message indicating that the lights are turned on
    System.out.println("Lights turned ON.");
  }

  // Override the `getDescription` method to provide a description of the event
  @Override
  public String getDescription() {
    // Return a string identifying the event
    return "LightOnEvent";
  }
}
