package com.jalowiec;

import javafx.scene.image.Image;

public class Die {

    private String dicePath;
    private int diceValue;

    public Die(String dicePath, int diceValue) {
        this.dicePath = dicePath;
        this.diceValue = diceValue;
    }

    public int getDiceValue() {
        return diceValue;
    }

    public Image getDiceImage(){
        return new Image(dicePath);
    }


}
