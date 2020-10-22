import javafx.application.Application;
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
import javafx.stage.Modality;
import javafx.stage.Stage;

public class Main extends Application {
    // main container
    private BorderPane mainContainer = new BorderPane();

    // status bar at the bottom
    private HBox statusBar = new HBox();
    private Text statusText = new Text("Program loaded");


    // The left pane containing the treeview
    private TreeView<Person> treeView = new TreeView<Person>();
    private Person rootPerson = null;

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

    /**
     * Start method for the GUI
     * @param primaryStage  Primary Stage
     * @throws Exception    Default Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        rootPerson = CreatePerson.createKirito();
        // buttons settings
        loadBtn.setText("Load");
        saveBtn.setText("Save");
        createBtn.setText("Create");

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
        editDetails = new Button("Edit Person");
        editDetails.setOnAction(evt -> {
            editPersonDialog(primaryStage, rootPerson);
        });
        addRelative = new Button("Add Relative");
        addRelative.setOnAction(evt -> {

        });
        rightPaneButtons.getChildren().addAll(editDetails, addRelative);
        rightPaneButtons.setAlignment(Pos.BOTTOM_RIGHT);
        rightPane.setPadding(new Insets(0,10,0,10));
        rightPane.setLeft(contentLabels);
        //rightPane.setCenter(contentDesc);
        rightPane.setBottom(rightPaneButtons);

        // Create the treeView
        treeView = createTree(rootPerson);

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

    private void editPersonDialog(Stage primaryStage, Person person) {

        Stage editPerson = new Stage();
        editPerson.setTitle("Edit Person");
        editPerson.initModality(Modality.APPLICATION_MODAL);    // set to block other windows
        editPerson.initOwner(primaryStage);
        GridPane editPersonMainBox = new GridPane();
        editPersonMainBox.setBorder(new Border(new BorderStroke(Color.valueOf("#9E9E9E"), BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.DEFAULT)));
        editPersonMainBox.setPadding(new Insets(3,3,3,3));

        // create the title
        Text editPersonTitle = new Text("Edit Person");
        editPersonTitle.setFont(Font.font("Times New Roman", 30));

        // Create the labels on the left side
        Text firstNameLabel = new Text("Firstname : ");
        editPersonMainBox.add(firstNameLabel, 0,0);
        Text surnameBefLabel = new Text("Lastname bef Marriage :");
        editPersonMainBox.add(surnameBefLabel, 0,1);
        Text surnameAftLabel = new Text("Lastname aft Marriage :");
        editPersonMainBox.add(surnameAftLabel, 0,2);
        Text genderLabel = new Text("Sex :");
        editPersonMainBox.add(genderLabel, 0,3);
        Text descriptionLabel = new Text("Description :");
        editPersonMainBox.add(descriptionLabel, 0,4);
        Text streetNumLabel = new Text("Street Num :");
        editPersonMainBox.add(streetNumLabel, 0,5);
        Text streetNameLabel = new Text("Street Name :");
        editPersonMainBox.add(streetNameLabel, 0,6);
        Text suburbLabel = new Text("Suburb :");
        editPersonMainBox.add(suburbLabel, 0,7);
        Text postCodeLabel = new Text("Postcode :");
        editPersonMainBox.add(postCodeLabel, 0,8);

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



        // use HBox to identify each attribute
        HBox buttonRow = new HBox(8);
        Button confirmBtn = new Button("Confirm");
        Button cancelBtn = new Button("Cancel");
        cancelBtn.setOnAction(evt -> {
            editPerson.close();
        });
        buttonRow.getChildren().addAll(cancelBtn, confirmBtn);
        buttonRow.setAlignment(Pos.BOTTOM_RIGHT);


        BorderPane mainEditPersonContainer = new BorderPane(editPersonMainBox);
        mainEditPersonContainer.setTop(editPersonTitle);
        mainEditPersonContainer.setBottom(buttonRow);
        mainEditPersonContainer.setPadding(new Insets(3,3,3,3));
        Scene dialogScene = new Scene(mainEditPersonContainer, 400, 600);
        editPerson.setScene(dialogScene);
        editPerson.show();
    }

    @SuppressWarnings({ "unchecked", "rawtypes" }) // suppress the title nodes using rawtypes since they are only title nodes
    private TreeView<Person> createTree(Person person) {
        TreeView<Person> famTree = new TreeView<>();
        TreeItem<Person> root = new TreeItem<Person>(person);
        famTree.setRoot(root);

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
        root.setExpanded(true);

        // setting the buttons
        famTree.setOnMouseClicked(evt -> showSelected(evt));
        return famTree;
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
