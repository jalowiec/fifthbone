package com.jalowiec;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class SceneUsersManager {

    Stage mainStage;
    UsersFileWriter usersFileWriter;
    UsersFileReader usersFileReader = new UsersFileReader();
    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
    List<Node> nodeList = new ArrayList<>();
    List<User> userNameList = usersFileReader.readUsersListFromFile();

    public SceneUsersManager(Stage mainStage) {
        this.mainStage = mainStage;
        usersFileWriter = new UsersFileWriter();
        showScene();
    }

    public void showScene() {

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        Scene sceneUsersManager = new Scene(vBox, 400, 600);
        sceneUsersManager.getStylesheets().add("sceneusersmanager.css");



        Label addUserLabel = new Label("Dodaj uzytkownika: ");
        addUserLabel.setMinSize(50, 10);
        addUserLabel.setId("label");
        vBox.getChildren().add(addUserLabel);


        TextField newUserTextField = new TextField("Nowy uzytkownik");
        newUserTextField.setMaxWidth(150);
         vBox.getChildren().add(newUserTextField);

        Button addNewUserButton = new Button("Dodaj");
        addNewUserButton.setOnAction(e -> {
            userNameList.add(new User(newUserTextField.textProperty().getValue(), false));
            usersFileWriter.writeUsersListToFile(userNameList);
            drawRadioButtons(vBox);
        });
        addNewUserButton.setId("button");
        vBox.getChildren().add(addNewUserButton);

        final Separator separator = new Separator();
        vBox.getChildren().addAll(separator);

        Label removeUserLabel = new Label("Usun uzytkownika: ");
        removeUserLabel.setMinSize(50, 10);
        removeUserLabel.setId("label");
        vBox.getChildren().add(removeUserLabel);

        drawRadioButtons(vBox);

        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(sceneUsersManager);
        mainStage.show();

    }

    public void drawRadioButtons(VBox vBox) {
        for(Node node : nodeList){
            vBox.getChildren().remove(node);
        }

        final ToggleGroup group = new ToggleGroup();
        for (User user : usersFileReader.readUsersListFromFile()) {
            RadioButton radioButton = new RadioButton(user.getUserName());
            radioButton.setId("radiobutton");
            radioButton.setToggleGroup(group);
            radioButton.setSelected(false);
            vBox.getChildren().add(radioButton);
            nodeList.add(radioButton);
        }
        EventHandler<MouseEvent> mouseHandler = e -> {
            commonDataStructure.getSceneMenuStart().showScene();

        };

        Button removeUserButton = new Button("Usun");
        removeUserButton.setId("button");
        removeUserButton.setOnAction(e -> {

            RadioButton selectedRadioButton = (RadioButton) group.getSelectedToggle();
            User userToRemove = getUserFromName(selectedRadioButton.getText());
            userNameList.remove(userToRemove);
            usersFileWriter.writeUsersListToFile(userNameList);
            drawRadioButtons(vBox);

        });
        vBox.getChildren().addAll(removeUserButton);
        nodeList.add(removeUserButton);


        ImageView backToMenuStartImage = new ImageView("file:resources/back.png");
        backToMenuStartImage.setOnMouseClicked(mouseHandler);
        vBox.getChildren().add(backToMenuStartImage);
        nodeList.add(backToMenuStartImage);

    }

    public User getUserFromName(String name){

        for(User user : userNameList){
            if(user.getUserName().equals(name)){
                return user;
            }
        }
        return null;
    }


}
