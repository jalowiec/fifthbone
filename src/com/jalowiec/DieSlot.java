package com.jalowiec;

public class DieSlot {

    private String name;
    private int columnIndex;
    private int rowIndex;
    private int columnSpan;
    private int rowSpan;

    public DieSlot(String name, int columnIndex, int rowIndex, int columnSpan, int rowSpan) {
        this.name = name;
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
