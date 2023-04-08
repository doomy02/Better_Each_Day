package app.single_point_access;

import app.repository.QuestRepository;
import app.repository.implemetation.BadgeRepositoryImpl;
import app.service.BadgeService;
import app.service.JourneyService;
import app.service.PersonService;
import app.service.QuestService;
import app.service.implementation.BadgeServiceImpl;
import app.service.implementation.JourneyServiceImpl;
import app.service.implementation.PersonServiceImpl;
import app.service.implementation.QuestServiceImpl;

public class ServiceSinglePointAccess {

    private static final PersonService personService;
    private static final BadgeService badgeService;
    private static final QuestService questService;
    private static final JourneyService journeyService;

    static {
        personService = new PersonServiceImpl();
        questService = new QuestServiceImpl();
        badgeService = new BadgeServiceImpl();
        journeyService = new JourneyServiceImpl();
    }

    public static PersonService getPersonService() {return personService;}

    public static QuestService getQuestService(){
        return questService;
    }

    public static BadgeService getBadgeService(){
        return badgeService;
    }

    public static JourneyService getJourneyService(){return journeyService;}
}
