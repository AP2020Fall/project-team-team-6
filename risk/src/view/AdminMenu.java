package view;

import model.Admin;

public class AdminMenu extends Menu {
    Admin admin =null;
    public AdminMenu(Menu parentMenu , Admin admin) {
        super("Admin Account", parentMenu);
        this.admin = admin;
    }
}
