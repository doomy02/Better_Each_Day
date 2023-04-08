package app.controller;

import app.model.Person;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.view.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView mainMenuView;

    public void startLogic(Person person){
        mainMenuView = new MainMenuView();
        GUIFrameSinglePointAccess.changePanel(mainMenuView.getMainPanel(), "Better Each Day");

        mainMenuView.getPageLabel().setText("Hi, " + person.getName() + "!");
        mainMenuView.getNameValue().setText(person.getName());
        mainMenuView.getEmailValue().setText(person.getEmail());
        mainMenuView.getTokensValue().setText(String.valueOf(person.getTokens()));

        mainMenuView.getSignOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });
    }
}
