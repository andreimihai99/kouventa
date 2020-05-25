import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
import javafx.fxml.FXML;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Base64;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LoginController  implements Initializable  {



    public Button loginButton;
    public PasswordField passwordField;
    public TextField usernameField;
    public Button loginRegisterButton;
    public Button loginRegisterAdminButton;
    public String key = "Jar12345Jar12345";
    public String initVector = "RandomInitVector";

    public void initialize(URL location, ResourceBundle resources) {
    }

    @FXML
    private void clickLoginButton(ActionEvent event) throws IOException{
        if(verificareFormular() == 1 && checkUserJSON() == 1)
            System.out.println("Acces permis");
        else
            System.out.println("Username sau parola gresite");
    }

    public int verificareFormular(){
        if(usernameField.getText().isEmpty()) {
            return 0;
        }
        if(passwordField.getText().isEmpty()){
            return 0;
        }
        return 1;
    }

    public int checkUserJSON(){
        JSONObject obj = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
            Iterator iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                JSONObject user= (JSONObject) iterator.next();

                if(usernameField.getText().equals(user.get("User")) && encrypt(key, initVector,passwordField.getText()).equals(user.get("Password")))
                {
                    //System.out.println("Intra in aplicatie");
                    return 1;
                }
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
        //System.out.println("Username sau parola incorecte");
        return 0;
    }

    public void clickLoginRegisterButton(ActionEvent actionEvent) throws Exception {
        Stage newStage =new Stage();
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/RegisterTalker.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene

        Scene scene = new Scene(root);
        newStage.setScene(scene);

        newStage.show();

    }



    public void clickLoginRegisterAdminButton(ActionEvent actionEvent) throws Exception {
        Stage newStage =new Stage();
        Node  source = (Node)  actionEvent.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/RegisterAdmin.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene

        Scene scene = new Scene(root);
        newStage.setScene(scene);

        newStage.show();


    }

    public static String encrypt(String key, String initVector, String value)
    {
        try {
            IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
            SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

            Cipher cipher = Cipher.getInstance("AES/CBC/PKCS5PADDING");
            cipher.init(1, skeySpec, iv);

            byte[] encrypted = cipher.doFinal(value.getBytes());

            return Base64.getEncoder().encodeToString(encrypted);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }
}
