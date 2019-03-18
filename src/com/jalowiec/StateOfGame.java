package com.jalowiec;

import javafx.scene.image.ImageView;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class StateOfGame implements Serializable {


    public void saveGame(CommonDataStructure commonDataStructure) {

        List<User> playersInTheGame = commonDataStructure.getPlayersInTheGame();
        int currentUserIndex = commonDataStructure.getCurrentUserIndex();
        List<Integer> usersScores = usersScoresToList(playersInTheGame);
        List<Integer> usersAlternativeScores = usersAlernativeScoresToList(playersInTheGame);
        Die[] diceArray = playersInTheGame.get(currentUserIndex).getUserDataStructures().getDiceArray();


        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("fifthdie.bin"))) {

            outputStream.writeObject(playersInTheGame);
            outputStream.writeObject(currentUserIndex);
            outputStream.writeObject(usersScores);
            outputStream.writeObject(usersAlternativeScores);
            outputStream.writeObject(diceArray);
            for(int i=0; i<diceArray.length; i++){
                System.out.print(diceArray[i].getDiceValue());
            }

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void readGame(CommonDataStructure commonDataStructure) {

        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("fifthdie.bin"))) {
            List<User> userList = (List<User>) inputStream.readObject();
            commonDataStructure.setPlayersInTheGame(userList);
            commonDataStructure.createPlayersWhoNotFinished();
            int currentUserIndex = (int) inputStream.readObject();
            User currentUser = userList.get(currentUserIndex);
            List<Integer> usersScores = (List<Integer>) inputStream.readObject();
            List<Integer> usersAlternativeScores = (List<Integer>) inputStream.readObject();
            new SceneUserGameTable(commonDataStructure.getMainStage(), currentUserIndex);
            setScoresForUsers(userList, usersScores);
            setAlternativeScoresForUsers(userList, usersAlternativeScores);
            commonDataStructure.getLeftPanelDrawer().drawPlayingUsersScoreInPanel();
            Die[] diceArray = (Die[]) inputStream.readObject();
            for(int i=0; i<diceArray.length; i++){
                System.out.print(diceArray[i].getDiceValue());
            }
            restoreDices(currentUser, diceArray);


        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private List<Integer> usersScoresToList(List<User> userList){
        List<Integer> usersScoresList = new ArrayList<>();
        for(User user : userList){
            usersScoresList.add(user.getUserDataStructures().getScoreValue());
        }
        return usersScoresList;
    }

    private List<Integer> usersAlernativeScoresToList(List<User> userList){
        List<Integer> usersScoresList = new ArrayList<>();
        for(User user : userList){
            usersScoresList.add(user.getUserDataStructures().getAlternativeScoreValue());
        }
        return usersScoresList;
    }

    private void setScoresForUsers(List<User> usersList, List<Integer> usersScores){
        for(int i=0; i<usersList.size(); i++){
            usersList.get(i).getUserDataStructures().setScoreValue(usersScores.get(i));
            usersList.get(i).getGameTableDrawer().drawScore(usersScores.get(i));
        }
    }

    private void setAlternativeScoresForUsers(List<User> usersList, List<Integer> usersScores){
        for(int i=0; i<usersList.size(); i++){
            usersList.get(i).getUserDataStructures().setAlternativeScoreValue(usersScores.get(i));
        }
    }

    private void restoreDices(User user, Die[] diceArray) {
        for (int i = 0; i < diceArray.length; i++) {
            user.getRoundInitCommon().drawDieInSlot(diceArray[i], i);
        }
    }

}