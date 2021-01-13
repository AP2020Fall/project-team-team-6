package controller;

import model.database.LocalDataBase;
import model.database.MySqlDataBase;
import model.gamesModels.*;
import model.usersModels.Player;
import model.usersModels.RequestForPlaying;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class GameController {

    private static GameController gameController = new GameController();
    private UserController userController = UserController.getUserController();
    private EventController eventController = EventController.getEventController();
    private LocalDataBase localDataBase;

    private GameController() {
        this.localDataBase = localDataBase.getLocalDataBase();
    }

    public static GameController getGameController() {
        return gameController;
    }

    public ArrayList<RiskGame> getAllRiskGames() {
        return localDataBase.getAllRiskGames();
    }

    public int matchCards(Card card1, Card card2, Card card3, Player player) {
        return 0;
    }

    public Card giveNewCard(Player player) {
        return null;
    }

    public static ArrayList<Card> makeAllCardsForGame() {
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


    public static void makeAllCountries(RiskGame riskGame) {
        makeAllNorthAmericasCountries(riskGame);
        makeAllSouthAmericasCountries(riskGame);
        makeAllAfricaCountries(riskGame);
        makeAllEuropeCountries(riskGame);
        makeAllAsiaCountries(riskGame);
        makeAllAustraliaCountries(riskGame);
        makeCountriesNeighbours(riskGame);
    }

    public static void makeAllNorthAmericasCountries(RiskGame riskGame) {
        String[] countriesName = {"Alaska", "Albert", "Central America", "Eastern United States",
                "Greenland", "Northwest Territory", "Ontario", "Quebec", "Western United States"};
        HashMap<Integer, Country> allCountries = new HashMap<>();
        for (int i = 1; i <= 9; i++) {
            allCountries.put(i, new Country(i, Continent.NORTH_AMERICA, countriesName[i - 1]));
        }
        riskGame.getAllNorthAmericasCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
    }

    public static void makeAllSouthAmericasCountries(RiskGame riskGame) {
        LocalDataBase localDataBase = LocalDataBase.getLocalDataBase();
        String[] countriesName = {"Argentina", "Brazil", "Peru", "Venezuela"};
        HashMap<Integer, Country> allCountries = new HashMap<>();
        int counter = 0;
        for (int i = 10; i <= 13; i++) {
            allCountries.put(i, new Country(i, Continent.SOUTH_AMERICA, countriesName[counter]));
            counter++;
        }
        riskGame.getAllSouthAmericasCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
    }

    public static void makeAllAfricaCountries(RiskGame riskGame) {
        HashMap<Integer, Country> allCountries = new HashMap<>();
        String[] countriesName = {"Congo", "East Africa", "Egypt", "Madagascar", "North Africa", "South Africa"};
        int counter = 0;
        for (int i = 14; i <= 19; i++) {
            allCountries.put(i, new Country(i, Continent.AFRICA, countriesName[counter]));
            counter++;
        }
        riskGame.getAllAfricaCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);

    }

    public static void makeAllEuropeCountries(RiskGame riskGame) {
        HashMap<Integer, Country> allCountries = new HashMap<>();
        String[] countriesName = {"Great Britain", "Iceland", "Northern Europe", "Scandinavia",
                "Southern Europe", "Ukraine", "Western Europe"};
        int counter = 0;
        for (int i = 20; i <= 26; i++) {
            allCountries.put(i, new Country(i, Continent.EUROPE, countriesName[counter]));
            counter++;
        }
        riskGame.getAllEuropeCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
    }

    public static void makeAllAsiaCountries(RiskGame riskGame) {
        HashMap<Integer, Country> allCountries = new HashMap<>();
        String[] countriesName = {"Afghanistan", "China", "India", "Irkutsk", "Japan",
                "Kamchatka", "Middle East", "Mongolia", "Siam (Southeast Asia)", "Siberia", "Ural", "Yakutsk"};
        int counter = 0;
        for (int i = 27; i <= 38; i++) {
            allCountries.put(i, new Country(i, Continent.NORTH_AMERICA, countriesName[counter]));
            counter++;
        }
        riskGame.getAllAsiaCountries().putAll(allCountries);
        riskGame.getAllCountriesWithNumber().putAll(allCountries);
    }

    public static void makeAllAustraliaCountries(RiskGame riskGame) {
        HashMap<Integer, Country> allCountries = new HashMap<>();
        String[] countriesName = {"Eastern Australia", "Indonesia", "New Guinea", "Western Australia"};
        int counter = 0;
        for (int i = 39; i <= 42; i++) {
            allCountries.put(i, new Country(i, Continent.AUSTRALIA, countriesName[counter]));
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
    private static void makeCountriesNeighbours(RiskGame riskGame) {
        String map = RiskGame.getDefaultMap();
        HashMap<Integer, Country> allCountries = riskGame.getAllCountriesWithNumber();
        String[] rows = map.split("\\s+");
        Country neighbourCountry = null;
        Pattern pattern = Pattern.compile("\\d+");
        Matcher matcher;
        for (int i = 0; i < rows.length; i++) {
            matcher = pattern.matcher(rows[i]);
            if (!matcher.find())
                continue;
            int countriesNumber = Integer.parseInt(rows[i]);
            int neighbourCountryNumber;
            Country country = allCountries.get(countriesNumber);
            matcher = pattern.matcher(rows[i]);
            if (i - 1 >= 0 && calculateRowNumber(i) == calculateRowNumber(i - 1)) {
                matcher = pattern.matcher(rows[i - 1]);
                if (matcher.find()) {
                    neighbourCountryNumber = Integer.parseInt(rows[i - 1]);
                    neighbourCountry = allCountries.get(neighbourCountryNumber);
                    country.getNeighboringCountries().add(neighbourCountry);
                }
            }
            if (i + 1 <= rows.length && calculateRowNumber(i) == calculateRowNumber(i + 1)) {
                matcher = pattern.matcher(rows[i + 1]);
                if (matcher.find()) {
                    neighbourCountryNumber = Integer.parseInt(rows[i + 1]);
                    neighbourCountry = allCountries.get(neighbourCountryNumber);
                    country.getNeighboringCountries().add(neighbourCountry);
                }
            }
            if (i - 23 >= 0) {
                matcher = pattern.matcher(rows[i - 23]);
                if (matcher.find()) {
                    neighbourCountryNumber = Integer.parseInt(rows[i - 23]);
                    neighbourCountry = allCountries.get(neighbourCountryNumber);
                    country.getNeighboringCountries().add(neighbourCountry);
                }
            }
            if (i + 23 <= rows.length) {
                matcher = pattern.matcher(rows[i + 23]);
                if (matcher.find()) {
                    neighbourCountryNumber = Integer.parseInt(rows[i + 23]);
                    neighbourCountry = allCountries.get(neighbourCountryNumber);
                    country.getNeighboringCountries().add(neighbourCountry);
                }
            }
        }
    }

    private static int calculateRowNumber(int countriesNumber) {
        return countriesNumber / 23;
    }

    public Player makeOfflinePlayerWithColor(String userName, Color color) {
        Player player = new Player("", "", userName, "", "", "");
        player.setCurrentColor(color);
        return player;
    }

    public void invitePlayerToGame(Player player , RiskGame riskGame){
        RequestForPlaying requestForPlaying = new RequestForPlaying(riskGame.getCreator() , player , riskGame);
        player.getRequestForPlaysList().add(requestForPlaying);
    }

    public void deleteRiskGame(RiskGame riskGame){
        localDataBase.getAllRiskGames().remove(riskGame);
    }


    //GAMES METHODS ----------------------------------------------------------------------------------
    //-------------------------------------------------------------------------------------------------
    public void addGameToLocalDataBase(RiskGame riskGame){
        localDataBase.getAllRiskGames().add(riskGame);
    }

    public void makeOnlineGameForEvent(String name, RiskGameType riskGameType, int numberOfPlayers, int gamePoint) {
        //TODO......
    }

    public void makeOfflineGame(String name, Player[] players, int numberOfPlayers, long timer, boolean isMapManually) {
        RiskGame offlineGame = new RiskGame(name, players, numberOfPlayers, timer, isMapManually);
        RiskGame.setOfflineGame(offlineGame);
    }

    public boolean checkGame(RiskGame riskGame) {
       int currentPlayersInGame =0 ;
       for(Player player : riskGame.getPlayers()){
           if(player != null)
               currentPlayersInGame++;
       }
        return currentPlayersInGame > 1;
    }

    public RiskGame getOfflineGame() {
        return RiskGame.getOfflineGame();
    }

    public void setOfflineGame(RiskGame riskGame) {
        RiskGame.setOfflineGame(riskGame);
    }

    public boolean checkNumberOfPlayers(int numberOfPlayers) throws Exception {
        if (numberOfPlayers < 2) {
            throw new Exception("You should have at least two player to start the game");
        } else if (numberOfPlayers > 6) {
            throw new Exception("You can't have more than six player in this game");
        } else {
            return true;
        }
    }

    public RiskGame getPlayersGame(Player player){
        ArrayList<RiskGame> allGames = localDataBase.getAllRiskGames();
        for(RiskGame riskGame : allGames){
            Player[] players =riskGame.getPlayers();
            for(Player player1 : players){
                if(player1 != null){
                    if(player1.equals(player))
                        return riskGame;
                }
            }
        }
        return null;
    }
    public void makeOnlineGameReadyToStart(RiskGame riskGame){
        riskGame.setGameStarted(true);
        gameController.addSoldiersForStartingGame(riskGame);
        riskGame.setCurrentPlayer(riskGame.getCreator());
    }
    public void addPlayerToGame(Player player , RiskGame riskGame) throws Exception{
        if(riskGame.isGameStarted()){
            throw new Exception("You can't join this game cause it in the game");
        }else {
            Player[] players = riskGame.getPlayers();
            int nullIndex = -1;
            for (int i = 0; i < players.length; i++) {
                Player player1 = players[i];
                if (player1 == null) {
                    nullIndex = i;
                    break;
                }
            }
            if (nullIndex == -1) {
                throw new Exception("You can't join this game");
            } else {
                players[nullIndex] = player;
            }
        }
    }
    public void removePlayerFromGame(RiskGame riskGame , Player player){
        Player[] players = riskGame.getPlayers();
        for(int i =0 ; i < players.length ; i++){
            Player player1 = players[i];
            if(player1 != null && player1.equals(player))
                players[i] = null;
        }
    }

    public HashMap<Integer , RiskGame> getAllRequestedGamesForPlayers(Player player , int index){
        ArrayList<RequestForPlaying> requestForPlayings = player.getRequestForPlaysList();
        ArrayList<RiskGame> allRiskGames = localDataBase.getAllRiskGames();
        HashMap<Integer , RiskGame> playerCreatedGames = new HashMap<>();
        for(RequestForPlaying requestForPlaying : requestForPlayings){
            if(allRiskGames.contains(requestForPlaying.getRiskGame())){
                playerCreatedGames.put(index , requestForPlaying.getRiskGame());
                index++;
            }
        }
        return playerCreatedGames;
    }


    public void startGame(RiskGame riskGame, Player creator) {
        //TODO.....
    }

    public boolean isGameStarted(RiskGame riskGame) {
        //TODO ....
        return true;
    }

    public void addSoldiersForStartingGame(RiskGame riskGame) {
        int numberOfPlayers = riskGame.getNumberOfPlayers();
        int numberOfSoldiers = 0;
        if (numberOfPlayers == 2) {
            numberOfSoldiers = 40;
        } else if (numberOfPlayers == 3) {
            numberOfSoldiers = 35;
        } else if (numberOfPlayers == 4) {
            numberOfSoldiers = 30;
        } else if (numberOfPlayers == 5) {
            numberOfSoldiers = 25;
        } else if (numberOfPlayers == 6) {
            numberOfSoldiers = 20;
        }
        for (Player player : riskGame.getPlayers()) {
            addSoldiers(player, numberOfSoldiers);
        }
    }

    public void addSoldiers(Player player, int numbersOfSoldiers) {
        int currentNumberOfSoldiers = player.getNumberOfSoldiers();
        currentNumberOfSoldiers += numbersOfSoldiers;
        player.setNumberOfSoldiers(currentNumberOfSoldiers);
    }

    public void calculateNumberOfSoldiersToAddInDraft(Player player) {
        int numberOfCountries = player.getPlayersCountry().size();
        if (numberOfCountries < 12)
            addSoldiers(player, 3);
        else {
            int numberOfSoldier = numberOfCountries / 4;
            addSoldiers(player, numberOfSoldier);
        }
        if (checkIfPlayerHasContinent(player, Continent.NORTH_AMERICA))
            addSoldiers(player, 5);
        else if (checkIfPlayerHasContinent(player, Continent.SOUTH_AMERICA))
            addSoldiers(player, 2);
        else if (checkIfPlayerHasContinent(player, Continent.AFRICA))
            addSoldiers(player, 3);
        else if (checkIfPlayerHasContinent(player, Continent.EUROPE))
            addSoldiers(player, 5);
        else if (checkIfPlayerHasContinent(player, Continent.ASIA))
            addSoldiers(player, 7);
        else if (checkIfPlayerHasContinent(player, Continent.AUSTRALIA))
            addSoldiers(player, 2);
    }

    public boolean checkIfPlayerHasContinent(Player player, Continent continent) {
        ArrayList<Integer> allContinentCountries = new ArrayList<>(continent.getCountries().keySet());
        ArrayList<Integer> allPlayersCountries = new ArrayList<>(player.getPlayersCountry().keySet());
        for (int i : allContinentCountries) {
            if (!allPlayersCountries.contains(i))
                return false;
        }
        return true;
    }

    public Card makeRandomCard() {
        ArrayList<Card> allCards = Card.getAllCards();
        Random r = new Random();
        int low = 0;
        int high = 125;
        int cardIndex = r.nextInt(high - low) + low;
        Card card = allCards.get(cardIndex);
        return new Card(card.getCountryID(), card.getCardsDesigns());
    }

    public void giveCardToPlayer(RiskGame riskGame) {
        boolean hasGotOneCountry = riskGame.isHasOneSuccessAttack();
        Player currentPlayer = riskGame.getCurrentPlayer();
        if (hasGotOneCountry) {
            Card card = makeRandomCard();
            currentPlayer.getPlayersCard().add(card);
            MySqlDataBase.getMySqlDataBase().updatePlayer(riskGame.getCurrentPlayer());
        }
    }

    public void getSoldiersFromCards(Player player, ArrayList<Card> cards) throws Exception {
        int numberOfSoldiers = 0;
        ArrayList<Card> playersCards = player.getPlayersCard();
        if (playersCards.containsAll(cards)) {
            int numberOfSameCards =0 ;
            for(int i =0 ;i < 2 ; i++){
                if(cards.get(i).getCardsDesigns().equals(cards.get(i+1)))
                    numberOfSameCards++;
            }
            if(numberOfSameCards == 2){
                CardDesigns cardDesigns = cards.get(0).getCardsDesigns();
                if(cardDesigns.equals(CardDesigns.ARTILLERY)){
                    numberOfSoldiers+=4;
                    if(checkIfCardsHaveSameCountriesWithPlayerCountries(player , cards))
                        numberOfSoldiers += 2;
                    player.getPlayersCard().removeAll(cards);
                }else if(cardDesigns.equals(CardDesigns.CAVALRY)){
                    numberOfSoldiers+=6;
                    if(checkIfCardsHaveSameCountriesWithPlayerCountries(player , cards))
                        numberOfSoldiers += 2;
                    player.getPlayersCard().removeAll(cards);
                }else{
                    numberOfSoldiers+=8;
                    if(checkIfCardsHaveSameCountriesWithPlayerCountries(player , cards))
                        numberOfSoldiers += 2;
                    player.getPlayersCard().removeAll(cards);
                }
            }else if(numberOfSameCards == 0){
                numberOfSoldiers += 10;
                if(checkIfCardsHaveSameCountriesWithPlayerCountries(player , cards))
                    numberOfSoldiers += 2;
                player.getPlayersCard().removeAll(cards);

            }else{
                throw new Exception("You can't match this cards");
            }
        }else{
            throw new Exception("Invalid cards");
        }
        gameController.addSoldiers(player , numberOfSoldiers);

    }

    public boolean checkIfCardsHaveSameCountriesWithPlayerCountries(Player player,ArrayList<Card> cards) throws Exception {
        ArrayList<Card> playersCards = player.getPlayersCard();
        Card firstCard = cards.get(0);
        Card secondCard = cards.get(1);
        Card thirdCard = cards.get(2);
        ArrayList<Integer> playersCountriesId = new ArrayList<>(player.getPlayersCountry().keySet());
        if (playersCards.contains(firstCard) && playersCards.contains(secondCard) && playersCards.contains(thirdCard)) {
            return playersCountriesId.contains(firstCard.getCountryID()) || playersCountriesId.contains(secondCard.getCountryID()) || playersCountriesId.contains(thirdCard.getCountryID());

        } else
            throw new Exception("Error cards");
    }


    public void changeHasGotSoldiersForDraft(RiskGame riskGame) {
        riskGame.setHasGotSoldiersForDraft(!riskGame.isHasGotSoldiersForDraft());
    }

    public boolean hasDoneFortify(RiskGame riskGame) {
        return riskGame.isHasDoneFortify();
    }

    public void setHashDoneFortify(RiskGame riskGame, boolean hasDoneFortify) {
        riskGame.setHasDoneFortify(hasDoneFortify);
    }

    public void setHashGotOneCountryInAttack(RiskGame riskGame, boolean hasGotOneCountry) {
        riskGame.setHasOneSuccessAttack(hasGotOneCountry);
    }


    public void placeSoldiers(Country country, int numbersOfSoldiers, Player player) throws Exception {
        if (numbersOfSoldiers > player.getNumberOfSoldiers())
            throw new Exception("You don't have this much soldiers");
        if (country.getColor() != null && country.getColor() != player.getCurrentColor())
            throw new Exception("This country is not yours");
        if (country.getColor() == null) {
            country.setColor(player.getCurrentColor());
            player.getPlayersCountry().put(country.getCountryCoordinate(), country);
        }
        addSoliderToCountry(country, numbersOfSoldiers);
        removeSoldiersFormPlayer(player, numbersOfSoldiers);

    }

    public Country[][] getAllCountriesInArray(RiskGame riskGame) {
        return riskGame.getAllCountriesInArray();
    }

    public void placeSoldiersForStartingStage(RiskGame riskGame, Country country, Player player) throws Exception {
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
        } else {
            throw new Exception("You are not in starting stage");
        }
    }

    public void goNextStage(RiskGame riskGame) {
        if (riskGame.getGameStages().equals(GameStages.STARTING))
            riskGame.setGameStages(GameStages.DRAFT);
        else if (riskGame.getGameStages().equals(GameStages.DRAFT))
            riskGame.setGameStages(GameStages.ATTACK);
        else if (riskGame.getGameStages().equals(GameStages.ATTACK))
            riskGame.setGameStages(GameStages.FORTIFY);
        else if (riskGame.getGameStages().equals(GameStages.FORTIFY))
            riskGame.setGameStages(GameStages.DRAFT);
    }

    public boolean checkCountriesForStartingStage(RiskGame riskGame) {
        HashMap<Integer, Country> allCountries = riskGame.getAllCountriesWithNumber();

        for (Country country : allCountries.values()) {
            if (country.getColor() == null)
                return false;
        }

        return true;
    }

    public void addSoliderToCountry(Country country, int numberOfSoldiers) {
        int numberOfSoldiersInCountry = country.getNumberOfSoldiers();
        numberOfSoldiersInCountry += numberOfSoldiers;
        country.setNumberOfSoldiers(numberOfSoldiersInCountry);
    }

    public void removeSoldierFormCountry(Country country, int numberOfSoldiers) throws Exception {
        int currentNumberOfSoldiers = country.getNumberOfSoldiers();
        if (currentNumberOfSoldiers < numberOfSoldiers)
            throw new Exception("You can't remove this much soldiers !!");
        else {
            currentNumberOfSoldiers -= numberOfSoldiers;
            country.setNumberOfSoldiers(currentNumberOfSoldiers);
        }
    }

    public void removeSoldiersFormPlayer(Player player, int numberOfSoldiers) throws Exception {
        int numberOfCurrentSoldiers = player.getNumberOfSoldiers();
        if (numberOfSoldiers > numberOfCurrentSoldiers)
            throw new Exception("Error");
        numberOfCurrentSoldiers -= numberOfSoldiers;
        player.setNumberOfSoldiers(numberOfCurrentSoldiers);
    }

    //TODO ....
    public void moveSoldiersFromACountryToAnotherCountry(Country first, Country second, int numberOfSoldiers) throws Exception {
        removeSoldierFormCountry(first, numberOfSoldiers);
        addSoliderToCountry(second, numberOfSoldiers);
    }

    public boolean isPathAvailableForFortifying(RiskGame riskGame, Player player, Country firstCountry, Country destinationCountry) {
        ArrayList<Country> chosenCountries = new ArrayList<>();
        ArrayList<Country> connectedCountries = new ArrayList<>();
        ArrayList<Country> countries = new ArrayList<>();
        connectedCountries.add(firstCountry);
        while (true) {
            for (Country country : connectedCountries) {
                chosenCountries.add(country);
                countries.addAll(getNeighboursCountriesWithPlayerColor(riskGame, player, country));
            }
            countries.removeAll(chosenCountries);
            if (countries.contains(destinationCountry))
                return true;
            if (countries.size() == 0)
                return false;
            connectedCountries.addAll(countries);

        }
    }

    public ArrayList<Country> getNeighboursCountriesWithPlayerColor(RiskGame riskGame, Player player, Country country) {
        ArrayList<Country> allNeighboursCountriesWithPlayerColor = new ArrayList<>();
        HashMap<Integer, Country> allCountries = riskGame.getAllCountriesWithNumber();
        for (Country country1 : country.getNeighboringCountries()) {
            if (country1.getColor().equals(player.getCurrentColor()))
                allNeighboursCountriesWithPlayerColor.add(country1);
        }
        return allNeighboursCountriesWithPlayerColor;
    }


    public void occupyingACountry(RiskGame riskGame, Player attacker, Country country) {
        Player defender = getDefenderPlayerByCountryColor(riskGame, country);
        HashMap<Integer, Country> attackerCountries = attacker.getPlayersCountry();
        HashMap<Integer, Country> defenderCountries = defender.getPlayersCountry();
        defenderCountries.remove(country.getCountryCoordinate());
        attackerCountries.put(country.getCountryCoordinate(), country);
        country.setColor(attacker.getCurrentColor());
    }

    public void removeOneSoldierFromCountry(Country country) {
        int currentNumberOfSoldiers = country.getNumberOfSoldiers();
        if (currentNumberOfSoldiers > 0) {
            currentNumberOfSoldiers--;
            country.setNumberOfSoldiers(currentNumberOfSoldiers);
        }
    }

    public Player getDefenderPlayerByCountryColor(RiskGame riskGame, Country defenderCounter) {
        Player[] players = riskGame.getPlayers();
        for (Player player : players) {
            if (player.getCurrentColor().equals(defenderCounter.getColor()))
                return player;
        }
        return null;
    }

    public boolean isCountryForPlayer(Country country, Player player) {
        return player.getPlayersCountry().containsKey(country.getCountryCoordinate());
    }

    public ArrayList<Integer> compareDiceForAttack(ArrayList<Integer> attackerDice, ArrayList<Integer> defenderDice) {
        int numberOfDefenderDice = defenderDice.size();
        Collections.sort(attackerDice);
        Collections.reverse(attackerDice);
        Collections.sort(defenderDice);
        Collections.reverse(defenderDice);
        ArrayList<Integer> finalDice = new ArrayList<>();
        int numberOfRepeat;
        if (numberOfDefenderDice > attackerDice.size())
            numberOfRepeat = attackerDice.size();
        else
            numberOfRepeat = defenderDice.size();
        for (int i = 0; i < numberOfRepeat; i++) {
            finalDice.add(attackerDice.get(i) - defenderDice.get(i));
        }
        return finalDice;
    }

    public boolean attack(RiskGame riskGame, Player attacker, Country attackerCountry, Country defenderCountry, ArrayList<Integer> attackerDices, ArrayList<Integer> defenderDices) throws Exception {
        if (isCountryForPlayer(attackerCountry, attacker)) {
            if (!isCountryForPlayer(defenderCountry, attacker)) {
                if (attackerCountry.getNumberOfSoldiers() > 1) {
                    ArrayList<Integer> totalDiceAfterAttack = compareDiceForAttack(attackerDices, defenderDices);
                    for (int i : totalDiceAfterAttack) {
                        if (i <= 0) {
                            removeOneSoldierFromCountry(attackerCountry);
                        } else {
                            removeOneSoldierFromCountry(defenderCountry);
                            if (defenderCountry.getNumberOfSoldiers() == 0)
                                return true;
                        }
                    }
                    return false;
                } else {
                    throw new Exception("You cant attack with this country cause u only have one player in this country");
                }
            } else {
                throw new Exception("You cant attack your own countries");
            }
        } else {
            throw new Exception("This country is not yours to attack");
        }
    }

    public ArrayList<Integer> rollBizzard(RiskGame riskGame, Player player, Country attackerCountry, Country defenderCountry) {
        ArrayList<Integer> totalDice = new ArrayList<>();
        while (true) {
            int maximumDiceForAttacker = getNumberOfDiceForAttacker(attackerCountry);
            int maximumDiceForDefender = getNumberOfDiceForDefender(defenderCountry);
            ArrayList<Integer> attackerDice = rollDice(maximumDiceForAttacker);
            ArrayList<Integer> defenderDice = rollDice(maximumDiceForDefender);
            totalDice.addAll(compareDiceForAttack(attackerDice, defenderDice));
            try {
                if (attack(riskGame, player, attackerCountry, defenderCountry, attackerDice, defenderDice)) {
                    break;
                }
            } catch (Exception e) {
                break;
            }
        }
        return totalDice;

    }

    public int getNumberOfDiceForAttacker(Country country) {
        if (country.getNumberOfSoldiers() > 4)
            return 3;
        else if (country.getNumberOfSoldiers() == 3)
            return 2;
        else if (country.getNumberOfSoldiers() == 2)
            return 1;
        else
            return 0;
    }

    public int getNumberOfDiceForDefender(Country country) {
        if (country.getNumberOfSoldiers() >= 2)
            return 2;
        return 1;
    }

    public ArrayList<Integer> rollDice(int numberOfDice) {
        ArrayList<Integer> dices = new ArrayList<>();
        for (int i = 0; i < numberOfDice; i++) {
            dices.add(rollDice());
        }
        return dices;
    }

    public int rollDice() {
        Random r = new Random();
        int low = 1;
        int high = 6;
        return r.nextInt(high - low) + low;
    }

    public void endAttack(Player player, RiskGame riskGame) {
        //TODO
    }

    public void moveSoldiers(Player player, RiskGame riskGame) {

    }

    public void endFortify(Player player, RiskGame riskGame) {

    }

    public void addCardToPlayer(RiskGame riskGame, Player player) {
        //TODO
    }

    public void nextPlayer(RiskGame riskGame) {
        Player[] players = riskGame.getPlayers();
        ArrayList<Player> gamePlayers = new ArrayList<>(Arrays.asList(players));
        Player currentPlayer = riskGame.getCurrentPlayer();

        int playersIndex = gamePlayers.indexOf(currentPlayer);
        if (playersIndex == gamePlayers.size() - 1)
            currentPlayer = gamePlayers.get(0);
        else {
            currentPlayer = gamePlayers.get(playersIndex + 1);
        }
        riskGame.setCurrentPlayer(currentPlayer);
    }

    public void endGame(RiskGame riskGame) {
        //TODO .....
    }

    public void setNewGameInformationForPlayers(Player[] players) {
        //TODO ......
    }

    public void doBlizzard(RiskGame riskGame) {
        //TODO
    }

    public void fogOfWar(RiskGame riskGame) {
        //TODO
    }

    public void setTimerForEachPlayer(Long timer, RiskGame riskGame) {
        //TODO ....
    }

    public void checkTimer(Player player, RiskGame riskGame) {
        //TODO
    }

    public ArrayList<Color> getDefaultColors() {
        Color[] colors = Color.getDefaultColor();
        ArrayList<Color> defaultColors = new ArrayList<>(Arrays.asList(colors));
        return defaultColors;
    }

    public HashMap<Integer, Color> getColorsToChose(ArrayList<Color> colors) {
        HashMap<Integer, Color> colorsToChose = new HashMap<>();
        int counter = 1;
        for (Color c : colors) {
            colorsToChose.put(counter, c);
            counter++;
        }
        return colorsToChose;
    }

    public ArrayList<Color> getAvailableColors(RiskGame riskGame){
        ArrayList<Color> defaultColors = getDefaultColors();
        Player[] players = riskGame.getPlayers();
        for(Player player : players){
            if(player != null)
                defaultColors.remove(player.getCurrentColor());
        }
        return defaultColors;
    }

    public boolean isStartingStageFinished(RiskGame riskGame) {
        Player[] players = riskGame.getPlayers();
        for (Player player : players) {
            if (player.getNumberOfSoldiers() != 0)
                return false;
        }
        return true;
    }

    public void putSoldiersForStartingStageUnManually(RiskGame riskGame) throws Exception {
        int numberOfPlayers = riskGame.getNumberOfPlayers();
        HashMap<Integer, Country> allCountries = riskGame.getAllCountriesWithNumber();
        Player[] players = riskGame.getPlayers();
        int numberOfCountriesForEachPlayer = 42 / players.length;
        ArrayList<Integer> countriesCoordinates = new ArrayList<>(allCountries.keySet());
        for (int i = 0; i < numberOfPlayers - 1; i++) {
            Player currentPlayer = players[i];
            for (int j = 0; j < numberOfCountriesForEachPlayer; j++) {
                int randomIndex = makeARandomNumber(countriesCoordinates.size() - 1);
                int randomCountryCoordinate = countriesCoordinates.get(randomIndex);
                Country randomCountry = allCountries.get(randomCountryCoordinate);
                placeSoldiersForStartingStage(riskGame, randomCountry, currentPlayer);
                countriesCoordinates.remove(randomIndex);
            }
        }
        Player lastPlayer = players[numberOfPlayers - 1];
        for (int i : countriesCoordinates) {
            Country country = allCountries.get(i);
            placeSoldiersForStartingStage(riskGame, country, lastPlayer);
        }
        for (int i = 0; i < numberOfPlayers; i++) {
            Player player = players[i];
            int numberOfSoldiers = player.getNumberOfSoldiers();
            ArrayList<Integer> playersCountries = new ArrayList<>(player.getPlayersCountry().keySet());
            for (int j = 0; j < numberOfSoldiers; j++) {
                int randomIndex = makeARandomNumber(playersCountries.size() - 1);
                int randomCountryCoordinate = playersCountries.get(randomIndex);
                Country country = player.getPlayersCountry().get(randomCountryCoordinate);
                placeSoldiersForStartingStage(riskGame, country, player);
            }
        }
    }

    public int makeARandomNumber(int max) {
        Random random = new Random();
        return random.nextInt(max);
    }

    public HashMap<Integer, Card> getAllCardsInHashMap(ArrayList<Card> cards , ArrayList<Card> chosenCards) {
        HashMap<Integer, Card> allCardsInHashMap = new HashMap<>();
        int index = 2;
        for(Card card : cards){
            if(!chosenCards.contains(card)){
                allCardsInHashMap.put(index , card);
                index++;
            }
        }
        return allCardsInHashMap;
    }


}
