package com.jalowiec;

import java.io.*;

public class StateOfGame {


    CommonDataStructure commonDataStructure;

    public StateOfGame(CommonDataStructure commonDataStructure) {
        this.commonDataStructure = commonDataStructure;
    }

    public void saveGame(CommonDataStructure commonDataStructure) {


        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream("fifthdie.bin"))) {
            outputStream.writeObject(commonDataStructure.getPlayersInTheGame().get(0).getUserDataStructures().getScoreValue());
        } catch (IOException e) {
            e.printStackTrace();

        }
    }

    public void readGame(CommonDataStructure commonDataStructure) {

    try( ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream("fifthdie.bin")))

    {
        String scoreValuee = (String) inputStream.readObject();
        System.out.println(scoreValuee);


    } catch (FileNotFoundException e) {
        e.printStackTrace();
    } catch (IOException e) {
        e.printStackTrace();
    } catch (ClassNotFoundException e) {
        e.printStackTrace();
    }
    }

}
