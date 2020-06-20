

        import java.io.*;
        import java.util.ArrayList;
        import java.util.Iterator;
        import java.util.List;

        import javafx.application.Application;
        import javafx.collections.ObservableList;
        import javafx.event.ActionEvent;
        import javafx.event.EventHandler;
        import javafx.fxml.FXML;
        import javafx.fxml.FXMLLoader;
        import javafx.scene.Group;
        import javafx.scene.Node;
        import javafx.scene.Scene;

        import javafx.scene.SubScene;
        import javafx.scene.control.Button;
        import javafx.scene.control.ScrollPane;
        import javafx.scene.control.TextArea;
        import javafx.scene.control.TextField;

        import javafx.scene.layout.AnchorPane;
        import javafx.scene.layout.Pane;
        import javafx.scene.paint.Color;
        import javafx.scene.text.Font;
        import javafx.scene.text.Text;
        import javafx.scene.text.TextAlignment;
        import javafx.scene.text.TextFlow;
        import javafx.stage.Stage;
        import org.json.simple.JSONArray;
        import org.json.simple.JSONObject;
        import org.json.simple.parser.JSONParser;
        import org.json.simple.parser.ParseException;

        import static java.util.Collections.sort;

public class User extends  LoginController {


    @FXML
    protected TextField postTxt;

    protected static String current_category;

    private static Stage pStage;
    protected String aug;
    protected List<Button> buttonlist = new ArrayList<Button>();
    public void deschidere(String aug, List buttonlist){
        try {

            String prov=new String();
            JSONParser jsonParser = new JSONParser();

            try {

                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Forum.json"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("Database");


                Iterator iterator = jsonArray.iterator();
                while (iterator.hasNext()) {
                    JSONObject categ = (JSONObject) iterator.next();
                    if (categ.containsKey(aug)) {
                        prov = (String) categ.get(aug);
                        break;

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
            pStage.close();
            Text txtField = new Text();
            txtField.setX(340);
            txtField.setY(20);
            txtField.setText(prov);
            Stage newStageSec = new Stage();


            // Create the FXMLLoader
            FXMLLoader loader = new FXMLLoader();
            // Path to the FXML File
            String fxmlDocPath = "src/main/resources/Forum.fxml";
            FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);


            Pane root = (Pane) loader.load(fxmlStream);
            ScrollPane scrPane = new ScrollPane();
            scrPane.setContent(txtField);
            scrPane.setLayoutY(20);
            scrPane.setLayoutX(200);
            scrPane.setFitToWidth(true);
            scrPane.setPrefWidth(380);
            scrPane.setPrefHeight(250);
            Group root1 = new Group();


            root.getChildren().addAll(buttonlist);
            ObservableList list = root.getChildren();


            //Setting the text object as a node to the group object
            list.add(scrPane);


            //Creating a scene object
            Scene scene = new Scene(root);

            newStageSec.setScene(scene);
            pStage=newStageSec;
            pStage.show();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }
        catch(IOException e)

        {
            e.printStackTrace();
        }
    }
    public void buton(){
        JSONParser jsonParser = new JSONParser();
        Integer i = new Integer(0);
        try {

            JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
            JSONArray jsonArray = (JSONArray) jsonObject.get("Category");

            Iterator iterator = jsonArray.iterator();
            while (iterator.hasNext()) {
                aug=((String)iterator.next());
                Button btn = new Button(aug);
                btn.setId(aug);

                btn.setLayoutY(i);
                EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
                    public void handle(ActionEvent eve) {
                        current_category =btn.getId();
                        deschidere(current_category, buttonlist);
                    }
                };
                btn.setOnAction(click);
                i=i+30;
                buttonlist.add(btn);


            }
        }

        catch(FileNotFoundException e)

        {
            e.printStackTrace();
        } catch(IOException e)

        {
            e.printStackTrace();
        } catch(ParseException e)

        {
            e.printStackTrace();
        }
    }
    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/TalkerGUI.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

        // Create the Pane and all Details
        Pane root = (Pane) loader.load(fxmlStream);

        // Create the Scene
        Scene scene = new Scene(root);

        primaryStage.setScene(scene);
        primaryStage.show();

    }
    @FXML
    private void clickViewCateg(ActionEvent event) throws IOException {


        Text txtField1=new Text();
        txtField1.setX(340);
        txtField1.setY(20);

        buton();
        Stage newStage =new Stage();
        Node source = (Node)  event.getSource();
        Stage stage  = (Stage) source.getScene().getWindow();
        stage.close();
        // Create the FXMLLoader
        FXMLLoader loader = new FXMLLoader();
        // Path to the FXML File
        String fxmlDocPath = "src/main/resources/Forum.fxml";
        FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);


        Pane root = (Pane) loader.load(fxmlStream);

        ScrollPane scrPane=new ScrollPane();
        scrPane.setContent(txtField1);
        scrPane.setLayoutY(20);
        scrPane.setLayoutX(200);
        scrPane.setFitToWidth(true);
        scrPane.setPrefWidth(380);
        scrPane.setPrefHeight(250);
        Group root1 = new Group();


        root.getChildren().addAll(buttonlist);
        ObservableList list = root.getChildren();


        //Setting the text object as a node to the group object
        list.add(scrPane);




        //Creating a scene object
        Scene scene = new Scene(root);
        newStage.setScene(scene);
        pStage = newStage;
        pStage.show();
        return;



    }
    @FXML
    private void clickPost(ActionEvent click)throws IOException {
        if(!postTxt.getText().isEmpty())
        {
            JSONParser jsonParser = new JSONParser();
            String prov = new String();
            try {

                JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Forum.json"));
                JSONArray jsonArray = (JSONArray) jsonObject.get("Database");

                if (!jsonArray.isEmpty()) {
                    Iterator iterator = jsonArray.iterator();
                    int verificator=0;
                    while(iterator.hasNext()) {
                        JSONObject categ=(JSONObject) iterator.next();
                        if(categ.containsKey(current_category))
                        {

                            prov = categ.get(current_category) + "\n" + user +": " + postTxt.getText();

                            categ.replace(current_category,prov);

                            verificator=1;

                        }




                    }
                    if(verificator==0)
                    {
                        prov =user+ ": "+postTxt.getText();
                        JSONObject aug=new JSONObject();
                        aug.put(current_category,prov);
                        jsonArray.add(aug);
                    }
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
                else {

                    prov =user+": "+ postTxt.getText();
                    JSONObject aug = new JSONObject();
                    aug.put(current_category, prov);
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


            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ParseException e) {
                e.printStackTrace();
            }
            buton();
            deschidere(current_category, buttonlist);
        }

    }
    @FXML
    private void clickBack(ActionEvent ev) throws IOException {
        pStage.close();
        opening("TalkerGUI",ev);
    }
    @FXML
    private void clickEdit(ActionEvent ev) throws IOException {

        opening("EditProfile",ev);
    }


}

