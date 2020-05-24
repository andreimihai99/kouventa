

    import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
import java.security.Security;
import javax.crypto.Cipher;
import javax.crypto.spec.SecretKeySpec;

import java.util.Base64;
import java.util.Iterator;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.crypto.Cipher;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;

    public class RegisterAdminController
    {
        @FXML
        private TextField nameText;
        @FXML
        private TextField comfText;
        @FXML
        private TextField phoneText;
        @FXML
        private TextField passText;
        @FXML
        private TextField userText;
        public String key = "Jar12345Jar12345";
        public String initVector = "RandomInitVector";

        @FXML
        private void clickRegisterAdmin(ActionEvent event) throws IOException {




            if (VerificareFormularCompletatCorect() == 1) {
                if (VerificareDBGoala() == 1) {
                    if (VerificareUserJSON() == 1 && VerificareKey()==1) {
                        updateDBase();
                    } else {
                        System.out.println("Exista deja");
                    }
                } else {
                    updateDBase();
                }
            }
            else
            {
                System.out.println("Va rugam complatati corect formularul");
            }
        }


        public int VerificareFormularCompletatCorect(){
            if(comfText.getText().isEmpty())
            {
                return 0;
            }
            if(nameText.getText().isEmpty())
            {
                return 0;
            }
            if(userText.getText().isEmpty() || userText.getLength()<5)
            {
                return 0;
            }
            if(phoneText.getText().isEmpty()||phoneText.getLength()!=10)
            {
                return 0;
            }
            if(passText.getText().isEmpty() || passText.getLength()<8)
            {
                return 0;
            }
            return 1;

        }
        public int VerificareKey()
        {
            JSONParser jsonParser = new JSONParser();
            try {

                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/keys.json"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("key");
                Iterator iterator = jsonArray.iterator();
                while(iterator.hasNext()) {
                    if(iterator.next().equals(comfText.getText())){
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
            return 0;

        }

        public int VerificareDBGoala()
        {
            JSONParser jsonParser = new JSONParser();
            try {

                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
                if(jsonArray.isEmpty()){
                    return 0;
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
            return 1;
        }


        public void updateDBase()
        {
            JSONArray db= new JSONArray();
            JSONObject obj = new JSONObject();
            JSONObject objfin = new JSONObject();
            obj.put("Rol", "Admin");
            obj.put("Full name", nameText.getText());
            obj.put("User",userText.getText());
            obj.put("Password",encrypt(key,initVector,passText.getText()));
            obj.put("Phone",phoneText.getText());
            // db.add(obj);

            JSONParser jsonParser = new JSONParser();
            try {

                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");

                jsonArray.add(obj);

                try {
                    FileWriter file = new FileWriter("src/main/resources/db.json");
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
                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/db.json"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("DataBase");
                Iterator iterator = jsonArray.iterator();
                while(iterator.hasNext()) {
                    JSONObject user= (JSONObject) iterator.next();
                    if(userText.getText().equals(user.get("User")))
                    {
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

        public static String decrypt(String key, String initVector, String encrypted) {
            try {
                IvParameterSpec iv = new IvParameterSpec(initVector.getBytes("UTF-8"));
                SecretKeySpec skeySpec = new SecretKeySpec(key.getBytes("UTF-8"), "AES");

                Cipher cipher = javax.crypto.Cipher.getInstance("AES/CBC/PKCS5PADDING");
                cipher.init(2, skeySpec, iv);

                byte[] original = cipher.doFinal(Base64.getDecoder().decode(encrypted));

                return new String(original);
            } catch (Exception ex) {
                Alert a = new Alert(Alert.AlertType.ERROR, "Input length must be multiple of 16",
                        ButtonType.CANCEL);
                a.show();
            }

            return null;
        }

        public RegisterAdminController()
        {
        }

        @FXML
        private void initialize()
        {
        }


    }

