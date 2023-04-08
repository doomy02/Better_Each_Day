package app;

import app.controller.LoginController;
import app.model.Person;
import app.service.PersonService;
import app.single_point_access.ServiceSinglePointAccess;

public class Main {
    public static void main(String[] args) {
        Person person = new Person();
        person.setName("Sebastian Stavar");
        person.setEmail("test@gmail.com");
        person.setPassword("password");
        person.setTokens(100);

        PersonService personService = ServiceSinglePointAccess.getPersonService();
        Person savedPerson = personService.save(person);

        LoginController loginController = new LoginController();
        loginController.startLogic();
    }
}
