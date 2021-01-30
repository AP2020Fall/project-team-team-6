package model.usersModels;

import java.util.ArrayList;

public class Admin extends User {


    public Admin(String firstName, String lastName, String username,
                 String password, String emailAddress, String telephoneNumber) {
        super(firstName, lastName, username, password, emailAddress, telephoneNumber, true);
    }




}
