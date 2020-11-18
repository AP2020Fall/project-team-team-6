package controller;

import model.DataBase;

public class EventController {
    private static EventController eventController = new EventController();
    private DataBase dataBase;
    public EventController() {
        dataBase = DataBase.getDataBase();
    }
}
