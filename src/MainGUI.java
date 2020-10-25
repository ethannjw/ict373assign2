import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.File;
import java.io.IOException;

public class MainGUI implements GUIHelper {
    // main container
    BorderPane mainContainer = new BorderPane();

    RootObservable root = RootObservable.getInstance();

    Stage primaryStage;

    public MainGUI(Stage primaryStage) {
        this.primaryStage = primaryStage;

        // Create the treeView on the right side
        GUIHelper.createTree(root.getRootPerson());

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
            GUIHelper.createTree(root.getRootPerson());
        });
        saveBtn.setText("Save");
        saveBtn.setOnAction(evt -> {
            saveFileChooser(primaryStage);
        });
        createBtn.setText("Create");
        createBtn.setOnAction(evt -> {
            statusText.setText("Creating New Person");
            Person newPerson = new Person();
            root.setRootPerson(newPerson);
            EditGUI editGUI = new EditGUI(primaryStage, newPerson, "Create Person", false);

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
            EditGUI editGUI = new EditGUI(primaryStage, root.getSelectedPerson(), "Edit "+root.getSelectedPerson(), false);
        });
        // add new person to the root person
        addRelative.setOnAction(evt -> {
            EditGUI editGUI = new EditGUI(primaryStage, new Person(), "Add Relative to " + root.getRootPerson(), true);
        });
        // set the root person
        setRoot.setOnAction(evt -> {
            root.updateRootToSelected();
            GUIHelper.createTree(root.getRootPerson());
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

    }

    public BorderPane getMainContainer() {
        return mainContainer;
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
            root.setRootPerson(FileHandler.load(userSelectedFile));

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

        if (root.getRootPerson() == null) {
            statusText.setText("Nothing to save!");
            return;
        }
        File userSelectedFile = fileChooser.showSaveDialog(primaryStage);
        try {
            FileHandler.save(userSelectedFile, root.getRootPerson());
            statusText.setText("File saved!");
        } catch (IOException e) {
            System.out.println(e);
            statusText.setText("Something Went Wrong when trying to save the file!");
            mainAlert.setHeaderText("Problem saving file");
            mainAlert.setContentText(e.getLocalizedMessage());
            mainAlert.showAndWait();
        } catch (NullPointerException e) {
            System.out.println("User did not select a place to save file");
        }
    }
//    /**
//     * Generate the tree from the specified person
//     * @param person    Specified person to create the tree details
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" }) // suppress the title nodes using rawtypes since they are only title nodes
//    public void createTree(Person person) {
//
//        TreeItem<Person> root = new TreeItem<Person>(person);
//        treeView.setRoot(root);
//
//        // setting the title nodes
//        TreeItem<Person> parents = new TreeItem("Parents");
//        TreeItem<Person> children = new TreeItem("Children");
//        TreeItem<Person> spouse = new TreeItem("Spouse");
//
//        // add the parents
//        for (Person parent : person.getParents()) {
//            parents.getChildren().add(new TreeItem<Person>(parent));
//        }
//        // add the spouse
//        spouse.getChildren().add(new TreeItem<Person>(person.getSpouse()));
//        // add the children
//        GUIHelper.addChildrenToTreeView(children, person);
//
//        root.getChildren().addAll(parents, children, spouse);
//        root.setExpanded(false);
//
//        // setting the buttons
//        treeView.setOnMouseClicked(evt -> showSelected(evt));
//
//    }
//
//    /**
//     * Event to show the selected person in the tree
//     * @param evt   Event
//     */
//    private void showSelected(Event evt) {
//
//        try {
//            TreeView<Person> tree = (TreeView<Person>)evt.getSource();    // gives you the component that triggers the event
//            TreeItem<Person> selectedItem = tree.getSelectionModel().getSelectedItem();
//            // set the status text
//            statusText.setText("Showing: " + selectedItem.getValue());
//            changeView(selectedItem.getValue());
//            // set the selected person
//            root.setSelectedPerson(selectedItem.getValue());
//        } catch (NullPointerException e) {
//            System.out.println("User selected null object");
//        } catch (ClassCastException e) {
//            System.out.println(e);
//        }
//
//    }
//
//    /**
//     * Changes the view in the right pane using the instance of the specified person
//     * @param newPerson Person object to change right pane view to
//     */
//    private void changeView(Person newPerson) {
//        if ((firstName.getChildren().get(1)) instanceof Text) {
//            ((Text)firstName.getChildren().get(1)).setText(newPerson.getFirstName());
//        } else {System.out.println("Cannot Change Firstname");}
//        if ((surnameBef.getChildren().get(1)) instanceof Text) {
//            ((Text)surnameBef.getChildren().get(1)).setText(newPerson.getLastnameAtBirth());
//        } else {System.out.println("Cannot Change surnameBef");}
//        if ((surnameAft.getChildren().get(1)) instanceof Text) {
//            ((Text)surnameAft.getChildren().get(1)).setText(newPerson.getLastnameUponMarriage());
//        } else {System.out.println("Cannot Change surnameAft");}
//        if ((gender.getChildren().get(1)) instanceof Text) {
//            ((Text)gender.getChildren().get(1)).setText(newPerson.getGender());
//        } else {System.out.println("Cannot Change gender");}
//
//        descContent.setText(newPerson.getDescription());
//
//        if ((streetNum.getChildren().get(1)) instanceof Text) {
//            ((Text)streetNum.getChildren().get(1)).setText("" + newPerson.getAddress().getStreetNum());
//        } else {System.out.println("Cannot Change street num");}
//        if ((streetName.getChildren().get(1)) instanceof Text) {
//            ((Text)streetName.getChildren().get(1)).setText("" + newPerson.getAddress().getStreetName());
//        } else {System.out.println("Cannot Change street name");}
//        if ((suburb.getChildren().get(1)) instanceof Text) {
//            ((Text)suburb.getChildren().get(1)).setText("" + newPerson.getAddress().getSuburb());
//        } else {System.out.println("Cannot Change suburb");}
//        if ((postCode.getChildren().get(1)) instanceof Text) {
//            ((Text)postCode.getChildren().get(1)).setText("" + newPerson.getAddress().getPostCode());
//        } else {System.out.println("Cannot Change postCode");}
//    }
}
