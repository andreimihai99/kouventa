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


public class ManageTalkersControllerTest extends ApplicationTest {
    private ManageTalkersController controller;

    @Before
    public void setUp() throws Exception {

        controller = new ManageTalkersController();
        controller.userInput=new TextField();


    }
    @After
    public void tearUp() throws Exception {

        controller.userInput=new TextField();



    }
    @Test
    public void checkUserTest() throws IOException, ParseException {
        controller.checkUser();
        controller.userInput.setText("user1123");

        assertEquals(0,controller.checkUser());
    }
    @Test
    public void checkUserTest2() throws IOException, ParseException {
        controller.checkUser();
        controller.userInput.setText("user1234");
        assertEquals(1,controller.checkUser());
    }

}
