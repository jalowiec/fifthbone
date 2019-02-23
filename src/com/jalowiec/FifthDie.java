package com.jalowiec;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;


public class FifthDie extends Application {

    private List<User> plaingUsers = new ArrayList<>();

    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage mainStage) throws Exception {
        plaingUsers.add(new User("Lukasz", false));
        plaingUsers.add(new User("Pawel", false));
        plaingUsers.add(new User("PC", true));


        for (User user : plaingUsers) {
            user.setGridPane(new GridPane());
            user.getGridPane().setPadding(new Insets(50, 50, 50, 50));
            user.getGridPane().setGridLinesVisible(false);

            user.setUserScene(new Scene(user.getGridPane(), 880, 900));
            if(!user.getPC()){
                user.getUserScene().getStylesheets().add("userstyle.css");
            }else{
                user.getUserScene().getStylesheets().add("computerstyle.css");
            }

            TableProperties tableProperties = new TableProperties(user);
            tableProperties.setColumnProperties(120, 60);
            tableProperties.setRowsProperties(30);

            TableDrawer tableDrawer = new TableDrawer(user);
            tableDrawer.drawTableHeader();
            tableDrawer.drawPointsRow();
            tableDrawer.drawMinusSection();
            tableDrawer.drawPlusSection();
            tableDrawer.drawUserName();
            tableDrawer.drawScoreHeader();
            tableDrawer.drawScore(0);
            tableDrawer.drawFifthBoneHeader();
            tableDrawer.drawFifthBoneSection();
            tableDrawer.drawChosenPair();
            tableDrawer.drawHorizontalLines();

            DiceSlotsOperation diceSlotsOperation = DiceSlotsOperation.getInstance();
            diceSlotsOperation.generateDice();

            user.setRoundInitCommon(new RoundInitCommon(user));
            user.getRoundInitCommon().generateDicesInSlots();


            if(!user.getPC()) {
                user.setRoundProccesorUser(new RoundProccesorUser(user));
                user.setRoundEnd(new RoundEnd(user));
                tableDrawer.drawRoundEndButton(user.getRoundEnd());
                tableDrawer.drawHelpMark();
            }
            Button nextPlayerButton = new Button("Kolejny zawodnik");
            nextPlayerButton.setOnAction(e-> mainStage.setScene(getNextUser(user).getUserScene()));
            user.getGridPane().add(nextPlayerButton, 10, 20, 3, 1);
        }


        mainStage.setTitle("The Fifth Dice");
        mainStage.setScene(plaingUsers.get(0).getUserScene());
        mainStage.show();
    }

    public User getNextUser(User user){
        int playingUserId = plaingUsers.indexOf(user);
        if(playingUserId == plaingUsers.size()-1){
            return plaingUsers.get(0);
        }
        return plaingUsers.get(playingUserId+1);
    }



    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

