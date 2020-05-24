import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.FileInputStream;

public class Main extends Application{
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "C:/Users/User/Desktop/Faculta/Sem2/FIS/Proiect/src/main/java/resources/LoginGUI.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("kouvénta");
        primaryStage.show();
    }

    public static void main(String[] args){
        launch(args);
    }
}
