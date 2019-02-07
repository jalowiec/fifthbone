package com.jalowiec;

import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

public class UserGameTableDrawer {

    private Scene scene;
    private GridPane grid;

    public UserGameTableDrawer(GridPane grid, Scene scene) {
        this.scene = scene;
        this.grid = grid;
    }

    public void drawFirstRow(){
        String[] rowValues = {"SUMA", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            cellText.setId("tableheader");
            grid.add(cellText,  i, 0);
        }
    }

    public void drawSecondRow(){
        String[] rowValues = {"PUNKTY", "10", "7", "6", "5", "4", "3", "4", "5", "6", "7", "10"};
        Text cellText;

        for(int i=0; i<rowValues.length; i++){
            cellText = new Text(rowValues[i]);
            cellText.setId("tablerow");
            grid.add(cellText,  i, 1);
        }
    }


}
