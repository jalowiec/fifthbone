package com.jalowiec;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class UsersFileWriter implements Serializable {

    public void writeUsersListToFile(List<User> userList) {
        Path path = Paths.get("c://Dice/users.txt");
        try (BufferedWriter writer = Files.newBufferedWriter(path))
        {
            for(User element : userList){
                writer.write(element.getUserName() + "\n");
            }
        } catch (IOException e) {
            System.out.println("wystąpił błąd: " + e);
        }

    }

}
