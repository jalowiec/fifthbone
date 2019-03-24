package com.jalowiec;

import javafx.scene.image.ImageView;

import java.io.*;
import java.util.*;

public class GameLoader implements Serializable {



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
            Map<User, Map<Integer, Integer>> chosenAlternativeFifthDiceMap = (Map<User, Map<Integer, Integer>>) inputStream.readObject();
            Map<User, Map<Integer, Integer>> scorePointerMaps = (Map<User, Map<Integer, Integer>>) inputStream.readObject();
            Map<User, Map<Integer, Integer>> alternativeScorePointerMaps = (Map<User, Map<Integer, Integer>>) inputStream.readObject();
            setScoresForUsers(userList, usersScores);
            setFifthDieForUser(userList, chosenFifthDiceMap);
            setAlternativeFifthDieForUser(userList, chosenAlternativeFifthDiceMap);
            setScorePointerMapsForUsers(userList, scorePointerMaps);
            setAlternativeScorePointerMapsForUsers(userList, alternativeScorePointerMaps);
            restoreDrawChosenFifthDie(userList);
            restoreDrawChosenFifthDieSlots(userList);
            restoreDrawUsedSlotsAfterRound(userList);

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




    private void setScoresForUsers(List<User> usersList, List<Integer> usersScores) {
        for (int i = 0; i < usersList.size(); i++) {
            usersList.get(i).getUserDataStructures().setScoreValue(usersScores.get(i));
            usersList.get(i).getGameTableDrawer().drawScore(usersScores.get(i));
        }
    }

    private void setAlternativeScoresForUsers(List<User> usersList, List<Integer> usersScores) {
        for (int i = 0; i < usersList.size(); i++) {
            usersList.get(i).getUserDataStructures().setAlternativeScoreValue(usersScores.get(i));
        }
    }

    private void setScorePointerMapsForUsers(List<User> usersList, Map<User, Map<Integer, Integer>> scorePointerMaps) {
        for (int i = 0; i < usersList.size(); i++) {
            usersList.get(i).getUserDataStructures().setScorePointerMap(scorePointerMaps.get(usersList.get(i)));
        }
    }

    private void setAlternativeScorePointerMapsForUsers(List<User> usersList, Map<User, Map<Integer, Integer>> alternativeScorePointerMaps) {
        for (int i = 0; i < usersList.size(); i++) {
            usersList.get(i).getUserDataStructures().setAlternativeScorePointerMap(alternativeScorePointerMaps.get(usersList.get(i)));
        }
    }

    private void restoreDices(User user, Die[] diceArray) {

        for (int i = 0; i < diceArray.length; i++) {
            user.getRoundInitCommon().clearFreeSlotState();
            user.getRoundInitCommon().drawDieInSlot(diceArray[i], i);

        }

    }

    private void restoreImageViewList(List<User> userList) {
        for (User user : userList) {
            List<ImageView> imageViewList = new ArrayList<>();
            user.getUserDataStructures().setImageViewList(imageViewList);
        }
    }

    private void setFifthDieForUser(List<User> userList, Map<User, Map<Integer, Integer>> chosenFifthDiceMap) {
        for (User user : userList) {
            user.getRoundEnd().setChosenFifthDiceMap(chosenFifthDiceMap.get(user));
        }

    }

    private void setAlternativeFifthDieForUser(List<User> userList, Map<User, Map<Integer, Integer>> chosenFifthDiceMap) {
        for (User user : userList) {
            user.getRoundEnd().setAlternativeChosenFifthDiceMap(chosenFifthDiceMap.get(user));
        }

    }

    private void restoreDrawChosenFifthDie(List<User> userList) {
        for (User user : userList) {
            Map<Integer, Integer> chosenFifthDiceMap = user.getRoundEnd().getChosenFifthDiceMap();
            int i = 0;
            for (Map.Entry<Integer, Integer> entry : chosenFifthDiceMap.entrySet()) {
                user.getGameTableDrawer().drawChosenFifthDie(entry.getKey(), i++);
                user.getRoundEnd().getFifthDiceList().add(entry.getKey());
            }
        }
    }

    private void restoreDrawChosenFifthDieSlots(List<User> userList) {
        for (User user : userList) {
            Map<Integer, Integer> chosenFifthDiceMap = user.getRoundEnd().getChosenFifthDiceMap();
            int row = 0;
            for (Map.Entry<Integer, Integer> entry : chosenFifthDiceMap.entrySet()) {
                int i = entry.getValue();
                for (int j = 1; j <= i; j++) {
                    user.getGameTableDrawer().drawChosenFifthDieSlots(j, row);
                }
                row++;

            }

        }
    }

    private void restoreDrawUsedSlotsAfterRound(List<User> userList) {
        for (User user : userList) {
            Map<Integer, Integer> scorePointerMap = user.getUserDataStructures().getScorePointerMap();
            for (Map.Entry<Integer, Integer> entry : scorePointerMap.entrySet()) {
                if(entry.getValue() > 1){
                for (int i = 2; i <= entry.getValue(); i++) {
                    user.getGameTableDrawer().drawUsedSlotsAfterRound(entry.getKey(), i);
                }}
            }

        }


    }
}