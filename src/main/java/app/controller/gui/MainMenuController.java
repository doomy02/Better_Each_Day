package app.controller.gui;

import app.model.*;
import app.service.BadgeService;
import app.service.JourneyService;
import app.service.PersonService;
import app.service.RankingService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.MainMenuView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

public class MainMenuController {
    private MainMenuView mainMenuView;
    private final PersonService personService = ServiceSinglePointAccess.getPersonService();
    private final BadgeService badgeService = ServiceSinglePointAccess.getBadgeService();

    public void startLogic(Person person, Ranking ranking){
        mainMenuView = new MainMenuView();
        GUIFrameSinglePointAccess.changePanel(mainMenuView.getMainPanel(), "Better Each Day");

        mainMenuView.getPageLabel().setText("Hi, " + person.getName() + "!");
        mainMenuView.getNameValue().setText(person.getName());
        mainMenuView.getEmailValue().setText(person.getEmail());
        mainMenuView.getTokensValue().setText(String.valueOf(person.getTokens()));
        mainMenuView.getSolvedValue().setText(String.valueOf(person.getNoOfQuest()));

        Badge b = badgeService.findById(1);
        Badge b2 = badgeService.findById(2);
        Badge b3 = badgeService.findById(3);
        boolean b1k = true, b100k = true, b1m = true;

        if(person.getTokens() >= 1000 && b1k){
            boolean ok = true;
            for(int i = 0; i < person.getBadges().size(); i++){
                if(Objects.equals(b, person.getBadges().get(i))){
                    ok = false;
                    break;
                }
            }

            if(ok){
                personService.addBadge(person, b);
                personService.update(person);

                b1k = false;
            }
        }

        if(person.getTokens() >= 100000 && b100k){
            boolean ok = true;
            for(int i = 0; i < person.getBadges().size(); i++){
                if(Objects.equals(b2, person.getBadges().get(i))){
                    ok = false;
                    break;
                }
            }

            if(ok){
                personService.addBadge(person, b2);
                personService.update(person);

                b100k = false;
            }
        }

        if(person.getTokens() >= 1000000 && b1m){
            boolean ok = true;
            for(int i = 0; i < person.getBadges().size(); i++){
                if(Objects.equals(b3, person.getBadges().get(i))){
                    ok = false;
                    break;
                }
            }

            if(ok){
                personService.addBadge(person, b3);
                personService.update(person);

                b1m = false;
            }
        }

        for(int i = 0; i < person.getBadges().size(); i++){
            mainMenuView.getBadgesBox().addItem(person.getBadges().get(i).getName());
        }

        mainMenuView.getSignOutButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic(ranking);
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

                RankingService rankingService = ServiceSinglePointAccess.getRankingService();
                Ranking ranking = rankingService.findById(1);

                QuestController questController = new QuestController();
                questController. startLogic(person, journey, ranking);
            }
        });
    }
}
