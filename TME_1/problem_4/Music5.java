package problem_4; // Declaring package name

interface Playable { // Defining Playable interface
    void play(); // Method signature for play
}

class Instrument implements Playable { // Creating Instrument class implementing Playable interface
    @Override
    public void play() { // Implementing play method
        System.out.println("Instrument playing"); // Printing a message indicating instrument is playing
    }

    void adjust() { // Defining adjust method
        System.out.println("Adjusting Instrument"); // Printing a message indicating instrument is being adjusted
    }
}

class Wind extends Instrument implements Playable { // Creating Wind class extending Instrument class and implementing Playable interface
    @Override
    public void play() { // Implementing play method
        System.out.println("Wind playing"); // Printing a message indicating wind instrument is playing
    }

    void clearSpitValve() { // Defining clearSpitValve method
        System.out.println("Clearing Spit Valve"); // Printing a message indicating the spit valve is being cleared
    }
}

class Percussion extends Instrument implements Playable { // Creating Percussion class extending Instrument class and implementing Playable interface
    @Override
    public void play() { // Implementing play method
        System.out.println("Percussion playing"); // Printing a message indicating percussion instrument is playing
    }

    void adjust() { // Defining adjust method
        System.out.println("Adjusting Percussion"); // Printing a message indicating percussion instrument is being adjusted
    }
}

class Stringed extends Instrument implements Playable { // Creating Stringed class extending Instrument class and implementing Playable interface
    @Override
    public void play() { // Implementing play method
        System.out.println("Stringed playing"); // Printing a message indicating stringed instrument is playing
    }

    void tune() { // Defining tune method
        System.out.println("Tuning Stringed"); // Printing a message indicating stringed instrument is being tuned
    }
}

class Brass extends Wind { // Creating Brass class extending Wind class
    @Override
    public void play() { // Implementing play method
        System.out.println("Brass playing"); // Printing a message indicating brass instrument is playing
    }

    void adjust() { // Defining adjust method
        System.out.println("Adjusting Brass"); // Printing a message indicating brass instrument is being adjusted
    }
}

class Woodwind extends Wind { // Creating Woodwind class extending Wind class
    @Override
    public void play() { // Implementing play method
        System.out.println("Woodwind playing"); // Printing a message indicating woodwind instrument is playing
    }

    void adjust() { // Defining adjust method
        System.out.println("Adjusting Woodwind"); // Printing a message indicating woodwind instrument is being adjusted
    }
}

public class Music5 { // Creating Music5 class
    static void tune(Playable playable) { // Defining static tune method accepting a Playable object
        playable.play(); // Invoking play method on the Playable object
    }

    static void tuneAll(Playable[] e) { // Defining static tuneAll method accepting an array of Playable objects
        for (Playable playable : e) { // Iterating over each Playable object in the array
            tune(playable); // Invoking tune method for each Playable object
        }
    }

    public static void main(String[] args) { // Defining main method
        Playable[] orchestra = { // Creating an array of Playable objects
                new Wind(), // Instantiating a Wind object
                new Percussion(), // Instantiating a Percussion object
                new Stringed(), // Instantiating a Stringed object
                new Brass(), // Instantiating a Brass object
                new Woodwind() // Instantiating a Woodwind object
        };

        tuneAll(orchestra); // Calling tuneAll method passing the orchestra array
    }
}
