
/**
 * Observable object that stores the root and selected persons, and whether the instance has changed. It is a singleton
 * that the other classes will use so that only one instance is created at any time
 */
public class RootObservable {
    private Person rootPerson;
    private Person selectedPerson;
    private static RootObservable rootInstance = null;
    private boolean changed;

    /**
     * Generates a new object that is only called by the get instance method.
     */
    private RootObservable() {
        rootPerson = new Person();  // change this value to generate from the CreatePerson class
        selectedPerson = rootPerson;
        this.changed = false;
    }

    /**
     * Generates a new object if none is present, otherwise returns the existing one.
     * @return  this object
     */
    public static RootObservable getInstance() {
        if (rootInstance == null) {
            rootInstance = new RootObservable();
        }
        return rootInstance;
    }

    /**
     * This is called when the changed is not to be set to true. i.e. when loading the root for the first time.
     * @param newPerson     The person to load into root
     */
    public void loadRootPerson (Person newPerson) {
        this.rootPerson = newPerson;
    }

    /**
     * Set new root person. Will set the changed variable to true
     * @param newPerson     The person to load into root
     */
    public void setRootPerson(Person newPerson) {
        this.rootPerson = newPerson;
        this.changed = true;
    }

    /**
     * Sets the selected person, which is used when user wants to view the person and allows the person to be edited.
     * @param newPerson     The person to load into selected person
     */
    public void setSelectedPerson(Person newPerson) {
        this.selectedPerson = newPerson;
    }

    /**
     * Method to set the root person same as the selected person. Called by the set root function
     */
    public void updateRootToSelected() {
        setRootPerson(getSelectedPerson());
    }

    /**
     * Gets the current root person
     * @return  The current root person
     */
    public Person getRootPerson() {
        return rootPerson;
    }
    /**
     * Gets the current selected person
     * @return  The current selected person
     */
    public Person getSelectedPerson() {
        return selectedPerson;
    }

    /**
     * Sets the current changed status
     * @param bool  Changed status boolean
     */
    public void setChanged(boolean bool) {
        this.changed = bool;
    }

    /**
     * Returns the current changed status
     * @return  Changed status boolean
     */
    public boolean getChanged() {
        return changed;
    }

}
