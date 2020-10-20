import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.layout.HBox;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.layout.GridPane;

public class Main extends Application {
    private HBox statusBar = new HBox();
    private Text statusText = new Text("Welcome to Family Tree management System");

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
        gridPane.getChildren().add(statusBar);


        primaryStage.setTitle("Family Tree Management System");

        primaryStage.setScene(new Scene(gridPane));
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
