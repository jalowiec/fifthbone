package com.jalowiec;

import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import java.text.SimpleDateFormat;
import java.util.*;

public class RoundEnd {

    private GridPane gridPane;
    private List<Integer> fifthDiceList = new ArrayList<>();
    private List<Integer> alternativeFifthDiceList = new ArrayList<>();
    private Map<Integer, Integer> chosenFifthDice = new HashMap<>();
    private Map<Integer, Integer> alternativeChosenFifthDice = new HashMap<>();
    private CommonDataStructure commonDataStructure;
    private GameTableDrawer gameTableDrawer;
    private User user;
    private boolean isRoundEnd;
    private boolean isGameEnd;


    public RoundEnd(User user) {
        this.gridPane = user.getGridPane();
        commonDataStructure = CommonDataStructure.getInstance();
        gameTableDrawer = new GameTableDrawer(user);
        this.user = user;
    }

    public boolean isRoundEnd() {
        return isRoundEnd;
    }

    public void setRoundEnd(boolean roundEnd) {
        isRoundEnd = roundEnd;
    }

    public boolean isGameEnd() {
        return isGameEnd;
    }

    public void setGameEnd(boolean gameEnd) {
        isGameEnd = gameEnd;
    }

    public Set<Integer> getChosenFifthDiceSet() {
        return chosenFifthDice.keySet();
    }

    public Set<Integer> getAlternativeChosenFifthDice() {
        return alternativeChosenFifthDice.keySet();
    }

    private void processFifthDie(int fifthDieValue) {
        if (!fifthDiceList.contains(fifthDieValue)) {
            if (fifthDiceList.size() < 3) {
                fifthDiceList.add(fifthDieValue);
                gameTableDrawer.drawChosenFifthDie(fifthDieValue, chosenFifthDice.size());
                chosenFifthDice.put(fifthDieValue, 0);
            }
        } else {
            chosenFifthDice.put(fifthDieValue, chosenFifthDice.get(fifthDieValue) + 1);
            gameTableDrawer.drawChosenFifthDieSlots(chosenFifthDice.get(fifthDieValue), fifthDiceList.indexOf(fifthDieValue));
        }
    }

    private void processAlternativeFifthDie(int alternativeFifthDieValue) {
        if (!alternativeFifthDiceList.contains(alternativeFifthDieValue)) {
            if (alternativeFifthDiceList.size() < 3) {
                alternativeFifthDiceList.add(alternativeFifthDieValue);
                chosenFifthDice.put(alternativeFifthDieValue, 0);
            }
        } else {
            chosenFifthDice.put(alternativeFifthDieValue, chosenFifthDice.get(alternativeFifthDieValue) + 1);
        }
    }

    public boolean isFifthSlotFree(int fifthDieValue) {
        if (chosenFifthDice.containsKey(fifthDieValue) && chosenFifthDice.get(fifthDieValue) == 8) {
            return false;
        }
        return true;
    }


    public boolean isPairSlotFree(int pairSum) {
        Map<Integer, Integer> scorePointerMap = user.getUserDataStructures().getScorePointerMap();

        if (scorePointerMap.get(pairSum) > 9) {
            return false;
        }
        return true;
    }

    private void processComputerRound() {


        int[] freeSlotState = orderComputerChoice();
        user.getUserDataStructures().setFreeSlotState(freeSlotState);

        if (user instanceof UserPC) {
            UserPC userPC = (UserPC) user;
            if (userPC.getUserLevel() == UserLevel.MEDIUM) {
                freeSlotState = calculateOptimumFourDice();
                user.getUserDataStructures().setFreeSlotState(freeSlotState);
            }
        }
        drawComputerChoice(user.getUserDataStructures().getFreeSlotState());
    }

    private void processAlternativeUserChoice() {

        int[] alternativeFreeSlotState = orderComputerChoice();
        user.getUserDataStructures().setAlternativeSlotState(alternativeFreeSlotState);

    }

    private int[] orderComputerChoice() {
        Die[] diceArray = user.getUserDataStructures().getDiceArray();
        int[] freeSlotState;
        if (user.getPC()) {
            freeSlotState = user.getUserDataStructures().getFreeSlotState();
        } else {
            freeSlotState = user.getUserDataStructures().getAlternativeSlotState();
        }

        for (int i = 0, j = 0; i < diceArray.length; i++) {
            if (i != getComputerFifthDieIndex()) {
                freeSlotState[j] = i;
                j++;
            }
        }
        return freeSlotState;
    }

    private int[] calculateOptimumFourDice() {

        int[] freeSlotState = user.getUserDataStructures().getFreeSlotState();
        int[] freeSlotStateFilter;
        int[] newFreeSlotState = new int[4];
        int[] firstSetDice = {0, 1, 2, 3};
        int[] secondSetDice = {0, 2, 1, 3};
        int[] thirdSetDice = {0, 3, 1, 2};
        int predictFirstSetDice = predictScoreFourDice(firstSetDice);
        int predictSecondSetDice = predictScoreFourDice(secondSetDice);
        int predictThirdSetDice = predictScoreFourDice(thirdSetDice);

        System.out.println(predictFirstSetDice + " " + predictSecondSetDice + " " + predictThirdSetDice);

        freeSlotStateFilter = firstSetDice;
        if (predictSecondSetDice > predictFirstSetDice) {
            freeSlotStateFilter = secondSetDice;
        }
        if (predictThirdSetDice > predictSecondSetDice) {
            freeSlotStateFilter = thirdSetDice;
        }

        for (int i = 0; i < newFreeSlotState.length; i++) {
            newFreeSlotState[i] = freeSlotState[freeSlotStateFilter[i]];
        }
        return newFreeSlotState;
    }

    private int predictScoreFourDice(int[] chosenElementsSetDice) {
        Die[] diceArray = user.getUserDataStructures().getDiceArray();
        int[] predictFreeSlotState = user.getUserDataStructures().getFreeSlotState().clone();
        Map<Integer, Integer> predictScorePointerMap = new HashMap<>(user.getUserDataStructures().getScorePointerMap());
        int predictResult = 0;
        int predictFirstPairSum = diceArray[predictFreeSlotState[chosenElementsSetDice[0]]].getDiceValue() + diceArray[predictFreeSlotState[chosenElementsSetDice[1]]].getDiceValue();
        int firstCouplePointer = predictScorePointerMap.get(predictFirstPairSum);
        predictScorePointerMap.replace(predictFirstPairSum, ++firstCouplePointer);

        int predictSecondPairSum = diceArray[predictFreeSlotState[chosenElementsSetDice[2]]].getDiceValue() + diceArray[predictFreeSlotState[chosenElementsSetDice[3]]].getDiceValue();
        int secondCouplePointer = predictScorePointerMap.get(predictSecondPairSum);
        predictScorePointerMap.replace(predictSecondPairSum, ++secondCouplePointer);


        predictResult = commonDataStructure.getScoreFromSchema(predictScorePointerMap);

        return predictResult;
    }


    private int getComputerFifthDieIndex() {
        Set<Integer> fifthDieSet;
        if(user.getPC()){
            fifthDieSet = getChosenFifthDiceSet();
        } else {
            fifthDieSet = getAlternativeChosenFifthDice();
        }

        Die[] diceArray = user.getUserDataStructures().getDiceArray();
        int result = 0;
        if (fifthDieSet.size() < 3) {
            for (int i = 0; i < diceArray.length - 1; i++) {
                if (fifthDieSet.contains(diceArray[i].getDiceValue())) {
                    result++;
                } else {
                    return i;
                }
            }
        } else {
            int lowerUsedFifthDiceNumber = getLowestUsedFifthDiceNumber();
            for (int i = 0; i < diceArray.length; i++) {
                if (diceArray[i].getDiceValue() == lowerUsedFifthDiceNumber) {
                    return i;
                }
            }
        }

        return result;

    }

    private boolean isFifthDieOnDiceArray(int fifthDieValue) {
        Die[] diceArray = user.getUserDataStructures().getDiceArray();
        for (int i = 0; i < diceArray.length; i++) {
            if (fifthDieValue == diceArray[i].getDiceValue()) {
                return true;
            }
        }

        return false;
    }

    private int getLowestUsedFifthDiceNumber() {
        int resultKey = 0;
        int resultValue = 9;
        Set<Integer> chosenFifthDiceSet;
        if (user.getPC()) {
            chosenFifthDiceSet = getChosenFifthDiceSet();
        }else{
            chosenFifthDiceSet = getAlternativeChosenFifthDice();
        }
        for (Integer key : chosenFifthDiceSet) {
            if (chosenFifthDice.get(key) < resultValue && isFifthDieOnDiceArray(key)) {
                resultKey = key;
                resultValue = chosenFifthDice.get(key);

            }
        }
        return resultKey;

    }

    private void drawComputerChoice(int[] freeSlotState) {

        List<ImageView> imageViewList = user.getUserDataStructures().getImageViewList();
        List<DieSlot> freeSlotsList = user.getUserDataStructures().getFreeSlotsList();
        for (int i = 0; i < freeSlotState.length; i++) {


            gridPane.getChildren().remove(imageViewList.get(freeSlotState[i]));
            gridPane.add(imageViewList.get(freeSlotState[i]),
                    freeSlotsList.get(i).getColumnIndex(),
                    freeSlotsList.get(i).getRowIndex(),
                    freeSlotsList.get(i).getColumnSpan(),
                    freeSlotsList.get(i).getRowSpan());
        }
    }


    public void  countScoreAfterRound(GameTableDrawer gameTableDrawer) {


        if (user.getPC()) {
            processComputerRound();
            user.getGameTableDrawer().getEndTurnButton().setDisable(false);
        } else {
            processAlternativeUserChoice();
        }

        Map<Integer, Integer> scorePointerMap = user.getUserDataStructures().getScorePointerMap();
        int chosenFifthDieValue = getFifthDieValue(user.getUserDataStructures().getFreeSlotState().clone());
        int firstPairSum = getFirstPairSum(user.getUserDataStructures().getFreeSlotState());
        int secondPairSum = getSecondPairSum(user.getUserDataStructures().getFreeSlotState());
        int firstCouplePointer = scorePointerMap.get(firstPairSum);
        int secondCouplePointer;

        if (!isFifthSlotFree(chosenFifthDieValue) || !isPairSlotFree(firstPairSum)) {
            endGameForUser(user);
            gameTableDrawer.drawScore(commonDataStructure.getScoreFromSchema(scorePointerMap));
        } else {
            scorePointerMap.replace(firstPairSum, ++firstCouplePointer);
            gameTableDrawer.drawUsedSlotsAfterRound(firstPairSum, firstCouplePointer);
            processFifthDie(chosenFifthDieValue);
            gameTableDrawer.drawScore(commonDataStructure.getScoreFromSchema(scorePointerMap));

            if (!isPairSlotFree(secondPairSum)) {
                endGameForUser(user);
                gameTableDrawer.drawScore(commonDataStructure.getScoreFromSchema(scorePointerMap));
            } else {
                secondCouplePointer = scorePointerMap.get(secondPairSum);
                scorePointerMap.replace(secondPairSum, ++secondCouplePointer);
                gameTableDrawer.drawUsedSlotsAfterRound(secondPairSum, secondCouplePointer);
                gameTableDrawer.drawScore(commonDataStructure.getScoreFromSchema(scorePointerMap));
            }
        }

        if(!user.getPC()){
            Map<Integer, Integer> alternativeScorePointerMap = user.getUserDataStructures().getAlternativeScorePointerMap();
            System.out.println(user.getUserDataStructures().getAlternativeScorePointerMap());
            int alternativeChosenFifthDieValue = getFifthDieValue(user.getUserDataStructures().getAlternativeSlotState().clone());
            int alternativeFirstPairSum = getFirstPairSum(user.getUserDataStructures().getAlternativeSlotState());
            int alternativeSecondPairSum = getSecondPairSum(user.getUserDataStructures().getAlternativeSlotState());
            int alternativeFirstCouplePointer = alternativeScorePointerMap.get(alternativeFirstPairSum);
            int alternativeSecondCouplePointer;

            if (!isFifthSlotFree(alternativeChosenFifthDieValue) || !isPairSlotFree(alternativeFirstPairSum)) {
                //endGameForUser(user);
                user.getUserDataStructures().setAlternativeScoreText(new Text(Integer.toString(commonDataStructure.getScoreFromSchema(alternativeScorePointerMap))));
            } else {
                alternativeScorePointerMap.replace(alternativeFirstPairSum, ++alternativeFirstCouplePointer);
                processAlternativeFifthDie(alternativeChosenFifthDieValue);
                user.getUserDataStructures().setAlternativeScoreText(new Text(Integer.toString(commonDataStructure.getScoreFromSchema(alternativeScorePointerMap))));

                if (!isPairSlotFree(alternativeSecondPairSum)) {
                    //endGameForUser(user);
                    user.getUserDataStructures().setAlternativeScoreText(new Text(Integer.toString(commonDataStructure.getScoreFromSchema(alternativeScorePointerMap))));
                } else {
                    alternativeSecondCouplePointer = alternativeScorePointerMap.get(alternativeSecondPairSum);
                    alternativeScorePointerMap.replace(alternativeSecondPairSum, ++alternativeSecondCouplePointer);
                    user.getUserDataStructures().setAlternativeScoreText(new Text(Integer.toString(commonDataStructure.getScoreFromSchema(alternativeScorePointerMap))));
                }
            }

            System.out.println(user.getUserDataStructures().getAlternativeScorePointerMap());
        }



        user.getRoundProccesorUser().setEndTurnButtonDisabled();
        setRoundEnd(true);
        user.getGameTableDrawer().getNextPlayerButton().setDisable(false);
        commonDataStructure.getLeftPanelDrawer().drawPlayingUsersScoreInPanel();


    }

    private void endGameForUser(User user) {
        System.out.println("KONIEC GRY DLA UZYTKOWNIKA: " + user.getUserName());
        RankingRecordDrawer rankingRecordDrawer = commonDataStructure.getLeftPanelDrawer().getRankingRecordDrawer();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        RankingRecord rankingRecordAfterEndGame = new RankingRecord(user.getUserName(), Integer.parseInt(user.getUserDataStructures().getScoreValue()), simpleDateFormat.format(new Date()));
        if (rankingRecordDrawer.isShouldBeAddedToRanking(rankingRecordAfterEndGame)) {
            rankingRecordDrawer.addToRanking(rankingRecordAfterEndGame);
        }
        setGameEnd(true);
        commonDataStructure.getPlayersWhoNotFinished().remove(user);

    }


    public int getFifthDieValue(int[] chosenSlots) {

        Arrays.sort(chosenSlots);
        int fifthDieIndex = 4;
        for (int i = 0; i < 4; i++) {
            if (i != chosenSlots[i]) {
                fifthDieIndex = i;
                break;
            }
        }
        Die[] diceArray = user.getUserDataStructures().getDiceArray();
        return diceArray[fifthDieIndex].getDiceValue();
    }

    public int getFirstPairSum(int[] freeSlotState) {
        Die[] diceArray = user.getUserDataStructures().getDiceArray();
         return diceArray[freeSlotState[0]].getDiceValue() + diceArray[freeSlotState[1]].getDiceValue();
    }

    public int getSecondPairSum(int[] freeSlotState) {
        Die[] diceArray = user.getUserDataStructures().getDiceArray();
          return diceArray[freeSlotState[2]].getDiceValue() + diceArray[freeSlotState[3]].getDiceValue();
    }


}
