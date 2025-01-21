/**
 * Class representing a generic cycle.
 */
class Cycle {
    /**
     * Returns the number of wheels for this cycle.
     * @return The number of wheels (0 for default implementation).
     */
    int wheels() {
        return 0; // Default implementation for Cycle
    }
    
    /**
     * Prints a message indicating riding a cycle with the number of wheels.
     */
    void ride() {
        System.out.println("Riding a cycle with " + wheels() + " wheels.");
    }
}

/**
 * Class representing a unicycle, extending Cycle.
 */
class Unicycle extends Cycle {
    @Override
    int wheels() {
        return 1;
    }
}

/**
 * Class representing a bicycle, extending Cycle.
 */
class Bicycle extends Cycle {
    @Override
    int wheels() {
        return 2;
    }
}

/**
 * Class representing a tricycle, extending Cycle.
 */
class Tricycle extends Cycle {
    @Override
    int wheels() {
        return 3;
    }
}

/**
 * Main class to demonstrate polymorphism with cycles.
 */
public class Main {
    public static void main(String[] args) {
        Cycle cycle1 = new Unicycle(); // Upcasting
        cycle1.ride(); // Calls Unicycle's ride() method

        Cycle cycle2 = new Bicycle(); // Upcasting
        cycle2.ride(); // Calls Bicycle's ride() method

        Cycle cycle3 = new Tricycle(); // Upcasting
        cycle3.ride(); // Calls Tricycle's ride() method
    }
}
