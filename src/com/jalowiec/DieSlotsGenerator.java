package com.jalowiec;

import java.util.ArrayList;
import java.util.List;

public class DieSlotsGenerator {

    private List<DieSlot> slotsList = new ArrayList<>();
    private List<DieSlot> freeSlotsList = new ArrayList<>();

    public void generateSlots() {
        DieSlot firstSlot = new DieSlot("First", 2, 16, 2, 2);
        DieSlot secondSlot = new DieSlot("Second", 4, 16, 2, 2);
        DieSlot thirdSlot = new DieSlot("Third", 6, 16, 2, 2);
        DieSlot fourthSlot = new DieSlot("Fourth", 8, 16, 2, 2);
        DieSlot fifthSlot = new DieSlot("Fifth", 10, 16, 2, 2);
        slotsList.add(firstSlot);
        slotsList.add(secondSlot);
        slotsList.add(thirdSlot);
        slotsList.add(fourthSlot);
        slotsList.add(fifthSlot);

        DieSlot firstFreeSlot = new DieSlot("FirstFree", 2, 22, 2, 2);
        DieSlot secondFreeSlot = new DieSlot("SecondFree", 4, 22, 2, 2);
        DieSlot thirdFreeSlot = new DieSlot("ThirdFree", 8, 22, 2, 2);
        DieSlot fourthFreeSlot = new DieSlot("FourthFree", 10, 22, 2, 2);
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
