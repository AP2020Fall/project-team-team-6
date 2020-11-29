package view;

import model.Country;
import model.GameStages;
import model.Player;
import model.RiskGame;


public class RiskGameMenu extends Menu {
    private RiskGame riskGame;
    private Player[] players;
    private Player currentPlayer ;
    private GameStages gameStages;
    public RiskGameMenu( Menu parentMenu , RiskGame riskGame) {
        super("Play Risk ", parentMenu);
        this.riskGame = riskGame;
        this.players = riskGame.getPlayers();
        this.currentPlayer = riskGame.getCurrentPlayer();
        this.gameStages = riskGame.getGameStages();
    }

    @Override
    public void show() {
        //1.Back
        super.show();
        showsCountries();
        System.out.println("Current Player : " +currentPlayer.getUsername());
        if(gameStages.equals(GameStages.DRAFT)){
            showDraft();
        }
    }

    @Override
    public void execute() {
        String inputInString;
        Menu nextMenu = this;

        //Starting stage

        if(gameStages.equals(GameStages.STARTING)){
            int numberOfRepeats = gameController.calculateNumberOfSoldiersForStartingStage(riskGame.getNumberOfPlayers());
        for (int i = 0 ; i < numberOfRepeats ; i++) {
            inputInString = getInputFormatWithHelpText("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$", "Enter a country number or type back to return");
            if (inputInString.equalsIgnoreCase("back")) {
                nextMenu = parentMenu;
                break;
                //TodO error
            }
            else {
                int countryCoordinate = Integer.parseInt(inputInString);
                Country country = riskGame.getAllCountriesWithNumber().get(countryCoordinate);
                try {
                    gameController.placeSoldiersForStartingStage(riskGame , country , currentPlayer);
                    gameController.nextPlayer(riskGame);
                    System.out.println(countryCoordinate +" added to " + currentPlayer.getUsername());
                    nextMenu = new RiskGameMenu(this.getParentMenu() , riskGame);
                    nextMenu.show();
                    nextMenu.execute();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                    i--;
                }
            }
        }
         gameController.goNextStage(riskGame);
        }


        //Draft Stage
        if(gameStages.equals(GameStages.DRAFT)){
            inputInString = getInputFormatWithHelpText("^1|2|3|4$ " , "Enter a number :");
            int input = Integer.parseInt(inputInString);
            if(input == 1){
                nextMenu = parentMenu;
            }else if (input == 2){
                nextMenu = new Menu("",this) {
                    @Override
                    public void show() {
                        System.out.println("Enter a country number or type back to return");
                    }

                    @Override
                    public void execute() {
                        Menu nextMenu = this;
                        String inputInString = getInputWithFormat("^[1-9]$|^[1-3][0-9]$|^4[0-2]$|^(?i)back$").trim();
                        if(inputInString.equalsIgnoreCase("Back"))
                            nextMenu = parentMenu;
                        else{
                            int input = Integer.parseInt(inputInString);
                            Country country = riskGame.getAllCountriesWithNumber().get(input);
                            showCountriesInformationForDraft(country);
                            System.out.println("Please Enter the number of soldiers you want to please our type back to return");
                            inputInString = getInputWithFormat("\\d+|(?i)back");
                            if(!inputInString.equalsIgnoreCase("back")){
                                int numberOfSoldiers = Integer.parseInt(inputInString);
                                try {
                                    gameController.placeSoldiers(country , numberOfSoldiers , currentPlayer);
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }
                            }
                        }
                        nextMenu.show();
                        nextMenu.execute();
                    }
                };
            }else if(input == 3){

            }
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private void showsCountries(){
        System.out.println(RiskGame.getDefaultMap());
    }
    private void showDraft(){
        System.out.println("Number of soldiers : "+currentPlayer.getNumberOfSoldiers());
        System.out.println("2.Place Soldiers");
        System.out.println("3.Use Cards");
        System.out.println("4.Next Faze");
    }

    private void showCountriesInformationForDraft(Country country) {
        if(country.getColor() == null){
            System.out.println("Name : " + country.getName());
            System.out.println("Continent : " + country.getContinent());
        }else {
            System.out.println("Name : " + country.getName());
            System.out.println("Continent : " + country.getContinent());
            if (country.getColor() != currentPlayer.getCurrentColor())
                System.err.println("This country is for " + country.getColor() + " Player");
            else {
                System.out.println("Number of soldiers : " + country.getNumberOfSoldiers());
            }
        }
    }

}
