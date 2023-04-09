package app.controller;

import app.model.Journey;
import app.model.Person;
import app.model.Quest;
import app.model.Ranking;
import app.repository.JourneyRepository;
import app.service.JourneyService;
import app.service.PersonService;
import app.service.QuestService;
import app.service.RankingService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.QuestView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;

public class QuestController {
    private QuestView questView;
    private final QuestService questService = ServiceSinglePointAccess.getQuestService();
    private final PersonService personService = ServiceSinglePointAccess.getPersonService();
    private final JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
    private final RankingService rankingService = ServiceSinglePointAccess.getRankingService();

    public void startLogic(Person person, Journey j, Ranking r){
        questView = new QuestView();
        GUIFrameSinglePointAccess.changePanel(questView.getMainPanel(), "Better Each Day");

        if(j.getNoQuests() != 0)
        {
            for(int i = 0; i < j.getNoQuests(); i++){
                if(j.getQuests().get(i).getAvailability()){
                    questView.getComboBox1().addItem(j.getQuests().get(i).getName());
                }
                else{
                    questService.delete(j.getQuests().get(i));
                    journeyService.update(j);
                }
            }
        }

        r.getPersons().sort(Comparator.comparing(Person::getNoOfQuest).thenComparing(Person::getTokens).thenComparing(Person::getId).reversed());
        rankingService.update(r);
        for(int i = 0; i < r.getNoOfPersons(); i++){
            questView.getRankBox().addItem((i + 1) + ". Quests solved: " + r.getPersons().get(i).getNoOfQuest() + " -" +r.getPersons().get(i).getName());
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

                if(person.getTokens() < tokens) {
                    GUIFrameSinglePointAccess.showDialogMessage("Insufficient tokens!");
                    return;
                }

                person.setTokens(person.getTokens() - tokens);
                Person user = personService.update(person);

                Quest q = new Quest();
                q.setTokens(tokens);
                q.setName(name);
                q.setDescription(description);
                q.setAvailability(true);
                q.setOwner(person);

                Quest savedQuest = questService.save(q);
                journeyService.addQuestJourney(j, q);
                Journey savedJourney = journeyService.update(j);
                questView.getComboBox1().addItem(q.getName());

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
                String name = questView.getComboBox1().getSelectedItem().toString();

                if(questView.getComboBox1().getSelectedItem() == null){
                    GUIFrameSinglePointAccess.showDialogMessage("No quests available! Come back later.");
                }
                else{
                    QuestService questService1 = ServiceSinglePointAccess.getQuestService();
                    Quest savedQuest = questService1.findByName(name);

                    questView.getAnswerNameValue().setText(savedQuest.getName());
                    questView.getTokensValue().setText(String.valueOf(savedQuest.getTokens()));
                    questView.getAnswerDescriptionField().setText(savedQuest.getDescription());
                }
            }
        });

        questView.getSubmit2Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                person.setNoOfQuest(person.getNoOfQuest() + 1);
                Person user = personService.update(person);
                rankingService.update(r);

                String name = questView.getComboBox1().getSelectedItem().toString();
                QuestService questService1 = ServiceSinglePointAccess.getQuestService();
                Quest savedQuest = questService1.findByName(name);

                savedQuest.setAvailability(false);
                savedQuest = questService1.update(savedQuest);
            }
        });
    }
}
