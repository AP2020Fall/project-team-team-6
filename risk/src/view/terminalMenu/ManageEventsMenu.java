package view.terminalMenu;

import model.gamesModels.Event;
import model.usersModels.Player;
import model.gamesModels.RiskGame;
import model.gamesModels.RiskGameType;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class ManageEventsMenu extends Menu {
    public ManageEventsMenu(Menu parentMenu) {
        super("Event manager", parentMenu);
    }

    @Override
    public void show() {
        System.out.println("1.Back");
        System.out.println("2.Make new Event");
        System.out.println("Enter a event number to change their information.");
        System.out.println("---------------------- ALL EVENTS ----------------------");
        System.out.println();
        showEvents();
    }

    @Override
    public void execute() {
        Menu nextMenu = this;
        String inputString = getInputFormatWithHelpText("^\\d+$", "Enter a number : ");
        HashMap<Integer, Event> allEventsInMap = eventController.getAllEventsInHashMap();
        int input = Integer.parseInt(inputString);
        if (input == 1) {
            nextMenu = parentMenu;
        } else if (input == 2) {
            if (makeNewEvent()) {
                System.out.println("You successfully made the event");
            }
        } else if (input > 2 && input < allEventsInMap.size() + 3) {
            //Edit Event
            Event event = allEventsInMap.get(input);
            nextMenu = new Menu("", this) {
                @Override
                public void show() {
                    showChangeOptionForEvent();
                }

                @Override
                public void execute() {
                    Menu nextMenu = this;
                    String inputString = getInputFormatWithHelpText("^1|2|3|4|5|6$", "Enter a number : ");
                    int input = Integer.parseInt(inputString);
                    if (input == 1) {
                        nextMenu = parentMenu;
                    } else if (input == 2) {
                        LocalDateTime startDate = null;
                        while (true) {
                            System.out.println("Start date : ");
                            String startDateInString = getInputFormatWithHelpText("^20[2-9][0-9]-(0[0-9])|(1[0-2])-([0-2][0-9])|30T([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])|(?i)back$", "Example :2020-12-03T09:12:24 or enter back to return");
                            if (startDateInString.equalsIgnoreCase("back"))
                                break;
                            startDate = LocalDateTime.parse(startDateInString);
                            try {
                                startDate = eventController.checkStartDate(startDate);
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (startDate != null) {
                            eventController.changeEvent(event, startDate, event.getEndDate(), event.getEventPoint(), event.getInvitedPlayers());
                            System.out.println("You changed Event start date to " + startDate.toString());
                        }

                    } else if (input == 3) {
                        LocalDateTime endDate = null;
                        while (true) {
                            System.out.println("End date : ");
                            String startDateInString = getInputFormatWithHelpText("^20[2-9][0-9]-(0[0-9])|(1[0-2])-([0-2][0-9])|30T([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])|(?i)back$", "Example :2020-12-03T09:12:24 or enter back to return");
                            if (startDateInString.equalsIgnoreCase("back"))
                                break;
                            endDate = LocalDateTime.parse(startDateInString);
                            try {
                                endDate = eventController.checkEndDate(endDate, event.getStartDate());
                                break;
                            } catch (Exception e) {
                                System.out.println(e.getMessage());
                            }
                        }
                        if (endDate != null) {
                            eventController.changeEvent(event, event.getStartDate(), endDate, event.getEventPoint(), event.getInvitedPlayers());
                            System.out.println("You changed Event end date to " + endDate.toString());
                        }

                    } else if (input == 4) {
                        System.out.println("Invited Players : ");
                        showInvitedPlayersToEventUserName(event);
                        System.out.println("------------------------------------------------------------------------");
                        ArrayList<Player> invitedPlayers = getPlayersForEvent();
                        if (invitedPlayers != null) {
                            eventController.changeEvent(event, event.getStartDate(), event.getEndDate(), event.getEventPoint(), invitedPlayers);
                            System.out.println("You changed the Invited List ");
                        }
                    } else if (input == 5) {
                        String eventPointString = getInputFormatWithHelpText("^\\d+|(?i)back$", "Event Point : ");
                        if (!eventPointString.equalsIgnoreCase("back")) {
                            double eventPoint = Double.parseDouble(eventPointString);
                            eventController.changeEvent(event, event.getStartDate(), event.getEndDate(), eventPoint, event.getInvitedPlayers());
                            System.out.println("You changed event point to " + eventPoint);
                        }
                    } else if (input == 6) {
                        String inputInString = getInputFormatWithHelpText("^(?i)yes|(?i)no$", "Are u sure you want to delete this event?");
                        if (inputInString.equalsIgnoreCase("yes"))
                            eventController.deleteEvent(event.getEventID());
                    }
                    nextMenu.show();
                    nextMenu.execute();
                }
            };

        } else {
            System.err.println("Invalid number !!!");
        }
        nextMenu.show();
        nextMenu.execute();
    }


    private void showEvents() {
        HashMap<Integer, Event> allEventsInMap = eventController.getAllEventsInHashMap();
        for (int i : allEventsInMap.keySet()) {
            System.out.println(i + ".");
            showEventInformation(allEventsInMap.get(i));
        }
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


    private boolean makeNewEvent() {
        LocalDateTime startDate = null;
        LocalDateTime endDate = null;
        while (true) {
            System.out.println("Start date : ");
            String startDateInString = getInputFormatWithHelpText("^20[2-9][0-9]-(0[0-9])|(1[0-2])-([0-2][0-9])|30T([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])|(?i)back$", "Example :2020-12-03T09:12:24 or enter back to return");
            if (startDateInString.equalsIgnoreCase("back"))
                return false;
            startDate = LocalDateTime.parse(startDateInString);
            try {
                startDate = eventController.checkStartDate(startDate);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        while (true) {
            System.out.println("End date : ");
            String endDateString = getInputFormatWithHelpText("^20[2-9][0-9]-(0[0-9])|(1[0-2])-([0-2][0-9])|30T([0-1][0-9]|2[0-3]):([0-5][0-9]):([0-5][0-9])|(?i)back$", "Example :2020-12-03T09:12:24 or enter back to return");
            if (endDateString.equalsIgnoreCase("back"))
                return false;
            endDate = LocalDateTime.parse(endDateString);
            try {
                endDate = eventController.checkEndDate(endDate, startDate);
                break;
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        String eventPointString = getInputFormatWithHelpText("^\\d+|(\\d+\\.\\d+)|(?i)back$", "Event Point : ");
        if (eventPointString.equalsIgnoreCase("back"))
            return false;
        double eventPoint = Double.parseDouble(eventPointString);
        RiskGame riskGame = makingRiskGame();
        if (riskGame == null)
            return false;
        System.out.println("1.Back   2.Invite all players  3.Invite players");
        String inputInString = getInputFormatWithHelpText("^1|2|3$", "Enter a number :");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            return false;
        } else if (input == 2) {
            eventController.createNewPublicEvent(startDate, endDate, riskGame, eventPoint);
        } else if (input == 3) {
            ArrayList<Player> invitedPlayers = getPlayersForEvent();
            if (invitedPlayers != null)
                eventController.createNewPrivateEvent(startDate, endDate, riskGame, eventPoint, invitedPlayers);
            else
                return false;
        }
        return true;
    }

    private RiskGame makingRiskGame() {
        int numberOfPlayer = 0;
        String riskGameName = getInputFormatWithHelpText("^.+$", "Enter a name for your risk game or type back to return :");
        if (riskGameName.equalsIgnoreCase("back"))
            return null;
        while (true) {
            String numberOfPlayersInString = getInputFormatWithHelpText("^\\d+|(?i)back$", "Enter the number of Player :");
            if (numberOfPlayersInString.equalsIgnoreCase("back"))
                return null;
            numberOfPlayer = Integer.parseInt(numberOfPlayersInString);
            try {
                gameController.checkNumberOfPlayers(numberOfPlayer);
                break;
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
        }
        String gamePointInString = getInputFormatWithHelpText("^\\d+|(?i)back$", "Game point : ");
        if (gamePointInString.equalsIgnoreCase("back"))
            return null;
        int gamePoint = Integer.parseInt(gamePointInString);
        long timer = setTimer();
        if (timer == 0)
            return null;
        System.out.println("Do U wanna Play Manually for Starting or Unmanually ?");
        System.out.println("1.Back      2.Manually      3.Unmanually");
        String input = getInputWithFormat("^1|2|3$");
        if (input.equalsIgnoreCase("1"))
            return null;
        else if (input.equalsIgnoreCase("2"))
            return new RiskGame(riskGameName, null, RiskGameType.PRIVATE, numberOfPlayer, gamePoint, timer, true);
        else
            return new RiskGame(riskGameName, null, RiskGameType.PRIVATE, numberOfPlayer, gamePoint, timer, false);
    }

    private long setTimer() {
        System.out.println("1.Back");
        System.out.println("2.3 min");
        System.out.println("3.5 min");
        System.out.println("4.7 min");
        String inputInString = getInputFormatWithHelpText("^1|2|3|4$", "Enter a number");
        int input = Integer.parseInt(inputInString);
        if (input == 1) {
            return 0;
        } else if (input == 2) {
            return 180;
        } else if (input == 3) {
            return 300;
        } else {
            return 420;
        }
    }

    private ArrayList<Player> getPlayersForEvent() {
        ArrayList<Player> invitedPlayers = new ArrayList<>();
        System.out.println("Type finish to close the list");
        while (true) {
            String userName = getInputFormatWithHelpText(".+|(?i)back", "Enter a username or type back to return : ");
            if (userName.equalsIgnoreCase("back"))
                return null;
            else if (userName.equalsIgnoreCase("finish"))
                return invitedPlayers;
            Player player = userController.findPlayerByUserName(userName);
            if (player == null)
                System.err.println("User didn't find");
            else {
                invitedPlayers.add(player);
            }
        }
    }

    private void showChangeOptionForEvent() {
        System.out.println("1.Back");
        System.out.println("2.Start date");
        System.out.println("3.End date");
        System.out.println("4.Change Invited Players");
        System.out.println("5.Event Point");
        System.out.println("6.Delete Event");
    }

}
