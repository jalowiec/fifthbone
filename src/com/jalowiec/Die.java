package com.jalowiec;

import javafx.scene.image.Image;

import java.io.Serializable;

public class Die implements Serializable {

    private String diePath;
    private int dieValue;

    public Die(String diePath, int dieValue) {
        this.diePath = diePath;
        this.dieValue = dieValue;
    }

    public int getDiceValue() {
        return dieValue;
    }

    public Image getDieImage(){
        return new Image(diePath);
    }

}
