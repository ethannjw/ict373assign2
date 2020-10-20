import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Main extends Application {
    private HBox statusBar = new HBox();
    private Text statusText = new Text("Welcome to Family Tree management System");

    private BorderPane mainContainer = new BorderPane();

    // The left panes
    private TreeView<Person> treeView = new TreeView<Person>();
    private TreeItem<Person> root;

    // the top pane
    private VBox topPane = new VBox();
    private Button loadBtn;
    private Button saveBtn;
    private Button createBtn;

    // the left pane containing the treeView
    private BorderPane treeViewBox = new BorderPane();
    private VBox treeViewVBox = new VBox();
    @Override
    public void start(Stage primaryStage) throws Exception {

        // Define the grid
        GridPane gridPane = new GridPane();
        gridPane.setMinSize(500, 700);
        gridPane.setPadding(new Insets(3,3,3,3));
        gridPane.setVgap(3);
        gridPane.setHgap(3);
        gridPane.setAlignment(Pos.CENTER);

        statusBar.getChildren().add(statusText);
        statusBar.setMinHeight(10);
        mainContainer.setBottom(statusBar);

        Text title = new Text("Family Tree Management System");
        HBox hBoxTopButtons = new HBox();
        topPane.getChildren().add(title);
        topPane.getChildren().add(hBoxTopButtons);


        // main container setttings
        mainContainer.setTop(topPane);
        gridPane.getChildren().add(mainContainer);

        primaryStage.setTitle("Family Tree Management System");

        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
