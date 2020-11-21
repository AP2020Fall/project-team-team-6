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
        String[] countriesName = {"Alaska","Albert","Central America","Eastern United States",
                "Greenland","Northwest Territory","Ontario","Quebec","Western United States"};
        HashMap<Integer , Country> allCountries = new HashMap<>();
        for(int i =1 ; i <= 9 ; i++){
            allCountries.put(i,new Country(i,Continent.NORTH_AMERICA,countriesName[i-1]));
        }
        riskGame.getAllNorthAmericasCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
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
        riskGame.getAllSouthAmericasCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
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
        riskGame.getAllAfricaCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);

    }
    public static void makeAllEuropeCountries(RiskGame riskGame){
        DataBase dataBase = DataBase.getDataBase();
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName = {"Great Britain","Iceland","Northern Europe","Scandinavia",
                "Southern Europe" , "Ukraine", "Western Europe"};
        int counter = 0;
        for(int i =20 ; i <= 26 ; i++){
            allCountries.put(i,new Country(i,Continent.EUROPE , countriesName[counter]));
            counter++;
        }
        riskGame.getAllEuropeCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
    }
    public static void makeAllAsiaCountries(RiskGame riskGame){
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName = {"Afghanistan", "China", "India", "Irkutsk", "Japan",
                "Kamchatka", "Middle East", "Mongolia", "Siam (Southeast Asia)", "Siberia", "Ural", "Yakutsk"};
        int counter = 0;
        for(int i =27 ; i <= 38 ; i++){
            allCountries.put(i,new Country(i,Continent.NORTH_AMERICA , countriesName[counter]));
            counter++;
        }
        riskGame.getAllAsiaCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
    }
    public static void makeAllAustraliaCountries(RiskGame riskGame){
        HashMap<Integer , Country> allCountries = new HashMap<>();
        String[] countriesName ={"Eastern Australia","Indonesia","New Guinea","Western Australia"};
        int counter  =0;
        for(int i =39 ; i <= 42 ; i++){
            allCountries.put(i,new Country(i,Continent.AUSTRALIA , countriesName[counter]));
            counter++;
        }
        riskGame.getAllAustraliaCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
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
    public void makeOfflineGame(String name , String[] playersName , int numberOfPlayers , long timer , boolean isMapManually){
        RiskGame offlineGame = new RiskGame(name , playersName , numberOfPlayers , timer , isMapManually );
        RiskGame.setOfflineGame(offlineGame);
    }
    public boolean checkGame(RiskGame riskGame){
     return true;
     //TODO ......
    }

    public RiskGame getOfflineGame(){
      return RiskGame.getOfflineGame();
    }
    public void setOfflineGame(RiskGame riskGame){
     RiskGame.setOfflineGame(riskGame);
    }
    public boolean checkNumberOfPlayers(int numberOfPlayers) throws Exception {
     if(numberOfPlayers < 2 ){
         throw new Exception("You should have at least two player to start the game");
     } else if (numberOfPlayers > 6) {
         throw new Exception("You can't have more than six player in this game") ;
     }else{
         return true;
     }
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
