package view;

import model.Player;

import java.util.ArrayList;
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
        friendsList = userController.getFriendsList(player);
        System.out.println("1.Back");
        System.out.println("2.show Requests");
        System.out.println("3.send new friend request");
        System.out.println("4.Remove friend");
        System.out.println("5.Manage masseges");
        System.out.println("6.show friends");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^1|2|3|4|5|6$" , "Enter a number : ");
        int input = Integer.parseInt(inputString);
        if(input == 1){
            nextMenu = parentMenu;
        }else if(input == 2){
            nextMenu = new Menu("Requests for friendship" , this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    showRequest();
                }

                @Override
                public void execute() {
                    super.execute();
                }
            };
        }else if(input == 3){
            nextMenu = new Menu("send new friend request",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println("enter the user name or enter (1) ");
                }

                @Override
                public void execute() {
                    if (input == 1){
                        nextMenu = parentMenu;
                    }
                        // TODO: 12/8/2020

                }
            };


        }else if(input == 4){
            nextMenu = new Menu("remove frome friendlist",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println(friendsList);
                }

                @Override
                public void execute() {
                    if (input == 1){
                        nextMenu = parentMenu;
                    }
                    // TODO: 12/8/2020
                }
            };

        }else if(input == 5){
            nextMenu = new Menu("manage masseges",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                }

                @Override
                public void execute() {
                    if (input == 1){
                        nextMenu = parentMenu;
                    }
                    // TODO: 12/8/2020
                }
            };

        }else if(input == 6){
            nextMenu = new Menu("show friends",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println(friendsList);
                }

                @Override
                public void execute() {
                    if (input == 1){
                        nextMenu = parentMenu;
                    }
                    // TODO: 12/8/2020
                }
            };

        }


        nextMenu.show();
        nextMenu.execute();
    }
    private void showRequest(){
        for(int i : friendsList.keySet()){
            System.out.println(i+"."+friendsList.get(i).getUsername());
        }
    }

}
