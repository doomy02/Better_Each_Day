package app;

import app.model.*;
import app.service.*;
import app.single_point_access.ServiceSinglePointAccess;

public class InnitApp {
    public Person person;
    public Journey journey;
    public Quest quest;
    public Quest quest1;
    public Ranking ranking;
    public InnitApp(){
        person = new Person();
        person.setName("Sebastian Stavar");
        person.setEmail("owner@gmail.com");
        person.setPassword("Password1");
        person.setTokens(100);

        PersonService personService = ServiceSinglePointAccess.getPersonService();
        Person savedPerson = personService.save(person);

        journey = new Journey();
        JourneyService journeyService = ServiceSinglePointAccess.getJourneyService();
        journey = journeyService.save(journey);

        quest = new Quest();
        quest.setName("Polynomial: I need help, fast!");
        quest.setOwner(person);
        quest.setTokens(100);
        quest.setAvailability(true);
        quest.setFirstPolynomial("x^6-2");
        quest.setOperation("add");
        quest.setLastPolynomial("x^4+2");

        quest1 = new Quest();
        quest1.setName("Subtract these polynomials, please!");
        quest1.setOwner(person);
        quest1.setTokens(100);
        quest1.setAvailability(true);
        quest1.setFirstPolynomial("x^10-x^2+1");
        quest1.setOperation("sub");
        quest1.setLastPolynomial("x^4+x+9");

        QuestService questService = ServiceSinglePointAccess.getQuestService();
        Quest savedQuest = questService.save(quest);
        savedQuest = questService.save(quest1);

        journeyService.addQuestJourney(journey, quest);
        journeyService.addQuestJourney(journey, quest1);

        ranking = new Ranking();
        RankingService rankingService = ServiceSinglePointAccess.getRankingService();
        Ranking savedRanking = rankingService.save(ranking);
        rankingService.addPersonRanking(ranking, person);

        Badge oneKTokens = new Badge();
        oneKTokens.setName("1k tokens");

        Badge oneHKTokens = new Badge();
        oneHKTokens.setName("100k tokens");

        Badge oneMTokens = new Badge();
        oneMTokens.setName("1M tokens");

        BadgeService badgeService = ServiceSinglePointAccess.getBadgeService();
        Badge savedBadge = badgeService.save(oneKTokens);
        savedBadge = badgeService.save(oneHKTokens);
        savedBadge = badgeService.save(oneMTokens);
    }
}
