package Server.controller;

import Server.model.database.LocalDataBase;
import Server.model.database.MySqlDataBase;
import Server.model.gamesModels.Event;
import Server.model.gamesModels.RiskGame;
import Server.model.usersModels.Admin;
import Server.model.usersModels.Player;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;

public class EventController {
    private static EventController eventController = new EventController();
    private GameController gameController = GameController.getGameController();
    private UserController userController = UserController.getUserController();
    private LocalDataBase localDataBase;

    private EventController() {
        localDataBase = LocalDataBase.getLocalDataBase();
    }

    public static EventController getEventController() {
        return eventController;
    }

    //creating event by admin methods
    //----------------------------------------------------------------------------------------------------------------------

    public void createNewPublicEvent(LocalDate startDate, LocalDate endDate, RiskGame riskGame) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame);
        allEvents.add(event);
        sendInviteMassageFromAdmin(event);
        MySqlDataBase.getMySqlDataBase().addNewEventToDataBase(event);
    }

    private void sendInviteMassageFromAdmin(Event event) {
        Admin admin = localDataBase.getAdmin();
        ArrayList<Player> invitedPlayers = event.getInvitedPlayers();
        for (Player player : invitedPlayers) {
            userController.sendMessageFromAdmin( player, "You are invited to an event please check the events");
        }
    }

    public void createNewPrivateEvent(LocalDate startDate, LocalDate endDate, RiskGame riskGame,  ArrayList<Player> invitedPlayer) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame, invitedPlayer);
        allEvents.add(event);
        MySqlDataBase.getMySqlDataBase().addNewEventToDataBase(event);
    }

    public void startEvent(int eventID) {
        //ToDO
    }

    public void endEvent(int eventID) {
        //ToDO
    }

    public void deleteEvent(int eventID) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        allEvents.remove(eventID);
    }

    public void changeEvent(Event event, LocalDate start, LocalDate endDate, double eventPoint, ArrayList<Player> invitedList) {
        event.setStartDate(start);
        event.setEndDate(endDate);
        event.setEventPoint(eventPoint);
        event.setInvitedPlayers(invitedList);
    }

    public void joinEvent(Player player, Event event) throws Exception {
        gameController.addPlayerToGame(player, event.getGame());
    }

    public HashMap<Integer, Event> getAllEventsInHashMap() {
        HashMap<Integer, Event> allEventsInMap = new HashMap<>();
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        for (int i = 3; i < allEvents.size() + 3; i++) {
            allEventsInMap.put(i, allEvents.get(i - 3));
        }
        return allEventsInMap;
    }

    public LocalDate checkStartDate(LocalDate startDate) throws Exception {
        if (startDate.isBefore(LocalDate.now()))
            throw new Exception("This date is before now");
        else
            return startDate;
        // TODO: 2/6/2021 ..........moshkel dare kolan
    }

    public LocalDate checkEndDate(LocalDate endDate, LocalDate startDate) throws Exception {
        if (endDate.isBefore(LocalDate.now()))
            throw new Exception("This date is before now");
        else if (endDate.isBefore(startDate))
            throw new Exception("This date is before start date");
        else
            return endDate;
    }

    public HashMap<Integer, Event> getEventForPlayer(Player player) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        HashMap<Integer, Event> playersEvents = new HashMap<>();
        int index = 2;
        for (Event event : allEvents) {
            boolean isEventValid = eventController.isEventValid(event);
            if (event.getInvitedPlayers().contains(player) && isEventValid) {
                playersEvents.put(index, event);
                index++;
            }
        }
        return playersEvents;
    }

    public ArrayList<RiskGame> getAllEventGamesForPlayer(Player player) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        ArrayList<RiskGame> eventsGame = new ArrayList<>();
        for (Event event : allEvents) {
            if (isEventValid(event)) {
                if (event.getInvitedPlayers().contains(player))
                    eventsGame.add(event.getGame());
            }
        }
        return eventsGame;
    }

    public HashMap<Integer, RiskGame> getEventsGameForPlayerInHashMap(Player player, int index) {
        ArrayList<RiskGame> eventGame = getAllEventGamesForPlayer(player);
        HashMap<Integer, RiskGame> eventsGameInHashMap = new HashMap<>();
        for (RiskGame riskGame : eventGame) {
            eventsGameInHashMap.put(index, riskGame);
            index++;
        }
        return eventsGameInHashMap;
    }

    public boolean isEventValid(Event event) {
        LocalDate startDate = event.getStartDate();
        LocalDate endDate = event.getEndDate();
//        return startDate.isBefore(LocalDateTime.now()) && endDate.isAfter(LocalDateTime.now());
        return endDate.isAfter(LocalDate.now());
    }

    public Event findEventByEventId(int eventId) {
        for (Event event : localDataBase.getAllEvents()) {
            if (event.getEventID() == eventId)
                return event;
        }
        return null;
    }


}
