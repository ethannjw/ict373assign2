import java.io.Serializable;
import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

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
            // not supposed to throw any exception
        }

    }
    public Person(String firstName, String lastnameAtBirth, String lastnameUponMarriage, String gender, Address address, String description) throws InvalidPersonParameterException {
        super();
        setFirstName(firstName);
        setLastnameAtBirth(lastnameAtBirth);
        setLastnameUponMarriage(lastnameUponMarriage);
        setGender(gender);
        setAddress(address);
        setDescription(description);
    }

    public static class InvalidPersonParameterException extends Exception {
        public InvalidPersonParameterException(String msg) {
            super(msg);
        }
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastnameAtBirth() {
        return lastnameAtBirth;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastnameAtBirth(String lastnameAtBirth) {
        this.lastnameAtBirth = lastnameAtBirth;
    }

    public String getLastnameUponMarriage() {
        return lastnameUponMarriage;
    }

    public void setLastnameUponMarriage(String lastnameUponMarriage) {
        this.lastnameUponMarriage = lastnameUponMarriage;
    }

    public String getGender() {
        return gender;
    }

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

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public ArrayList<Person> getChildren() {
        return (ArrayList<Person>) children;
    }

    public void setChildren(ArrayList<Person> children) {
        this.children = children;
    }

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
        for (Person thisChild : this.children) {
            if (thisChild == child) {
                return true;
            }
        }
        return false;
    }

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
        for (Person thisParent : this.parents) {
            if (thisParent == parent) {
                return true;
            }
        }
        return false;
    }

    /**
     * Sets the person's parent
     * @param parent        Person parent
     * @throws InvalidPersonParameterException    If the person already has 2 parents
     */
    public void setParents(Person parent) throws InvalidPersonParameterException {
        if (this.parents.size() >= 2) {
            throw new InvalidPersonParameterException("Already has 2 parents");
        }
        // check if the parent already exist in this person before setting parent
        if (!this.searchParents(parent)) {
            this.parents.add(parent);
        }
        // check if the parent already exist before setting child
        if (!parent.searchChildren(this)) {
            parent.setChildren(this);
        }
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) throws InvalidPersonParameterException {

        if (spouse.getGender().equals(gender) && gender.length() != 0) {
            throw new InvalidPersonParameterException("No same sex marriage allowed!");
        }
        if (this.spouse != spouse) {
            this.spouse = spouse;
        }

        // automatically sets the other person's spouse to be this person.
        if (spouse.getSpouse() == null) {   // if the spouse to be is null
            spouse.setSpouse(this);
        }
        if (spouse.getSpouse() != this) {   // if the spouse to be is not this person
            spouse.setSpouse(this);
            return;     // skips adding this person's children to new spouse
        }

        // automatically add this person's children to the spouse.
        for (Person thisChild : this.children) {
            spouse.setChildren(thisChild);
        }

    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Person person = (Person) o;
        return getFirstName().equals(person.getFirstName()) &&
                getLastnameAtBirth().equals(person.getLastnameAtBirth()) &&
                Objects.equals(getLastnameUponMarriage(), person.getLastnameUponMarriage()) &&
                getGender().equals(person.getGender()) &&
                Objects.equals(getDescription(), person.getDescription());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getFirstName(), getLastnameAtBirth(), getLastnameUponMarriage(), getGender(), getDescription());
    }

    @Override
    public String toString() {
        return firstName;
    }
}
