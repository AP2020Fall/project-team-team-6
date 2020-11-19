package controller;

import model.Card;
import model.DataBase;
import model.Player;
import model.RiskGame;

import java.util.ArrayList;
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
  public int matchCards(Card card1, Card card2, Card card3, Player player) {
     return 0;
  }
  public Card giveNewCard(Player player) {
     return null;
  }


}
