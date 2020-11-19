package controller;

import model.DataBase;
import model.Event;
import model.RiskGame;

import java.time.LocalDateTime;

public class EventController {
    private static EventController eventController = new EventController();
    private DataBase dataBase;
    public EventController() {
        dataBase = DataBase.getDataBase();
    }


    public void addEvent(String start , String end , RiskGame riskGame , Double eventPoint){
        //TODO .....
    }
    public void startEvent(){

    }
    public void endEvent(){

    }
    public void deleteEvent(int EventID){
        //TODO
    }

    public void changeEvent(int EventID ,String start , String endDate , double eventPoint){
        //TODO
    }
    public void joinEvent(int userID , int eventId){

    }



}
