import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import javafx.fxml.FXML;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.Iterator;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public Button loginButton;
    public PasswordField passwordField;
    public TextField usernameField;
    public Button loginRegisterButton;
    public Button loginRegisterAdminButton;

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
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:/Users/User/Desktop/Faculta/Sem2/FIS/Proiect/src/main/java/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
            Iterator iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                JSONObject user= (JSONObject) iterator.next();

                if(usernameField.getText().equals(user.get("User")) && passwordField.getText().equals(user.get("Password")))
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

    public void clickLoginRegisterButton(ActionEvent actionEvent) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("C:/Users/User/Desktop/Faculta/Sem2/FIS/Proiect/src/main/java/resources/RegisterTalker.fxml"));
        Scene scene = new Scene(root, 250, 350);
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }

    public void clickLoginRegisterAdminButton(ActionEvent actionEvent) {

    }
}
