package com.jalowiec;

import java.util.ArrayList;
import java.util.List;

public class DieSlotsGenerator {

    private List<DieSlot> slotsList = new ArrayList<>();
    private List<DieSlot> freeSlotsList = new ArrayList<>();

    public void generateSlots() {
        DieSlot firstSlot = new DieSlot("First", 1, 17, 2, 2);
        DieSlot secondSlot = new DieSlot("Second", 3, 17, 2, 2);
        DieSlot thirdSlot = new DieSlot("Third", 5, 17, 2, 2);
        DieSlot fourthSlot = new DieSlot("Fourth", 7, 17, 2, 2);
        DieSlot fifthSlot = new DieSlot("Fifth", 9, 17, 2, 2);
        slotsList.add(firstSlot);
        slotsList.add(secondSlot);
        slotsList.add(thirdSlot);
        slotsList.add(fourthSlot);
        slotsList.add(fifthSlot);

        DieSlot firstFreeSlot = new DieSlot("FirstFree", 1, 22, 2, 2);
        DieSlot secondFreeSlot = new DieSlot("SecondFree", 3, 22, 2, 2);
        DieSlot thirdFreeSlot = new DieSlot("ThirdFree", 7, 22, 2, 2);
        DieSlot fourthFreeSlot = new DieSlot("FourthFree", 9, 22, 2, 2);
        freeSlotsList.add(firstFreeSlot);
        freeSlotsList.add(secondFreeSlot);
        freeSlotsList.add(thirdFreeSlot);
        freeSlotsList.add(fourthFreeSlot);
    }

    public List<DieSlot> getSlotsList() {
        return slotsList;
    }

    public List<DieSlot> getFreeSlotsList() {
        return freeSlotsList;
    }
}
