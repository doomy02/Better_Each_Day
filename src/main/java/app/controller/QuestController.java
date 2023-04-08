package app.controller;

import app.model.Journey;
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
    private final QuestService questService = ServiceSinglePointAccess.getQuestService();

    public void startLogic(Person person, Journey j){
        questView = new QuestView();
        GUIFrameSinglePointAccess.changePanel(questView.getMainPanel(), "Better Each Day");

        for(int i = 0; i < j.getNoQuests(); i++){
            questView.getComboBox1().addItem(j.getQuests().get(i).getName());
        }

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

        questView.getBack2Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuController menuController = new MainMenuController();
                menuController.startLogic(person);
            }
        });

        questView.getSelectButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = questView.getComboBox1().getName();
                Quest quest = questService.findByName(name);

                questView.getTokensValue().setText(String.valueOf(quest.getTokens()));
                questView.getAnswerNameValue().setText(quest.getName());
                questView.getDescriptionField().setText(quest.getDescription());
            }
        });
    }
}
