import java.util.List;
import java.util.ArrayList;
import java.util.Objects;

public class Person {
    private String firstName;
    private String lastnameAtBirth;
    private String lastnameUponMarriage;
    private String gender;
    private String description;
    private Address address;
    private Person spouse = null;
    private List<Person> children = new ArrayList<>();
    private List<Person> parents = new ArrayList<>();

    public Person(String firstName, String lastnameAtBirth, String lastnameUponMarriage, String gender, Address address, String description) {
        super();
        this.firstName = firstName;
        this.lastnameAtBirth = lastnameAtBirth;
        this.lastnameUponMarriage = lastnameUponMarriage;
        this.gender = gender;
        this.address = address;
        this.description = description;
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

    public void setGender(String gender) throws Exception {
        if (gender.equalsIgnoreCase("female")) {
            this.gender = "Female";
            return;
        }
        if (gender.equalsIgnoreCase("male")) {
            this.gender = "Male";
            return;
        }
        throw new Exception("Invalid Gender: Usage is 'Male' or 'Female'");
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

    public void setChildren(Person children) {
        this.children.add(children);
    }

    public ArrayList<Person> getParents() {
        return (ArrayList<Person>) parents;
    }

    public void setParents(ArrayList<Person> parents) throws Exception {
        if (parents.size() > 2) {
            throw new Exception("Already has 2 parents");
        }
        this.parents = parents;
    }

    public void setParents(Person parents) throws Exception {
        if (this.parents.size() >= 2) {
            throw new Exception("Already has 2 parents");
        }
        this.parents.add(parents);
    }

    public Person getSpouse() {
        return spouse;
    }

    public void setSpouse(Person spouse) {
        this.spouse = spouse;
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
        return "Person{" +
                "firstName='" + firstName + '\'' +
                ", lastnameAtBirth='" + lastnameAtBirth + '\'' +
                ", gender='" + gender + '\'' +
                ", address=" + address +
                ", description='" + description + '\'' +
                '}';
    }
}
