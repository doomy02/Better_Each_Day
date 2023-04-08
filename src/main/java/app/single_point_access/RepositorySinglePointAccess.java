package app.single_point_access;

import app.repository.BadgeRepository;
import app.repository.PersonRepository;
import app.repository.implemetation.BadgeRepositoryImpl;
import app.repository.implemetation.PersonRepositoryImpl;

public class RepositorySinglePointAccess {

    private static PersonRepository personRepository;
    private static BadgeRepository badgeRepository;

    static {

        personRepository = new PersonRepositoryImpl();
        badgeRepository = new BadgeRepositoryImpl();
    }

    public static PersonRepository getPersonRepository() {
        return personRepository;
    }

    public static BadgeRepository getCarRepository() {
        return badgeRepository;
    }
}
