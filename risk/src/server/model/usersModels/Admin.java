package server.model.usersModels;

public class Admin extends User {

    public Admin(String firstName, String lastName, String username,
                 String password, String emailAddress, String telephoneNumber) {
        super(firstName, lastName, username, password, emailAddress, telephoneNumber, true);
    }

}
