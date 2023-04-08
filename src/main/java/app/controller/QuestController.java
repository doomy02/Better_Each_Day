package app.controller;

import app.model.Person;
import app.model.Quest;
import app.service.QuestService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.QuestView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class QuestController {
    private QuestView questView;
    private QuestService questService = ServiceSinglePointAccess.getQuestService();

    public void startLogic(Person person){
        questView = new QuestView();
        GUIFrameSinglePointAccess.changePanel(questView.getMainPanel(), "Better Each Day");

        questView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuController menuController = new MainMenuController();
                menuController.startLogic(person);
            }
        });

        questView.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = questView.getNameField().getText();
                Integer tokens = Integer.valueOf(questView.getTokensField().getText());
                String description = questView.getDescriptionField().getText();

                Quest q = new Quest();
                q.setTokens(tokens);
                q.setName(name);
                q.setDescription(description);
                q.setAvailability(true);
                q.setOwner(person);

                Quest savedQuest = questService.update(q);

                GUIFrameSinglePointAccess.showDialogMessage("Success!");
                questView.getNameField().setText("");
                questView.getDescriptionField().setText("");
                questView.getTokensField().setText("");
            }
        });
    }
}
