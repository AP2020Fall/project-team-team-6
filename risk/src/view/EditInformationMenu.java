package view;

import model.User;

public class EditInformationMenu extends Menu {
    private User user;

    public EditInformationMenu(Menu parentMenu, User user) {
        super("edit information" , parentMenu);
        this.user = user;
    }

    @Override
    public void show() {
        super.show();
        System.out.println("2.change user name");
        System.out.println("3.change first name");
        System.out.println("4.change last name");
        System.out.println("5.change email address");
        System.out.println("6.change telephone number");
        System.out.println("7.change password");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String userName = user.getUsername();
        String inputString = getInputFormatWithHelpText("[1-7]" , "Enter a number:");
        int input = Integer.parseInt(inputString);
        if (input == 1){
            nextMenu = parentMenu;
        } else if (input == 2) {
            String newUserName = editUsername();
            if (newUserName != null) {
                try {
                    userController.changeUsername(userName, newUserName);
                } catch (Exception e) {
                    System.err.println(e.getMessage());
                }
            }
        } else if (input == 3) {
            String newFirstName = editFirstName();
            if (newFirstName != null)
            userController.changeFirstName(userName, newFirstName);
        } else if (input == 4) {
            String newLastName = editLastName();
            if (newLastName != null)
            userController.changeLastName(userName, newLastName);
        } else if (input == 5) {
            String newEmailAddress = editEmailAddress();
            if (newEmailAddress != null)
            userController.changeEmailAddress(userName, newEmailAddress);
        } else if(input == 6) {
            String newTelephoneNumber = editTelephoneNumber();
            if (newTelephoneNumber != null)
            userController.changeTelephoneNumber(userName, newTelephoneNumber);
        } else if (input == 7) {
            String newPassword = editPassword();
            if (newPassword != null)
            userController.changePassword(userName, newPassword);
        }
        nextMenu.show();
        nextMenu.execute();
    }

    private String editUsername() {
        String newUserName = getInputFormatWithHelpText(".+|^back$" , "Enter new user name or type back to return:");
        if (newUserName.equalsIgnoreCase("back"))
            return null;
        return newUserName;
    }

    private String editFirstName() {
        String newFirstName = getInputFormatWithHelpText(".+|^back$" , "Enter new first name or type back to return:");
        if (newFirstName.equalsIgnoreCase("back"))
            return null;

        return newFirstName;
    }

    private String editLastName() {
        String newLastName = getInputFormatWithHelpText(".+|^back$" , "Enter new last name or type back to return:");
        if (newLastName.equalsIgnoreCase("back"))
            return null;
        return newLastName;
    }

    private String editEmailAddress() {
        String newEmailAddress = getInputFormatWithHelpText(".+|^back$" , "Enter new email address or type back to return:");
        if (newEmailAddress.equalsIgnoreCase("back"))
            return null;
        return newEmailAddress;
    }

    private String editTelephoneNumber() {
        String newTelephoneNumber = getInputFormatWithHelpText(".+|^back$" , "Enter new telephone number or type back to return:");
        if (newTelephoneNumber.equalsIgnoreCase("back"))
            return null;
        return newTelephoneNumber;
    }

    private String editPassword() {
        String newPassword;
        while (true) {
            newPassword = getInputFormatWithHelpText(".+|^back$" , "Enter new password or type back to return:");
            if (newPassword.equalsIgnoreCase("back"))
                return null;
            else {
                String confirmNewPassword = getInputFormatWithHelpText(".+", "repeat your password:");
                if (confirmNewPassword.equals(newPassword))
                    return newPassword;
                else
                    System.err.println("password doesn't match.");
            }
        }
    }
}
