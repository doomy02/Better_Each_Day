package app.single_point_access;

import app.service.PersonService;
import app.service.implementation.PeronServiceImpl;

public class ServiceSinglePointAccess {

    private static PersonService personService;

    static {
        personService = new PeronServiceImpl();
    }

    public static PersonService getPersonService() {
        return personService;
    }

}
