package view;

import model.Massage;
import model.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FriendsMenu extends Menu{
    private Player player;
    private HashMap<Integer , Player> friendsList;
    public FriendsMenu(Menu parentMenu , Player player) {
        super("Friends Menu", parentMenu);
        this.player = player;
    }

    @Override
    public void show() {
        friendsList = userController.getFriendsList(player);


        System.out.println("1.Back");
        System.out.println("2.show Requests");
        System.out.println("3.send new friend request");
        System.out.println("4.Manage massages");
        System.out.println("5.Show Friends ");
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^1|2|3|4|5$" , "Enter a number : ");
        int input = Integer.parseInt(inputString);
        if(input == 1){
            nextMenu = parentMenu;
        }else if(input == 2){
            nextMenu = new Menu("Show Requests",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println("enter the user name or enter back ");
                }

                @Override
                public void execute() {
                    super.execute();
                }
            };


        }else if(input == 3){
            nextMenu = new Menu("Send new friend request",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println(friendsList);
                }

                @Override
                public void execute() {
                    super.execute();
                }
            };

        }else if(input == 4){
            nextMenu = new Menu("manage massages",this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println("2.Send new Massage");
                    System.out.println("-----------------------------");
                    showInbox();
                }

                @Override
                public void execute() {
                    super.execute();
                }
            };

        }else if(input == 5){
            nextMenu = new Menu("show friends",this) {
                @Override
                public void show() {
                    HashMap<Integer , Player> friends = userController.getPlayersFriends(player);
                    System.out.println("1.Back");
                    if(friends.size() == 0)
                        System.out.println("You don't have any friend");
                    else{
                        showFriends(friends);
                    }
                }

                @Override
                public void execute() {
                    HashMap<Integer , Player> friends = userController.getPlayersFriends(player);
                    String inputInString = getInputFormatWithHelpText("^\\d+$" , "Enter a number : ");
                    int input = Integer.parseInt(inputInString);
                    Menu nextMenu = this;
                    if (input == 1){
                        nextMenu = parentMenu;
                    }else if(input > player.getFriends().size() +1 ){
                        System.err.println("Invalid number");
                    }else{
                        Player friend = friends.get(input);
                        showFriendInformation(friend);
                        inputInString = getInputFormatWithHelpText("^1|2|3$" , "Enter a number : ");
                        input = Integer.parseInt(inputInString);
                        if(input == 2){
                            showMessages(friend);
                            while (true) {
                                showMessages(friend);
                                String messageText = getInputFormatWithHelpText("^.+|(?i)back$", "Enter your text or type back to return");
                                if (!messageText.equalsIgnoreCase("back"))
                                    userController.sendMessage(player, friend, messageText);
                            }
                        }else if(input == 3){
                            inputInString = getInputFormatWithHelpText("^(?i)yes|(?i)no$" , "Are you sure ?");
                            if(inputInString.equalsIgnoreCase("yes")) {
                                player.getFriends().remove(friend);
                                System.out.println("You removed" + friend.getUsername() + " from your friends");
                            }
                        }
                    }
                    nextMenu.show();
                    nextMenu.execute();
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


    private void showFriends(HashMap<Integer , Player> friends){
        for(Integer playersNumber : friends.keySet()){
            System.out.println(playersNumber+"."+friends.get(playersNumber).getUsername());
        }
    }

    private void showFriendInformation(Player player){
        System.out.println("Username : " + player.getUsername());
        System.out.println("Player point : " + player.getRate());
        System.out.println("------------------------------------------");
        System.out.println("1.Back");
        System.out.println("2.Send massage");
        System.out.println("3.Remove Friend");
    }

    private void showMessages(Player secondPlayer){
        ArrayList<Massage> massages = userController.getPlayersMassage(player , secondPlayer );
        if(massages.size() == 0)
            System.out.println("Empty");
        else{
            for(Massage massage : massages){
                System.out.println(massage.getSender() + ":" + massage.getMassage());
            }
        }
    }

    private void showInbox(){
        HashSet<Player> inbox = userController.getAllPlayersThatHadMessageWith(player);
        ArrayList<Player> inboxInArray = new ArrayList<>(inbox);
        for(int i = 3; i <= inboxInArray.size()+2; i++){
            System.out.println(i+"."+inboxInArray.get(i-3).getUsername());
        }
    }






}
