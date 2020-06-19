import com.sun.deploy.security.SelectableSecurityManager;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.util.Base64;
import java.util.Iterator;

public class ManageTalkersController extends Application {
    @FXML
    private TextField userInput;

    @FXML
    private Button blockUserButton;

    @FXML
    private Button unblockUserButton;

    public void start(Stage primaryStage) throws Exception {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/ManagesTalkers.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    public static void main(String[] args){
        launch(args);
    }

    @FXML
    void blockUser(ActionEvent event) {
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currentUser = (JSONObject) iterator.next();
                if (currentUser.get("User").equals(userInput.getText())) {
                    if(currentUser.get("Status").equals("Blocked")) {
                        System.out.println("E blocat deja");
                        return;
                    }
                    currentUser.replace("Status", "Blocked");
                    break;
                }
            }
            JSONObject aux = new JSONObject();
            aux.put("DataBase", jsonArray);
            try {
                FileWriter file = new FileWriter("src/main/resources/db.json");
                file.write(aux.toJSONString());
                file.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @FXML
    void unblockUser(ActionEvent event) {
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                JSONObject currentUser = (JSONObject) iterator.next();
                if (currentUser.get("User").equals(userInput.getText())) {
                    if(currentUser.get("Status").equals("Unblocked")) {
                        System.out.println("E deblocat deja");
                        return;
                    }
                    currentUser.replace("Status", "Unblocked");
                    break;
                }
            }
            JSONObject aux = new JSONObject();
            aux.put("DataBase", jsonArray);
            try {
                FileWriter file = new FileWriter("src/main/resources/db.json");
                file.write(aux.toJSONString());
                file.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
