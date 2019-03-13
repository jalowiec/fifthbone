package com.jalowiec;

import java.util.List;

public class PlayingUsers {

    CommonDataStructure commonDataStructure;

    public PlayingUsers(CommonDataStructure commonDataStructure) {
        this.commonDataStructure = commonDataStructure;
    }

    public void clearPlayingUsers(){
        commonDataStructure.getPlayersInTheGame().clear();
    }

    public void addPCUsers() {

        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();

        playingUsersList.add(new UserPC("PC Begginer", true, UserLevel.BEGGINER));
        playingUsersList.add(new UserPC("PC Medium", true, UserLevel.MEDIUM));

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