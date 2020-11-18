package view;

import model.User;

public class UserMenu extends Menu {
    private User user;
    public UserMenu(Menu parentMenu , User user) {
        super("Account", parentMenu);
        this.user = user;
    }

    @Override
    public void show() {
        super.show();
    }

    @Override
    public void execute() {
        super.execute();
    }
}
