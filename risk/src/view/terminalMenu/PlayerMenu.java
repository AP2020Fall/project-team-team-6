package view.terminalMenu;

import model.usersModels.Player;

public class PlayerMenu extends Menu {
    private Player user;

    public PlayerMenu(Menu parentMenu, Player player) {
        super("Account", parentMenu);
        this.user = player;
        subMenus.put(2, new GamesMenu(user, this));
        subMenus.put(3, new FriendsMenu(this, player));
        subMenus.put(4, new EventMenu(this, player));
        subMenus.put(5, new EditInformationMenu(this, user));
    }


    @Override
    public void show() {
        showPlayerInformation(user);
        super.show();
        System.out.println("6.Logout");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^[1-6]$".trim(), "Enter a number:");
        int input = Integer.parseInt(inputString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            nextMenu = subMenus.get(input);
        } else if (input == 3) {
            nextMenu = subMenus.get(input);
        } else if (input == 4) {
            nextMenu = subMenus.get(input);
        } else if (input == 5) {
            nextMenu = subMenus.get(input);
        } else if (input == 6) {
            System.out.println("You have logged out  from system.");
            OnlineGameMenu.setCurrentUser(null);
            nextMenu = parentMenu;
        }

        nextMenu.show();
        nextMenu.execute();
    }

    private void showPlayerInformation(Player player) {
        System.out.println("Username : " + player.getUsername());
        System.out.println("First name : " + player.getFirstName());
        System.out.println("Last name : " + player.getLastName());
        System.out.println("Email Address : " + player.getEmailAddress());
        System.out.println("Telephone number : " + player.getTelephoneNumber());
        System.out.println("Number of wins : " + player.getNumbersOfWin());
        System.out.println("Number of games : " + player.getNumbersOfGames());
        System.out.println("Number of days since register : " + player.getNumbersOfDaysSinceRegistration());
        System.out.println("Point number : " + player.getRate());
        System.out.println("---------------------------------------------------------------");
    }
}