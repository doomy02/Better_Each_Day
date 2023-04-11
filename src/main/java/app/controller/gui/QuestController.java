package app.controller.gui;

import app.exception.ValidationException;
import app.model.*;
import app.service.JourneyService;
import app.service.PersonService;
import app.service.QuestService;
import app.service.RankingService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.validator.implementation.ValidateImpl;
import app.view.QuestView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Comparator;
import java.util.Objects;

public class QuestController {
    private QuestView questView;
    private final QuestService questService = ServiceSinglePointAccess.getQuestService();
    private final PersonService personService = ServiceSinglePointAccess.getPersonService();
    private final JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
    private final RankingService rankingService = ServiceSinglePointAccess.getRankingService();

    public void startLogic(Person person, Journey journey, Ranking ranking){
        questView = new QuestView();
        GUIFrameSinglePointAccess.changePanel(questView.getMainPanel(), "Better Each Day");

        journey.getQuests().sort(Comparator.comparing(Quest::getTokens).reversed());
        if(journey.getNoQuests() >= 1)
        {
            for(int i = 0; i < journey.getNoQuests(); i++){
                if(journey.getQuests().get(i).getAvailability()){
                    questView.getComboBox1().addItem(journey.getQuests().get(i).getName());
                }
                else{
                    journeyService.update(journey);
                }
            }
        }
        else{
            questView.getComboBox1().addItem("No quests yet!");
        }

        ranking.getPersons().sort(Comparator.comparing(Person::getNoOfQuest).thenComparing(Person::getTokens).thenComparing(Person::getId).reversed());
        rankingService.update(ranking);
        for(int i = 0; i < ranking.getNoOfPersons(); i++){
            questView.getRankBox().addItem((i + 1) + ". Quests solved: " + ranking.getPersons().get(i).getNoOfQuest() + " -" + ranking.getPersons().get(i).getName());
        }

        questView.getOperationCreateBox().addItem("add");
        questView.getOperationCreateBox().addItem("sub");
        questView.getOperationCreateBox().addItem("mul");
        questView.getOperationCreateBox().addItem("div");

        questView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuController menuController = new MainMenuController();
                menuController.startLogic(person, ranking);
            }
        });

        questView.getSubmitButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = questView.getNameField().getText();
                Integer tokens = Integer.valueOf(questView.getTokensField().getText());
                String firstPolynomial = questView.getFirstPolynomialField().getText();
                String secondPolynomial = questView.getSecondPolynomialField().getText();
                String operation = questView.getOperationCreateBox().getSelectedItem().toString();

                ValidateImpl validate = new ValidateImpl();
                boolean validateTokens = validate.validateTokens(String.valueOf(tokens));
                if(!validateTokens){
                    try {
                        questView.getNameField().setText("");
                        questView.getSecondPolynomialField().setText("");
                        questView.getFirstPolynomialField().setText("");
                        questView.getTokensField().setText("");
                        GUIFrameSinglePointAccess.showDialogMessage("Wrong amount of tokens! Needs to be a value of [100, 999]");
                        throw new ValidationException("Wrong tokens format!");
                    } catch (ValidationException ex) {
                        throw new RuntimeException(ex);
                    }
                }

                if(person.getTokens() < tokens) {
                    GUIFrameSinglePointAccess.showDialogMessage("Insufficient tokens!");
                    return;
                }

                person.setTokens(person.getTokens() - tokens);

                Quest q = new Quest();
                q.setTokens(tokens);
                q.setName(name);
                q.setFirstPolynomial(firstPolynomial);
                q.setLastPolynomial(secondPolynomial);
                q.setAvailability(true);
                q.setOperation(operation);
                q.setOwner(person);

                Quest savedQuest = questService.save(q);
                journeyService.addQuestJourney(journey, q);
                Journey savedJourney = journeyService.update(journey);
                questView.getComboBox1().addItem(q.getName());

                personService.addQuest(person, q);
                Person user = personService.update(person);

                GUIFrameSinglePointAccess.showDialogMessage("Success!");
                questView.getNameField().setText("");
                questView.getSecondPolynomialField().setText("");
                questView.getFirstPolynomialField().setText("");
                questView.getTokensField().setText("");

                QuestController questController = new QuestController();
                questController.startLogic(person, journey, ranking);
            }
        });

        questView.getBack2Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MainMenuController menuController = new MainMenuController();
                menuController.startLogic(person, ranking);
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
                    questView.getFirstPolynomialValue().setText(savedQuest.getFirstPolynomial());
                    questView.getSecondPolynomialValue().setText(savedQuest.getLastPolynomial());
                    questView.getOperationValue().setText(savedQuest.getOperation());
                }
            }
        });

        questView.getSubmit2Button().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = questView.getComboBox1().getSelectedItem().toString();
                QuestService questService1 = ServiceSinglePointAccess.getQuestService();
                Quest savedQuest = questService1.findByName(name);
                String submit = questView.getAnswerTextField().getText();

                Polynomial x = new Polynomial();
                Polynomial y = new Polynomial();
                x.stringToPolynomial(savedQuest.getFirstPolynomial());
                y.stringToPolynomial(savedQuest.getLastPolynomial());

                Polynomial res = new Polynomial();
                switch(savedQuest.getOperation()){
                    case "add":
                        res = Operations.add(x, y);
                        break;
                    case "sub":
                        res = Operations.sub(x, y);
                        break;
                    case "mul":
                        res = Operations.mul(x, y);
                        break;
                    case "div":
                        res = Operations.divide(x, y);
                        break;
                }

                if(Objects.equals(res.printPolynomial(), submit)){
                    person.setTokens(person.getTokens() + savedQuest.getTokens());
                    person.setNoOfQuest(person.getNoOfQuest() + 1);
                    Person user = personService.update(person);
                    rankingService.update(ranking);

                    GUIFrameSinglePointAccess.showDialogMessage("Quest accomplished! Refresh to update the leader score!");
                    savedQuest.setResult(res.printPolynomial());
                    savedQuest.setAvailability(false);
                    savedQuest = questService1.update(savedQuest);
                }
                else{
                    GUIFrameSinglePointAccess.showDialogMessage("Wrong answer, try again to earn your prize! P.S: constants may need to end with '.0'");
                }

                MainMenuController menuController = new MainMenuController();
                menuController.startLogic(person, ranking);
            }
        });
    }
}
