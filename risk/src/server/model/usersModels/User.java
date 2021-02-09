package server.model.usersModels;

import server.controller.UserController;
import server.model.database.LocalDataBase;

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
            stringBuilder.append(Massage.changeMessageToString(massage)).append("#");
        }
        return stringBuilder.toString();
    }

    public void getAllMessageFromString(String text) {
        Admin admin = LocalDataBase.getLocalDataBase().getAdmin();
        String[] allMessagesInArray = text.split("#");
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

    public String changeUserToString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("user$").append(ID).append("$").append(firstName).append("$").append(lastName).append("$");
        stringBuilder.append(username).append("$").append(password).append("$").append(emailAddress).append("$");
        stringBuilder.append(telephoneNumber).append("$").append(isAdmin).append("$");
        return String.valueOf(stringBuilder);
    }

    public static User changeUserFromString(String input){
        String[] inputs = input.split("\\$");
        int ID = Integer.parseInt(inputs[1]);
        String firstName = inputs[2];
        String lastName = inputs[3];
        String username = inputs[4];
        String password = inputs[5];
        String email = inputs[6];
        String telephoneNumber = inputs[7];
        boolean isAdmin = Boolean.parseBoolean(inputs[8]);
        User user = new User(firstName , lastName , username , password , email , telephoneNumber , isAdmin);
        user.setID(ID);
        return user;
    }

}
