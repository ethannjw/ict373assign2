import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Main extends Application {
    // main container
    private BorderPane mainContainer = new BorderPane();

    // status bar at the bottom
    private HBox statusBar = new HBox();
    private Text statusText = new Text("Program loaded");


    // The left pane containing the treeview
    private TreeView<Person> treeView = new TreeView<>();
    private Person rootPerson = new Person();
    private Person selectedPerson = new Person();

    // the top pane
    private VBox topPane = new VBox(8);
    // HBox to contain the buttons
    HBox hBoxTopButtons = new HBox(8);
    private Button loadBtn = new Button();
    private Button saveBtn = new Button();
    private Button createBtn = new Button();

    // the right pane
    private BorderPane rightPane = new BorderPane();
    // the right pane attributes
    private VBox contentLabels = new VBox(8);
    private HBox firstName = new HBox(8);
    private HBox surnameBef = new HBox(8);
    private HBox surnameAft = new HBox(8);
    private HBox gender = new HBox(8);
    private VBox desc = new VBox(8);
    private TextArea descContent = new TextArea("");
    private HBox streetNum = new HBox(8);
    private HBox streetName = new HBox(8);
    private HBox suburb = new HBox(8);
    private HBox postCode = new HBox(8);
    // buttons to be set to the bottom
    private HBox rightPaneButtons = new HBox(8);
    Button editDetails;
    Button addRelative;
    Button setRoot;

    String selectedRelativeType = null;    // for storing the relative type

    /**
     * Start method for the GUI
     * @param primaryStage  Primary Stage
     * @throws Exception    Default Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPerson = new Person();
        //rootPerson = CreatePerson.createKirito();
        selectedPerson = rootPerson;
        // buttons settings
        loadBtn.setText("Load");
        loadBtn.setOnAction(evt -> {
            openFileChooser(evt, primaryStage);
        });
        saveBtn.setText("Save");
        saveBtn.setOnAction(evt -> {
            saveFileChooser(evt, primaryStage);
        });
        createBtn.setText("Create");
        createBtn.setOnAction(evt -> {
            statusText.setText("Creating New Person");
            editPersonDialog(primaryStage, rootPerson, "Create Person", false);
            selectedPerson = rootPerson;    // set the view to show the newly created person

        });
        // Create the treeView
        createTree(rootPerson);

        // bottom status bar settings
        statusBar.getChildren().add(statusText);
        statusBar.setMinHeight(10);

        // Defining the top part of the GUI
        Text title = new Text("Family Tree Management System");
        title.setFont(Font.font("Times New Roman", 30));
        topPane.getChildren().add(title);
        hBoxTopButtons.getChildren().add(loadBtn);
        hBoxTopButtons.getChildren().add(saveBtn);
        hBoxTopButtons.getChildren().add(createBtn);
        topPane.getChildren().add(hBoxTopButtons);

        // Defining the right part of the GUI
        Text personTitle = new Text("Person Details");
        personTitle.setFont(Font.font(20));
        //firstname
        firstName = generateAttribute("First name : ", "");
        // surnamebef
        surnameBef = generateAttribute("Lastname bef marriage : ", "");
        // surnameAft
        surnameAft = generateAttribute("Lastname aft marriage : ", "");
        // gender
        gender = generateAttribute("Sex : ", "");
        // desc
        desc = new VBox(8);
        Text descLabel = new Text("Life description : ");
        descContent.autosize();
        descContent.setWrapText(true);
        desc.getChildren().addAll(descLabel, descContent);

        //Address Box
        Text addressTitle = new Text("Address info");
        addressTitle.setFont(Font.font(20));
        // streetNum
        streetNum = generateAttribute("Street number : ", "");
        // streetName
        streetName = generateAttribute("Street name : ", "");
        // suburb
        suburb = generateAttribute("Suburb : ", "");
        // post code
        postCode = generateAttribute("Postcode : ", "");

        contentLabels.autosize();
        contentLabels.setMaxWidth(300);
        contentLabels.setMinWidth(175);
        contentLabels.getChildren().addAll(personTitle, firstName, surnameBef, surnameAft, gender, desc, addressTitle, streetNum, streetName, suburb, postCode);

        // buttons for adding relative and editing details
        // edit the selected person
        editDetails = new Button("Edit Person");
        editDetails.setOnAction(evt -> {
            editPersonDialog(primaryStage, selectedPerson, "Edit Person", false);
        });
        // add new person to the root person
        addRelative = new Button("Add Relative");
        addRelative.setOnAction(evt -> {
            editPersonDialog(primaryStage, new Person(), "Add New Relative", true);
            createTree(rootPerson);
            treeView.refresh();
        });
        setRoot = new Button("Set as Root");
        setRoot.setOnAction(evt -> {
            createTree(selectedPerson);
            rootPerson = selectedPerson;
        });

        rightPaneButtons.getChildren().addAll(setRoot, editDetails, addRelative);
        rightPaneButtons.setAlignment(Pos.BOTTOM_RIGHT);
        rightPane.setPadding(new Insets(0,10,0,10));
        rightPane.setLeft(contentLabels);
        //rightPane.setCenter(contentDesc);
        rightPane.setBottom(rightPaneButtons);

        // main container border pane settings
        mainContainer.setPrefSize(600,700);
        mainContainer.setTop(topPane);
        mainContainer.setLeft(treeView);
        mainContainer.setCenter(rightPane);
        mainContainer.setBottom(statusBar);
        mainContainer.setPadding(new Insets(3,3,3,3));
        primaryStage.setTitle("Family Tree Management System");

        primaryStage.setScene(new Scene(mainContainer));
        primaryStage.show();
    }

    private void openFileChooser(Event evt, Stage primaryStage) {
        File userSelectedFile;
        FileChooser fileChooser = new FileChooser();
        File initDir = new File(".");
        fileChooser.setInitialDirectory(initDir);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Family Tree Type(*.ft)", "*.ft"));
        try {
            userSelectedFile = fileChooser.showOpenDialog(primaryStage);
        } catch (NullPointerException e) {
            statusText.setText("User quit the file choose dialog");
            return;
        }

        try {
            rootPerson = FileHandler.load(userSelectedFile);
            createTree(rootPerson);
        } catch (IOException e) {
            System.out.println(e);
            statusText.setText("Something Went Wrong when trying to open the file!");
        } catch (NullPointerException e) {
            System.out.println(e);
            statusText.setText("File not selected!");
        }

    }
    private void saveFileChooser(Event evt, Stage primaryStage) {
        FileChooser fileChooser = new FileChooser();
        File initDir = new File(".");
        fileChooser.setInitialDirectory(initDir);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Family Tree Type(*.ft)", "*.ft"));
        if (rootPerson == null) {
            statusText.setText("Nothing to save!");
            return;
        }
        File userSelectedFile = fileChooser.showSaveDialog(primaryStage);
        try {
            FileHandler.save(userSelectedFile, rootPerson);
            statusText.setText("File saved!");
        } catch (IOException e) {
            System.out.println(e);
            statusText.setText("Something Went Wrong when trying to save the file!");
        }
    }

    private ComboBox relativeComboBox() {

        // Generate the relative types list
        String[] relativeTypes = {"Parent", "Child", "Spouse"};
            ComboBox<String> relativeTypesBox = new ComboBox<>(FXCollections.observableArrayList(relativeTypes));
        relativeTypesBox.setOnAction(evt -> {
            this.selectedRelativeType = relativeTypesBox.getValue();
        });
        return relativeTypesBox;
    }
    /**
     * This is the edit/create Person dialog box that allows the user to edit the person
     * @param primaryStage  The main stage
     * @param person        The person object to edit
     */
    private void editPersonDialog(Stage primaryStage, Person person, String dialogTitle, boolean relativeField) {

        Stage editPerson = new Stage();
        editPerson.setTitle(dialogTitle);
        editPerson.initModality(Modality.APPLICATION_MODAL);    // set to block other windows
        editPerson.initOwner(primaryStage);
        GridPane editPersonMainBox = new GridPane();
        editPersonMainBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        editPersonMainBox.setPadding(new Insets(3,3,3,3));

        // create the title
        Text editPersonTitle = new Text(dialogTitle);
        editPersonTitle.setFont(Font.font("Times New Roman", 30));

        // Create the labels on the left side
        editPersonMainBox.add(new Text("Firstname : "), 0,0);
        editPersonMainBox.add(new Text("Lastname bef Marriage :"), 0,1);
        editPersonMainBox.add(new Text("Lastname aft Marriage :"), 0,2);
        editPersonMainBox.add(new Text("Sex :"), 0,3);
        editPersonMainBox.add(new Text("Description :"), 0,4);
        editPersonMainBox.add(new Text("Street Num :"), 0,5);
        editPersonMainBox.add(new Text("Street Name :"), 0,6);
        editPersonMainBox.add(new Text("Suburb :"), 0,7);
        editPersonMainBox.add(new Text("Postcode :"), 0,8);

        // Create the text fields on the right side
        TextField firstNameField = new TextField(person.getFirstName());
        editPersonMainBox.add(firstNameField, 1, 0);
        TextField surnameBefField = new TextField(person.getLastnameAtBirth());
        editPersonMainBox.add(surnameBefField, 1,1);
        TextField surnameAftField = new TextField(person.getLastnameUponMarriage());
        editPersonMainBox.add(surnameAftField, 1,2);
        TextField genderField = new TextField(person.getGender());
        editPersonMainBox.add(genderField, 1,3);
        TextArea descriptionField = new TextArea(person.getDescription());
        descriptionField.autosize();
        descriptionField.setWrapText(true);
        editPersonMainBox.add(descriptionField, 1,4);
        TextField streetNumField = new TextField("" + person.getAddress().getStreetNum());
        editPersonMainBox.add(streetNumField, 1,5);
        TextField streetNameField = new TextField(person.getAddress().getStreetName());
        editPersonMainBox.add(streetNameField, 1,6);
        TextField suburbField = new TextField(person.getAddress().getSuburb());
        editPersonMainBox.add(suburbField, 1,7);
        TextField postCodeField = new TextField("" + person.getAddress().getPostCode());
        editPersonMainBox.add(postCodeField, 1,8);

        // add the relative box if needed
        if (relativeField) {
            editPersonMainBox.add(new Text("Relative :"),0, 9);
            editPersonMainBox.add(relativeComboBox(), 1, 9);
        }

        // alert popup
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText("");
        alert.setContentText("");

        // use HBox to arrange the buttons
        HBox buttonRow = new HBox(8);
        Button confirmBtn = new Button("Confirm");

        // set the confirm button action
        confirmBtn.setOnAction(evt -> {
            try {
                saveEditedPerson(
                        person,
                        firstNameField.getText(),
                        surnameBefField.getText(),
                        surnameAftField.getText(),
                        genderField.getText(),
                        descriptionField.getText(),
                        streetNumField.getText(),
                        streetNameField.getText(),
                        suburbField.getText(),
                        postCodeField.getText()
                );
                if (relativeField) {
                    if (this.selectedRelativeType == null) {
                        alert.setHeaderText("Must set relative!");
                        alert.setContentText("Please enter the relative");
                        alert.showAndWait();
                        return;
                    }
                    if (this.selectedRelativeType.equals("Parent")) rootPerson.setParents(person);
                    if (this.selectedRelativeType.equals("Child")) rootPerson.setChildren(person);
                    if (this.selectedRelativeType.equals("Spouse")) rootPerson.setSpouse(person);
                }
                changeView(person); // change view to the newly created person
                createTree(rootPerson); // refresh view for the root person
                treeView.getRoot().setExpanded(true);   // expand the treeview
                statusText.setText(person.getFirstName() + " done!");
                editPerson.close();
            } catch (Exception e) {
                System.out.println(e);
                alert.setHeaderText("Error in saving person");
                alert.setContentText(e.getMessage());
                alert.showAndWait();

            }

        });
        // set the cancel button action
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(evt -> {
            editPerson.close();
        });
        buttonRow.getChildren().addAll(cancelBtn, confirmBtn);
        buttonRow.setAlignment(Pos.BOTTOM_RIGHT);


        // Setting the stage
        BorderPane mainEditPersonContainer = new BorderPane(editPersonMainBox);
        mainEditPersonContainer.setTop(editPersonTitle);
        mainEditPersonContainer.setBottom(buttonRow);
        mainEditPersonContainer.setPadding(new Insets(3,3,3,3));
        Scene dialogScene = new Scene(mainEditPersonContainer, 400, 600);
        editPerson.setScene(dialogScene);
        editPerson.show();
    }

    /**
     * Saves the edited person to the selected person instance
     * @param selectedPerson
     * @param firstName
     * @param lastNameAtBirth
     * @param lastNameUponMarriage
     * @param gender
     * @param desc
     * @param streetNum
     * @param streetName
     * @param suburb
     * @param postCode
     * @throws Exception                Will let the caller handle the exception
     */
    private void saveEditedPerson(Person selectedPerson, String firstName, String lastNameAtBirth, String lastNameUponMarriage, String gender, String desc, String streetNum, String streetName,String suburb, String postCode) throws Exception {
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
     * Generate the tree from the specified person
     * @param person    Specified person to create the tree details
     */
    @SuppressWarnings({ "unchecked", "rawtypes" }) // suppress the title nodes using rawtypes since they are only title nodes
    private void createTree(Person person) {

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
        children = addChildrenToTreeView(children, person);

        root.getChildren().addAll(parents, children, spouse);
        root.setExpanded(false);

        // setting the buttons
        treeView.setOnMouseClicked(evt -> showSelected(evt));

    }

    /**
     * Recursively add all children to the tree
     * @param rootChild     Treeitem of the child root
     * @param firstChild    next child to be added to the child root
     * @return
     */
    private TreeItem<Person> addChildrenToTreeView(TreeItem<Person> rootChild, Person firstChild) {
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
     * Event to show the selected person in the tree
     * @param evt   Event
     */
    private void showSelected(Event evt) {
        TreeView<Person> tree = (TreeView<Person>)evt.getSource();    // gives you the component that triggers the event
        try {
            TreeItem<Person> selectedItem = tree.getSelectionModel().getSelectedItem();

            System.out.println("Showing: " + selectedItem.getValue());
            changeView(selectedItem.getValue());
            // set the selected person
            selectedPerson = selectedItem.getValue();
        } catch (NullPointerException | ClassCastException e) {
            System.out.println("");
        }

    }

    /**
     * Changes the view in the right pane to the specified person
     * @param newPerson Person object to change right pane view to
     */
    private void changeView(Person newPerson) {
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
     * Used for generating each attribute in the details pane
     * @param label     String label for the attribute
     * @param content   The content for the attribute
     * @return          The HBox that is showing the person attribute
     */
    private HBox generateAttribute(String label, String content) {
        HBox attribute = new HBox(8);
        Text aLabel = new Text(label);
        Text aContent = new Text(content);
        attribute.setMaxHeight(8);
        aContent.autosize();
        attribute.getChildren().addAll(aLabel, aContent);
        return attribute;
    }

    /**
     * Main class
     * @param args  default args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
