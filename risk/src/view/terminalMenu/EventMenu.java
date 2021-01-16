package view.terminalMenu;


import model.gamesModels.Event;
import model.gamesModels.RiskGame;
import model.usersModels.Player;

import java.util.ArrayList;
import java.util.HashMap;

public class EventMenu extends Menu {
    private Player currentPlayer;
    private HashMap<Integer, Event> playersEvent;

    public EventMenu(Menu parentMenu, Player currentPlayer) {
        super("Events Menu ", parentMenu);
        this.currentPlayer = currentPlayer;
        playersEvent = eventController.getEventForPlayer(currentPlayer);
    }

    @Override
    public void show() {
        System.out.println("1.back");
        showEvents(playersEvent);
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputInString = getInputFormatWithHelpText("^\\d+$", "Enter a number : ");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input > playersEvent.size() + 1) {
            System.out.println("Invalid number");
        } else {
            Event event = playersEvent.get(input);
            String confirmJoin = getInputFormatWithHelpText("^(?i)yes|(?i)no$", "Do you want to join this event ?");
            if (confirmJoin.equalsIgnoreCase("yes")) {
                try {
                    eventController.joinEvent(currentPlayer, event);
                    System.out.println("You have added to game please check your game menu");
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        nextMenu.show();
        nextMenu.execute();
    }


    private void showRiskGameInformation(RiskGame riskGame) {
        if (riskGame != null) {
            System.out.println("Name : " + riskGame.getName());
            System.out.println("Player number : " + riskGame.getNumberOfPlayers());
            System.out.println("Timer : " + riskGame.getTimer() + " s");
            System.out.println(riskGame.getRiskGameType());
            if (riskGame.isMapManually())
                System.out.println("Manually");
            else {
                System.out.println("Unmanually");
            }
            System.out.println("------------------------------------------------");
        } else
            System.out.println("There is no game for this event");
    }

    private void showEventInformation(Event event) {
        System.out.println("Event ID : " + event.getEventID());
        System.out.println("Start Date : " + event.getStartDate());
        System.out.println("End Date : " + event.getEndDate());
        System.out.println("Event point : " + event.getEventPoint());
        System.out.println("Invited Players : ");
        System.out.println("-----------------------------------------------------------");
        showInvitedPlayersToEventUserName(event);
        System.out.println("-----------------------------------------------------------");
        showRiskGameInformation(event.getGame());
    }

    private void showInvitedPlayersToEventUserName(Event event) {
        ArrayList<Player> invitedPlayers = event.getInvitedPlayers();
        ArrayList<Player> allPlayers = userController.getAllPlayers();
        if (invitedPlayers.containsAll(allPlayers))
            System.out.println("All Players");
        else if (invitedPlayers.size() == 0) {
            System.out.println("No one has invited to this event");
        } else {
            for (int i = 1; i < event.getInvitedPlayers().size() + 1; i++) {
                System.out.print("[" + i + "." + event.getInvitedPlayers().get(i - 1).getUsername() + " ]  ");
            }
            System.out.println();
        }
    }

    private void showEvents(HashMap<Integer, Event> playersEvent) {
        for (int i : playersEvent.keySet()) {
            System.out.println(i + ".");
            showEventInformation(playersEvent.get(i));
        }
    }
}



