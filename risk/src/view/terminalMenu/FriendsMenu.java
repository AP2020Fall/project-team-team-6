package view.terminalMenu;

import model.usersModels.Massage;
import model.usersModels.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

public class FriendsMenu extends Menu {
    private Player player;
    private HashMap<Integer, Player> friendsList;

    public FriendsMenu(Menu parentMenu, Player player) {
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
        String inputString = getInputFormatWithHelpText("^1|2|3|4|5$", "Enter a number : ");
        int input = Integer.parseInt(inputString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            nextMenu = new Menu("Show Requests", this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    showRequest();
                }

                @Override
                public void execute() {
                    Menu nextMenu = this;
                    String inputString = getInputFormatWithHelpText("^\\d+$", "Enter a number : ");
                    int input = Integer.parseInt(inputString);
                    if (input == 1) {
                        nextMenu = parentMenu;
                    } else if (input <= player.getRequestsForFriendShips().size() + 1) {
                        Player player1 = player.getRequestsForFriendShips().get(input - 2);
                        showAccountInformation(player1);
                        System.out.println("1.Back\t2.Accept\t3.Reject");
                        inputString = getInputFormatWithHelpText("^1|2|3$", "Enter a number : ");
                        int newInput = Integer.parseInt(inputString);
                        if (newInput == 2) {
                            try {
                                userController.acceptFriendShip(player, player1);
                                System.out.println("You added " + player1.getUsername() + " to your friends");
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        } else if (newInput == 3) {
                            try {
                                userController.rejectFriend(player, player1);
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    } else {
                        System.err.println("Invalid number");
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };


        } else if (input == 3) {

            while (true) {
                String username = getInputFormatWithHelpText("^.+|(?i)back$", "Enter a username or type back to return");
                if (username.equalsIgnoreCase("back"))
                    break;
                if (!username.equals(player.getUsername())) {


                    Player player1 = userController.findPlayerByUserName(username);
                    if (player1 != null) {
                        showAccountInformation(player1);
                        System.out.println("Are you sure ? ");
                        String confirm = getInputFormatWithHelpText("^(?i)yes|(?i)no$", "Enter yes or no. ");
                        if (confirm.equalsIgnoreCase("yes")) {
                            player1.getRequestsForFriendShips().add(player);
                            System.out.println("You have sent friend request for " + username);
                            break;
                        }
                    } else {
                        System.err.println("There is no user by that username ");
                    }
                } else {
                    System.err.println("enter another username");
                }
            }
        } else if (input == 4) {
            nextMenu = new Menu("manage massages", this) {
                @Override
                public void show() {
                    System.out.println("1.Back");
                    System.out.println("2.Send new Massage");
                    System.out.println("-----------------------------");
                    showInbox();
                    System.out.println("-----------------------------");
                    System.out.println("Admin massages : ");
                    showAdminMassage();
                }

                @Override
                public void execute() {
                    String inputString = getInputFormatWithHelpText("^\\d+$", "Enter a number : ");
                    int input = Integer.parseInt(inputString);
                    ArrayList<Player> inbox = new ArrayList<>(userController.getAllPlayersThatHadMessageWith(player));
                    Menu nextMenu = this;
                    if (input == 1) {
                        nextMenu = parentMenu;
                    } else if (input > inbox.size() + 2) {
                        System.err.println("Invalid number");
                    } else if (input == 2) {
                        while (true) {
                            String userName = getInputFormatWithHelpText("^.+|(?i)back$", "Enter a username to send message :");
                            Player player1 = userController.findPlayerByUserName(userName);
                            if (player1 != null && !userName.equals(player.getUsername())) {
                                System.out.println("-------------------------------------------------------");
                                showMessages(player1);
                                String messageText = getInputFormatWithHelpText("^.+|(?i)back$", "Enter your text or type back to return");
                                if (!messageText.equalsIgnoreCase("back")) {
                                    userController.sendMessage(player, player1, messageText);
                                    break;
                                } else if (messageText.equalsIgnoreCase("back"))
                                    break;
                            } else {
                                System.err.println("enter another username");
                            }
                        }

                       /* String userName = getInputFormatWithHelpText("^.+|(?i)back$" , "Enter a username to send message :");
                        Player player1 = userController.findPlayerByUserName(userName);
                        if(player1 == null&&userName.equals(player.getUsername()))
                            System.err.println("enter another username");
                        else{
                            while (true) {
                                System.out.println("-------------------------------------------------------");
                                showMessages(player1);
                                String messageText = getInputFormatWithHelpText("^.+|(?i)back$", "Enter your text or type back to return");
                                if (!messageText.equalsIgnoreCase("back"))
                                    userController.sendMessage(player, player1, messageText);
                                else if(messageText.equalsIgnoreCase("back"))
                                    break;
                            }
                        }*/
                    } else {
                        Player friend = inbox.get(input - 3);
                        while (true) {
                            System.out.println("-------------------------------------------------------");
                            showMessages(friend);
                            String messageText = getInputFormatWithHelpText("^.+|(?i)back$", "Enter your text or type back to return");
                            if (!messageText.equalsIgnoreCase("back"))
                                userController.sendMessage(player, friend, messageText);
                            else if (messageText.equalsIgnoreCase("back"))
                                break;
                        }
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };

        } else if (input == 5) {
            nextMenu = new Menu("show friends", this) {
                @Override
                public void show() {
                    HashMap<Integer, Player> friends = userController.getPlayersFriends(player);
                    System.out.println("1.Back");
                    if (friends.size() == 0)
                        System.out.println("You don't have any friend");
                    else {
                        showFriends(friends);
                    }
                }

                @Override
                public void execute() {
                    HashMap<Integer, Player> friends = userController.getPlayersFriends(player);
                    String inputInString = getInputFormatWithHelpText("^\\d+$", "Enter a number : ");
                    int input = Integer.parseInt(inputInString);
                    Menu nextMenu = this;
                    if (input == 1) {
                        nextMenu = parentMenu;
                    } else if (input > player.getFriends().size() + 1) {
                        System.err.println("Invalid number");
                    } else {
                        Player friend = friends.get(input);
                        showFriendInformation(friend);
                        inputInString = getInputFormatWithHelpText("^1|2|3$", "Enter a number : ");
                        input = Integer.parseInt(inputInString);
                        if (input == 2) {
                            while (true) {
                                System.out.println("-------------------------------------------------------");
                                showMessages(friend);
                                String messageText = getInputFormatWithHelpText("^.+|(?i)back$", "Enter your text or type back to return");
                                if (!messageText.equalsIgnoreCase("back"))
                                    userController.sendMessage(player, friend, messageText);
                                else if (messageText.equalsIgnoreCase("back"))
                                    break;
                            }
                        } else if (input == 3) {
                            inputInString = getInputFormatWithHelpText("^(?i)yes|(?i)no$", "Are you sure ?");
                            if (inputInString.equalsIgnoreCase("yes")) {
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

    private void showRequest() {
        for (int i = 2; i <= player.getRequestsForFriendShips().size() + 1; i++) {
            System.out.println(i + ". " + player.getRequestsForFriendShips().get(i - 2).getUsername());
        }
    }


    private void showFriends(HashMap<Integer, Player> friends) {
        for (Integer playersNumber : friends.keySet()) {
            System.out.println(playersNumber + "." + friends.get(playersNumber).getUsername());
        }
    }

    private void showFriendInformation(Player player) {
        System.out.println("Username : " + player.getUsername());
        System.out.println("Player point : " + player.getRate());
        System.out.println("------------------------------------------");
        System.out.println("1.Back");
        System.out.println("2.Send massage");
        System.out.println("3.Remove Friend");
    }

    private void showMessages(Player secondPlayer) {
        ArrayList<Massage> massages = userController.getPlayersMassage(player, secondPlayer);
        if (massages.size() == 0)
            System.out.println("Empty");
        else {
            for (Massage massage : massages) {
                System.out.println(massage.getSender().getUsername() + ":" + massage.getMassage());
            }
        }
    }

    private void showInbox() {
        HashSet<Player> inbox = userController.getAllPlayersThatHadMessageWith(player);
        ArrayList<Player> inboxInArray = new ArrayList<>(inbox);
        for (int i = 3; i <= inboxInArray.size() + 2; i++) {
            System.out.println(i + "." + inboxInArray.get(i - 3).getUsername());
        }
    }

    private void showAccountInformation(Player player) {
        System.out.println("Username : " + player.getUsername());
        System.out.println("Player's point : " + player.getRate());
    }

    private void showAdminMassage() {
        ArrayList<Massage> adminMassages = player.getAdminMassages();
        for (Massage massage : adminMassages) {
            System.out.println("Admin : " + massage.getMassage());
        }
    }


}
