package view;

import model.User;

public class UserMenu extends Menu {
    private User user;

    public UserMenu(Menu parentMenu, User user) {
        super("Account", parentMenu);
        this.user = user;
    }


    @Override
    public void show() {
        System.out.println("1.Edit user information");
        System.out.println("2.Friends menu");
        System.out.println("3.Games menu");
        System.out.println("4.Events");
        System.out.println("5.Logout");
    }

    @Override
    public void execute() {
        String inputString = getInputFormatWithHelpText("[1-5]" , "Enter a number:");
        if (inputString.equals("1")) {

        }
    }


    private void editUserInformation(User user) throws Exception {
        String userName = user.getUsername();
        System.out.println("1.change user name");
        System.out.println("2.change first name");
        System.out.println("3.change last name");
        System.out.println("4.change email address");
        System.out.println("5.change telephone number");
        System.out.println("6.change password");
        System.out.println("7.back");
        String input = getInputFormatWithHelpText("[1-7]", "Enter a number:");
        if (input.equals("1")) {
            String newUserName = getInputFormatWithHelpText(".+", "Enter new user name:");
            userController.changeUsername(userName, newUserName);
        }
        if (input.equals("2")) {
            String newFirstName = getInputFormatWithHelpText(".+", "Enter new first name:");
            userController.changeFirstName(userName, newFirstName);
        }
        if (input.equals("3")) {
            String newLastName = getInputFormatWithHelpText(".+", "Enter new last name:");
            userController.changeLastName(userName, newLastName);
        }
        if (input.equals("4")) {
            String newEmailAddress = getInputFormatWithHelpText("^.+\\@.+\\.com$", "Enter new email address:");
            userController.changeEmailAddress(userName, newEmailAddress);
        }
        if (input.equals("5")) {
            String newTelephoneNumber = getInputFormatWithHelpText("^\\d{11}$", "Enter new telephone number:");
            userController.changeTelephoneNumber(userName, newTelephoneNumber);
        }
        if (input.equals("6")) {
            while (true) {
                String password = getInputFormatWithHelpText(".+", "Enter your password:");
                if (password.equals(user.getPassword())) {
                    String newPassword = getInputFormatWithHelpText(".+", "Enter new password:");
                    String confirmNewPassword = getInputFormatWithHelpText(".+", "repeat password:");
                    if (confirmNewPassword.equals(newPassword))
                        userController.checkPassword(userName, newPassword);
                    else
                        System.out.println("enter new password one more time.");
                } else
                    System.out.println("password is not correct.");
            }
        }
    }
}