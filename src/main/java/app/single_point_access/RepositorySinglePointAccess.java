package app.single_point_access;

import app.repository.*;
import app.repository.implemetation.*;

public class RepositorySinglePointAccess {

    private static final PersonRepository personRepository;
    private static final BadgeRepository badgeRepository;
    private static final QuestRepository questRepository;
    private static final JourneyRepository journeyRepository;
    private static final RankingRepository rankingRepository;

    static {
        personRepository = new PersonRepositoryImpl();
        badgeRepository = new BadgeRepositoryImpl();
        questRepository = new QuestRepositoryImpl();
        journeyRepository = new JourneyRepositoryImpl();
        rankingRepository = new RankingRepositoryImpl();
    }

    public static PersonRepository getPersonRepository() {
        return personRepository;
    }
    public static BadgeRepository getBadgeRepository() {
        return badgeRepository;
    }
    public static QuestRepository getQuestRepository(){return questRepository;}
    public static JourneyRepository getJourneyRepository(){return journeyRepository;}
    public static RankingRepository getRankingRepository(){return rankingRepository;}
}
