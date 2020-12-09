package model;

public class Massage {
    private final String massage;
    private final Player sender ;
    private final Player receiver;

    public Massage(String massage, Player sender, Player receiver) {
        this.massage = massage;
        this.sender = sender;
        this.receiver = receiver;
    }

    public String getMassage() {
        return massage;
    }

    public Player getSender() {
        return sender;
    }

    public Player getReceiver() {
        return receiver;
    }
}
