package app;

import app.controller.LoginController;
import app.model.Journey;
import app.model.Person;
import app.model.Quest;
import app.service.JourneyService;
import app.service.PersonService;
import app.service.QuestService;
import app.single_point_access.ServiceSinglePointAccess;
import org.apache.catalina.Store;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Sebastian Stavar");
        person.setEmail("test@gmail.com");
        person.setPassword("password");
        person.setTokens(100);

        PersonService personService = ServiceSinglePointAccess.getPersonService();
        person = personService.save(person);

        Journey j = new Journey();
        JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
        j = journeyService.save(j);

        Quest q = new Quest();
        q.setName("Polynomial: I need help, fast!");
        q.setOwner(person);
        q.setTokens(100);
        q.setDescription("Can you effectuate the add operation on this two polynomials?" + " " + "x^2+10" + "and x^2-10");
        QuestService questService = ServiceSinglePointAccess.getQuestService();
        q = questService.save(q);

        journeyService.addQuestJourney(j, q);

        LoginController loginController = new LoginController();
        loginController.startLogic();
    }
}
