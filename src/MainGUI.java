import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
    // create the root observable instance for easy access
    RootObservable root = RootObservable.getInstance();

    /**
     * Constructor that sets up the main window
     * @param primaryStage  The main stage
     */
    public MainGUI(Stage primaryStage) {

        // Create the treeView on the right side
        GUIHelper.createTree(root.getRootPerson());

        // set the main window to check if the file is saved when closing
        primaryStage.setOnCloseRequest(this::closeDialog);

        // bottom status bar settings
        statusBar.getChildren().add(statusText);
        statusBar.setMinHeight(10);

        // Defining the title
        Text title = new Text("Family Tree Management System");
        title.setFont(Font.font("Times New Roman", 30));
        topPane.getChildren().add(title);

        // specify the load button
        loadBtn.setText("Load");
        loadBtn.setOnAction(evt -> {
            openFileChooser(primaryStage);
            GUIHelper.createTree(root.getRootPerson());
        });

        // specify the save button
        saveBtn.setText("Save");
        saveBtn.setOnAction(evt -> {
            saveFileChooser(primaryStage);
        });

        // Specify the create button
        createBtn.setText("Create");
        createBtn.setOnAction(evt -> {
            createNewPerson(primaryStage);
        });

        // specify the top row
        hBoxTopButtons.getChildren().add(loadBtn);
        hBoxTopButtons.getChildren().add(saveBtn);
        hBoxTopButtons.getChildren().add(createBtn);
        topPane.getChildren().add(hBoxTopButtons);

        // Defining the right part of the GUI
        Text personTitle = new Text("Person Details");
        personTitle.setFont(Font.font(20));

        // specify the life description box
        descContent.autosize();
        descContent.setWrapText(true);
        descContent.setEditable(false);
        desc.getChildren().addAll(descLabel, descContent);

        // Setting the address title
        addressTitle.setFont(Font.font(20));

        // setting the labels specification for the right pane
        contentLabels.autosize();
        contentLabels.setMaxWidth(400);
        contentLabels.setMinWidth(175);
        contentLabels.getChildren().addAll(personTitle, firstName, surnameBef, surnameAft, gender, desc, addressTitle, streetNum, streetName, suburb, postCode);

        // buttons for adding relative and editing details
        // edit the selected person
        editDetails.setOnAction(evt -> {
            new EditGUI(primaryStage, root.getSelectedPerson(), "Edit "+root.getSelectedPerson(), false);
        });
        // add new person to the root person
        addRelative.setOnAction(evt -> {
            new EditGUI(primaryStage, new Person(), "Add Relative to " + root.getRootPerson(), true);
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

    /**
     * Gets the main window to the caller
     * @return  BorderPane main window
     */
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
            root.loadRootPerson(FileHandler.load(userSelectedFile));

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
            root.setChanged(false);     // update that the file has been saved
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

    /**
     * Sets up the create new person dialog
     * @param primaryStage  The main stage
     */
    private void createNewPerson(Stage primaryStage) {
        statusText.setText("Creating New Person");
        Person newPerson = new Person();
        root.setRootPerson(newPerson);
        new EditGUI(primaryStage, newPerson, "Create Person", false);
    }

    /**
     * Sets up the confirmation close dialog
     * @param evt   Close event
     */
    private void closeDialog(Event evt) {
        if (root.getChanged()) {    // will display confirmation box only when user has not saved file
            Alert closeAlert = new Alert(Alert.AlertType.CONFIRMATION, "File not saved. Do you really want to quit?", ButtonType.YES, ButtonType.NO);
            ButtonType answer = closeAlert.showAndWait().orElse(ButtonType.NO);

            if (ButtonType.NO.equals(answer)) {
                evt.consume();  // so that the program do not close if user clicks no
            }
        }
    }
}
