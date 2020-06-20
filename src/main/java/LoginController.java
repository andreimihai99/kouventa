import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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

import static javafx.scene.text.TextAlignment.CENTER;
import static javax.swing.text.StyleConstants.Bold;

public class LoginController extends Main implements Initializable {



    public Button loginButton;
    public PasswordField passwordField;
    public TextField usernameField;
    public Button loginRegisterButton;
    public Button loginRegisterAdminButton;
    public String key = "Jar12345Jar12345";
    public String initVector = "RandomInitVector";

    public void initialize(URL location, ResourceBundle resources) {
    }
    void opening(String s,ActionEvent ev) throws IOException {
        Stage newStage =new Stage();
        Node  source = (Node)  ev.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/"+s+".fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene

        Scene scene = new Scene(root);
        newStage.setScene(scene);

        newStage.show();
    }

    @FXML
    private void clickLoginButton(ActionEvent event) throws IOException{
        if(verificareFormular() == 1 && checkUserJSON() == 1) {
            user = usernameField.getText();
            opening("TalkerGUI",event);
        }
        if(verificareFormular() == 1 && checkUserJSON() == 2) {

            System.out.println("block");
        }
        if(verificareFormular() == 1 && checkUserJSON() == 3) {
            user = usernameField.getText();
            opening("Admin",event);
        }

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

    public void errorWindow(String s,ActionEvent ev){
         final Stage newStage =new Stage();
        Label text=new Label(s);

        text.setFont(Font.font(13));
        text.setPrefSize(250,30);
        text.setLayoutY(15);
        text.setAlignment(Pos.CENTER);
        text.setTextAlignment(CENTER);
        Button b=new Button();

        b.setText("Close");
        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
            public void handle(ActionEvent eve) {
               newStage.close();
            }
        };
        b.setOnAction(click);
        b.setLayoutX(103);
        b.setLayoutY(61);
        Pane root=new Pane();
        root.setStyle("-fx-background-color: #2EA1BD;");
        root.setPrefHeight(100);
        root.setPrefWidth(250);

        root.getChildren().addAll(b);
        ObservableList list = root.getChildren();
        list.add(text);
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        newStage.show();
        return;
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

                if(usernameField.getText().equals(user.get("User")) && encrypt(key, initVector,passwordField.getText()).equals(user.get("Password"))&&user.get("Rol").equals("Talker")&&user.get("Status").equals("Unblocked"))
                {

                    return 1;
                }
                if(usernameField.getText().equals(user.get("User")) && encrypt(key, initVector,passwordField.getText()).equals(user.get("Password"))&&user.get("Rol").equals("Talker")&&user.get("Status").equals("Blocked"))
                {

                    return 2;
                }
                if(usernameField.getText().equals(user.get("User")) && encrypt(key, initVector,passwordField.getText()).equals(user.get("Password"))&&user.get("Rol").equals("Admin")&&user.get("Status").equals("Unblocked"))
                {

                    return 3;
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
        opening("RegisterTalker",actionEvent);

    }



    public void clickLoginRegisterAdminButton(ActionEvent actionEvent) throws Exception {
        opening("RegisterAdmin",actionEvent);


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
