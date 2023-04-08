package app.single_point_access;

import app.repository.BadgeRepository;
import app.repository.PersonRepository;
import app.repository.QuestRepository;
import app.repository.implemetation.BadgeRepositoryImpl;
import app.repository.implemetation.PersonRepositoryImpl;
import app.repository.implemetation.QuestRepositoryImpl;

public class RepositorySinglePointAccess {

    private static PersonRepository personRepository;
    private static BadgeRepository badgeRepository;
    private static QuestRepository questRepository;

    static {
        personRepository = new PersonRepositoryImpl();
        badgeRepository = new BadgeRepositoryImpl();
        questRepository = new QuestRepositoryImpl();
    }

    public static PersonRepository getPersonRepository() {
        return personRepository;
    }

    public static BadgeRepository getBadgeRepository() {
        return badgeRepository;
    }
    public static QuestRepository getQuestRepository(){return questRepository;}
}
