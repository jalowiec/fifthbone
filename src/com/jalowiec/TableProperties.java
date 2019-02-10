package com.jalowiec;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class TableProperties {

    private GridPane grid;

    public TableProperties(GridPane grid) {
        this.grid = grid;
    }

    public void setColumnProperties(int firstColumnWidth, int restColumnsWidth, int firstColumnIndex){
        for(int i=0; i<firstColumnIndex; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(restColumnsWidth));
        }

        grid.getColumnConstraints().add(new ColumnConstraints(firstColumnWidth));

        for(int i=0; i<11; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(restColumnsWidth));
        }
    }
    public void setRowsProperties(int allColumnsHeight){
        for(int i=0; i<15; i++) {
            grid.getRowConstraints().add(new RowConstraints(allColumnsHeight));
        }
    }

}