package controller;

import model.DataBase;
import model.Event;
import model.Player;
import model.RiskGame;

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

    public void createNewEvent(LocalDateTime startDate , LocalDateTime endDate , RiskGame riskGame , Double eventPoint) {
        ArrayList<Event> allEvents = dataBase.getAllEvents();
        Event event = new Event(startDate, endDate, riskGame, eventPoint);
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

    public void changeEvent(int EventID ,String start , String endDate , double eventPoint){
        //TODO
    }
    public void joinEvent(int userID , int eventId){
        //ToDo
    }
    public ArrayList<Event> showAllEvents() {
        ArrayList<Event> allEvents = dataBase.getAllEvents();
        return allEvents;
     }


}
