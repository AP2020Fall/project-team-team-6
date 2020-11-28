package view;

import controller.GameController;
import model.Admin;
import model.RiskGame;
import model.User;

public class MainMenu extends Menu {
    private static Admin admin = null;
    private static User currentUser = null;
    private RiskGame offlineRiskGame;
    public MainMenu(Menu parentMenu) {
        super("Main Menu", parentMenu);
        offlineRiskGame = null;
        subMenus.put(2,new OnlineGameMenu(this));
    }

    @Override
    public void show() {
        //This super shows 1.Back and 2.Online game
        super.show();
        //This shows 3.Play Offline game
        System.out.println("3.Play Offline Game");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^1|2|3$" ,"Enter a number : ");
        int input = Integer.parseInt(inputString);
        if(input == 1){
            System.exit(1);
        }else if(input == 2 ){
            nextMenu = subMenus.get(2);
        }else if(input == 3){
            nextMenu = this;
            offlineRiskGame = gameController.getOfflineGame();
            if(offlineRiskGame == null) {
                makeNewOfflineGame();
                nextMenu = new OfflineGame(this);
            }else{
                nextMenu = new Menu("Do you want to continue your last game ? " , this) {
                    @Override
                    public void show() {
                        System.out.println("1.Back");
                        System.out.println("2.Continue playing");
                        System.out.println("3.Start new Game");
                    }

                    @Override
                    public void execute() {
                        Menu nextMenu = this;
                        String inputInString = getInputFormatWithHelpText("^1|2|3$" , "Enter a number:");
                        int input = Integer.parseInt(inputInString);
                        if(input == 1){
                            nextMenu = parentMenu;
                        }else if(input == 2 ){
                            nextMenu = new OfflineGame(this);
                        }else if(input == 3){
                            makeNewOfflineGame();
                            nextMenu = new OfflineGame(this);
                        }
                        nextMenu.show();
                        nextMenu.execute();
                    }
                };
            }
        }
        nextMenu.show();
        nextMenu.execute();
    }
    private String[] getPlayersName(int playersNumber){
        String[] playersName = new String[playersNumber];
        System.out.println("Enter Players names or type back to return");
        for(int i =1 ; i < playersName.length+1 ; i++){
            System.out.print(i+".");
            playersName[i-1] = getInputWithFormat(".+");
            if(playersName[i-1].equalsIgnoreCase("Back")) {
                return null;
            }
            System.out.println();
        }
        return playersName;
    }

    private String getRiskGameName(){
        String riskGameName = getInputFormatWithHelpText("^(.+$)|(?i)back" , "Enter your risk game name or write back to return");
        if(riskGameName.equalsIgnoreCase("Back")){
            return null;
        }
        return riskGameName;
    }
    private int getPlayersNumber(){
        String playersNumberString =  getInputFormatWithHelpText("^(\\d+)|(?i)back$" , "Enter number of players or write back to return");
        if(playersNumberString.equalsIgnoreCase("Back")){
            return 0;
        }
        int playersNumber =Integer.parseInt(playersNumberString);
        while (true){
            try {
                if(gameController.checkNumberOfPlayers(playersNumber))
                    break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
            playersNumberString =  getInputFormatWithHelpText("^\\d+$" , "Enter number of players or write back to return");
            playersNumber =Integer.parseInt(playersNumberString);
        }
        return playersNumber;
    }
    private long setTimer(){
        System.out.println("1.Back");
        System.out.println("2.3 min");
        System.out.println("3.5 min");
        System.out.println("4.7 min");
        String inputInString = getInputFormatWithHelpText("^1|2|3|4$" , "Enter a number");
        int input = Integer.parseInt(inputInString);
        if(input == 1){
            return 0;
        }else if(input == 2){
            return 180;
        }else if(input == 3){
            return 300;
        }else {
            return 420;
        }
    }
    //TODO Manually is not complete
    public void makeNewOfflineGame(){
        String riskGameName = getRiskGameName();
        if(riskGameName == null)
            return ;
        int numberOfPlayers = getPlayersNumber();
        if(numberOfPlayers == 0)
            return ;
        String[] playersName = getPlayersName(numberOfPlayers);
        if(playersName == null)
            return ;
        long timer = setTimer();
        if(timer == 0){
            return;
        }
        gameController.makeOfflineGame(riskGameName , playersName , numberOfPlayers , timer , true);
    }
}
