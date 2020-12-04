package view;

import model.User;

public class RegisterMenu extends Menu {
    public RegisterMenu( Menu parentMenu) {
        super("Register Menu", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("Enter your information or type back to return .");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String userName = getUserName();
        if(userName == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String firstName = getFirstName();
        if(firstName == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String lastName = getLastName();
        if(lastName == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String telephoneNumber = getTelephoneNumber();
        if(telephoneNumber == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String emailAddress = getEmailAddress();
        if(emailAddress == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        String password = getPassword();
        if(password == null) {
            nextMenu = parentMenu;
            nextMenu.show();
            nextMenu.execute();
        }
        System.out.println("Your account has successfully created.");
        User user = userController.signUp(firstName , lastName, userName , password , emailAddress , telephoneNumber);
        OnlineGameMenu.setCurrentUser(user);
        nextMenu = new UserMenu(this.parentMenu.getParentMenu() , user);
        nextMenu.show();
        nextMenu.execute();

    }

    private String getUserName(){
        while (true) {
            String userName = getInputFormatWithHelpText(".+|^(?i)back$", "Username :");
            if(userName.equalsIgnoreCase("Back"))
                return null;
            if(userController.checkUsername(userName))
                return userName;
        }
    }

    private String getFirstName(){

        while (true){
            String firstName = getInputFormatWithHelpText("^\\w+$|^(?i)back$" , "Fist name :");
            if (firstName.equalsIgnoreCase("back"))
                return null;
            else
                return firstName;
        }
    }
    private String getLastName(){
        while (true){
            String lastName = getInputFormatWithHelpText("^\\w+$|^(?i)back$" , "Last name :");
            if (lastName.equalsIgnoreCase("back"))
                return null;
            else
                return lastName;
        }
    }
    private String getTelephoneNumber(){
        while (true){
            String telephoneNumber  = getInputFormatWithHelpText("^\\d{11}$|^(?i)back$" , "Telephone number :");
            if (telephoneNumber.equalsIgnoreCase("back"))
                return null;
            else
                return telephoneNumber;
        }
    }
    private String getEmailAddress(){
        while (true){
            String emailAddress  = getInputFormatWithHelpText("^.+\\@.+\\.com$|^(?i)back$" , "Email address :");
            if (emailAddress.equalsIgnoreCase("back"))
                return null;
            else
                return emailAddress;
        }
    }
    private String getPassword(){
        String password;
        while (true){
            password  = getInputFormatWithHelpText("^.+$|^(?i)back$" , "Password :");
            if (password.equalsIgnoreCase("back"))
                return null;
           String confirmPassword = getInputFormatWithHelpText("^.+$|^(?i)back$" , "Repeat your password  :");
           if(confirmPassword.equals(password))
               return password;
           else{
               System.err.println("Passwords didn't match");
           }
        }
    }

}
