import javafx.collections.FXCollections;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class EditGUI implements GUIHelper {
    Stage editPersonStage = new Stage();
    GridPane editPersonMainBox = new GridPane();
    Text editPersonTitle = new Text("Edit Person");
    RootObservable root = RootObservable.getInstance();

//    /**
//     * Sets up the stage parameter
//     * @param primaryStage
//     * @param dialogTitle
//     */
//    public void EditGUI(Stage primaryStage, String dialogTitle) {
//        editPersonStage.setTitle(dialogTitle);
//        editPersonStage.initModality(Modality.APPLICATION_MODAL);    // set to block other windows
//        editPersonStage.initOwner(primaryStage);
//    }

    /**
     * This is the edit/create Person dialog box that allows the user to edit the person
     * @param primaryStage  The main stage
     * @param person        The person object to edit
     * @param relativeField True if the dialog needs user to input relative type
     */
    public EditGUI(Stage primaryStage, Person person, String dialogTitle, boolean relativeField) {

        // setup the stage
        editPersonStage.setTitle(dialogTitle);
        editPersonStage.initModality(Modality.APPLICATION_MODAL);    // set to block other windows
        editPersonStage.initOwner(primaryStage);

        // setup the main box that contains the fields
        editPersonMainBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        editPersonMainBox.setPadding(new Insets(3,3,3,3));

        // create the title
        editPersonTitle.setText(dialogTitle);
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
        ComboBox<String> relativeComboBoxInstance = relativeComboBox();

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
            confirmAction(editPersonStage, person, relativeField, relativeComboBoxInstance.getValue(),
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
            editPersonStage.close();
        });
        buttonRow.getChildren().addAll(cancelBtn, confirmBtn);
        buttonRow.setAlignment(Pos.BOTTOM_RIGHT);

        // Setting the stage
        BorderPane mainEditPersonContainer = new BorderPane(editPersonMainBox);
        mainEditPersonContainer.setTop(editPersonTitle);
        mainEditPersonContainer.setBottom(buttonRow);
        mainEditPersonContainer.setPadding(new Insets(3,3,3,3));
        Scene dialogScene = new Scene(mainEditPersonContainer, 400, 600);
        editPersonStage.setScene(dialogScene);
        editPersonStage.show();
    }


    /**
     * Method to define the action to be taken to save the edited attributes into the person instance
     * @param editPerson
     * @param person
     * @param relativeField
     * @param selectedRelativeType
     * @param firstName
     * @param lastNameAtBirth
     * @param lastNameUponMarriage
     * @param gender
     * @param desc
     * @param streetNum
     * @param streetName
     * @param suburb
     * @param postCode
     */
    private void confirmAction(Stage editPerson, Person person, boolean relativeField, String selectedRelativeType, String firstName, String lastNameAtBirth, String lastNameUponMarriage, String gender, String desc, String streetNum, String streetName, String suburb, String postCode) {

        try {
            if (relativeField) {
                if (selectedRelativeType == null) {
                    mainAlert.setHeaderText("Must set relative!");
                    mainAlert.setContentText("Please enter the relative");
                    mainAlert.showAndWait();
                    return;
                }
                if (selectedRelativeType.equals("Parent")) root.getRootPerson().setParents(person);
                if (selectedRelativeType.equals("Child")) root.getRootPerson().setChildren(person);
                if (selectedRelativeType.equals("Spouse")) root.getRootPerson().setSpouse(person);
            }
            System.out.println("Editing: "+person);
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
            System.out.println("Edited: "+person);
            statusText.setText(person.getFirstName() + " done!");
            GUIHelper.createTree(root.getRootPerson());
            treeView.getRoot().setExpanded(true);   // expand the treeview
            GUIHelper.changeView(person);
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
     * Method to create the relative dropdown box containing "Parent", "Child", "Spouse"
     * @return  The dropdown box component
     */
    static ComboBox<String> relativeComboBox() {

        String[] relativeTypes = {"Parent", "Child", "Spouse"};
        return new ComboBox<>(FXCollections.observableArrayList(relativeTypes));

    }
}
