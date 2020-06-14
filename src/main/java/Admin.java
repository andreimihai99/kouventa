
import java.io.*;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TextField;

import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import static java.util.Collections.sort;

public class Admin extends Application {

    @FXML
    private TextField category;
    @FXML
    private TextField changeInTxt;
    static String change=new String();

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

            if (jsonArray.contains(category.getText())) {
                System.out.println("Deja exista categoria");
            } else {
                jsonArray.add(category.getText());
                sort(jsonArray);

                try {
                    FileWriter file = new FileWriter("src/main/resources/Categories.json");
                    objfin.put("Category", jsonArray);
                    file.write(objfin.toJSONString());
                    file.close();

                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
            } catch(FileNotFoundException e){
                e.printStackTrace();
            } catch(IOException e)
            {
                e.printStackTrace();
            } catch(ParseException e)
            {
                e.printStackTrace();
            }

    }
    @FXML
    private void clickDelete(ActionEvent event) throws IOException {
        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            if(jsonArray.contains(category.getText()))
            {
                jsonArray.remove(category.getText());

                JSONObject objfin = new JSONObject();
                FileWriter file = new FileWriter("src/main/resources/Categories.json");
                objfin.put("Category",jsonArray);
                file.write(objfin.toJSONString());
                file.close();

            }
            else
            {

                System.out.println("Nu exista categoria");
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
    private void clickChange(ActionEvent event) throws IOException {
        JSONParser jsonParser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            if(jsonArray.contains(category.getText()))
            {
                change=category.getText();
                Stage newStage =new Stage();
                Node  source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();

                // Create the FXMLLoader
                FXMLLoader loader = new FXMLLoader();
                // Path to the FXML File
                String fxmlDocPath = "src/main/resources/ChangeIn.fxml";
                FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

                // Create the Pane and all Details
                Pane root = (Pane) loader.load(new FileInputStream(fxmlDocPath));

                // Create the Scene

                Scene scene = new Scene(root);
                newStage.setScene(scene);

                newStage.show();
            }
            else
            {
                System.out.println("Nu exista categoria");
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
    private void clickChangeIn(ActionEvent event) throws IOException
    {
        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            if(jsonArray.contains(changeInTxt.getText()))
            {
                System.out.println("Exista deja");
            }
            else
            {
                jsonArray.remove(change);

                jsonArray.add(changeInTxt.getText());
                sort(jsonArray);
                JSONObject objfin = new JSONObject();
                FileWriter file = new FileWriter("src/main/resources/Categories.json");
                objfin.put("Category",jsonArray);
                file.write(objfin.toJSONString());
                file.close();
                Node  source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();

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
}
