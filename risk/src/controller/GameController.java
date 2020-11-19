package controller;

import model.DataBase;
import model.RiskGame;

import java.util.HashMap;

public class GameController {

 private static GameController gameController = new GameController();
 private DataBase dataBase;
 private GameController() {
       this.dataBase = DataBase.getDataBase();
    }
  public HashMap<Integer, RiskGame> getAllRiskGames(){
     return dataBase.getAllRiskGames();
  }

}
