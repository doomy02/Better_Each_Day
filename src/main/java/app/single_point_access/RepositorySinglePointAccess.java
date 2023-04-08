package app.single_point_access;

import app.repository.BadgeRepository;
import app.repository.JourneyRepository;
import app.repository.PersonRepository;
import app.repository.QuestRepository;
import app.repository.implemetation.BadgeRepositoryImpl;
import app.repository.implemetation.JourneyRepositoryImpl;
import app.repository.implemetation.PersonRepositoryImpl;
import app.repository.implemetation.QuestRepositoryImpl;

public class RepositorySinglePointAccess {

    private static final PersonRepository personRepository;
    private static final BadgeRepository badgeRepository;
    private static final QuestRepository questRepository;
    private static final JourneyRepository journeyRepository;

    static {
        personRepository = new PersonRepositoryImpl();
        badgeRepository = new BadgeRepositoryImpl();
        questRepository = new QuestRepositoryImpl();
        journeyRepository = new JourneyRepositoryImpl();
    }

    public static PersonRepository getPersonRepository() {
        return personRepository;
    }
    public static BadgeRepository getBadgeRepository() {
        return badgeRepository;
    }
    public static QuestRepository getQuestRepository(){return questRepository;}
    public static JourneyRepository getJourneyRepository(){return journeyRepository;}
}
