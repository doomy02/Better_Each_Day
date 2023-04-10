package app;

import app.controller.gui.LoginController;

public class Main {
    public static void main(String[] args) {
        InnitApp innitApp = new InnitApp();

        LoginController loginController = new LoginController();
        loginController.startLogic(innitApp.ranking);
    }
}
