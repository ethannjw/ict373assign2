import java.io.Serializable;
import java.util.Objects;

/**
 * The Address class that stores the address of the person
 */
public class Address implements Serializable {
    private String streetNum;
    private String streetName;
    private String suburb;
    private int postCode;

    /**
     * The address empty constructor
     */
    public Address() {
        super();
        streetNum = ("");
        streetName = ("");
        suburb = ("");
        postCode = (1000);
    }

    /**
     * Address constructor that takes in all the variables
     * @param streetNum     Street number in string
     * @param streetName    Street name in string
     * @param suburb        Suburb in string
     * @param postCode      Postcode in integer
     */
    public Address(String streetNum, String streetName, String suburb, int postCode) throws Address.InvalidAddressParameterException{
        super();
        setStreetNum(streetNum);
        setStreetName(streetName);
        setSuburb(suburb);
        setPostCode(postCode);
    }

    /**
     * Defines own exception that gives useful message
     */
    public static class InvalidAddressParameterException extends Exception {
        public InvalidAddressParameterException(String msg) {
            super(msg);
        }
    }

    /**
     * Gets the street number
     * @return  Street number string
     */
    public String getStreetNum() {
        return streetNum;
    }

    /**
     * Sets the street number in string
     * @param streetNum Street number string
     */
    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    /**
     * Gets the street name string
     * @return  The street name in string
     */
    public String getStreetName() {
        return streetName;
    }

    /**
     * Sets the street name in string
     * @param streetName Street name string
     */
    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    /**
     * Gets the suburb string
     * @return  The suburb in string
     */
    public String getSuburb() {
        return suburb;
    }

    /**
     * Sets the suburb in string
     * @param suburb Suburb in string
     */
    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }
    /**
     * Gets the post code
     * @return Post code in integer
     */
    public int getPostCode() {
        return postCode;
    }

    /**
     * Sets the post code in integer
     * @param postCode Post code in integer
     */
    public void setPostCode(int postCode) throws Address.InvalidAddressParameterException{
        if (postCode < 9999 && postCode > 999) {
            this.postCode = postCode;
        } else {
            throw new InvalidAddressParameterException("Post code can only be 4 digits long");
        }
    }

    /**
     * Overrides the equals method checking for equality for all parameters
     * @param other     Other object
     * @return      True if the other object is same as this
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Address address = (Address) other;
        return getStreetNum() == address.getStreetNum() &&
                getPostCode() == address.getPostCode() &&
                getStreetName().equals(address.getStreetName()) &&
                getSuburb().equals(address.getSuburb());
    }
//
//    /**
//     * Overrides the hashCode method retrieving the hashes for all attributes
//     * @return      This object hash
//     */
//    @Override
//    public int hashCode() {
//        return Objects.hash(getStreetNum(), getStreetName(), getSuburb(), getPostCode());
//    }
//
//    /**
//     * Overrides the toString method to print out human readable text
//     * @return  Human readable string text of this object
//     */
//    @Override
//    public String toString() {
//        return  "streetNum: " + streetNum +
//                "streetName: " + streetName +
//                "suburb: " + suburb +
//                "postCode: " + postCode;
//    }
}
