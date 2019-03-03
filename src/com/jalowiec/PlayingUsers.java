package com.jalowiec;

import java.util.List;

public class PlayingUsers {

    CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();


    public void addUsers() {

        List<User> playingUsersList = commonDataStructure.getPlayingUsersList();

   //     plaingUsersList.add(new User("Michal", false));
        playingUsersList.add(new User("Lukasz", false));
        playingUsersList.add(new User("Lukasz1", false));
        playingUsersList.add(new User("Lukasz2", false));
        playingUsersList.add(new User("PC", true));

    }

    public User getNextUser(User user){
        List<User> playingUsersList = commonDataStructure.getPlayingUsersList();
        int playingUserId = playingUsersList.indexOf(user);
        if(playingUserId == playingUsersList.size()-1){
            return playingUsersList.get(0);
        }
        return playingUsersList.get(playingUserId+1);
    }

}
