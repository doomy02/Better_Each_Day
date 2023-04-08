package app.controller;

import app.model.Journey;
import app.model.Person;
import app.service.JourneyService;
import app.service.PersonService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenuController {
    private MainMenuView mainMenuView;
    private final PersonService personService = ServiceSinglePointAccess.getPersonService();

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

        mainMenuView.getClaimButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person.setTokens(person.getTokens() + 50);
                Person savedPerson = personService.update(person);

                mainMenuView.getTokensValue().setText(String.valueOf(person.getTokens()));

                GUIFrameSinglePointAccess.showDialogMessage("Daily reward claimed!");
                mainMenuView.getClaimButton().setVisible(false);
            }
        });

        mainMenuView.getQuestButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
                Journey journey = journeyService.findById(1);

                QuestController questController = new QuestController();
                questController.startLogic(person, journey);
            }
        });
    }
}
