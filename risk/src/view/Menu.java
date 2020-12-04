package view;

import controller.EventController;
import controller.GameController;
import controller.UserController;

import java.util.HashMap;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public abstract class Menu {
    protected String name;
    protected HashMap<Integer,Menu> subMenus;
    protected Menu parentMenu;
    protected Scanner scanner;
    private Scanner generalScanner = new Scanner(System.in);
    protected GameController gameController;
    protected UserController userController;
    protected EventController eventController;


    public Menu(String name , Menu parentMenu){
        this.name = name;
        this.parentMenu = parentMenu;
        this.subMenus = new HashMap<>();
        this.scanner = generalScanner;
        this.eventController = EventController.getEventController();
        this.userController = UserController.getUserController();
        this.gameController = GameController.getGameController();
    }

    public Menu() {

    }

    public String getName() {
        return name;
    }

    public HashMap<Integer, Menu> getSubMenus() {
        return subMenus;
    }

    public Menu getParentMenu() {
        return parentMenu;
    }

    public void show(){
        System.out.println(this.name);
        if(parentMenu == null){
            System.out.println("1.Exit");
        }else{
            System.out.println("1.Back");
        }
        for(Integer i : subMenus.keySet()){
            System.out.println(i+"."+subMenus.get(i).getName());
        }
    }
    public void execute(){
        String input = getInputFormatWithHelpText("^\\d+$","Enter a number:");
        int inputNumber = Integer.parseInt(input);
        Menu nextMenu = this;
        if(inputNumber == 1){
            if(parentMenu == null)
                System.exit(1);
            else
                nextMenu = parentMenu;
        }else if(inputNumber > 1 && inputNumber <= subMenus.size()+1) {
            nextMenu = subMenus.get(inputNumber);
        }else{
            System.out.println("Invalid Number");
        }
        nextMenu.show();
        nextMenu.execute();
    }
    public String getInputWithFormat(String regex){
        return getInputFormatWithHelpText(regex, "");
    }
    public String getInputFormatWithHelpText(String regex , String helpText){
        String input;
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher;
        System.out.println(helpText);
        while (true){
            input = scanner.nextLine();
            matcher = pattern.matcher(input);
            if (matcher.find())
                return input;
            System.out.println("Invalid input");
        }
    }
}
