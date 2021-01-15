package controller;

import model.database.LocalDataBase;
import model.database.MySqlDataBase;
import model.gamesModels.Event;
import model.gamesModels.RiskGame;
import model.usersModels.Admin;
import model.usersModels.Player;

import java.time.LocalDateTime;
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

    public void createNewPublicEvent(LocalDateTime startDate , LocalDateTime endDate , RiskGame riskGame , Double eventPoint) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame, eventPoint);
        allEvents.add(event);
        sendInviteMassageFromAdmin(event);
        MySqlDataBase.getMySqlDataBase().addNewEventToDataBase(event);
    }

    private void sendInviteMassageFromAdmin(Event event){
        Admin admin = localDataBase.getAdmin();
        ArrayList<Player> invitedPlayers = event.getInvitedPlayers();
        for(Player player : invitedPlayers){
            userController.sendMessageFromAdmin(admin , player , "You are invited to an event please check the events");
        }
    }
    public void createNewPrivateEvent(LocalDateTime startDate , LocalDateTime endDate , RiskGame riskGame , Double eventPoint , ArrayList<Player> invitedPlayer) {
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame, eventPoint , invitedPlayer);
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

    public void changeEvent(Event event , LocalDateTime start , LocalDateTime endDate , double eventPoint , ArrayList<Player> invitedList){
        event.setStartDate(start);
        event.setEndDate(endDate);
        event.setEventPoint(eventPoint);
        event.setInvitedPlayers(invitedList);
    }
    public void joinEvent(Player player , Event event) throws Exception {
        gameController.addPlayerToGame(player , event.getGame());
    }

    public HashMap<Integer , Event > getAllEventsInHashMap(){
        HashMap<Integer ,Event> allEventsInMap = new HashMap<>();
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        for(int i = 3 ; i < allEvents.size() + 3; i++){
            allEventsInMap.put(i , allEvents.get( i -3 ));
        }
        return allEventsInMap;
    }

    public LocalDateTime checkStartDate(LocalDateTime startDate) throws Exception {
        if(startDate.isBefore(LocalDateTime.now()))
            throw new Exception("This date is before now");
        else
            return startDate;
    }

    public LocalDateTime checkEndDate(LocalDateTime endDate , LocalDateTime startDate) throws Exception {
        if(endDate.isBefore(LocalDateTime.now()))
            throw new Exception("This date is before now");
        else if(endDate.isBefore(startDate))
            throw new Exception("This date is before start date");
        else
            return endDate;
    }

    public HashMap<Integer , Event> getEventForPlayer(Player player){
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        HashMap<Integer , Event> playersEvents = new HashMap<>();
        int index = 2 ;
        for(Event event : allEvents){
            boolean isEventValid = eventController.isEventValid(event);
            if(event.getInvitedPlayers().contains(player) && isEventValid){
                playersEvents.put(index , event);
                index++;
            }
        }
        return playersEvents;
    }
    public ArrayList<RiskGame> getAllEventGamesForPlayer(Player player){
        ArrayList<Event> allEvents = localDataBase.getAllEvents();
        ArrayList<RiskGame> eventsGame = new ArrayList<>();
        for(Event event : allEvents){
            if(isEventValid(event)){
                if(event.getInvitedPlayers().contains(player))
                    eventsGame.add(event.getGame());
            }
        }
        return eventsGame;
    }
    public HashMap<Integer , RiskGame> getEventsGameForPlayerInHashMap(Player player , int index){
        ArrayList<RiskGame> eventGame = getAllEventGamesForPlayer(player);
        HashMap<Integer , RiskGame> eventsGameInHashMap = new HashMap<>();
        for(RiskGame riskGame : eventGame){
            eventsGameInHashMap.put(index , riskGame);
            index ++;
        }
        return eventsGameInHashMap;
    }
    public boolean isEventValid(Event event){
        LocalDateTime startDate = event.getStartDate();
        LocalDateTime endDate = event.getEndDate();
//        return startDate.isBefore(LocalDateTime.now()) && endDate.isAfter(LocalDateTime.now());
        return endDate.isAfter(LocalDateTime.now());
    }

    public Event findEventByEventId(int eventId){
        for(Event event : localDataBase.getAllEvents()){
            if(event.getEventID() == eventId)
                return event;
        }
        return null;
    }



}
