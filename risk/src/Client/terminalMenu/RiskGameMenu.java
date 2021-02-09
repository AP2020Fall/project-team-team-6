package Client.terminalMenu;

import Server.model.gamesModels.*;
import Server.model.usersModels.Player;

import java.util.ArrayList;
import java.util.HashMap;


public class RiskGameMenu extends Menu {
    private RiskGame riskGame;
    private Player[] players;
    private Player currentPlayer;
    private GameStages gameStages;
    private Country[][] countries;

    public RiskGameMenu(Menu parentMenu, RiskGame riskGame) {
        super("Play Risk ", parentMenu);
        this.riskGame = riskGame;
        this.players = riskGame.getPlayers();
        this.currentPlayer = riskGame.getCurrentPlayer();
        this.gameStages = riskGame.getGameStages();
        this.countries = gameController.getAllCountriesInArray(riskGame);
    }

    @Override
    public void show() {
        if (gameStages.equals(GameStages.STARTING)) {
            if (!riskGame.isMapManually()) {
                try {
                    gameController.putSoldiersForStartingStageUnManually(riskGame);
                    gameController.goNextStage(riskGame);
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }

        gameStages = riskGame.getGameStages();

        //1.Back
        super.show();
        showsCountries();
        System.out.println("Current Player : " + currentPlayer.getUsername());
        if (gameStages.equals(GameStages.STARTING)) {
            if (riskGame.isMapManually()) {
                System.out.println("Starting Stage");
                System.out.println("----------------------");
                System.out.println("Number of soldiers : " + currentPlayer.getNumberOfSoldiers());
            }
        }
        if (gameStages.equals(GameStages.DRAFT)) {
            if (!riskGame.isHasGotSoldiersForDraft()) {
                gameController.calculateNumberOfSoldiersToAddInDraft(currentPlayer);
                gameController.changeHasGotSoldiersForDraft(riskGame);
            }
            System.out.println("Draft Stage");
            System.out.println("----------------------");
            showDraft();
        }
        if (gameStages.equals(GameStages.ATTACK)) {
            System.out.println("Attack Stage");
            System.out.println("----------------------");
            showAttack();
        }
        if (gameStages.equals(GameStages.FORTIFY)) {
            System.out.println("Fortify Stage");
            System.out.println("----------------------");
            showFortify();
        }

    }

    @Override
    public void execute() {
        String inputInString;
        Menu nextMenu = this;

        //Starting stage
        gameStages = riskGame.getGameStages();
        if (gameStages.equals(GameStages.STARTING)) {
            try {
                startGameStartingStage();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        gameStages = riskGame.getGameStages();
        //Draft Stage
        if (gameStages.equals(GameStages.DRAFT)) {
            draftStage();
        }

        gameStages = riskGame.getGameStages();
        //Attack Stage
        if (gameStages.equals(GameStages.ATTACK)) {
            attack();
        }
        gameStages = riskGame.getGameStages();
        //Fortify Stage
        if (gameStages.equals(GameStages.FORTIFY)) {
            fortify();
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private void showsCountries() {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 23; j++) {
                Country currentCountry = countries[i][j];
                if (currentCountry != null) {
                    if (currentCountry.getColor() != null)
                        System.out.print(currentCountry.getCountryCoordinate() + changeColorStringFormat(currentCountry.getColor()) + "  ");
                    else
                        System.out.print(currentCountry.getCountryCoordinate() + "   ");
                } else {
                    System.out.print("--   ");
                }
            }
            System.out.println();
        }
    }

    private void showDraft() {
        System.out.println("Number of soldiers : " + currentPlayer.getNumberOfSoldiers());
        System.out.println("2.Place Soldiers");
        System.out.println("3.Use Cards");
        System.out.println("4.Countries Information");
        System.out.println("5.Next Faze");
    }

    private void showCountriesInformationForDraft(Country country) {
        if (country.getColor() == null) {
            System.out.println("Name : " + country.getName());
            System.out.println("Continent : " + country.getContinent());
        } else {
            System.out.println("Name : " + country.getName());
            System.out.println("Continent : " + country.getContinent());
            if (country.getColor() != currentPlayer.getCurrentColor())
                System.err.println("This country is for " + country.getColor() + " Player");
            else {
                System.out.println("Number of soldiers : " + country.getNumberOfSoldiers());
            }
        }
    }

    private String changeColorStringFormat(Color color) {
        return String.valueOf(color.toString().toUpperCase().charAt(0));
    }

    private void startGameStartingStage() throws Exception {
        Menu nextMenu = this;
        String inputInString;
        if (riskGame.isMapManually()) {
            while (true) {

                if (gameController.isStartingStageFinished(riskGame))
                    break;
                inputInString = getInputFormatWithHelpText("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$", "Enter a country number or type back to return");
                if (inputInString.equalsIgnoreCase("back")) {
                    nextMenu = parentMenu;
                    nextMenu.show();
                    nextMenu.execute();
                } else {
                    int countryCoordinate = Integer.parseInt(inputInString);
                    Country country = riskGame.getAllCountriesWithNumber().get(countryCoordinate);
                    showCountriesInformationForDraft(country);
                    try {
                        gameController.placeSoldiersForStartingStage(riskGame, country, currentPlayer);
                        System.out.println(countryCoordinate + " added to " + currentPlayer.getUsername());
                        gameController.nextPlayer(riskGame);
                        nextMenu = new RiskGameMenu(this.getParentMenu(), riskGame);
                        nextMenu.show();
                        nextMenu.execute();
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                }
            }
        }
        gameController.goNextStage(riskGame);
        gameController.calculateNumberOfSoldiersToAddInDraft(currentPlayer);
    }

    private void showAttack() {
        System.out.println("2.Attack");
        System.out.println("3.Show Countries Information");
        System.out.println("4.Next Stage");
    }

    private void draftStage() {
        Menu nextMenu = this;
        String inputInString;
        inputInString = getInputFormatWithHelpText("^(1|2|3|4|5)$ ".trim(), "Enter a number :");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            nextMenu = new Menu("", this) {
                @Override
                public void show() {
                    System.out.println("Enter a country number or type back to return");
                }

                @Override
                public void execute() {
                    Menu nextMenu = this;
                    String inputInString = getInputWithFormat("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$").trim();
                    if (inputInString.equalsIgnoreCase("Back"))
                        nextMenu = parentMenu;
                    else {
                        int input = Integer.parseInt(inputInString);
                        Country country = riskGame.getAllCountriesWithNumber().get(input);
                        showCountriesInformationForDraft(country);
                        System.out.println("Please Enter the number of soldiers you want to please our type back to return");
                        inputInString = getInputWithFormat("^\\d+$|^(?i)back$".trim());
                        if (!inputInString.equalsIgnoreCase("back")) {
                            int numberOfSoldiers = Integer.parseInt(inputInString);
                            try {
                                gameController.placeSoldiers(country, numberOfSoldiers, currentPlayer);
                                System.out.println("You put " + numberOfSoldiers + " in " + country.getName());
                                System.out.println("Number of soldiers : " + currentPlayer.getNumberOfSoldiers());
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };
        } else if (input == 3) {
            nextMenu = new Menu("", this) {
                ArrayList<Card> cards = new ArrayList<>();
                ArrayList<Card> playerCards = currentPlayer.getPlayersCard();

                @Override
                public void show() {
                    if (cards.size() == 3) {
                        System.out.println("You chose this cards do u wanna match them or not ?");
                        showChosenCards(cards);
                        System.out.println("Type yes to match or no to return ");
                    } else {
                        System.out.println("1.Back");
                        HashMap<Integer, Card> getAllCards = gameController.getAllCardsInHashMap(playerCards, cards);
                        if (getAllCards.size() == 0) {
                            System.out.println("You don't have any card");
                        } else {
                            for (int i : getAllCards.keySet()) {
                                System.out.println(i + ".");
                                Card card = getAllCards.get(i);
                                showCard(card);
                                System.out.println();
                            }
                        }
                    }
                }

                @Override
                public void execute() {
                    Menu nextMenu = this;
                    if (cards.size() != 3) {
                        String inputInString = getInputFormatWithHelpText("^\\d+$", "Enter a number :");
                        int input = Integer.parseInt(inputInString);
                        if (input == 1)
                            nextMenu = parentMenu;
                        else if (input > currentPlayer.getPlayersCard().size() + 1 || input < 1)
                            System.err.println("Invalid number");
                        else {
                            HashMap<Integer, Card> getAllCards = gameController.getAllCardsInHashMap(playerCards, cards);
                            Card card = getAllCards.get(input);
                            cards.add(card);
                            System.out.println("You added this card for matching");
                            showCard(card);
                        }
                    } else {
                        String inputInString = getInputFormatWithHelpText("^(?i)yes|(?i)no$".trim(), "");
                        if (inputInString.equalsIgnoreCase("yes")) {
                            try {
                                gameController.getSoldiersFromCards(currentPlayer, cards);
                                nextMenu = parentMenu;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else {
                            nextMenu = parentMenu;
                        }
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };
        } else if (input == 4) {
            inputInString = getInputFormatWithHelpText("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$".trim(), "Enter a country number or type back to return");
            if (!inputInString.equals("back")) {
                input = Integer.parseInt(inputInString);
                Country country = riskGame.getAllCountriesWithNumber().get(input);
                showCountriesInformationForAttack(country);
            }
        } else if (input == 5) {
            if (currentPlayer.getNumberOfSoldiers() == 0)
                gameController.goNextStage(riskGame);
            else {
                System.err.println("You can't go to next Stage cause you still have soldiers to place ");
            }
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private void showDices(ArrayList<Integer> dices) {
        for (int i : dices) {
            System.out.print(i + "\t");
        }
        System.out.println();
    }

    private void showNumberOfDiceToChose(Country country) {
        int maximumNumberOfDice = gameController.getNumberOfDiceForAttacker(country);
        System.out.print("1.Back\t");
        for (int i = 2; i < maximumNumberOfDice + 2; i++) {
            int numberOfDice = i - 1;
            System.out.print(i + ". " + numberOfDice + " Dice\t");
        }
        System.out.println(maximumNumberOfDice + 2 + " . Blitz");
    }

    private int getNumberOfDiceToRoll(Country country) {
        int maximumDice = gameController.getNumberOfDiceForAttacker(country);
        int input;
        while (true) {
            String inputInString = getInputWithFormat("\\d+");
            input = Integer.parseInt(inputInString);
            if (input <= maximumDice + 2 && input >= 1)
                break;
        }
        if (input == 1) {
            //This is for back
            return 0;
        } else if (input < maximumDice + 2) {
            return input - 1;
        } else {
            //This is for bizard
            return 6;
        }

    }

    private Country getCountriesToAttack(Country attackerCountry) {
        HashMap<Integer, Country> neighboursCountries = new HashMap<>();
        int counter = 1;
        for (Country country : attackerCountry.getNeighboringCountries()) {
            if (!gameController.isCountryForPlayer(country, currentPlayer)) {
                neighboursCountries.put(counter, country);
                counter++;
            }
        }
        for (int i : neighboursCountries.keySet()) {
            System.out.println(i + ".");
            showCountriesInformationForAttack(neighboursCountries.get(i));
        }
        int input = 0;
        while (true) {
            String inputString = getInputFormatWithHelpText("\\d+|(?i)back", "Enter a country to attack or type back to return");
            if (inputString.equalsIgnoreCase("back"))
                return null;
            else {
                input = Integer.parseInt(inputString);
                if (input <= neighboursCountries.size() && input > 0)
                    return neighboursCountries.get(input);
                else {
                    System.err.println("Invalid input !!");
                }
            }
        }
    }

    private void showCountriesInformationForAttack(Country country) {
        System.out.println();
        System.out.println("----------------------------------------------------------------------------");
        System.out.println("Country Coordinate : " + country.getCountryCoordinate());
        System.out.println("Country Name : " + country.getName());
        System.out.println("Continent : " + country.getContinent());
        System.out.println("Color : " + country.getColor());
        System.out.println("Number Of soldiers : " + country.getNumberOfSoldiers());
        System.out.println("----------------------------------------------------------------------------");
        System.out.println();
    }

    private void showHowManySoldiersLost(ArrayList<Integer> totalDice) {
        int defenderLost = 0;
        int attackerLost = 0;
        for (int i : totalDice) {
            if (i > 0)
                defenderLost++;
            else
                attackerLost++;
        }
        System.out.println("Number of soldiers that attacker lost :" + attackerLost);
        System.out.println("Number of soldiers that defender lost :" + defenderLost);
    }

    private void moveSoldiersAfterWinningACountry(Country attackerCountry, Country defenderCountry, int minimumSoldierToMove) {
        int maximumSoldierToMove = attackerCountry.getNumberOfSoldiers() - 1;
        String inputInString;
        int numberOfSoldier;
        System.out.println("Number of soldiers in your country : " + attackerCountry.getNumberOfSoldiers());
        while (true) {
            inputInString = getInputFormatWithHelpText("^\\d+$", "Enter the number of soldiers you want to move");
            numberOfSoldier = Integer.parseInt(inputInString);
            if (numberOfSoldier >= minimumSoldierToMove && numberOfSoldier <= maximumSoldierToMove)
                break;
            else if (numberOfSoldier < minimumSoldierToMove)
                System.err.println("You can't move less than " + minimumSoldierToMove + " soldiers");
            else System.err.println("You can't move more than " + maximumSoldierToMove + " soldiers");
        }
        try {
            gameController.moveSoldiersFromACountryToAnotherCountry(attackerCountry, defenderCountry, numberOfSoldier);
            gameController.occupyingACountry(riskGame, currentPlayer, defenderCountry);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void attack() {
        Menu nextMenu = this;
        String inputInString = getInputFormatWithHelpText("^1|2|3|4$", "Enter a number : ");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            nextMenu = new Menu("", this) {
                @Override
                public void show() {
                    System.out.println("Enter a country coordinate for attacking or type back to return. ");
                }

                @Override
                public void execute() {
                    Menu nextMenu = this;
                    String inputInString = getInputWithFormat("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$");
                    if (inputInString.equalsIgnoreCase("back"))
                        nextMenu = parentMenu;
                    else {
                        int input = Integer.parseInt(inputInString);
                        Country country = riskGame.getAllCountriesWithNumber().get(input);
                        if (gameController.isCountryForPlayer(country, currentPlayer)) {
                            if (country.getNumberOfSoldiers() != 1) {
                                showNumberOfDiceToChose(country);
                                int numberOfDiceToAttack = getNumberOfDiceToRoll(country);
                                if (numberOfDiceToAttack != 0) {
                                    Country defenderCountry = getCountriesToAttack(country);
                                    if (defenderCountry != null && numberOfDiceToAttack != 6) {
                                        ArrayList<Integer> attackerDice = gameController.rollDice(numberOfDiceToAttack);
                                        ArrayList<Integer> defendersDice = gameController.rollDice(gameController.getNumberOfDiceForDefender(defenderCountry));
                                        System.out.println("Attacker Dice : ");
                                        showDices(attackerDice);
                                        System.out.println("Defender Dice : ");
                                        showDices(defendersDice);
                                        ArrayList<Integer> totalDice = gameController.compareDiceForAttack(attackerDice, defendersDice);
                                        showHowManySoldiersLost(totalDice);
                                        boolean hasGotTheCountry = false;
                                        try {
                                            hasGotTheCountry = gameController.attack(riskGame, currentPlayer, country, defenderCountry, attackerDice, defendersDice);
                                            if (hasGotTheCountry) {
                                                gameController.setHashGotOneCountryInAttack(riskGame, true);
                                            }
                                        } catch (Exception e) {
                                            System.out.println(e.getMessage());
                                        }
                                        if (hasGotTheCountry) {
                                            //TODO
                                            System.out.println("You got the " + defenderCountry.getName() + " Country coordinate : " + defenderCountry.getCountryCoordinate());
                                            moveSoldiersAfterWinningACountry(country, defenderCountry, attackerDice.size());
                                            if (gameController.isGameFinished(riskGame)) {
                                                System.out.println(currentPlayer.getUsername() + " have got all countries");
                                                gameController.endGame(riskGame);
                                                nextMenu = parentMenu.getParentMenu().getParentMenu();
                                            }
                                            giveAllDefenderCardsToAttacker(defenderCountry);
                                            showsCountries();
                                        }
                                    } else if (defenderCountry != null && numberOfDiceToAttack == 6) {
                                        int numberOfSoldiersForDefenderCountry = defenderCountry.getNumberOfSoldiers();
                                        ArrayList<Integer> totalDice = gameController.rollBizzard(riskGame, currentPlayer, country, defenderCountry);
                                        showHowManySoldiersLost(totalDice);
                                        int numberOfSoldierThatDefenderHasLost = 0;
                                        for (Integer i : totalDice) {
                                            if (i > 0)
                                                numberOfSoldierThatDefenderHasLost++;
                                        }
                                        if (numberOfSoldierThatDefenderHasLost == numberOfSoldiersForDefenderCountry) {
                                            int attackerDice;
                                            gameController.setHashGotOneCountryInAttack(riskGame, true);
                                            System.out.println("You got the " + defenderCountry.getName() + " Country coordinate : " + defenderCountry.getCountryCoordinate());
                                            if (country.getNumberOfSoldiers() >= 4)
                                                attackerDice = 3;
                                            else if (country.getNumberOfSoldiers() == 3)
                                                attackerDice = 2;
                                            else
                                                attackerDice = 1;
                                            moveSoldiersAfterWinningACountry(country, defenderCountry, attackerDice);
                                            if (gameController.isGameFinished(riskGame)) {
                                                System.out.println(currentPlayer.getUsername() + " have got all countries");
                                                gameController.endGame(riskGame);
                                                nextMenu = parentMenu.getParentMenu().getParentMenu();
                                            }
                                            giveAllDefenderCardsToAttacker(defenderCountry);
                                            showsCountries();
                                        }
                                    }
                                }
                            } else {
                                System.err.println("you cant attack with this country cause u only have one soldiers in this country.");
                            }
                        } else {
                            System.err.println("This country is not yours");
                        }
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };
        } else if (input == 3) {
            inputInString = getInputFormatWithHelpText("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$".trim(), "Enter a country number or type back to return");
            if (!inputInString.equals("back")) {
                input = Integer.parseInt(inputInString);
                Country country = riskGame.getAllCountriesWithNumber().get(input);
                showCountriesInformationForAttack(country);
            }
        } else if (input == 4) {
            boolean hasGotAnyCountry = riskGame.isHasOneSuccessAttack();
            if (hasGotAnyCountry) {
                gameController.giveCardToPlayer(riskGame);
            }
            gameController.setHashGotOneCountryInAttack(riskGame, false);
            gameController.goNextStage(riskGame);
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private void showFortify() {
        System.out.println("2.Fortify");
        System.out.println("3.Show Countries information");
        System.out.println("4.Next Player");
    }

    private Country getCountry(String massage) {
        Country country;
        while (true) {
            String inputInString = getInputFormatWithHelpText("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$", massage);
            if (inputInString.equalsIgnoreCase("back"))
                return null;
            int countryCoordination = Integer.parseInt(inputInString);
            country = riskGame.getAllCountriesWithNumber().get(countryCoordination);
            if (country.getColor().equals(currentPlayer.getCurrentColor()))
                break;
        }
        return country;
    }

    private int getNumberOfSoldiersToMoveFromACountryToAnother(Country firstCountry) {
        int numberOfSoldiers = firstCountry.getNumberOfSoldiers();
        while (true) {
            String inputInString = getInputFormatWithHelpText("\\d+|(?i)back", "Enter number of soldiers you want to move or type back to return ");
            if (inputInString.equalsIgnoreCase("back"))
                return 0;
            int input = Integer.parseInt(inputInString);
            if (input >= 0 || input < numberOfSoldiers)
                return input;
        }
    }

    private void fortify() {
        String inputInString;
        Menu nextMenu = this;
        inputInString = getInputWithFormat("^1|2|3|4$");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            if (!gameController.hasDoneFortify(riskGame)) {
                Country firstCountry = getCountry("Enter the country coordinate that you want to move soldiers from or type back to return");
                if (firstCountry == null) {
                    nextMenu.show();
                    nextMenu.execute();
                }
                Country destinationCountry = getCountry("Enter the country that you want to move soldiers to it or type back to return");
                if (destinationCountry == null) {
                    nextMenu.show();
                    nextMenu.execute();
                }
                assert firstCountry != null;
                int numberOfSoldier = getNumberOfSoldiersToMoveFromACountryToAnother(firstCountry);
                if (numberOfSoldier == 0) {
                    nextMenu.show();
                    nextMenu.execute();
                }
                if (gameController.isPathAvailableForFortifying(riskGame, currentPlayer, firstCountry, destinationCountry)) {
                    try {
                        gameController.moveSoldiersFromACountryToAnotherCountry(firstCountry, destinationCountry, numberOfSoldier);
                        gameController.setHashDoneFortify(riskGame, true);
                        System.out.println("You moved " + numberOfSoldier + "from " + firstCountry.getName() + " to " + destinationCountry.getName());
                        System.out.println("Number of soldiers in " + firstCountry.getName() + " : " + firstCountry.getNumberOfSoldiers());
                        System.out.println("Number of soldiers in " + destinationCountry.getName() + " : " + destinationCountry.getNumberOfSoldiers());
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                } else {
                    System.err.println("There is no path for this country");
                }
            } else {
                System.err.println("You can't fortify anymore !");
            }
        } else if (input == 3) {
            inputInString = getInputFormatWithHelpText("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$".trim(), "Enter a country number or type back to return");
            if (!inputInString.equals("back")) {
                input = Integer.parseInt(inputInString);
                Country country = riskGame.getAllCountriesWithNumber().get(input);
                showCountriesInformationForAttack(country);
            }
        } else if (input == 4) {
            gameController.setHashDoneFortify(riskGame, false);
            gameController.goNextStage(riskGame);
            gameController.nextPlayer(riskGame);
            currentPlayer = riskGame.getCurrentPlayer();
            while (true) {
                if (gameController.checkIfPlayerHasAnyCountries(currentPlayer))
                    break;
                gameController.nextPlayer(riskGame);
                currentPlayer = riskGame.getCurrentPlayer();
            }
            gameController.calculateNumberOfSoldiersToAddInDraft(riskGame.getCurrentPlayer());
            nextMenu = new RiskGameMenu(parentMenu, riskGame);
            nextMenu.show();
            nextMenu.execute();
        }
        nextMenu.show();
        nextMenu.execute();
    }


    private void showCard(Card card) {
        System.out.println("Card design : " + card.getCardsDesigns());
        System.out.println("Country coordination : " + card.getCountryID());
    }

    private void showChosenCards(ArrayList<Card> cards) {
        if (cards.size() != 0) {
            for (int i = 1; i <= cards.size(); i++) {
                System.out.println(i + ".");
                showCard(cards.get(i - 1));
            }
        }
    }

    private void giveAllDefenderCardsToAttacker(Country defenderCountry) {
        Player defender = null;
        try {
            defender = gameController.getDefenderByCountry(riskGame, defenderCountry);
            if (defender.getPlayersCountry().size() == 1) {
                System.out.println("You got all " + defender.getUsername() + " cards");
                gameController.addAllPlayersCardToAnother(defender, currentPlayer);
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
