package com.jalowiec;

import java.util.ArrayList;
import java.util.List;

public class PlayingUsers {

    private List<User> plaingUsersList = new ArrayList<>();


    public List<User> getPlaingUsersList() {
        return plaingUsersList;
    }

    public void addUsers() {

   //     plaingUsersList.add(new User("Michal", false));
        plaingUsersList.add(new User("Lukasz", false));
        plaingUsersList.add(new User("PC", true));

    }

    public User getNextUser(User user){
        int playingUserId = plaingUsersList.indexOf(user);
        if(playingUserId == plaingUsersList.size()-1){
            return plaingUsersList.get(0);
        }
        return plaingUsersList.get(playingUserId+1);
    }

    //public void

}
