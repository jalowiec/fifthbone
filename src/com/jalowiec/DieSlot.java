package com.jalowiec;

import java.io.Serializable;

public class DieSlot implements Serializable {

    private int columnIndex;
    private int rowIndex;
    private int columnSpan;
    private int rowSpan;

    public DieSlot( int columnIndex, int rowIndex, int columnSpan, int rowSpan) {

        this.columnIndex = columnIndex;
        this.rowIndex = rowIndex;
        this.columnSpan = columnSpan;
        this.rowSpan = rowSpan;
    }

    public int getColumnIndex() {
        return columnIndex;
    }

    public int getRowIndex() {
        return rowIndex;
    }

    public int getColumnSpan() {
        return columnSpan;
    }

    public int getRowSpan() {
        return rowSpan;
    }
}
