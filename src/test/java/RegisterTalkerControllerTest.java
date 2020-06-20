import javafx.event.ActionEvent;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.apache.commons.io.FileUtils;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.junit.After;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import org.testfx.framework.junit.ApplicationTest;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import static org.junit.Assert.assertEquals;

public class RegisterTalkerControllerTest extends ApplicationTest {
    private RegisterTalkerController controller;

    @Before
    public void setUp() throws Exception {

        controller = new RegisterTalkerController();
        controller.phoneText = new TextField();
        controller.passText = new PasswordField();
        controller.nameText = new TextField();
        controller.userText = new TextField();


    }
    @After
    public void tearUp() throws Exception {

        controller = new RegisterTalkerController();
        controller.phoneText = new TextField();
        controller.passText = new PasswordField();
        controller.nameText = new TextField();
        controller.userText = new TextField();

    }
    @Test
    public void testVerificareFormularCompletatCorect() throws IOException {
        controller.VerificareFormularCompletatCorect();
        controller.nameText.setText("");
        controller.userText.setText("");
        controller.phoneText.setText("");
        controller.passText.setText("");
        assertEquals(0,controller.VerificareFormularCompletatCorect());



    }
    @Test
    public void testVerificareFormularCompletatCorect2() throws IOException {
        controller.VerificareFormularCompletatCorect();
        controller.nameText.setText("ana");
        controller.userText.setText("user");
        controller.phoneText.setText("1234567890");
        controller.passText.setText("parola123");

        assertEquals(0,controller.VerificareFormularCompletatCorect());



    }
    @Test
    public void testVerificareFormularCompletatCorect3() throws IOException {
        controller.VerificareFormularCompletatCorect();
        controller.nameText.setText("ana");
        controller.userText.setText("user12");
        controller.phoneText.setText("1234567890");
        controller.passText.setText("parola123");
        assertEquals(1,controller.VerificareFormularCompletatCorect());



    }



    @Test
    public void testDbGoala2() throws IOException, ParseException {
        controller.VerificareDBGoala();


        assertEquals(1,controller.VerificareDBGoala());

    }
    @Test
    public void testCheckUser() throws IOException, ParseException {
        controller.VerificareUserJSON();
        controller.userText.setText("user123");
        assertEquals(0,controller.VerificareUserJSON());

    }
    @Test
    public void testCheckUser2() throws IOException, ParseException {
        controller.VerificareUserJSON();
        controller.userText.setText("user123879");
        assertEquals(1,controller.VerificareUserJSON());

    }

}
