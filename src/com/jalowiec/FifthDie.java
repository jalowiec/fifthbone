package com.jalowiec;

import javafx.application.Application;
import javafx.stage.Stage;

import java.io.Serializable;


public class FifthDie extends Application implements Serializable {



    public static void main(String[] args){
        launch(args);
    }


    @Override
    public void init() throws Exception {
        super.init();
    }

    @Override
    public void start(Stage mainStage) throws Exception {


        CommonDataStructure commonDataStructure = CommonDataStructure.getInstance();
        commonDataStructure.setMainStage(mainStage);
        SceneMenuStart sceneMenuStart = new SceneMenuStart(mainStage);
    }


    @Override
    public void stop() throws Exception {
        super.stop();
    }
}

