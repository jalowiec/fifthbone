package com.jalowiec;

import java.util.*;

public class CommonDataStructure {

    private static CommonDataStructure instance;
    private int[][] scoreSchema;
    private List<User> playersInTheGame = new ArrayList<>();
    private List<User> playersWhoNotFinished;
    private List<RankingRecord> rankingList;
    private DiceGenerator diceGenerator;
    private LeftPanelDrawer leftPanelDrawer;
    private RankingRecordFileReader rankingRecordFileReader;
    private RankingRecordFileWriter rankingRecordFileWriter;
    private PlayingUsers playingUsers;
    private SceneMenuStart sceneMenuStart;
    private StateOfGame stateOfGame;
    private User currentUser;


    private CommonDataStructure(){

        initScoreSchema();
        diceGenerator = new DiceGenerator();
        rankingRecordFileReader = new RankingRecordFileReader();
        rankingRecordFileWriter = new RankingRecordFileWriter();
        playingUsers = new PlayingUsers(this);
        stateOfGame = new StateOfGame(this);

    }

    public static CommonDataStructure getInstance(){
        if(instance==null){
            instance=new CommonDataStructure();
        }
        return instance;
    }


    public PlayingUsers getPlayingUsers() {
        return playingUsers;
    }

    public void setPlayersInTheGame(List<User> playersInTheGame) {
        this.playersInTheGame = playersInTheGame;
    }

    public List<User> getPlayersInTheGame() {
        return playersInTheGame;
    }


    public List<User> getPlayersWhoNotFinished() {
        return playersWhoNotFinished;
    }

    public void createPlayersWhoNotFinished() {
        playersWhoNotFinished = new ArrayList<>(playersInTheGame);
    }

    public LeftPanelDrawer getLeftPanelDrawer() {
        return leftPanelDrawer;
    }

    public void setLeftPanelDrawer(LeftPanelDrawer leftPanelDrawer) {
        this.leftPanelDrawer = leftPanelDrawer;
    }

    public StateOfGame getStateOfGame() {
        return stateOfGame;
    }

    public void setStateOfGame(StateOfGame stateOfGame) {
        this.stateOfGame = stateOfGame;
    }

    public SceneMenuStart getSceneMenuStart() {
        return sceneMenuStart;
    }

    public void setSceneMenuStart(SceneMenuStart sceneMenuStart) {
        this.sceneMenuStart = sceneMenuStart;
    }

    public List<RankingRecord> getRankingList() {
        return rankingList;
    }

    public int getIndexOfCurentUser() {
        return indexOfCurentUser;
    }

    public void setIndexOfCurentUser(int indexOfCurentUser) {
        this.indexOfCurentUser = indexOfCurentUser;
    }



    public void getRankingFromTheFile(){
        this.rankingList = rankingRecordFileReader.readRankingListFromFile();
    }

    public RankingRecordFileWriter getRankingRecordFileWriter() {
        return rankingRecordFileWriter;
    }

    public void generateDice(){
        diceGenerator.createDiceForPlaying();
    }

    public List<Die> getDiceForPlaying()
    {
        return diceGenerator.getDiceForPlaying();
    }

    public Die getDieFromValue(int dieValue){
        List<Die> diceForPlaying = getDiceForPlaying();
        Die result = null;
        for(Die element : diceForPlaying){
            if(element.getDiceValue()==dieValue){
                return element;
            }
        }
        return result;
    }




    private void initScoreSchema(){
        scoreSchema = new int[][] {
                {2, 0, -30, -20, -10, 0, 10, 20, 30, 40, 50, -100, -100},
                {3, 0, -21, -14, -7, 0, 7, 14, 21, 28, 35, -100, -100},
                {4, 0, -18, -12, -6, 0, 6, 12, 18, 24, 30, -100, -100},
                {5, 0, -15, -10, -5, 0, 5, 10, 15, 20, 25, -100, -100},
                {6, 0, -12, -8, -4, 0, 4, 8, 12, 16, 20, -100, -100},
                {7, 0, -9, -6, -3, 0, 3, 6, 9, 12, 15, -100, -100},
                {8, 0, -12, -8, -4, 0, 4, 8, 12, 16, 20, -100, -100},
                {9, 0, -15, -10, -5, 0, 5, 10, 15, 20, 25, -100, -100},
                {10, 0, -18, -12, -6, 0, 6, 12, 18, 24, 30, -100, -100},
                {11, 0, -21, -14, -7, 0, 7, 14, 21, 28, 35, -100, -100},
                {12, 0, -30, -20, -10, 0, 10, 20, 30, 40, 50, -100, -100}};
    }



    public int getScoreFromSchema(Map<Integer, Integer>scorePointerMap){
        int result= 0;
        for(int i=0; i<11; i++){
            result += scoreSchema[i][scorePointerMap.get(scoreSchema[i][0])];

        }

        return result;
    }

}

