package client.terminalMenu;

import server.model.usersModels.Admin;
import server.model.usersModels.Player;
import server.model.usersModels.User;

public class RegisterMenu extends Menu {
    public RegisterMenu(Menu parentMenu) {
        super("Register Menu", parentMenu);
    }

    @Override
    public void show() {
        Admin admin = userController.getAdmin();
        if (admin != null)
            System.out.println("Enter your information or type back to return .");
        else
            System.out.println("Please fill the information to make admin account");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String userName = getUserName();
        if (userName == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String firstName = getFirstName();
        if (firstName == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String lastName = getLastName();
        if (lastName == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String telephoneNumber = getTelephoneNumber();
        if (telephoneNumber == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String emailAddress = getEmailAddress();
        if (emailAddress == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String password = getPassword();
        if (password == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        System.out.println("Your account has successfully created.");
        Admin admin = userController.getAdmin();
        if (admin != null) {
            User user = userController.signUpAsPlayer(firstName, lastName, userName, password, emailAddress, telephoneNumber);
            OnlineGameMenu.setCurrentUser(user);
            Player player = userController.findPlayerByUserName(userName);
            nextMenu = new PlayerMenu(this.parentMenu.getParentMenu(), player);
        } else {
            Admin admin1 = userController.signUpAsAdmin(firstName, lastName, userName, password, emailAddress, telephoneNumber);
            OnlineGameMenu.setCurrentUser(admin1);
            nextMenu = new AdminMenu(this, admin1);
        }
        nextMenu.show();
        nextMenu.execute();

    }

    private String getUserName() {
        while (true) {
            String userName = getInputFormatWithHelpText(".+|^(?i)back$", "Username :");
            if (userName.equalsIgnoreCase("Back"))
                return null;
            if (userController.checkUsername(userName))
                return userName;
        }
    }

    private String getFirstName() {

        while (true) {
            String firstName = getInputFormatWithHelpText("^\\w+$|^(?i)back$", "Fist name :");
            if (firstName.equalsIgnoreCase("back"))
                return null;
            else
                return firstName;
        }
    }

    private String getLastName() {
        while (true) {
            String lastName = getInputFormatWithHelpText("^\\w+$|^(?i)back$", "Last name :");
            if (lastName.equalsIgnoreCase("back"))
                return null;
            else
                return lastName;
        }
    }

    private String getTelephoneNumber() {
        while (true) {
            String telephoneNumber = getInputFormatWithHelpText("^\\d{11}$|^(?i)back$", "Telephone number :");
            if (telephoneNumber.equalsIgnoreCase("back"))
                return null;
            else
                return telephoneNumber;
        }
    }

    private String getEmailAddress() {
        while (true) {
            String emailAddress = getInputFormatWithHelpText("^.+\\@.+\\.com$|^(?i)back$", "Email address :");
            if (emailAddress.equalsIgnoreCase("back"))
                return null;
            else
                return emailAddress;
        }
    }

    private String getPassword() {
        String password;
        while (true) {
            password = getInputFormatWithHelpText("^.+$|^(?i)back$", "Password :");
            if (password.equalsIgnoreCase("back"))
                return null;
            String confirmPassword = getInputFormatWithHelpText("^.+$|^(?i)back$", "Repeat your password  :");
            if (confirmPassword.equals(password))
                return password;
            else {
                System.err.println("Passwords didn't match");
            }
        }
    }

}
