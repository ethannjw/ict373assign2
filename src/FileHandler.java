import java.io.*;

/**
 * Class that contains the file handling logic
 */
public class FileHandler implements Serializable {

    public FileHandler() {

    }
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
    public static void save(File file, Person root) throws IOException
    {
        FileOutputStream outFile = new FileOutputStream(file);
        ObjectOutputStream outObject = new ObjectOutputStream(outFile);
        outObject.writeObject(root);
    }
}
