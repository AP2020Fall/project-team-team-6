package view;

import model.Player;

import java.util.HashMap;

public class FriendsMenu extends Menu{
    private Player player;
    private HashMap<Integer , Player> friendsList;
    public FriendsMenu(Menu parentMenu , Player player) {
        super("Friends Menu", parentMenu);
        this.player = player;
        this.friendsList = player.getFriends();
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
