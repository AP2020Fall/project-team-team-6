package model.usersModels;

import controller.UserController;
import model.database.LocalDataBase;

import java.util.ArrayList;

public class User {
    protected int ID;
    protected String firstName;
    protected String lastName;
    protected String username;
    protected String password;
    protected String emailAddress;
    protected String telephoneNumber;
    protected boolean isAdmin;
    private ArrayList<Massage> messages;

    public User(String firstName, String lastName, String username,
                String password, String emailAddress, String telephoneNumber, boolean isAdmin) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.emailAddress = emailAddress;
        this.telephoneNumber = telephoneNumber;
        this.isAdmin = isAdmin;
        this.ID = 0;
        this.messages = new ArrayList<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public boolean isAdmin() {
        return isAdmin;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public ArrayList<Massage> getMessages() {
        return messages;
    }

    public String getAllPlayerMessagesInString() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Massage massage : messages) {
            stringBuilder.append(Massage.changeMessageToString(massage)).append("\n");
        }
        return stringBuilder.toString();
    }

    public void getAllMessageFromString(String text) {
        Admin admin = LocalDataBase.getLocalDataBase().getAdmin();
        String[] allMessagesInArray = text.split("\n");
        for (String s : allMessagesInArray) {
            Massage massage = Massage.changeMessageFromStringToMessage(s);
            if(massage != null){
                User sender = massage.getSender();
                User receiver = massage.getReceiver();
                if(sender.getID()  == this.getID()){
                    if(sender.isAdmin){
                        admin.getMessages().add(massage);
                    }else{
                        Player player = UserController.getUserController().findPlayerByUserName(sender.getUsername());
                        player.getMessages().add(massage);
                    }
                }else{
                    if(receiver.isAdmin){
                        admin.getMessages().add(massage);
                    }else{
                        Player player = UserController.getUserController().findPlayerByUserName(receiver.getUsername());
                        player.getMessages().add(massage);
                    }
                }
            }
        }
    }
}
