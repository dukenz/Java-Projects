/*
 * Documentation for Event Class

Overview
The Event class is an abstract base class that models an event which performs an action after a specified delay. It implements the Runnable interface to allow events to run in separate threads.

Features
Specifies a delay before executing an action.
Abstract methods performAction and getDescription ensure customization for specific event behaviors.
Implements robust error handling for thread interruptions during execution.
 */

// Declare the package where this class resides
package problem_1;

// Define an abstract class named `Event` that implements the `Runnable` interface
public abstract class Event implements Runnable {

  // Protected field to store the delay time for the event in milliseconds
  protected final long delayTime;

  // Constructor to initialize the delay time for the event
  public Event(long delayTime) {
    this.delayTime = delayTime;
  }

  // Abstract method that subclasses must implement to define the event's action
  public abstract void performAction();

  // Abstract method that subclasses must implement to provide a description of the event
  public abstract String getDescription();

  // Override the `run` method from the `Runnable` interface
  @Override
  public void run() {
    try {
      // Pause the current thread for the specified delay time
      Thread.sleep(delayTime);

      // Execute the event-specific action after the delay
      performAction();
    } catch (InterruptedException e) {
      // Handle the case where the thread is interrupted during sleep
      System.out.println("Event interrupted: " + getDescription());

      // Restore the interrupted status of the thread
      Thread.currentThread().interrupt();
    }
  }
}
