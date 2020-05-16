import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Iterator;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class RegisterTalkerController
{
    @FXML
    private TextField nameText;
    @FXML
    private TextField phoneText;
    @FXML
    private TextField passText;
    @FXML
    private TextField userText;


    @FXML
    private void clickRegisterTalker(ActionEvent event) throws IOException {
        /*JSONParser parser = new JSONParser();
        try {
            Object pars = parser.parse(new FileReader("C:/Users/MDM/Desktop/kouventa-master/src/main/java/resources/db.json"));

            // A JSON object. Key value pairs are unordered. JSONObject supports java.util.Map interface.
            JSONObject jsonObject = (JSONObject) pars;

            // A JSON array. JSONObject supports java.util.List interface.

            JSONArray dbase = (JSONArray) jsonObject.get("DataBase");
            */

             VerificareUserJSON();
            }
            // An iterator over a collection. Iterator takes the place of Enumeration in the Java Collections Framework.
            // Iterators differ from enumerations in two ways:
            // 1. Iterators allow the caller to remove elements from the underlying collection during the iteration with well-defined semantics.
            // 2. Method names have been improved.
          /*Iterator<JSONObject> iterator =dbase.iterator();
            while (iterator.hasNext()) {
                System.out.println(iterator.next());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }*/

    public void updateDBase()
    {
        JSONArray db= new JSONArray();
        JSONObject obj = new JSONObject();
        JSONObject objfin = new JSONObject();
        obj.put("Full name", nameText.getText());
        obj.put("User",userText.getText());
        obj.put("Password",passText.getText());
        obj.put("Phone",phoneText.getText());
        db.add(obj);
        objfin.put("DataBase",db);

        //objfin.put("DataBase",db);
        //JSONObject objf = new JSONObject();
       // objf.put("DataBase",db);

        try {
            FileWriter file = new FileWriter("C:/Users/MDM/Desktop/kouventa-master/src/main/java/resources/db.json");
            file.write(objfin.toJSONString());
            file.close();

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public void VerificareUserJSON()
    {
        JSONObject obj = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:/Users/MDM/Desktop/kouventa-master/src/main/java/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
            Iterator iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                JSONObject user= (JSONObject) iterator.next();
               if(user.get("User").equals(userText.getText()))
               {
                   System.out.println("Exista deja");
                   return;
               }
               else
               {
                   System.out.println("Cont Creat");
                   return;
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
    }
    public RegisterTalkerController()
    {
    }

    @FXML
    private void initialize()
    {
    }


}