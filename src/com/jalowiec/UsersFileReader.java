package com.jalowiec;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class UsersFileReader implements Serializable {


    public List<User> readUsersListFromFile() {

        List<User> usersList = new ArrayList<>();

        File file = new File("c://Dice/users.txt");

        try (BufferedReader b = new BufferedReader(new FileReader(file))) {
            String readLine = "";

            while ((readLine = b.readLine()) != null ) {
                usersList.add(new User(readLine, false));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usersList;
    }


}
