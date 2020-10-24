
import javafx.application.Application;
import javafx.event.Event;
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
import java.io.*;


public class Main extends Application implements GUIHelper {

    // initialise the root person that is the root of the tree
    private Person rootPerson = new Person();

    // initialse the person that is being viewed by the user
    private Person selectedPerson = new Person();

    // for storing the relative type to be used in adding relatives
    //private String selectedRelativeType = null;

    /**
     * Start method for the GUI
     * @param primaryStage  Primary Stage
     * @throws Exception    Default Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        // initialise the rootPerson
        rootPerson = new Person();
        selectedPerson = rootPerson;

        // Create the treeView on the right side
        createTree(rootPerson);

        // bottom status bar settings
        statusBar.getChildren().add(statusText);
        statusBar.setMinHeight(10);

        // Defining the top part of the GUI
        Text title = new Text("Family Tree Management System");
        title.setFont(Font.font("Times New Roman", 30));
        topPane.getChildren().add(title);
        // buttons settings
        loadBtn.setText("Load");
        loadBtn.setOnAction(evt -> {
            openFileChooser(primaryStage);
        });
        saveBtn.setText("Save");
        saveBtn.setOnAction(evt -> {
            saveFileChooser(primaryStage);
        });
        createBtn.setText("Create");
        createBtn.setOnAction(evt -> {
            statusText.setText("Creating New Person");
            editPersonDialog(primaryStage, rootPerson, "Create Person", false);
            selectedPerson = rootPerson;    // set the view to show the newly created person

        });
        hBoxTopButtons.getChildren().add(loadBtn);
        hBoxTopButtons.getChildren().add(saveBtn);
        hBoxTopButtons.getChildren().add(createBtn);
        topPane.getChildren().add(hBoxTopButtons);

        // Defining the right part of the GUI
        Text personTitle = new Text("Person Details");
        personTitle.setFont(Font.font(20));

        descContent.autosize();
        descContent.setWrapText(true);
        desc.getChildren().addAll(descLabel, descContent);

        // Setting the address title
        addressTitle.setFont(Font.font(20));

        contentLabels.autosize();
        contentLabels.setMaxWidth(400);
        contentLabels.setMinWidth(175);
        contentLabels.getChildren().addAll(personTitle, firstName, surnameBef, surnameAft, gender, desc, addressTitle, streetNum, streetName, suburb, postCode);

        // buttons for adding relative and editing details
        // edit the selected person
        editDetails.setOnAction(evt -> {
            editPersonDialog(primaryStage, selectedPerson, "Edit "+selectedPerson, false);
        });
        // add new person to the root person
        addRelative.setOnAction(evt -> {
            editPersonDialog(primaryStage, new Person(), "Add Relative to " + rootPerson, true);
            createTree(rootPerson);
            treeView.refresh();
        });
        // set the root person
        setRoot.setOnAction(evt -> {
            createTree(selectedPerson);
            rootPerson = selectedPerson;
        });

        // set the buttons at the bottom
        rightPaneButtons.getChildren().addAll(setRoot, editDetails, addRelative);
        rightPaneButtons.setAlignment(Pos.BOTTOM_RIGHT);
        rightPane.setPadding(new Insets(0,10,0,10));
        rightPane.setLeft(contentLabels);
        rightPane.setBottom(rightPaneButtons);

        // main container settings
        mainContainer.setPrefSize(710,700);
        mainContainer.setTop(topPane);
        mainContainer.setLeft(treeView);
        mainContainer.setCenter(rightPane);
        mainContainer.setBottom(statusBar);
        mainContainer.setPadding(new Insets(3,3,3,3));
        primaryStage.setTitle("Family Tree Management System");

        primaryStage.setScene(new Scene(mainContainer));
        primaryStage.show();
    }

    /**
     * Sets up the file chooser dialog for user to choose a file to open.
     * @param primaryStage  The main stage
     */
    private void openFileChooser(Stage primaryStage) {

        File userSelectedFile;
        FileChooser fileChooser = GUIHelper.getFileChooserInstance();

        try {
            userSelectedFile = fileChooser.showOpenDialog(primaryStage);
        } catch (NullPointerException e) {
            statusText.setText("User quit the file choose dialog");
            return;
        }
        // load the root person into the rootPerson instance
        try {
            rootPerson = FileHandler.load(userSelectedFile);
            createTree(rootPerson);
        } catch (IOException e) {   // problem with opening file
            System.out.println(e);
            statusText.setText("Something Went Wrong when trying to open the file!");
            mainAlert.setHeaderText("Problem opening file");
            mainAlert.setContentText(e.getLocalizedMessage());
            mainAlert.showAndWait();
        } catch (NullPointerException e) {  // user did not select a file
            System.out.println(e);
            statusText.setText("File not selected!");
        }

    }

    /**
     * Sets up the file chooser dialog for user to choose a file to save.
     * @param primaryStage  The main stage
     */
    private void saveFileChooser(Stage primaryStage) {
        FileChooser fileChooser = GUIHelper.getFileChooserInstance();

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
            mainAlert.setHeaderText("Problem saving file");
            mainAlert.setContentText(e.getLocalizedMessage());
            mainAlert.showAndWait();
        }
    }

    /**
     * This is the edit/create Person dialog box that allows the user to edit the person
     * @param primaryStage  The main stage
     * @param person        The person object to edit
     * @param relativeField True if the dialog needs user to input relative type
     */
    private void editPersonDialog(Stage primaryStage, Person person, String dialogTitle, boolean relativeField) {

        // setup the stage
        Stage editPerson = new Stage();
        editPerson.setTitle(dialogTitle);
        editPerson.initModality(Modality.APPLICATION_MODAL);    // set to block other windows
        editPerson.initOwner(primaryStage);

        // setup the main box that contains the fields
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

        // declare the relative drop down box
        ComboBox<String> relativeComboBoxInstance = GUIHelper.relativeComboBox();

        // add the relative box if needed
        if (relativeField) {

            editPersonMainBox.add(new Text("Relative :"),0, 9);
            editPersonMainBox.add(relativeComboBoxInstance, 1, 9);
        }

        // use HBox to arrange the buttons
        HBox buttonRow = new HBox(8);
        Button confirmBtn = new Button("Confirm");

        // set the confirm button action
        confirmBtn.setOnAction(evt -> {
            confirmAction(editPerson, person, relativeField, relativeComboBoxInstance.getValue(),
                    firstNameField.getText(),
                    surnameBefField.getText(),
                    surnameAftField.getText(),
                    genderField.getText(),
                    descriptionField.getText(),
                    streetNumField.getText(),
                    streetNameField.getText(),
                    suburbField.getText(),
                    postCodeField.getText());
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

    private void confirmAction(Stage editPerson, Person person, boolean relativeField, String selectedRelativeType, String firstName, String lastNameAtBirth, String lastNameUponMarriage, String gender, String desc, String streetNum, String streetName,String suburb, String postCode) {

        try {
            GUIHelper.saveEditedPerson(
                    person,
                    firstName,
                    lastNameAtBirth,
                    lastNameUponMarriage,
                    gender,
                    desc,
                    streetNum,
                    streetName,
                    suburb,
                    postCode
            );
            if (relativeField) {
                if (selectedRelativeType == null) {
                    mainAlert.setHeaderText("Must set relative!");
                    mainAlert.setContentText("Please enter the relative");
                    mainAlert.showAndWait();
                    return;
                }
                if (selectedRelativeType.equals("Parent")) rootPerson.setParents(person);
                if (selectedRelativeType.equals("Child")) rootPerson.setChildren(person);
                if (selectedRelativeType.equals("Spouse")) rootPerson.setSpouse(person);
            }
            GUIHelper.changeView(person); // change view to the newly created person
            createTree(rootPerson); // refresh view for the root person
            treeView.getRoot().setExpanded(true);   // expand the treeview
            statusText.setText(person.getFirstName() + " done!");
            editPerson.close();
        } catch (NumberFormatException e) {
            System.out.println(e);
            mainAlert.setHeaderText("Error in saving person");
            mainAlert.setContentText("The postcode must be all numbers!");
            mainAlert.showAndWait();

        } catch (Exception e) {
            System.out.println(e);
            mainAlert.setHeaderText("Error in saving person");
            mainAlert.setContentText(e.getMessage());
            mainAlert.showAndWait();
        }
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
        GUIHelper.addChildrenToTreeView(children, person);

        root.getChildren().addAll(parents, children, spouse);
        root.setExpanded(false);

        // setting the buttons
        treeView.setOnMouseClicked(this::showSelected);

    }

    /**
     * Event to show the selected person in the tree
     * @param evt   Event
     */
    private void showSelected(Event evt) {

        try {
            TreeView<Person> tree = (TreeView<Person>)evt.getSource();    // gives you the component that triggers the event
            TreeItem<Person> selectedItem = tree.getSelectionModel().getSelectedItem();
            // set the status text
            statusText.setText("Showing: " + selectedItem.getValue());
            GUIHelper.changeView(selectedItem.getValue());
            // set the selected person
            selectedPerson = selectedItem.getValue();
        } catch (NullPointerException e) {
            System.out.println("User selected null object");
        } catch (ClassCastException e) {
            System.out.println(e);
        }

    }


    /**
     * Main class
     * @param args  default args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
