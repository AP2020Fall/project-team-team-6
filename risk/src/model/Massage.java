package model;

public class Massage {
    private final String massage;
    private final Player sender;
    private final Player receiver;
    private final Admin admin;

    public Massage(String massage, Player sender, Player receiver) {
        this.massage = massage;
        this.sender = sender;
        this.receiver = receiver;
        this.admin = null;
    }

    public Massage(String massage, Admin admin, Player receiver) {
        this.massage = massage;
        this.admin = admin;
        this.receiver = receiver;
        this.sender = null;
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
