
import java.io.*;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Iterator;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
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
public class Admin extends Application {

    @FXML
    private TextField category;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/Admin.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    @FXML
    private void clickManageCat(ActionEvent e) throws IOException
    {
        Stage newStage =new Stage();
        Node  source = (Node)  e.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/ManageCategories.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(new FileInputStream(fxmlDocPath));

        // Create the Scene

        Scene scene = new Scene(root);
        newStage.setScene(scene);

        newStage.show();
    }
    @FXML
    private void clickAdd(ActionEvent event) throws IOException {

        JSONObject obj = new JSONObject();
        JSONObject objfin = new JSONObject();

        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            jsonArray.add(category.getText());

            try {
                FileWriter file = new FileWriter("src/main/resources/Categories.json");
                objfin.put("Category",jsonArray);
                file.write(objfin.toJSONString());
                file.close();

            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }


        } catch (FileNotFoundException e)  {
            e.printStackTrace();
        } catch (IOException e)
        {
            e.printStackTrace();
        } catch (ParseException e)
        {
            e.printStackTrace();
        }
    }
    @FXML
    private void clickDelete(ActionEvent e) throws IOException {


    }
    @FXML
    private void clickChange(ActionEvent e) throws IOException {
    }
}
