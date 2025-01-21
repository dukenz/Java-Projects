// Declare the package where this class resides
package problem_1;

// Define the `TwoTuple` class, which is a generic class for holding a key-value pair
public class TwoTuple<K, V> {

  // Declare the fields to store the key and value
  public final K key; // The key of type K
  public final V value; // The value of type V

  // Constructor to initialize the key and value fields
  public TwoTuple(K key, V value) {
    this.key = key; // Set the key
    this.value = value; // Set the value
  }

  // Override the `toString` method to return a string representation of the tuple
  @Override
  public String toString() {
    // Return a string in the format "(key, value)"
    return "(" + key + ", " + value + ")";
  }
}
