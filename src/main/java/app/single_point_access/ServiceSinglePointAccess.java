package app.single_point_access;

import app.repository.QuestRepository;
import app.repository.implemetation.BadgeRepositoryImpl;
import app.service.BadgeService;
import app.service.PersonService;
import app.service.QuestService;
import app.service.implementation.BadgeServiceImpl;
import app.service.implementation.PersonServiceImpl;
import app.service.implementation.QuestServiceImpl;

public class ServiceSinglePointAccess {

    private static PersonService personService;
    private static BadgeService badgeService;
    private static QuestService questService;

    static {
        personService = new PersonServiceImpl();
        questService = new QuestServiceImpl();
        badgeService = new BadgeServiceImpl();
    }

    public static PersonService getPersonService() {
        return personService;
    }

    public static QuestService getQuestService(){
        return questService;
    }

    public static BadgeService badgeService(){
        return badgeService;
    }

}
