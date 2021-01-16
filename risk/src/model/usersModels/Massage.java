package model.usersModels;

import controller.UserController;

public class Massage {
    private final String massage;
    private final User sender;
    private final User receiver;

    public Massage(String massage, User sender, User receiver) {
        this.massage = massage;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMassage() {
        return massage;
    }

    public User getSender() {
        return sender;
    }

    public User getReceiver() {
        return receiver;
    }

    public static String changeMessageToString(Massage message) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(message.getSender().getUsername()).append(":"); // Sender
        stringBuilder.append(message.getReceiver().getUsername()).append(":"); // reciver
        stringBuilder.append(message.getMassage());
        return String.valueOf(stringBuilder);
    }

    public static Massage changeMessageFromStringToMessage(String string) {
        String[] messageInArray = string.split(":");
        if (messageInArray.length > 2) {
            User sender = UserController.getUserController().findUserByUsername(messageInArray[0]);
            User receiver = UserController.getUserController().findUserByUsername(messageInArray[1]);
            String messageText = messageInArray[2];
            Massage massage = new Massage(messageText, sender, receiver);
            return massage;
        }
        return null;
    }
}
