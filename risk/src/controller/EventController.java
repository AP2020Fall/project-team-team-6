package controller;

import model.DataBase;
import model.Player;
import model.RiskGame;

import java.util.ArrayList;

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

    public void createNewEvent(String start , String end , RiskGame riskGame , Double eventPoint) {
        //TODO .....
    }

    public void startEvent(int eventID) {
    //ToDO
    }

    public void endEvent(int eventID) {
        //ToDO
    }

    public void deleteEvent(int eventID) {
        //TODO
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



}
