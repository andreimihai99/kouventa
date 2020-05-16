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


            if(VerificareUserJSON()==1)
            {
                updateDBase();
            }
            else{
                System.out.println("Exista deja");
            }
            }


    public void updateDBase()
    {
        JSONArray db= new JSONArray();
        JSONObject obj = new JSONObject();
        JSONObject objfin = new JSONObject();
        obj.put("Full name", nameText.getText());
        obj.put("User",userText.getText());
        obj.put("Password",passText.getText());
        obj.put("Phone",phoneText.getText());
       // db.add(obj);

       JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:/Users/MDM/Desktop/kouventa-master/src/main/java/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");

                jsonArray.add(obj);

            try {
                FileWriter file = new FileWriter("C:/Users/MDM/Desktop/kouventa-master/src/main/java/resources/db.json");
                objfin.put("DataBase",jsonArray);
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


        //objfin.put("DataBase",db);
        //JSONObject objf = new JSONObject();
       // objf.put("DataBase",db);


    }

    public int VerificareUserJSON()
    {
        JSONObject obj = new JSONObject();
        JSONParser jsonParser = new JSONParser();
        try {
        JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("C:/Users/MDM/Desktop/kouventa-master/src/main/java/resources/db.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
            Iterator iterator = jsonArray.iterator();
            while(iterator.hasNext()) {
                JSONObject user= (JSONObject) iterator.next();

               if(userText.getText().equals(user.get("User")))
               {
                   System.out.println("Exista deja");
                   return 0;
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
        System.out.println("Cont Creat");
        return 1;

    }
    public RegisterTalkerController()
    {
    }

    @FXML
    private void initialize()
    {
    }


}