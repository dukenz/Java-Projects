package problem_2;
// FullName class
class FullName {
    // Private fields to store title, first name, middle name, and last name
    private String title;
    private String firstName;
    private String middleName;
    private String lastName;

    // Constructor to initialize FullName object with provided values
    public FullName(String title, String firstName, String middleName, String lastName) {
        this.title = title;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
    }

    // Override toString method to return nicely formatted name
    @Override
    public String toString() {
        return title + " " + firstName + " " + middleName + " " + lastName;
    }
}

// MailingAddress class
class MailingAddress {
    // Private fields to store FullName object, street address, city, province, and postal code
    private FullName fullName;
    private String streetAddress;
    private String city;
    private String province;
    private String postalCode;

    // Constructor to initialize MailingAddress object with provided values
    public MailingAddress(FullName fullName, String streetAddress, String city, String province, String postalCode) {
        this.fullName = fullName;
        this.streetAddress = streetAddress;
        this.city = city;
        this.province = province;
        this.postalCode = postalCode;
    }

    // Override toString method to return nicely formatted address
    @Override
    public String toString() {
        return "Recipient: " + fullName + "\nAddress: " + streetAddress + "\nCity: " + city + "\nProvince: " + province + "\nPostal Code: " + postalCode;
    }
}

// ShippingLabel class
class ShippingLabel {
    // Private fields to store MailingAddress objects for ship-from and ship-to addresses
    private MailingAddress shipFrom;
    private MailingAddress shipTo;

    // Constructor to initialize ShippingLabel object with ship-from and ship-to addresses
    public ShippingLabel(MailingAddress shipFrom, MailingAddress shipTo) {
        this.shipFrom = shipFrom;
        this.shipTo = shipTo;
    }

    // Method to print the label to console
    public void printLabel() {
        System.out.println("Ship From:\n" + shipFrom + "\n\nShip To:\n" + shipTo);
    }
}

// Main class
public class Main {
    public static void main(String[] args) {
        // Creating FullName objects for sender and receiver
        FullName senderName = new FullName("Mr.", "Odinakachukwu", "Duke", "Nzekwe");
        FullName receiverName = new FullName("Ms.", "Janet", "Olivia", "Nzekwe");

        // Creating MailingAddress objects for sender and receiver
        MailingAddress senderAddress = new MailingAddress(senderName, "123 Main St", "Anytown", "Province", "A1B 2C3");
        MailingAddress receiverAddress = new MailingAddress(receiverName, "456 Elm St", "Sometown", "Province", "X1Y 3Z5");

        // Creating ShippingLabel object with ship-from and ship-to addresses
        ShippingLabel label = new ShippingLabel(senderAddress, receiverAddress);

        // Printing the label
        label.printLabel();
    }
}
