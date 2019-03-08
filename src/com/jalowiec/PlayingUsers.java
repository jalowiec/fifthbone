package com.jalowiec;

import java.util.List;

public class PlayingUsers {

    CommonDataStructure commonDataStructure;

    public PlayingUsers(CommonDataStructure commonDataStructure) {
        this.commonDataStructure = commonDataStructure;
    }

    public void addPCUsers() {

        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();

        playingUsersList.add(new UserPC("PC Begginer", true, UserLevel.BEGGINER));
        playingUsersList.add(new UserPC("PC Medium", true, UserLevel.MEDIUM));

    }

    public User getNextUser(User user) {
        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        int playingUserId = playingUsersList.indexOf(user);
        if (playingUserId == playingUsersList.size() - 1) {
            return playingUsersList.get(0);
        }
        return playingUsersList.get(playingUserId + 1);
    }

    public User getNextUserWhoNotFinished(User user) {
        List<User> playersWhoNotFinished = commonDataStructure.getPlayersWhoNotFinished();
        int playingUserId = playersWhoNotFinished.indexOf(user);
        if (playingUserId == playersWhoNotFinished.size() - 1) {
            return playersWhoNotFinished.get(0);
        }
        return playersWhoNotFinished.get(playingUserId + 1);
    }

}