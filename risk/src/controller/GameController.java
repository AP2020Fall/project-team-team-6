package controller;

import model.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        makeAllNorthAmericasCountries(riskGame);
        makeAllSouthAmericasCountries(riskGame);
        makeAllAfricaCountries(riskGame);
        makeAllEuropeCountries(riskGame);
        makeAllAsiaCountries(riskGame);
        makeAllAustraliaCountries(riskGame);
        makeCountriesNeighbours(riskGame);
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

//    public static  Player[] makeOfflinePlayers(String[] playersName){
//      Player[] players = new Player[playersName.length];
//      for(int i = 0; i < playersName.length ; i++){
//          Player player = makeOfflinePlayerWithColor()
//          players[i] = player;
//      }
//      return players;
//    }
    private static void makeCountriesNeighbours(RiskGame riskGame){
     String map = RiskGame.getDefaultMap();
     HashMap<Integer, Country> allCountries = riskGame.getAllCountriesWithNumber();
     String[] rows = map.split("\\s+");
     Country neighbourCountry = null;
     Pattern pattern = Pattern.compile("\\d+");
     Matcher matcher;
     for(int i =0 ; i < rows.length; i++){
         matcher = pattern.matcher(rows[i]);
         if(!matcher.find())
             continue;
         int countriesNumber = Integer.parseInt(rows[i]);
         int neighbourCountryNumber;
         Country country = allCountries.get(countriesNumber);
          matcher = pattern.matcher(rows[i]);
         if(i-1 >= 0 && calculateRowNumber(i)== calculateRowNumber(i-1)){
             matcher = pattern.matcher(rows[i-1]);
             if(matcher.find()) {
                 neighbourCountryNumber = Integer.parseInt(rows[i - 1]);
                 neighbourCountry = allCountries.get(neighbourCountryNumber);
                 country.getNeighboringCountries().add(neighbourCountry);
             }
         }
         if(i+1 <= rows.length  && calculateRowNumber(i)== calculateRowNumber(i+1)){
             matcher = pattern.matcher(rows[i+1]);
             if(matcher.find()) {
                 neighbourCountryNumber = Integer.parseInt(rows[i + 1]);
                 neighbourCountry = allCountries.get(neighbourCountryNumber);
                 country.getNeighboringCountries().add(neighbourCountry);
             }
         }
         if(i-23 >= 0){
             matcher = pattern.matcher(rows[i-23]);
             if(matcher.find()) {
                 neighbourCountryNumber = Integer.parseInt(rows[i - 23]);
                 neighbourCountry = allCountries.get(neighbourCountryNumber);
                 country.getNeighboringCountries().add(neighbourCountry);
             }
         }
         if(i+23 <= rows.length){
             matcher = pattern.matcher(rows[i+23]);
             if(matcher.find()) {
                 neighbourCountryNumber = Integer.parseInt(rows[i + 23]);
                 neighbourCountry = allCountries.get(neighbourCountryNumber);
                 country.getNeighboringCountries().add(neighbourCountry);
             }
         }
     }
    }
    private static int calculateRowNumber(int countriesNumber){
     return countriesNumber/23;
    }

    public  Player makeOfflinePlayerWithColor(String userName , Color color){
     Player player = new Player("","",userName,"" ,"" , "");
     player.setCurrentColor(color);
     return player;
    }


    //GAMES METHODS ----------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------
    public void makeOnlineGameForPlayer(String name , Player creator , RiskGameType riskGameType , int numberOfPlayers){
        //TODO......
    }
    public void makeOnlineGameForEvent(String name , RiskGameType riskGameType , int numberOfPlayers , int gamePoint){
       //TODO......
    }
    public void makeOfflineGame(String name , Player[] players, int numberOfPlayers , long timer , boolean isMapManually){
        RiskGame offlineGame = new RiskGame(name , players , numberOfPlayers , timer , isMapManually );
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
    public void addSoldiersForStartingGame(RiskGame riskGame){
      int numberOfPlayers = riskGame.getNumberOfPlayers();
      int numberOfSoldiers =0;
      if(numberOfPlayers == 2){
          numberOfSoldiers = 40;
      }else if(numberOfPlayers == 3){
          numberOfSoldiers = 35;
      }else if(numberOfPlayers == 4){
          numberOfSoldiers = 30;
      }else if(numberOfPlayers == 5){
          numberOfSoldiers = 25;
      }else if(numberOfPlayers == 6){
          numberOfSoldiers = 20;
      }
      for(Player player : riskGame.getPlayers()){
         player.setNumberOfSoldiers(numberOfSoldiers);
      }
    }
    public void addSoldiers(Player player  , int numbersOfSoldiers , RiskGame riskGame){
     //TODO .......
    }
    public void calculateNumberOfSoldiersToAdd(Player player){

    }
    public void placeSoldiers(Country country ,int numbersOfSoldiers , Player player ) throws Exception{
      if(numbersOfSoldiers > player.getNumberOfSoldiers())
          throw new Exception("You don't have this much soldiers");
      if(country.getColor() != null && country.getColor() != player.getCurrentColor())
          throw new Exception("This country is not yours");
      if(country.getColor() == null){
          country.setColor(player.getCurrentColor());
      }
      addSoliderToCountry(country , numbersOfSoldiers);
      removeSoldiersFormPlayer(player,numbersOfSoldiers);

    }

    public void placeSoldiersForStartingStage(RiskGame riskGame ,Country country , Player player ) throws Exception {
     if (riskGame.getGameStages().equals(GameStages.STARTING)) {
         if (checkCountriesForStartingStage(riskGame)) {
             if (country.getColor() != null && country.getColor() != player.getCurrentColor())
                 throw new Exception("This country is not yours");
             else
                 placeSoldiers(country, 1, player);
         } else {
             if (country.getColor() != null)
                 throw new Exception("You can't put soldiers in this country while you are in starting stage");
             placeSoldiers(country, 1, player);
         }
     }else{
         throw new Exception("You are not in starting stage");
     }
    }
    public int calculateNumberOfSoldiersForStartingStage(int playersNumber){
       int numberOfSoldiers = 40;
       for(int i = 2; i < playersNumber ; i++){
           numberOfSoldiers -=5;
       }
       return numberOfSoldiers * playersNumber;
    }

    public void goNextStage(RiskGame riskGame){
       if(riskGame.getGameStages().equals(GameStages.STARTING))
           riskGame.setGameStages(GameStages.DRAFT);
       else if(riskGame.getGameStages().equals(GameStages.DRAFT))
           riskGame.setGameStages(GameStages.ATTACK);
       else if(riskGame.getGameStages().equals(GameStages.ATTACK))
           riskGame.setGameStages(GameStages.FORTIFY);
    }
    public boolean checkCountriesForStartingStage(RiskGame riskGame){
       HashMap<Integer , Country> allCountries = riskGame.getAllCountriesWithNumber();

       for(Country country : allCountries.values()){
           if(country.getColor() ==null)
               return false;
       }

       return true;
    }

    public void addSoliderToCountry(Country country , int numberOfSoldiers){
      int numberOfSoldiersInCountry = country.getNumberOfSoldiers();
      numberOfSoldiersInCountry+= numberOfSoldiers;
      country.setNumberOfSoldiers(numberOfSoldiersInCountry);
    }

    public void removeSoldiersFormPlayer(Player player , int numberOfSoldiers) throws Exception {
      int numberOfCurrentSoldiers = player.getNumberOfSoldiers();
      if(numberOfSoldiers> numberOfCurrentSoldiers)
          throw new Exception("Error");
      numberOfCurrentSoldiers -= numberOfSoldiers;
      player.setNumberOfSoldiers(numberOfCurrentSoldiers);
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

    public void nextPlayer(RiskGame riskGame){
      Player[] players = riskGame.getPlayers();
      ArrayList<Player> gamePlayers = new ArrayList<>(Arrays.asList(players));
      Player currentPlayer = riskGame.getCurrentPlayer();

      int playersIndex = gamePlayers.indexOf(currentPlayer);
      if(playersIndex == gamePlayers.size()-1)
          currentPlayer = gamePlayers.get(0);
      else{
          currentPlayer = gamePlayers.get(playersIndex+1);
      }
      riskGame.setCurrentPlayer(currentPlayer);
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

    public ArrayList<Color> getDefaultColors(){
     Color[] colors = Color.getDefaultColor();
        ArrayList<Color> defaultColors = new ArrayList<>(Arrays.asList(colors));
     return defaultColors;
    }

    public HashMap<Integer , Color> getColorsToChose(ArrayList<Color> colors){
     HashMap<Integer, Color> colorsToChose = new HashMap<>();
     int counter = 1;
     for(Color c : colors){
         colorsToChose.put(counter , c);
         counter++;
     }
     return colorsToChose;
    }









}
