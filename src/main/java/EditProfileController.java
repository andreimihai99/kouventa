import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Base64;


public class EditProfileController {

    @FXML
    private TextField newNameField;

    @FXML
    private TextField newPhoneField;

    @FXML
    private PasswordField newPasswordField;

    @FXML
    private Button saveButton;
    public String key = "Jar12345Jar12345";
    public String initVector = "RandomInitVector";

    @FXML
    public void clickSave(ActionEvent event) {
        if(verificareFormularCorect() == 1)

    }

    public int verificareFormularCorect(){
        if(newNameField.getText().isEmpty() || newNameField.getLength()<5)
        {
            return 0;
        }
        if(newPhoneField.getText().isEmpty() || newPhoneField.getLength()!=10)
        {
            return 0;
        }
        if(newPasswordField.getText().isEmpty() || newPasswordField.getLength()<8)
        {
            return 0;
        }
        return 1;
    }

    public void updateDatabase(){
        JSONObject currentUser = new JSONObject();
        JSONParser jsonParser = new JSONParser();

        try {
            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
            currentUser.replace("Full name", newNameField.getText());
            currentUser.replace("Password", encrypt(key, initVector, newPasswordField.getText()));
            currentUser.replace("Phone", newPhoneField.getText());
        }
        catch (ParseException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } {

        }
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
