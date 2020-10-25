
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    /**
     * Start method for the GUI
     * @param primaryStage  Primary Stage
     * @throws Exception    Default Exception
     */
    @Override
    public void start(Stage primaryStage) throws Exception {
        MainGUI mainGUI = new MainGUI(primaryStage);
        primaryStage.setTitle("Family Tree Management System");
        primaryStage.setScene(new Scene(mainGUI.getMainContainer()));
        primaryStage.show();
    }

    /**
     * Main class
     * @param args  default args
     */
    public static void main(String[] args) {
        launch(args);
    }
}
