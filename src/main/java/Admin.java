
import java.io.*;
import java.util.Iterator;

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

public class Admin extends LoginController {

    @FXML
    private TextField category;
    @FXML
    private TextField changeInTxt;
    static String change = new String();

    @FXML
    private void clickManageUser(ActionEvent ev) throws IOException {
        opening("ManagesTalkers",ev);
    }

    @FXML
    public void clickBack3(ActionEvent event) throws IOException {
        opening("Admin",event);
    }

    @FXML
    private void clickManageCat(ActionEvent e) throws IOException {
        Stage newStage = new Stage();
        Node source = (Node) e.getSource();
        Stage stage = (Stage) source.getScene().getWindow();
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
                errorWindow("Deja exista categoria",event);

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
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clickDelete(ActionEvent event) throws IOException {
        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            if (jsonArray.contains(category.getText())) {
                jsonArray.remove(category.getText());

                JSONObject objfin = new JSONObject();
                FileWriter file = new FileWriter("src/main/resources/Categories.json");
                objfin.put("Category", jsonArray);
                file.write(objfin.toJSONString());
                file.close();

            } else {


                errorWindow("Nu exista categoria",event);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser1 = new JSONParser();
        JSONObject categ=new JSONObject();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser1.parse(new FileReader("src/main/resources/Forum.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Database");

            if (!jsonArray.isEmpty()) {
                Iterator iterator = jsonArray.iterator();
                int ver=0;
                while (iterator.hasNext()) {
                    categ = (JSONObject) iterator.next();
                    if (categ.containsKey(category.getText())) {
                        ver=1;
                        break;
                    }

                }
                if(ver==1) {
                    jsonArray.remove(categ);
                    JSONObject aug2 = new JSONObject();
                    aug2.put("Database", jsonArray);

                    try {
                        FileWriter file = new FileWriter("src/main/resources/Forum.json");
                        file.write(aug2.toJSONString());
                        file.close();


                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
            }
        } catch (ParseException e) {
            e.printStackTrace();
        }

    }

    @FXML
    private void clickChange(ActionEvent event) throws IOException {
        JSONParser jsonParser = new JSONParser();

        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            if (jsonArray.contains(category.getText())) {
                change = category.getText();
                Stage newStage = new Stage();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();

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
            } else {

                errorWindow("Nu exista categoria",event);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void clickChangeIn(ActionEvent event) throws IOException {
        JSONParser jsonParser = new JSONParser();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            if (jsonArray.contains(changeInTxt.getText())) {

                errorWindow("Exista deja",event);
            } else {
                jsonArray.remove(change);

                jsonArray.add(changeInTxt.getText());
                sort(jsonArray);
                JSONObject objfin = new JSONObject();
                FileWriter file = new FileWriter("src/main/resources/Categories.json");
                objfin.put("Category", jsonArray);
                file.write(objfin.toJSONString());
                file.close();
                Node source = (Node) event.getSource();
                Stage stage = (Stage) source.getScene().getWindow();
                stage.close();

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        }
        JSONParser jsonParser1 = new JSONParser();
        String prov = new String();
        JSONObject categ=new JSONObject();
        try {

            JSONObject jsonObject = (JSONObject) jsonParser1.parse(new FileReader("src/main/resources/Forum.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Database");

            if (!jsonArray.isEmpty()) {
                Iterator iterator = jsonArray.iterator();

                while (iterator.hasNext()) {
                    categ = (JSONObject) iterator.next();
                    if (categ.containsKey(change)) {
                        break;
                    }

                }

                    prov = (String) categ.get(change);
                    jsonArray.remove(categ);

                    JSONObject aug = new JSONObject();
                    aug.put(changeInTxt.getText(), prov);
                    System.out.println();
                    jsonArray.add(aug);

                JSONObject aug2 = new JSONObject();
                aug2.put("Database", jsonArray);

                try {
                    FileWriter file = new FileWriter("src/main/resources/Forum.json");
                    file.write(aug2.toJSONString());
                    file.close();


                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }

            }
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
