package view;

import model.User;

public class LoginMenu extends Menu {


    public LoginMenu(Menu parentMenu) {
        super("Login Menu", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("Enter your user name and password to login.");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String userName = getUserName();
        if (userName == null) {
            System.out.println("this user name does not exist.");
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }

        String password = getPassword();
        if (password == null) {
            System.out.println("password is not correct.");
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }

        User user = userController.login(userName, password);

        nextMenu = new UserMenu(this.parentMenu.getParentMenu() , user);
        nextMenu.show();
        nextMenu.execute();
    }

    private String  getUserName() {
        String userName;
        while (true) {
            userName = getInputFormatWithHelpText(".+", "Username :");
            if(userController.checkUsername(userName))
                return null;
            else
                return userName;

        }
    }

    private String getPassword() {
        String userName = getUserName();
        String password;
        while (true){
            password  = getInputFormatWithHelpText("^.+$" , "Password :");
            if(userController.checkPassword(userName, password))
                return password;
            else{
                return null;
            }
        }

    }

}
