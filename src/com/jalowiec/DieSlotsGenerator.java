package com.jalowiec;

import java.util.ArrayList;
import java.util.List;

public class DieSlotsGenerator {

    private List<DieSlot> slotsList = new ArrayList<>();

    public void generateSlots() {
        DieSlot firstSlot = new DieSlot("First", 2, 20, 2, 2);
        DieSlot secondSlot = new DieSlot("Second", 4, 20, 2, 2);
        DieSlot thirdSlot = new DieSlot("Third", 6, 20, 2, 2);
        DieSlot fourthSlot = new DieSlot("Fourth", 8, 20, 2, 2);
        DieSlot fifthSlot = new DieSlot("Fifth", 10, 20, 2, 2);
        slotsList.add(firstSlot);
        slotsList.add(secondSlot);
        slotsList.add(thirdSlot);
        slotsList.add(fourthSlot);
        slotsList.add(fifthSlot);
    }

    public List<DieSlot> getSlotsList() {
        return slotsList;
    }
}
