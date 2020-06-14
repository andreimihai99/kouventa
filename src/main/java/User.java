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
import javafx.scene.Node;
import javafx.scene.Scene;

import javafx.scene.control.Button;
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

public class User extends  Application {


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
                Stage newStage =new Stage();
                Node source = (Node)  event.getSource();
                Stage stage  = (Stage) source.getScene().getWindow();
                stage.close();
                // Create the FXMLLoader
                FXMLLoader loader = new FXMLLoader();
                // Path to the FXML File
                String fxmlDocPath = "src/main/resources/Forum.fxml";
                FileInputStream fxmlStream = new FileInputStream(fxmlDocPath);

                // Create the Pane and all Details
                Pane root = (Pane) loader.load(fxmlStream);

                // Create the Scene

                Scene scene = new Scene(root);
                newStage.setScene(scene);

                newStage.show();
                JSONParser jsonParser = new JSONParser();
                Integer i = new Integer(0);
                try {

                    JSONObject jsonObject = (JSONObject) jsonParser.parse(new FileReader("src/main/resources/Categories.json"));
                    JSONArray jsonArray = (JSONArray) jsonObject.get("Category");
                    List<Button> buttonlist = new ArrayList<>();
                    Iterator iterator = jsonArray.iterator();
                    while (iterator.hasNext()) {
                        String aug=new String((String)iterator.next());
                        Button btn = new Button(aug);
                        btn.setId(aug);
                        btn.setLayoutX(i);
                        btn.setLayoutY(i);
                        EventHandler<ActionEvent> click = new EventHandler<ActionEvent>() {
                            public void handle(ActionEvent e)
                            {
                                //Creating text objects
                                Text text1 = new Text("Welcome to Tutorialspoint ");

                                //Setting font to the text
                                text1.setFont(new Font(15));

                                //Setting color to the text
                                text1.setFill(Color.DARKSLATEBLUE);

                                Text text2 = new Text("We provide free tutorials for readers in various technologies  ");

                                        //Setting font to the text
                                        text2.setFont(new Font(15));

                                //Setting color to the text
                                text2.setFill(Color.DARKGOLDENROD);
                                Text text3 = new Text("\n Recently we started free video tutorials too ");

                                //Setting font to the text
                                text3.setFont(new Font(15));

                                //Setting color to the text
                                text3.setFill(Color.DARKGRAY);

                                Text text4 = new Text("We believe in easy learning");

                                //Setting font to the text
                                text4.setFont(new Font(15));
                                text4.setFill(Color.MEDIUMVIOLETRED);

                                //Creating the text flow plane
                                TextFlow textFlowPane = new TextFlow();

                                //Setting the line spacing between the text objects
                                textFlowPane.setTextAlignment(TextAlignment.JUSTIFY);

                                //Setting the width
                                textFlowPane.setPrefSize(600, 300);

                                //Setting the line spacing
                                textFlowPane.setLineSpacing(5.0);

                                //Retrieving the observable list of the TextFlow Pane
                                ObservableList list = textFlowPane.getChildren();

                                //Adding cylinder to the pane
                                list.addAll(text1, text2, text3, text4);

                                //Creating a scene object
                                Scene scene = new Scene(textFlowPane);

                                //Setting title to the Stage
                                stage.setTitle("text Flow Pane Example");

                                //Adding scene to the stage
                                stage.setScene(scene);

                                //Displaying the contents of the stage
                                stage.show();
                            }
                        };
                        btn.setOnAction(click);
                        i=i+30;
                        buttonlist.add(btn);


                    }
                    root.getChildren().clear(); //remove all Buttons that are currently in the container
                    root.getChildren().addAll(buttonlist);

                }

             catch(
            FileNotFoundException e)

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
    private void click(ActionEvent click) {

    }

    }

