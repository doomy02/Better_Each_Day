package app.single_point_access;

import app.service.BadgeService;
import app.service.PersonService;
import app.service.implementation.PersonServiceImpl;

public class ServiceSinglePointAccess {

    private static PersonService personService;
    private static BadgeService badgeService;

    static {
        personService = new PersonServiceImpl();
    }

    public static PersonService getPersonService() {
        return personService;
    }

}
