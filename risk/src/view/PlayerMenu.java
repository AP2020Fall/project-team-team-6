package view;

import model.Player;
import model.User;

public class PlayerMenu extends Menu {
    private User user;

    public PlayerMenu(Menu parentMenu, User user) {
        super("Account", parentMenu);
        this.user = user;
        subMenus.put(2 , new GamesMenu(this));
        subMenus.put(3 , new FriendsMenu(this , (Player) user));
        subMenus.put(4 , new EventMenu(this));
        subMenus.put(5 , new EditInformationMenu(this, user));
    }



    @Override
    public void show() {
        super.show();
        System.out.println("6.Logout");
    }

    @Override
    public void execute() {
        String userName = user.getUsername();
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^[1-6]$".trim() , "Enter a number:");
        int input  = Integer.parseInt(inputString);
        if(input == 1){
            nextMenu = parentMenu;
        }else if(input == 2){
            nextMenu = subMenus.get(input);
        }else if(input == 3){
           nextMenu =  subMenus.get(input);
        }else if(input == 4){
            nextMenu = subMenus.get(input);
        }else if(input == 5){
         nextMenu = subMenus.get(input);
        }else if(input == 6){
            System.out.println("You have logged out  from system.");
            OnlineGameMenu.setCurrentUser(null);
            nextMenu = parentMenu;
        }

        nextMenu.show();
        nextMenu.execute();
    }
}