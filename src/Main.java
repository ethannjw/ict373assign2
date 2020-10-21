import javafx.application.Application;
import javafx.event.Event;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    // right side containing the file contents
    private VBox contentDesc = new VBox(8);
    private TextArea content;
    //left side containing the labels
    private VBox contentLabels = new VBox(8);
    // buttons to be set to the bottom
    private HBox rightPaneButtons = new HBox(8);
    Button editDetails;
    Button addRelative;

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
        HBox firstName = generateAttribute("First name : ", rootPerson.getFirstName());
        // surnamebef
        HBox surnameBef = generateAttribute("Lastname bef marriage : ", rootPerson.getLastnameAtBirth());
        // surnameAft
        HBox surnameAft = generateAttribute("Lastname aft marriage : ", rootPerson.getLastnameUponMarriage());
        // gender
        HBox gender = generateAttribute("Sex : ", rootPerson.getGender());
        // desc
        VBox desc = new VBox(8);
        Text descLabel = new Text("Life description : ");
        TextArea descContent = new TextArea(rootPerson.getDescription());
        descContent.autosize();
        descContent.setWrapText(true);
        desc.getChildren().addAll(descLabel, descContent);

        //Address Box
        Text addressTitle = new Text("Address info");
        addressTitle.setFont(Font.font(20));
        // streetNum
        HBox streetNum = generateAttribute("Street number : ", "" + rootPerson.getAddress().getStreetNum());
        // streetName
        HBox streetName = generateAttribute("Street name : ", rootPerson.getAddress().getStreetName());
        // suburb
        HBox suburb = generateAttribute("Suburb : ", rootPerson.getAddress().getSuburb());
        // post code
        HBox postCode = generateAttribute("Postcode : ", "" + rootPerson.getAddress().getPostCode());

        contentLabels.autosize();
        contentLabels.setMaxWidth(300);
        contentLabels.setMinWidth(175);
        contentLabels.getChildren().addAll(personTitle, firstName, surnameBef, surnameAft, gender, desc, addressTitle, streetNum, streetName, suburb, postCode);
        // Content
        content = new TextArea();
        content.autosize();
        content.setMaxWidth(150);
        content.setMinWidth(150);
        content.setMinHeight(350);
        //content.setPadding(new Insets(0,20,0,0));
        contentDesc.getChildren().add(content);
        // buttons for adding relative and editing details
        editDetails = new Button("Edit Person");
        editDetails.setOnAction(e -> {

        });
        addRelative = new Button("Add Relative");
        addRelative.setOnAction(e -> {

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

        primaryStage.setTitle("Family Tree Management System");

        primaryStage.setScene(new Scene(mainContainer));
        primaryStage.show();
    }

    private TreeView<Person> createTree(Person person) {
        TreeView<Person> famTree = new TreeView<>();
        TreeItem<Person> root = new TreeItem<Person>(person);
        famTree.setRoot(root);
        TreeItem<Person> parents = new TreeItem("Parents");
        TreeItem<Person> children = new TreeItem("Children");
        TreeItem<Person> spouse = new TreeItem("Spouse");
        // add the parents
        for (Person parent : person.getParents()) {
            parents.getChildren().add(new TreeItem<Person>(parent));
        }
        // add the children
        for (Person child : person.getChildren()) {
            children.getChildren().add(new TreeItem<Person>(child));
        }
        spouse.getChildren().add(new TreeItem<Person>(person.getSpouse()));
        root.getChildren().addAll(parents, children, spouse);

        // setting the buttons

        return famTree;
    }

    private void showSelected(Event evt) {
        TreeView<String> tree = (TreeView<String>)evt.getSource();    // gives you the component that triggers the event
        TreeItem<String> selectedItem = tree.getSelectionModel().getSelectedItem();

        System.out.println("Showing: " + selectedItem.getValue());
    }

    private HBox generateAttribute(String label, String content) {
        HBox attribute = new HBox(8);
        Text aLabel = new Text(label);
        Text aContent = new Text(content);
        attribute.setMaxHeight(8);
        aContent.autosize();
        attribute.getChildren().addAll(aLabel, aContent);
        return attribute;
    }
    public static void main(String[] args) {
        launch(args);
    }
}
