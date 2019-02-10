package com.jalowiec;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;

import java.util.ArrayList;
import java.util.List;

public class DieSlotsManager {

    private GridPane grid;
    private List<DieSlot> dieSlots = new ArrayList<>();


    public DieSlotsManager(GridPane grid) {
        this.grid = grid;
    }

    public void generateSlots(){
        DieSlot firstSlot = new DieSlot("First", 2, 20, 2, 2);
        DieSlot secondSlot = new DieSlot("Second", 4, 20, 2, 2);
        DieSlot thirdSlot = new DieSlot("Third", 6, 20, 2, 2);
        DieSlot fourthSlot = new DieSlot("Fourth", 8, 20, 2, 2);
        DieSlot fifthSlot = new DieSlot("Fifth", 10, 20, 2, 2);
        dieSlots.add(firstSlot);
        dieSlots.add(secondSlot);
        dieSlots.add(thirdSlot);
        dieSlots.add(fourthSlot);
        dieSlots.add(fifthSlot);
    }

    public void drawDieInSlot(Die die, int slotNumber){
        ImageView diceInSlot = new ImageView(die.getDiceImage());
        grid.add(diceInSlot, dieSlots.get(slotNumber).getColumnIndex(), dieSlots.get(slotNumber).getRowIndex(), dieSlots.get(slotNumber).getColumnSpan(), dieSlots.get(slotNumber).getRowSpan());

    }

}
