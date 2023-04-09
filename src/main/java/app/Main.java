package app;

import app.controller.LoginController;
import app.model.Journey;
import app.model.Person;
import app.model.Quest;
import app.model.Ranking;
import app.service.JourneyService;
import app.service.PersonService;
import app.service.QuestService;
import app.service.RankingService;
import app.single_point_access.ServiceSinglePointAccess;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Sebastian Stavar");
        person.setEmail("test@gmail.com");
        person.setPassword("password");
        person.setTokens(100);

        Person person2 = new Person();
        person2.setName("Mister White");
        person2.setEmail("white@gmail.com");
        person2.setPassword("1234");
        person2.setTokens(101);

        PersonService personService = ServiceSinglePointAccess.getPersonService();
        Person savedPerson = personService.save(person);
        savedPerson = personService.save(person2);

        Journey j = new Journey();
        JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
        j = journeyService.save(j);

        Quest q = new Quest();
        q.setName("Polynomial: I need help, fast!");
        q.setOwner(person);
        q.setTokens(100);
        q.setAvailability(true);
        q.setDescription("Can you effectuate the add operation on this two polynomials?" + " " + "x^2+10" + " and x^2-10");

        QuestService questService = ServiceSinglePointAccess.getQuestService();
        Quest savedQuest = questService.save(q);

        journeyService.addQuestJourney(j, q);

        Ranking r = new Ranking();
        RankingService rankingService = ServiceSinglePointAccess.getRankingService();
        Ranking savedRanking = rankingService.save(r);
        rankingService.addPersonRanking(r, person);
        rankingService.addPersonRanking(r, person2);

        LoginController loginController = new LoginController();
        loginController.startLogic();
    }
}
