package controller;

import model.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;

public class EventController {
    private static EventController eventController = new EventController();
    private GameController gameController = GameController.getGameController();
    private UserController userController = UserController.getUserController();
    private DataBase dataBase;
    private EventController() {
        dataBase = DataBase.getDataBase();
    }

    public static EventController getEventController() {
        return eventController;
    }

    //creating event by admin methods
    //----------------------------------------------------------------------------------------------------------------------

    public void createNewPublicEvent(LocalDateTime startDate , LocalDateTime endDate , RiskGame riskGame , Double eventPoint) {
        ArrayList<Event> allEvents = dataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame, eventPoint);
        allEvents.add(event);
        sendInviteMassageFromAdmin(event);
    }

    private void sendInviteMassageFromAdmin(Event event){
        Admin admin = dataBase.getAdmin();
        ArrayList<Player> invitedPlayers = event.getInvitedPlayers();
        for(Player player : invitedPlayers){
            userController.sendMessageFromAdmin(admin , player , "You are invited to an event please check the events");
        }
    }
    public void createNewPrivateEvent(LocalDateTime startDate , LocalDateTime endDate , RiskGame riskGame , Double eventPoint , ArrayList<Player> invitedPlayer) {
        ArrayList<Event> allEvents = dataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame, eventPoint , invitedPlayer);
        allEvents.add(event);
    }

    public void startEvent(int eventID) {
    //ToDO
    }

    public void endEvent(int eventID) {
        //ToDO
    }

    public void deleteEvent(int eventID) {
        ArrayList<Event> allEvents = dataBase.getAllEvents();
        allEvents.remove(eventID);
    }

    public void inviteAllPLayers(int eventID) {
        //ToDo
    }

    public void inviteChosenPlayers(int eventID, ArrayList<Player> chosenPlayers) {
        //ToDo
    }
    public void changeEvent(Event event , LocalDateTime start , LocalDateTime endDate , double eventPoint , ArrayList<Player> invitedList){
        event.setStartDate(start);
        event.setEndDate(endDate);
        event.setEventPoint(eventPoint);
        event.setInvitedPlayers(invitedList);
    }
    public void joinEvent(int userID , int eventId){
        //ToDo
    }

    public HashMap<Integer , Event > getAllEventsInHashMap(){
        HashMap<Integer ,Event> allEventsInMap = new HashMap<>();
        ArrayList<Event> allEvents = dataBase.getAllEvents();
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



}
