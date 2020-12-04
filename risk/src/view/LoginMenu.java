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
            nextMenu = parentMenu;
        }

        String password = getPassword();
        if (password == null) {
            nextMenu = parentMenu;
        }
        try {
            User user  = userController.login(userName , password);
            OnlineGameMenu.setCurrentUser(user);
            nextMenu = new UserMenu(this.parentMenu.getParentMenu() , user);
        }catch (Exception e){
            System.err.println(e.getMessage());
        }

        nextMenu.show();
        nextMenu.execute();
    }

    private String  getUserName() {
        String userName;
            userName = getInputFormatWithHelpText(".+|^(?i)back$".trim(), "Username :");
            if(userName.equalsIgnoreCase("back"))
                return null;
            else
                return userName;
    }

    private String getPassword() {
        String password;
            password  = getInputFormatWithHelpText(".+|^(?i)back$".trim() , "Password :");
            if(password.equalsIgnoreCase("back"))
                return null;
            else{
                return password;
            }
    }


}
