import java.io.*;
import java.util.*;

// Custom exception for handling errors in the controller
class ControllerException extends Exception {

  public ControllerException(String message) {
    super(message);
  }
}

// Interface representing actions for fixable issues
interface Fixable {
  void fix(); // Method to apply a fix
  void log(); // Method to log the fix action
}

// Base class for the system controller
class Controller {

  // List of scheduled events to manage
  protected List<Event> events = new ArrayList<>();

  // Add a new event to the controller
  public void addEvent(Event event) {
    events.add(event);
    event.setController(this); // Set the controller for the event
    event.start(); // Start the event
  }

  // Run the event loop until all events are processed
  public void run() {
    while (!events.isEmpty()) {
      // Create a copy to iterate and handle events safely
      for (Event e : new ArrayList<>(events)) {
        if (e.ready()) {
          e.action(); // Perform the event's action
          events.remove(e); // Remove completed event
        }
      }
    }
  }

  // Shutdown procedure for the system
  public void shutdown() {
    System.out.println("System shutting down...");
  }
}

// Abstract base class for events
abstract class Event {

  protected long delayTime; // Time delay before the event starts
  private long startTime; // When the event will start
  protected Controller controller; // Reference to the parent controller

  // Constructor to initialize the event with a delay
  public Event(long delayTime) {
    this.delayTime = delayTime;
  }

  // Set the controller for the event
  public void setController(Controller controller) {
    this.controller = controller;
  }

  // Start the event by scheduling its start time
  public void start() {
    startTime = System.currentTimeMillis() + delayTime;
  }

  // Check if the event is ready to execute
  public boolean ready() {
    return System.currentTimeMillis() >= startTime;
  }

  // Abstract method to define the action for the event
  public abstract void action();
}

// GreenhouseControls class extending Controller with serialization capability
public class GreenhouseControls extends Controller implements Serializable {

  private boolean fans = false; // State of the fans
  private boolean windowOk = true; // State of the windows
  private boolean powerOn = true; // State of the power
  private int errorCode = 0; // Error code for issues

  // Get the current error code
  public int getErrorCode() {
    return errorCode;
  }

  // Return a Fixable object based on the error code
  public Fixable getFixable(int errorCode) {
    switch (errorCode) {
      case 1:
        return new PowerOnFixable(); // Power issue
      case 2:
        return new WindowFixable(); // Window issue
      default:
        return null;
    }
  }

  // Override shutdown to include logging and saving state
  @Override
  public void shutdown() {
    try (
      PrintWriter writer = new PrintWriter(new FileWriter("error.log", true));
      ObjectOutputStream oos = new ObjectOutputStream(
        new FileOutputStream("dump.out")
      )
    ) {
      // Log the shutdown details
      writer.println("Shutdown at " + new Date() + ". Reason:");
      if (errorCode == 1) writer.println("Power Out");
      if (errorCode == 2) writer.println("Window Malfunction");

      // Save the current state to a file
      oos.writeObject(this);
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  // Main method to run the Greenhouse Controls
  public static void main(String[] args) {
    try {
      // Restore the system state if specified in arguments
      if (
        args.length > 0 && args[0].equals("-d") && new File("dump.out").exists()
      ) {
        Restore restore = new Restore("dump.out");
        restore.restoreSystem();
      } else {
        // Create a new GreenhouseControls instance and schedule events
        GreenhouseControls gc = new GreenhouseControls();
        gc.addEvent(gc.new ThermostatNight(0));
        gc.addEvent(gc.new LightOn(2000));
        gc.addEvent(gc.new WaterOff(8000));
        gc.addEvent(gc.new ThermostatDay(10000));
        gc.addEvent(gc.new Bell(9000, 1));
        gc.addEvent(gc.new WaterOn(6000));
        gc.addEvent(gc.new LightOff(4000));
        gc.addEvent(gc.new Terminate(12000));
        gc.run();
      }
    } catch (ControllerException e) {
      e.printStackTrace();
    }
  }

  // Inner classes representing various events
  class ThermostatNight extends Event {

    public ThermostatNight(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("Thermostat set to night mode");
    }
  }

  class LightOn extends Event {

    public LightOn(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("Lights turned on");
    }
  }

  class WaterOff extends Event {

    public WaterOff(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("Water turned off");
    }
  }

  class ThermostatDay extends Event {

    public ThermostatDay(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("Thermostat set to day mode");
    }
  }

  class Bell extends Event {

    private int rings; // Number of rings for the bell

    public Bell(long delayTime, int rings) {
      super(delayTime);
      this.rings = rings;
    }

    @Override
    public void action() {
      for (int i = 0; i < rings; i++) {
        System.out.println("Bell rings");
      }
    }
  }

  class WaterOn extends Event {

    public WaterOn(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("Water turned on");
    }
  }

  class LightOff extends Event {

    public LightOff(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("Lights turned off");
    }
  }

  class Terminate extends Event {

    public Terminate(long delayTime) {
      super(delayTime);
    }

    @Override
    public void action() {
      System.out.println("System terminating...");
      System.exit(0);
    }
  }
}

// Class to handle power restoration fixes
class PowerOnFixable implements Fixable {

  @Override
  public void fix() {
    System.out.println("Power restored");
  }

  @Override
  public void log() {
    System.out.println("Power restored at " + new Date());
  }
}

// Class to handle window repair fixes
class WindowFixable implements Fixable {

  @Override
  public void fix() {
    System.out.println("Window repaired");
  }

  @Override
  public void log() {
    System.out.println("Window repaired at " + new Date());
  }
}

// Class to restore the system state from a saved file
class Restore {

  private final String dumpFileName; // File to restore from

  public Restore(String dumpFileName) {
    this.dumpFileName = dumpFileName;
  }

  // Restore the system state and run the controller
  public void restoreSystem() throws ControllerException {
    try (
      ObjectInputStream ois = new ObjectInputStream(
        new FileInputStream(dumpFileName)
      )
    ) {
      // Deserialize the GreenhouseControls object
      GreenhouseControls gc = (GreenhouseControls) ois.readObject();

      // Display the restored system state
      System.out.println("Restored system state:");
      System.out.println("Fans: " + gc.fans);
      System.out.println("Window OK: " + gc.windowOk);
      System.out.println("Power On: " + gc.powerOn);

      // Apply fixes based on error codes
      Fixable fixable = gc.getFixable(gc.getErrorCode());
      if (fixable != null) {
        fixable.fix();
      }

      // Run the restored controller
      gc.run();
    } catch (IOException | ClassNotFoundException e) {
      throw new ControllerException(
        "Error restoring system: " + e.getMessage()
      );
    }
  }
}
