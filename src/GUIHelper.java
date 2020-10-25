
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

/**
 * Interface Class that contains static methods and the final attributes to specify the attributes and generate the UI
 */
public interface GUIHelper {


    // status bar at the bottom
    HBox statusBar = new HBox();
    Text statusText = new Text("Program loaded");

    // The left pane containing the treeview
    TreeView<Person> treeView = new TreeView<>();

    // the top pane
    VBox topPane = new VBox(8);

    // the right pane
    BorderPane rightPane = new BorderPane();

    // VBox to contain the field labels. Left side of the right pane
    VBox contentLabels = new VBox(8);
    //firstname
    HBox firstName = generateAttribute("First name : ", "");
    // surnamebef
    HBox surnameBef = generateAttribute("Lastname bef marriage : ", "");
    // surnameAft
    HBox surnameAft = generateAttribute("Lastname aft marriage : ", "");
    // gender
    HBox gender = generateAttribute("Sex : ", "");
    // VBox that contains the person description
    VBox desc = new VBox(8);
    // Description label
    Text descLabel = new Text("Life description : ");
    // text area to contain the person description
    TextArea descContent = new TextArea("");
    //Address Box
    Text addressTitle = new Text("Address info");
    // streetNum
    HBox streetNum = GUIHelper.generateAttribute("Street number : ", "");
    // streetName
    HBox streetName = GUIHelper.generateAttribute("Street name : ", "");
    // suburb
    HBox suburb = GUIHelper.generateAttribute("Suburb : ", "");
    // post code
    HBox postCode = GUIHelper.generateAttribute("Postcode : ", "");

    // HBox to contain the buttons at the top load, save and new
    HBox hBoxTopButtons = new HBox(8);
    Button loadBtn = new Button();
    Button saveBtn = new Button();
    Button createBtn = new Button();

    // buttons at the bottom, edit, add relative and set root
    HBox rightPaneButtons = new HBox(8);
    Button editDetails = new Button("Edit selected");
    Button addRelative = new Button("Add Relative to root");
    Button setRoot = new Button("Set selected as Root");

    Alert mainAlert = new Alert(Alert.AlertType.ERROR);
    RootObservable root = RootObservable.getInstance();

    /**
     * Changes the view in the right pane using the instance of the specified person
     * @param newPerson Person object to change right pane view to
     */
    static void changeView(Person newPerson) {
        if ((firstName.getChildren().get(1)) instanceof Text) {
            ((Text)firstName.getChildren().get(1)).setText(newPerson.getFirstName());
        } else {System.out.println("Cannot Change Firstname");}
        if ((surnameBef.getChildren().get(1)) instanceof Text) {
            ((Text)surnameBef.getChildren().get(1)).setText(newPerson.getLastnameAtBirth());
        } else {System.out.println("Cannot Change surnameBef");}
        if ((surnameAft.getChildren().get(1)) instanceof Text) {
            ((Text)surnameAft.getChildren().get(1)).setText(newPerson.getLastnameUponMarriage());
        } else {System.out.println("Cannot Change surnameAft");}
        if ((gender.getChildren().get(1)) instanceof Text) {
            ((Text)gender.getChildren().get(1)).setText(newPerson.getGender());
        } else {System.out.println("Cannot Change gender");}

        descContent.setText(newPerson.getDescription());

        if ((streetNum.getChildren().get(1)) instanceof Text) {
            ((Text)streetNum.getChildren().get(1)).setText("" + newPerson.getAddress().getStreetNum());
        } else {System.out.println("Cannot Change street num");}
        if ((streetName.getChildren().get(1)) instanceof Text) {
            ((Text)streetName.getChildren().get(1)).setText("" + newPerson.getAddress().getStreetName());
        } else {System.out.println("Cannot Change street name");}
        if ((suburb.getChildren().get(1)) instanceof Text) {
            ((Text)suburb.getChildren().get(1)).setText("" + newPerson.getAddress().getSuburb());
        } else {System.out.println("Cannot Change suburb");}
        if ((postCode.getChildren().get(1)) instanceof Text) {
            ((Text)postCode.getChildren().get(1)).setText("" + newPerson.getAddress().getPostCode());
        } else {System.out.println("Cannot Change postCode");}
    }

    /**
     * modifies the selected person instance to the updated values
     * @param selectedPerson            The selected person to edit
     * @param firstName                 String Firstname to set
     * @param lastNameAtBirth           String Lastname at birth to set
     * @param lastNameUponMarriage      String lastname upon marriage to set
     * @param gender                    String gender to set
     * @param desc                      String Description to set
     * @param streetNum                 String streetNum to set
     * @param streetName                String streetname to set
     * @param suburb                    String suburb to set
     * @param postCode                  String postcode to set
     * @throws Exception                Will let the caller handle the exception
     */
    static void saveEditedPerson(Person selectedPerson, String firstName, String lastNameAtBirth, String lastNameUponMarriage, String gender, String desc, String streetNum, String streetName, String suburb, String postCode) throws Person.InvalidPersonParameterException {
        selectedPerson.setFirstName(firstName);
        selectedPerson.setLastnameAtBirth(lastNameAtBirth);
        selectedPerson.setLastnameUponMarriage(lastNameUponMarriage);
        selectedPerson.setGender(gender);
        selectedPerson.setDescription(desc);
        selectedPerson.getAddress().setStreetNum(streetNum);
        selectedPerson.getAddress().setStreetName(streetName);
        selectedPerson.getAddress().setSuburb(suburb);
        selectedPerson.getAddress().setPostCode(Integer.parseInt(postCode));

    }

    /**
     * Used for generating each attribute in the details pane
     * @param label     String label for the attribute
     * @param content   The content for the attribute
     * @return          The HBox that is showing the person attribute
     */
    static HBox generateAttribute(String label, String content) {
        HBox attribute = new HBox(8);
        Text aLabel = new Text(label);
        Text aContent = new Text(content);
        attribute.setMaxHeight(8);
        aContent.autosize();
        attribute.getChildren().addAll(aLabel, aContent);
        return attribute;
    }

    /**
     * Recursively add all children to the tree
     * @param rootChild     Treeitem of the child root
     * @param firstChild    next child to be added to the child root
     * @return TreeItem     The treeitem of the parent
     */
    static TreeItem<Person> addChildrenToTreeView(TreeItem<Person> rootChild, Person firstChild) {
        if (firstChild.getChildren() != null) {
            for (Person child : firstChild.getChildren()) {
                // Create a new tree item to hold each child
                TreeItem<Person> childRoot = new TreeItem<>(child);
                // Add the tree item to the root
                rootChild.getChildren().add(addChildrenToTreeView(childRoot, child));
            }
        }
        return rootChild;
    }

    /**
     * Generate the tree from the specified person
     * @param person    Specified person to create the tree details
     */
    @SuppressWarnings({ "unchecked", "rawtypes" }) // suppress the title nodes using rawtypes since they are only title nodes
    static void createTree(Person person) {

        TreeItem<Person> root = new TreeItem<Person>(person);
        treeView.setRoot(root);

        // setting the title nodes
        TreeItem<Person> parents = new TreeItem("Parents");
        TreeItem<Person> children = new TreeItem("Children");
        TreeItem<Person> spouse = new TreeItem("Spouse");

        // add the parents
        for (Person parent : person.getParents()) {
            parents.getChildren().add(new TreeItem<Person>(parent));
        }
        // add the spouse
        spouse.getChildren().add(new TreeItem<Person>(person.getSpouse()));
        // add the children
        GUIHelper.addChildrenToTreeView(children, person);

        root.getChildren().addAll(parents, children, spouse);
        root.setExpanded(false);

        // setting the buttons
        treeView.setOnMouseClicked(evt -> showSelected(evt));

    }

    /**
     * Event to show the selected person in the tree
     * @param evt   Event
     */
    static void showSelected(Event evt) {

        try {
            TreeView<Person> tree = (TreeView<Person>)evt.getSource();    // gives you the component that triggers the event
            TreeItem<Person> selectedItem = tree.getSelectionModel().getSelectedItem();
            // set the status text
            statusText.setText("Showing: " + selectedItem.getValue());
            changeView(selectedItem.getValue());
            // set the selected person
            root.setSelectedPerson(selectedItem.getValue());
        } catch (NullPointerException e) {
            System.out.println("User selected null object");
        } catch (ClassCastException e) {
            System.out.println(e);
        }

    }

    /**
     * Sets up the file chooser with init dir and file extension of *.ft
     * @return  The configured FileChooser
     */
    static FileChooser getFileChooserInstance() {
        FileChooser fileChooser = new FileChooser();
        File initDir = new File(".");
        fileChooser.setInitialDirectory(initDir);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Family Tree Type(*.ft)", "*.ft"));
        return fileChooser;
    }


}
