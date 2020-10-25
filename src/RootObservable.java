import java.util.Objects;
import java.util.Observable;
import java.util.Observer;

/**
 * Observable object that stores the root and selected persons
 */
public class RootObservable {
    private Person rootPerson;
    private Person selectedPerson;
    private static RootObservable rootInstance = null;

    private RootObservable() {
        rootPerson = new Person();
        selectedPerson = rootPerson;
    }

    /**
     * Singleton
     * @return  this object
     */
    public static RootObservable getInstance() {
        if (rootInstance == null) {
            rootInstance = new RootObservable();
        }
        return rootInstance;
    }
    public void setRootPerson(Person newPerson) {
        this.rootPerson = newPerson;
    }
    public void setSelectedPerson(Person newPerson) {
        this.selectedPerson = newPerson;
    }

    public void updateRootToSelected() {
        setRootPerson(getSelectedPerson());
    }

    public Person getRootPerson() {
        return rootPerson;
    }

    public Person getSelectedPerson() {
        return selectedPerson;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof RootObservable)) return false;
        RootObservable that = (RootObservable) o;
        return Objects.equals(getRootPerson(), that.getRootPerson()) &&
                Objects.equals(getSelectedPerson(), that.getSelectedPerson());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getRootPerson(), getSelectedPerson());
    }
}
