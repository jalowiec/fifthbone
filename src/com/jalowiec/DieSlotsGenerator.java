package com.jalowiec;

import java.util.ArrayList;
import java.util.List;

public class DieSlotsGenerator {

    private List<DieSlot> slotsList = new ArrayList<>();
    private List<DieSlot> freeSlotsList = new ArrayList<>();

    public void generateSlots() {
        DieSlot firstSlot = new DieSlot( 6, 17, 2, 2);
        DieSlot secondSlot = new DieSlot( 8, 17, 2, 2);
        DieSlot thirdSlot = new DieSlot( 10, 17, 2, 2);
        DieSlot fourthSlot = new DieSlot( 12, 17, 2, 2);
        DieSlot fifthSlot = new DieSlot( 14, 17, 2, 2);
        slotsList.add(firstSlot);
        slotsList.add(secondSlot);
        slotsList.add(thirdSlot);
        slotsList.add(fourthSlot);
        slotsList.add(fifthSlot);

        DieSlot firstFreeSlot = new DieSlot( 6, 22, 2, 2);
        DieSlot secondFreeSlot = new DieSlot( 8, 22, 2, 2);
        DieSlot thirdFreeSlot = new DieSlot(12, 22, 2, 2);
        DieSlot fourthFreeSlot = new DieSlot(14, 22, 2, 2);
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
