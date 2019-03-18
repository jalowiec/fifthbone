package com.jalowiec;

import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class SceneUsersInGame implements Serializable {

    transient private Stage mainStage;
    private UsersFileReader usersFileReader;
    private CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();


    public SceneUsersInGame(Stage mainStage) {
        this.mainStage = mainStage;
        usersFileReader = new UsersFileReader();
    }

    public void showScene(){

        VBox vBox = new VBox(20);
        vBox.setPadding(new Insets(10, 10, 10, 10));

        List<User> userNameList = usersFileReader.readUsersListFromFile();
        PlayingUsers plaingUsers = new PlayingUsers(commonDataStructure);
        plaingUsers.clearPlayingUsers();
        plaingUsers.addPCUsers();
        userNameList.addAll(commonDataStructure.getPlayersInTheGame());


        EventHandler<MouseEvent> mouseHandler = e -> {
            commonDataStructure.getSceneMenuStart().showScene();
        };

        ImageView homeToMenuStartImage = new ImageView("file:resources/home.png");
        homeToMenuStartImage.setOnMouseClicked(mouseHandler);


        Label label = new Label( "Wybierz użytkowników: ");
        label.setMinSize(50, 10);
        label.setId("label");
        vBox.getChildren().addAll(homeToMenuStartImage, label);


        List<CheckBox> checkBoxes = new ArrayList<>();

        for(User user : userNameList){
            CheckBox checkBox = new CheckBox(user.getUserName());
            checkBox.setId("checkbox");
            vBox.getChildren().add(checkBox);
            checkBoxes.add(checkBox);
        }


        Button startGameButton = new Button("Rozpocznij gre");
        startGameButton.setOnAction(e -> {
            if(isUsersSelected(checkBoxes, userNameList)) {
                new SceneUserGameTable(mainStage, 0);
            } else {
                Alert dialog = new Alert(Alert.AlertType.ERROR);
                dialog.setTitle("Ostrzeżenie");
                dialog.setContentText("Nie wybrano zadnego uzytkownika!");
                dialog.show();
            }
        });
        startGameButton.setId("button");
        vBox.getChildren().add(startGameButton);

        Scene sceneUsersInGame = new Scene(vBox, 400, 600);
        sceneUsersInGame.getStylesheets().add("sceneusersingame.css");
        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(sceneUsersInGame);
        mainStage.show();


    }

    private boolean isUsersSelected(List<CheckBox> checkBoxes, List<User> users) {
        List<User> selectedUsers = new ArrayList<>();
        for (CheckBox checkBox : checkBoxes) {
            if(checkBox.isSelected()){
                int index = checkBoxes.indexOf(checkBox);
                selectedUsers.add(users.get(index));
            }
        }
        if(selectedUsers.size()>0){
            commonDataStructure.setPlayersInTheGame(selectedUsers);
            commonDataStructure.createPlayersWhoNotFinished();
            return true;

        }

        return false;
    }


}
