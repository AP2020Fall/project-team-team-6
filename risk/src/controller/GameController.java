package controller;

import model.*;

import java.util.ArrayList;
import java.util.HashMap;

public class GameController {

 private static GameController gameController = new GameController();
 private UserController userController = UserController.getUserController();
 private EventController eventController = EventController.getEventController();
 private DataBase dataBase;
 private GameController() {
       this.dataBase = DataBase.getDataBase();
    }

    public static GameController getGameController() {
        return gameController;
    }

    public HashMap<Integer, RiskGame> getAllRiskGames(){
     return dataBase.getAllRiskGames();
  }
  public int matchCards(Card card1, Card card2, Card card3, Player player) {
     return 0;
  }
  public Card giveNewCard(Player player) {
     return null;
  }

  public static ArrayList<Card> makeAllCardsForGame(){
     //TODO

      return null;
  }

    public ArrayList<Card> playerCards(Player player) {
        //todo
        return null;
    }
    public ArrayList<Card> giveAllPlayerCards(Player player1, Player player2) {
        //todo
        return null;
    }


    public static void makeAllCountries(RiskGame riskGame){
        //TODO hanooz marz haro va rah ertebati ghate va kamel nashode
        makeAllNorthAmericasCountries(riskGame);
        makeAllSouthAmericasCountries(riskGame);
        makeAllAfricaCountries(riskGame);
        makeAllEuropeCountries(riskGame);
        makeAllAsiaCountries(riskGame);
        makeAllAustraliaCountries(riskGame);
    }
    public static void makeAllNorthAmericasCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        String[] countriesName = {"Alaska","Albert","Central America","Eastern United States",
                "Greenland","Northwest Territory","Ontario","Quebec","Western United States"};
        HashMap<Integer , Country> allCountries = new HashMap<>();
        for(int i =1 ; i <= 9 ; i++){
            allCountries.put(i,new Country(i,Continent.NORTH_AMERICA,countriesName[i-1]));
        }
//        dataBase.getAllCountriesWithNumber().putAll(allCountries);
//        dataBase.getAllNorthAmericasCountries().putAll(allCountries);
    }
    public static void makeAllSouthAmericasCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        String[] countriesName ={"Argentina","Brazil","Peru","Venezuela"};
        HashMap<Integer , Country> allCountries = new HashMap<>();
        int counter =0 ;
        for(int i=10 ; i <=13 ; i++){
            allCountries.put(i,new Country(i,Continent.SOUTH_AMERICA , countriesName[counter]));
            counter++;
        }
//        dataBase.getAllCountriesWithNumber().putAll(allCountries);
//        dataBase.getAllSouthAmericasCountries().putAll(allCountries);
    }
    public static void makeAllAfricaCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName= {"Congo","East Africa","Egypt","Madagascar","North Africa" ,"South Africa"};
        int counter = 0;
        for(int i =14 ; i <= 19 ; i++){
            allCountries.put(i,new Country(i,Continent.AFRICA,countriesName[counter]));
            counter++;
        }
//        dataBase.getAllCountriesWithNumber().putAll(allCountries);
//        dataBase.getAllAfricaCountries().putAll(allCountries);
    }
    public static void makeAllEuropeCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName = {"Great Britain","Iceland","Northern Europe","Scandinavia",
                "Southern Europe" , "Ukraine", "Western Europe"};
        int counter = 0;
        for(int i =19 ; i <= 27 ; i++){
            allCountries.put(i,new Country(i,Continent.EUROPE , countriesName[counter]));
            counter++;
        }
//        dataBase.getAllCountriesWithNumber().putAll(allCountries);
//        dataBase.getAllEuropeCountries().putAll(allCountries);
    }
    public static void makeAllAsiaCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName = {"Afghanistan", "China", "India", "Irkutsk", "Japan",
                "Kamchatka", "Middle East", "Mongolia", "Siam (Southeast Asia)", "Siberia", "Ural", "Yakutsk"};
        int counter = 0;
        for(int i =27 ; i <= 39 ; i++){
            allCountries.put(i,new Country(i,Continent.NORTH_AMERICA , countriesName[counter]));
            counter++;
        }
//        dataBase.getAllCountriesWithNumber().putAll(allCountries);
//        dataBase.getAllAsiaCountries().putAll(allCountries);
    }
    public static void makeAllAustraliaCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName ={"Eastern Australia","Indonesia","New Guinea","Western Australia"};
        int counter  =0;
        for(int i =38 ; i <= 42 ; i++){
            allCountries.put(i,new Country(i,Continent.AUSTRALIA , countriesName[counter]));
            counter++;
        }
//        dataBase.getAllCountriesWithNumber().putAll(allCountries);
//        dataBase.getAllAsiaCountries().putAll(allCountries);
    }

    public static  Player[] makeOfflinePlayers(String[] playersName){
      Player[] players = new Player[playersName.length];
      for(int i = 0; i < playersName.length ; i++){
          Player player = new Player("","",playersName[i] ,
                                     "","","");
          players[i] = player;
      }
      return players;
    }

    //GAMES METHODS ----------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------
    public void makeOnlineGameForPlayer(String name , Player creator , RiskGameType riskGameType , int numberOfPlayers){
        //TODO......
    }
    public void makeOnlineGameForEvent(String name , RiskGameType riskGameType , int numberOfPlayers , int gamePoint){
       //TODO......
    }
    public void makeOfflineGame(String name , String[] playersName , int numberOfPlayers){
      //TODO ......
    }
    public boolean checkGame(RiskGame riskGame){
     return true;
     //TODO ......
    }

    public void startGame(RiskGame riskGame , Player creator){
      //TODO.....
    }

    public boolean isGameStarted(RiskGame riskGame){
     //TODO ....
        return true;
    }
    public void addSoldiersForStartingGame(RiskGame riskGame , int numberOfPlayers){
     //TODO ....
    }
    public void addSoldiers(Player player  , int numbersOfSoldiers , RiskGame riskGame){
     //TODO .......
    }
    public void calculateNumberOfSoldiersToAdd(Player player){

    }
    public void placeSoldiers(RiskGame  riskGame , int numbersOfSoldiers , Player player){
     //TODO .........
    }
    public boolean attack(Country country , Player attacker , Player defender , RiskGame riskGame){
     //TODO .....
        return true;
    }
    public int calculateDiceNumber(boolean isAttack , Country country , RiskGame riskGame){
        //TODO
        return 0;
    }
    public int rollDice(){
        //TODO
        return 0;
    }
    public void endAttack(Player player , RiskGame riskGame){
     //TODO
    }

    public void moveSoldiers(Player player , RiskGame riskGame){

    }
    public void endFortify(Player player , RiskGame riskGame){

    }
    public void addCardToPlayer(RiskGame riskGame , Player player){
     //TODO
    }

    public void nextTurn(RiskGame riskGame){
     //TODO
    }
    public void endGame(RiskGame riskGame){
     //TODO .....
    }
    public void setNewGameInformationForPlayers(Player[] players){
     //TODO ......
    }

    public void doBlizzard(RiskGame riskGame){
     //TODO
    }
    public void fogOfWar(RiskGame riskGame){
     //TODO
    }
    public void setTimerForEachPlayer(Long timer , RiskGame riskGame){
     //TODO ....
    }
    public void checkTimer(Player player , RiskGame riskGame){
     //TODO
    }
    public void setCountriesUnManually(RiskGame riskGame){
     //TODO
    }









}
