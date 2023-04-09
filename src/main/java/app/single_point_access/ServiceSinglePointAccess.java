package app.single_point_access;

import app.repository.QuestRepository;
import app.repository.implemetation.BadgeRepositoryImpl;
import app.service.*;
import app.service.implementation.*;

public class ServiceSinglePointAccess {

    private static final PersonService personService;
    private static final BadgeService badgeService;
    private static final QuestService questService;
    private static final JourneyService journeyService;
    private static final RankingService rankingService;

    static {
        personService = new PersonServiceImpl();
        questService = new QuestServiceImpl();
        badgeService = new BadgeServiceImpl();
        journeyService = new JourneyServiceImpl();
        rankingService = new RankingServiceImpl();
    }

    public static PersonService getPersonService() {return personService;}

    public static QuestService getQuestService(){
        return questService;
    }

    public static BadgeService getBadgeService(){
        return badgeService;
    }

    public static JourneyService getJourneyService(){return journeyService;}
    public static RankingService getRankingService(){return rankingService;}
}
