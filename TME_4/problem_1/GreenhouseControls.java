/*
 Documentation for GreenhouseControls Class

Overview
The GreenhouseControls class manages state variables and event scheduling in a greenhouse control system. It allows the creation, suspension, and resumption of events through thread management. State variables are stored and updated in a thread-safe manner using a ConcurrentHashMap.

Features
<p>State Variable Management: Handles state variables and their associated values in a thread-safe ConcurrentHashMap.</p>
<p>Event Scheduling: Dynamically creates and starts event threads based on class names and delay times.</p>
<p>Thread Suspension/Resumption: Provides functionality to suspend and resume all event threads in a controlled manner.</p>
 */

// Declare the package where this class resides
package problem_1;

// Import necessary classes
import java.lang.reflect.Constructor; // For creating instances of classes dynamically
import java.util.ArrayList; // For using an ArrayList to store threads
import java.util.List; // For using the List interface
import java.util.Map; // For using a Map to store state variables
import java.util.concurrent.ConcurrentHashMap; // For a thread-safe implementation of Map

// Define the `GreenhouseControls` class to manage events and state variables
public class GreenhouseControls {

  // A thread-safe map to store state variables, where the key is a string and the value is a `TwoTuple`
  private final Map<String, TwoTuple<String, Object>> stateVariables = new ConcurrentHashMap<>();

  // A list to store all the threads associated with events
  private final List<Thread> eventThreads = new ArrayList<>();

  // Method to set a state variable in a thread-safe manner
  public synchronized void setVariable(String key, Object value) {
    // Add or update the state variable in the map
    stateVariables.put(key, new TwoTuple<>(key, value));
  }

  // Method to retrieve a copy of the state variables in a thread-safe manner
  public synchronized Map<String, TwoTuple<String, Object>> getStateVariables() {
    // Return a new ConcurrentHashMap containing the current state variables
    return new ConcurrentHashMap<>(stateVariables);
  }

  // Method to add an event by creating an instance dynamically and starting its thread
  public void addEvent(String className, long delayTime) {
    try {
      // Use reflection to get the class object for the specified class name
      Class<?> cls = Class.forName("problem_1." + className);

      // Get the constructor that takes a single `long` parameter
      Constructor<?> constructor = cls.getConstructor(long.class);

      // Create a new instance of the event using the constructor
      Event event = (Event) constructor.newInstance(delayTime);

      // Create a new thread to run the event
      Thread thread = new Thread(event);

      // Add the thread to the list of event threads
      eventThreads.add(thread);

      // Start the thread
      thread.start();
    } catch (Exception e) {
      // Handle any exception that occurs during reflection or thread creation
      System.out.println("Error creating event: " + e.getMessage());
    }
  }

  // Method to suspend all event threads
  public void suspendAll() {
    for (Thread thread : eventThreads) { // Iterate through all event threads
      synchronized (thread) { // Synchronize on the thread object
        try {
          // Make the thread wait, effectively suspending its execution
          thread.wait();
        } catch (InterruptedException e) {
          // Handle interruption and restore the thread's interrupted status
          System.out.println("Error suspending thread: " + e.getMessage());
          Thread.currentThread().interrupt();
        }
      }
    }
  }

  // Method to resume all event threads
  public void resumeAll() {
    for (Thread thread : eventThreads) { // Iterate through all event threads
      synchronized (thread) { // Synchronize on the thread object
        // Notify the thread to wake it up
        thread.notify();
      }
    }
  }
}
