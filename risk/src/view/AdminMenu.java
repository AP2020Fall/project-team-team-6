package view;

import model.*;


public class AdminMenu extends Menu {
    Admin admin = null;

    public AdminMenu(Menu parentMenu, Admin admin) {
        super("Admin Account", parentMenu);
        this.admin = admin;
    }

    @Override
    public void show() {
        showUserInformation();
        System.out.println("---------------------------------");
        System.out.println("1.Back");
        System.out.println("2.Edit information");
        System.out.println("3.Manage Events");
        System.out.println("4.Remove user");
        System.out.println("5.Log out");
    }

    @Override
    public void execute() {
        String inputInString = getInputFormatWithHelpText("^1|2|3|4|5$", "Enter a number : ");
        int input = Integer.parseInt(inputInString);
        Menu nextMenu = this;
        if (input == 1) {
            if (parentMenu.getParentMenu() == null)
                nextMenu = parentMenu;
            else
                nextMenu = parentMenu.getParentMenu();
        } else if (input == 2) {
            User currentUser = OnlineGameMenu.getCurrentUser();
            nextMenu = new EditInformationMenu(this, currentUser);
        } else if (input == 3) {
            nextMenu = new ManageEventsMenu(this);
        } else if (input == 4) {
            Player player = null;
            while (true) {
                String userName = getInputFormatWithHelpText(".+", "Enter a username or type back to return");
                if (userName.equalsIgnoreCase("back"))
                    break;
                player = userController.findPlayerByUserName(userName);
                if (player == null)
                    System.err.println("User didn't find");
                else
                    break;
            }
            if (player != null) {
                showPlayerInformation(player);
                System.out.println("Are you sure you want to delete this user ?");
                String confirmation = getInputWithFormat("^(?i)yes|(?i)no$");
                if (confirmation.equalsIgnoreCase("yes")) {
                    userController.deletePlayer(player);
                    System.out.println("You deleted " + player.getUsername());
                }
            }
        } else if (input == 5) {
            OnlineGameMenu.setCurrentUser(null);
            if (parentMenu.getParentMenu() == null)
                nextMenu = parentMenu;
            else
                nextMenu = parentMenu.getParentMenu();
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
        System.out.println("Point number : " + player.getCredit());
        System.out.println("---------------------------------------------------------------");
    }

    private void showUserInformation() {
        System.out.println("Username : " + admin.getUsername());
        System.out.println("First name : " + admin.getFirstName());
        System.out.println("Last name : " + admin.getLastName());
        System.out.println("Email address : " + admin.getEmailAddress());
        System.out.println("Telephone number : " + admin.getTelephoneNumber());
    }


}
