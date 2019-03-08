package com.jalowiec;

import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;

public class TableProperties {

    private GridPane grid;

    public TableProperties(User user) {
        this.grid = user.getGridPane();
    }

    public void setLeftPanelProperties(int paddingColumnWidth, int longColumnWidth, int firstShortColumnWidth, int secondShortColumnWidth){


        grid.getColumnConstraints().add(new ColumnConstraints(paddingColumnWidth));
        grid.getColumnConstraints().add(new ColumnConstraints(longColumnWidth));
        grid.getColumnConstraints().add(new ColumnConstraints(firstShortColumnWidth));
        grid.getColumnConstraints().add(new ColumnConstraints(secondShortColumnWidth));
        grid.getColumnConstraints().add(new ColumnConstraints(paddingColumnWidth));
    }

    public void setColumnProperties(int firstColumnWidth,  int restColumnsWidth){

        grid.getColumnConstraints().add(new ColumnConstraints(firstColumnWidth));

        for(int i=0; i<11; i++){
            grid.getColumnConstraints().add(new ColumnConstraints(restColumnsWidth));
        }
    }
    public void setRowsProperties(int allColumnsHeight){
        for(int i=0; i<27; i++) {
            grid.getRowConstraints().add(new RowConstraints(allColumnsHeight));
        }
    }

}
