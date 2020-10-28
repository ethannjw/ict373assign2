import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

/**
 * The person object that stores the person contents and its relatives
 */
public class Person implements Serializable {
    private String firstName;
    private String lastnameAtBirth;
    private String lastnameUponMarriage;
    private String gender;
    private String description;
    private Address address;
    private Person spouse = null;
    private List<Person> children = new ArrayList<>();
    private List<Person> parents = new ArrayList<>();

    /**
     * Creates a new empty person
     */
    public Person() {
        super();
        try {
            setFirstName("");
            setLastnameAtBirth("");
            setLastnameUponMarriage("");
            setGender("");
            setAddress(new Address());
            setDescription("");
        } catch (Exception e) {
            // not supposed to throw any exception since the person is empty
        }
    }

    /**
     * The person constructor that takes in all the required attributes, but no relatives yet
     * @param firstName                         String Firstname to set
     * @param lastNameAtBirth                   String Lastname at birth to set
     * @param lastNameUponMarriage              String lastname upon marriage to set
     * @param gender                            String gender to set
     * @param description                       String Description to set
     * @throws InvalidPersonParameterException  If any of parameter is not correct throws a useful message
     */
    public Person(String firstName, String lastNameAtBirth, String lastNameUponMarriage, String gender, Address address, String description) throws InvalidPersonParameterException {
        super();
        setFirstName(firstName);
        setLastnameAtBirth(lastNameAtBirth);
        setLastnameUponMarriage(lastNameUponMarriage);
        setGender(gender);
        setAddress(address);
        setDescription(description);
    }

    /**
     * Defines own exception that gives useful message
     */
    public static class InvalidPersonParameterException extends Exception {
        public InvalidPersonParameterException(String msg) {
            super(msg);
        }
    }

    /**
     * Returns the firstName
     * @return  firstName in string
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the firstName
     * @param firstName      firstName in string
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Returns the lastnameAtBirth
     * @return  lastnameAtBirth in string
     */
    public String getLastnameAtBirth() {
        return lastnameAtBirth;
    }

    /**
     * Sets the lastnameAtBirth
     * @param lastnameAtBirth      lastnameAtBirth in string
     */
    public void setLastnameAtBirth(String lastnameAtBirth) {
        this.lastnameAtBirth = lastnameAtBirth;
    }

    /**
     * Returns the lastnameUponMarriage
     * @return  lastnameUponMarriage in string
     */
    public String getLastnameUponMarriage() {
        return lastnameUponMarriage;
    }

    /**
     * Sets the LastnameUponMarriage
     * @param lastnameUponMarriage      LastnameUponMarriage in string
     */
    public void setLastnameUponMarriage(String lastnameUponMarriage) {
        this.lastnameUponMarriage = lastnameUponMarriage;
    }

    /**
     * Returns the gender of person in string
     * @return  gender in string
     */
    public String getGender() {
        return gender;
    }

    /**
     * Sets the gender. can only be empty string, "male" or "female" case insensitive
     * @param gender                            gender in string
     * @throws InvalidPersonParameterException  If the parameter does not match "male" or "female"
     */
    public void setGender(String gender) throws InvalidPersonParameterException {
        if (gender.equalsIgnoreCase("")) {
            this.gender = "";
            return;
        }
        if (gender.equalsIgnoreCase("female")) {
            this.gender = "Female";
            return;
        }
        if (gender.equalsIgnoreCase("male")) {
            this.gender = "Male";
            return;
        }
        throw new InvalidPersonParameterException("Invalid Gender: Usage is 'Male' or 'Female'");
    }

    /**
     * Returns the address object
     * @return  the address object
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address object
     * @param address   the address object
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Returns the description of person in string
     * @return  description in string
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description string
     * @param description   the description string
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Returns the children list
     * @return  The children list
     */
    public ArrayList<Person> getChildren() {
        return (ArrayList<Person>) children;
    }

    /**
     * Sets the children using an array. Adds children to the list of existing children
     * @param children  arraylist of persons
     */
    public void setChildren(ArrayList<Person> children) throws InvalidPersonParameterException {
        for (Person child : children) {
            this.setChildren(child);
        }
    }

    /**
     * This method will try to add the child to this person
     * Checks if the child's parent is not this person.
     * If not, will try to add this person to the child's parent.
     * Will check and try to add the child to this person's spouse.
     * Obviously, this would cause error if the child already has two parent specified
     * @param child                             Child person object
     * @throws InvalidPersonParameterException  If the child has already two parents
     */
    public void setChildren(Person child) throws InvalidPersonParameterException {
        if (!this.searchChildren(child)) {
            this.children.add(child);
        }
        // check if the child contain this as parent. If not, add as parent
        if (!child.searchParents(this)) {
            try {
                child.setParents(this);
            } catch (Exception e) {     // in case the child already has 2 parent
                throw new InvalidPersonParameterException("Trying to add " + child.getFirstName() + " but he/she already has two parents");
            }
        }
        // check if any spouse set as child also
        if (spouse != null) {
            if (!spouse.searchChildren(child)) {
                spouse.setChildren(child);
            }
        }
    }

    /**
     * Allows the user to check if the child already exist
     * @param child The child to test against
     * @return      True if the person already present, false if not
     */
    public boolean searchChildren(Person child) {
        return this.children.contains(child);
    }

    /**
     * Returns the parent list
     * @return  parent list as an arraylist
     */
    public ArrayList<Person> getParents() {
        return (ArrayList<Person>) parents;
    }

    public void setParents(ArrayList<Person> parents) throws InvalidPersonParameterException {
        if (parents.size() > 2) {
            throw new InvalidPersonParameterException("Already has 2 parents");
        }
        this.parents = parents;
    }

    /**
     * Allows the user to check if the child already exist
     * @param parent    Person parent to check against
     * @return          True if the person already present, false if not
     */
    public boolean searchParents(Person parent) {
        return this.parents.contains(parent);

    }

    /**
     * Sets the person's parent
     * @param parent                                Person parent
     * @throws InvalidPersonParameterException      If the person already has 2 parents
     */
    public void setParents(Person parent) throws InvalidPersonParameterException {
        // check if the parent already exist in this person before setting parent
        if (!this.searchParents(parent)) {  // if parent does not exist yet
            // check if the person already has 2 parents before trying to add
            if (this.parents.size() >= 2) {
                throw new InvalidPersonParameterException("Already has 2 parents");
            }
            this.parents.add(parent);
        }

        // check if the parent already exist before setting child
        if (!parent.searchChildren(this)) {
            parent.setChildren(this);
        }
    }

    /**
     * Returns the person's spouse
     * @return  Spouse person
     */
    public Person getSpouse() {
        return spouse;
    }

    /**
     * Sets the person's spouse
     * Tries to add this person's children to the spouse also.
     * Checks and try to set the spouse's spouse as this person. But if either of them is remarry, the children will
     * not be added
     * (which will violate that child cannot have more than 2 parents).
     * @param spouse
     * @throws InvalidPersonParameterException
     */
    public void setSpouse(Person spouse) throws InvalidPersonParameterException {

        if (spouse.getGender().equals(gender) && gender.length() != 0) {
            throw new InvalidPersonParameterException("No same sex marriage allowed!");
        }

        // check if this person is unmarried
        if (this.spouse == null) {
            this.spouse = spouse;
            // automatically sets the other person's spouse to be this person.
            // if the spouse to be is also unmarried then add children
            if (spouse.getSpouse() == null) {
                spouse.setSpouse(this);
                // automatically add this person's children to the spouse.
                for (Person thisChild : this.children) {
                    spouse.setChildren(thisChild);
                }
                // automatically add other children to this person
                for (Person otherChild : spouse.getChildren()) {
                    this.setChildren(otherChild);
                }
            }
        }

        // check if this person remarried, then only change the spouse but do not affect the children
        if (!this.spouse.equals(spouse)) {
            this.spouse = spouse;
        }

        // if the spouse to be remarried,, then only change the spouse but do not affect the children
        if (spouse.getSpouse() != this) {
            spouse.setSpouse(this);
        }
    }

    /**
     * equals method that compares the firstname, LastnameAtBirth, LastnameUponMarriage, gender, description
     * @param other other person to compare to
     * @return  true if same, false if not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) return true;
        if (other == null || getClass() != other.getClass()) return false;
        Person person = (Person) other;
        return getFirstName().equals(person.getFirstName()) &&
                getLastnameAtBirth().equals(person.getLastnameAtBirth()) &&
                Objects.equals(getLastnameUponMarriage(), person.getLastnameUponMarriage()) &&
                getGender().equals(person.getGender()) &&
                Objects.equals(getDescription(), person.getDescription());
    }

    /**
     * Overrides the hashCode method retrieving the hashes for firstname, LastnameAtBirth, LastnameUponMarriage,
     * gender, description
     * @return      This object hash
     */
    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastnameAtBirth(), getLastnameUponMarriage(), getGender(), getDescription());
    }

    /**
     * This object will return the person first name and last name. (For use in the treeview)
     * If person has lastnameUponMarriage, will return lastnameUponMarriage
     * if not, will return lastnameAtBirth
     * @return  first and lastname string
     */
    @Override
    public String toString() {
        if (lastnameUponMarriage.equals("")) return firstName + " " + lastnameAtBirth;
        else return firstName + " " + lastnameUponMarriage;
    }
}
