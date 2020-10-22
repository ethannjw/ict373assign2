import java.io.Serializable;
import java.util.Objects;

public class Address implements Serializable {
    private String streetNum;
    private String streetName;
    private String suburb;
    private int postCode;

    public Address(String streetNum, String streetName, String suburb, int postCode) {
        super();
        this.streetNum = streetNum;
        this.streetName = streetName;
        this.suburb = suburb;
        this.postCode = postCode;
    }

    public String getStreetNum() {
        return streetNum;
    }

    public void setStreetNum(String streetNum) {
        this.streetNum = streetNum;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getSuburb() {
        return suburb;
    }

    public void setSuburb(String suburb) {
        this.suburb = suburb;
    }

    public int getPostCode() {
        return postCode;
    }

    public void setPostCode(int postCode) {
        this.postCode = postCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Address address = (Address) o;
        return getStreetNum() == address.getStreetNum() &&
                getPostCode() == address.getPostCode() &&
                getStreetName().equals(address.getStreetName()) &&
                getSuburb().equals(address.getSuburb());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getStreetNum(), getStreetName(), getSuburb(), getPostCode());
    }

    @Override
    public String toString() {
        return "Address{" +
                "streetNum=" + streetNum +
                ", streetName='" + streetName + '\'' +
                ", suburb='" + suburb + '\'' +
                ", postCode=" + postCode +
                '}';
    }
}
