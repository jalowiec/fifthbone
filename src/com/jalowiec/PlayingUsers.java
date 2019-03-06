package com.jalowiec;

import java.util.List;

public class PlayingUsers {

    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();


    public void addUsers() {

        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        //     plaingUsersList.add(new User("Michal", false));
        playingUsersList.add(new User("Lukasz", false));


        //  playingUsersList.add(new User("Lukasz2", false));
        playingUsersList.add(new User("PC", true));
        playingUsersList.add(new User("Lukasz1", false));

        commonDataStructure.createPlayersWhoNotFinished();
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

/*
    public User getNextPlayingUser(User user) {
        List<User> playingUsersList = commonDataStructure.getPlayersInTheGame();
        int playingUserId = playingUsersList.indexOf(user);
        int nextUserId = playingUserId;
        if(playingUserId == playingUsersList.size()-1){
            nextUserId=0;
        }
        else{
            nextUserId++;
        }

        User result = null;
        while (!playingUsersList.get(playingUserId).equals(playingUsersList.get(nextUserId))) {
            System.out.println("next user id: " +  nextUserId);
            if (!playingUsersList.get(nextUserId).getRoundEnd().isGameEnd()) {
                return playingUsersList.get(nextUserId);
            }
            if (nextUserId == playingUsersList.size()) {
                nextUserId = 0;
            } else {
                nextUserId++;
            }

        }

        return result;
    }

*/

}