package view;

import model.Admin;
import model.User;

public class OnlineGameMenu extends Menu {
    private static Admin admin = null;
    private static User currentUser = null;
    public OnlineGameMenu(Menu parentMenu) {
        super("Play Online", parentMenu);
        calculateSubMenusForMainMeu();
    }


    @Override
    public void show() {
        calculateSubMenusForMainMeu();
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }

    public static User getCurrentUser() {
        return currentUser;
    }

    public static void setCurrentUser(User currentUser) {
        OnlineGameMenu.currentUser = currentUser;
    }

    private void calculateSubMenusForMainMeu(){
        if(currentUser == null){
            subMenus.put(2,new RegisterMenu(this));
            subMenus.put(3,new LoginMenu(this));
        }else{
            subMenus.put(2,new UserMenu(this , currentUser));
        }
    }
}
