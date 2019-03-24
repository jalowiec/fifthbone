package com.jalowiec;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.*;

public class GameSaver implements Serializable {


    public void saveGame(CommonDataStructure commonDataStructure) {

        List<User> playersInTheGame = commonDataStructure.getPlayersInTheGame();
        int currentUserIndex = commonDataStructure.getCurrentUserIndex();
        List<Integer> usersScores = usersScoresToList(playersInTheGame);
        List<Integer> usersAlternativeScores = usersAlernativeScoresToList(playersInTheGame);
        Die[] diceArray = playersInTheGame.get(currentUserIndex).getUserDataStructures().getDiceArray();
        Map<User, Map<Integer, Integer>> chosenFifthDiceMap = saveChosenFifthDiceMapForUsers(playersInTheGame);
        Map<User, Map<Integer, Integer>> chosenAlternativeFifthDiceMap = saveAlternativeChosenFifthDiceMapForUsers(playersInTheGame);
        Map<User, Map<Integer, Integer>> scorePointerMaps = saveScorePointerMaps(playersInTheGame);
        Map<User, Map<Integer, Integer>> alternativeScorePointerMaps = saveAlternativeScorePointerMaps(playersInTheGame);
        List<Boolean> isRoundEndForUsersList = saveIsRoundEndForUsers(playersInTheGame);
        List<Boolean> isGameEndForUsersList = saveIsGameEndForUsers(playersInTheGame);


        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("fifthdie.bin"))) {

            outputStream.writeObject(playersInTheGame);
            outputStream.writeObject(currentUserIndex);
            outputStream.writeObject(usersScores);
            outputStream.writeObject(usersAlternativeScores);
            outputStream.writeObject(diceArray);
            outputStream.writeObject(chosenFifthDiceMap);
            outputStream.writeObject(chosenAlternativeFifthDiceMap);
            outputStream.writeObject(scorePointerMaps);
            outputStream.writeObject(alternativeScorePointerMaps);
            outputStream.writeObject(isRoundEndForUsersList);
            outputStream.writeObject(isGameEndForUsersList);

        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    private Map<User, Map<Integer, Integer>> saveChosenFifthDiceMapForUsers(List<User> playersInTheGame) {
        Map<User, Map<Integer, Integer>> chosenFifthDiceMapForUsers = new HashMap<>();
        for (User user : playersInTheGame) {
            chosenFifthDiceMapForUsers.put(user, user.getRoundEnd().getChosenFifthDiceMap());
        }
        return chosenFifthDiceMapForUsers;
    }


    private Map<User, Map<Integer, Integer>> saveAlternativeChosenFifthDiceMapForUsers(List<User> playersInTheGame) {
        Map<User, Map<Integer, Integer>> chosenAlternativeFifthDiceMapForUsers = new HashMap<>();
        for (User user : playersInTheGame) {
            chosenAlternativeFifthDiceMapForUsers.put(user, user.getRoundEnd().getAlternativeChosenFifthDiceMap());
        }
        return chosenAlternativeFifthDiceMapForUsers;
    }

    private Map<User, Map<Integer, Integer>> saveScorePointerMaps(List<User> playersInTheGame) {
        Map<User, Map<Integer, Integer>> scorePointerMapForUsers = new LinkedHashMap<>();
        for (User user : playersInTheGame) {
            scorePointerMapForUsers.put(user, user.getUserDataStructures().getScorePointerMap());
        }
        return scorePointerMapForUsers;
    }

    private Map<User, Map<Integer, Integer>> saveAlternativeScorePointerMaps(List<User> playersInTheGame) {
        Map<User, Map<Integer, Integer>> alternativeScorePointerMapForUsers = new LinkedHashMap<>();
        for (User user : playersInTheGame) {
            alternativeScorePointerMapForUsers.put(user, user.getUserDataStructures().getAlternativeScorePointerMap());
        }
        return alternativeScorePointerMapForUsers;
    }

    private List<Integer> usersScoresToList(List<User> userList) {
        List<Integer> usersScoresList = new ArrayList<>();
        for (User user : userList) {
            usersScoresList.add(user.getUserDataStructures().getScoreValue());
        }
        return usersScoresList;
    }

    private List<Integer> usersAlernativeScoresToList(List<User> userList) {
        List<Integer> usersScoresList = new ArrayList<>();
        for (User user : userList) {
            usersScoresList.add(user.getUserDataStructures().getAlternativeScoreValue());
        }
        return usersScoresList;

    }
    private List<Boolean> saveIsRoundEndForUsers(List<User> userList){
        List<Boolean> isRoundEndForUsersList = new ArrayList<>();
        for(User user : userList){
            isRoundEndForUsersList.add(user.getRoundEnd().isRoundEnd());
        }

        return isRoundEndForUsersList;
    }

    private List<Boolean> saveIsGameEndForUsers(List<User> userList){
        List<Boolean> isGameEndForUsersList = new ArrayList<>();
        for(User user : userList){
            isGameEndForUsersList.add(user.getRoundEnd().isRoundEnd());
        }

        return isGameEndForUsersList;
    }

}
