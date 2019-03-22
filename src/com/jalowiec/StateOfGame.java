package com.jalowiec;

import javafx.scene.image.ImageView;

import java.io.*;
import java.util.*;

public class StateOfGame implements Serializable {


    public void saveGame(CommonDataStructure commonDataStructure) {

        List<User> playersInTheGame = commonDataStructure.getPlayersInTheGame();
        int currentUserIndex = commonDataStructure.getCurrentUserIndex();
        System.out.println(currentUserIndex);
        List<Integer> usersScores = usersScoresToList(playersInTheGame);
        List<Integer> usersAlternativeScores = usersAlernativeScoresToList(playersInTheGame);
        Die[] diceArray = playersInTheGame.get(currentUserIndex).getUserDataStructures().getDiceArray();
        Map<User, Map<Integer, Integer>> chosenFifthDiceMap = saveChosenFifthDiceMapForUsers(playersInTheGame);



        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("fifthdie.bin"))) {

            outputStream.writeObject(playersInTheGame);
            outputStream.writeObject(currentUserIndex);
            outputStream.writeObject(usersScores);
            outputStream.writeObject(usersAlternativeScores);
            outputStream.writeObject(diceArray);
            outputStream.writeObject(chosenFifthDiceMap);


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
            Die[] diceArray = (Die[]) inputStream.readObject();
            Die[] diceArrayForCurrentUser = diceArray.clone();
            new SceneUserGameTable(commonDataStructure.getMainStage(), currentUserIndex);
            diceArray = diceArrayForCurrentUser.clone();
            Map<User, Map<Integer, Integer>> chosenFifthDiceMap = (Map<User, Map<Integer, Integer>>) inputStream.readObject();
            setScoresForUsers(userList, usersScores);
            setFifthDieForUser(userList,chosenFifthDiceMap);
            restoreDrawChosenFifthDie(userList);
            setAlternativeScoresForUsers(userList, usersAlternativeScores);
            commonDataStructure.getLeftPanelDrawer().drawPlayingUsersScoreInPanel();

            restoreImageViewList(userList);
            restoreDices(currentUser, diceArray);





        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    private Map<User, Map<Integer, Integer>> saveChosenFifthDiceMapForUsers(List<User> playersInTheGame){
        Map<User, Map<Integer, Integer>> chosenFifthDiceMapForUsers = new HashMap<>();
        for(User user : playersInTheGame){
            chosenFifthDiceMapForUsers.put(user, user.getRoundEnd().getChosenFifthDiceMap());
        }
        return chosenFifthDiceMapForUsers;
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
            user.getRoundInitCommon().clearFreeSlotState();
            user.getRoundInitCommon().drawDieInSlot(diceArray[i], i);

        }

    }

    private void restoreImageViewList(List<User> userList){
        for(User user : userList) {
            List<ImageView> imageViewList = new ArrayList<>();
            user.getUserDataStructures().setImageViewList(imageViewList);
        }
    }

    private void setFifthDieForUser(List<User> userList, Map<User, Map<Integer, Integer>> chosenFifthDiceMap){
        for(User user : userList){
            user.getRoundEnd().setChosenFifthDiceMap(chosenFifthDiceMap.get(user));
        }

    }

    private void restoreDrawChosenFifthDie(List<User> userList){
        for(User user : userList){
            Map<Integer, Integer> chosenFifthDiceMap = user.getRoundEnd().getChosenFifthDiceMap();
            int i=0;
            for(Map.Entry<Integer, Integer> entry : chosenFifthDiceMap.entrySet()){
                System.out.println("pp: " + entry.getValue());
                user.getGameTableDrawer().drawChosenFifthDie(entry.getKey(), i++);
                user.getRoundEnd().getFifthDiceList().add(entry.getKey());
            }
        }
    }


}