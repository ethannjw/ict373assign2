import java.io.*;

/**
 * Class that contains the file handling logic with static classes
 */
public class FileHandler implements Serializable {
    /**
     * The load method returns the person object from the file
     * @param file          The file to open
     * @return              The person object from the file
     * @throws IOException  If the file is not readable
     */
    public static Person load(File file) throws IOException {
        Person root;
        FileInputStream inFile = new FileInputStream(file);
        ObjectInputStream inObject = new ObjectInputStream(inFile);
        try {
            // set the root to the read object
            root = (Person) inObject.readObject();
        } catch (ClassNotFoundException e) {
            System.out.println(e);
            return null;
        }
        inObject.close();
        return root;
    }

    /**
     * The save method that saves the person into the file
     * @param file          The file to save to
     * @param person        The person object to save
     * @throws IOException  If the file is not writable
     */
    public static void save(File file, Person person) throws IOException
    {
        FileOutputStream outFile = new FileOutputStream(file);
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        outObject.writeObject(person);
    }
}
