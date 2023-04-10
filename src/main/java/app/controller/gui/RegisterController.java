package app.controller.gui;

import app.exception.ValidationException;
import app.model.Person;
import app.model.Ranking;
import app.service.PersonService;
import app.service.RankingService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.validator.implementation.ValidateImpl;
import app.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterController {
    private RegisterView registerView;
    private final PersonService personService = ServiceSinglePointAccess.getPersonService();
    private final RankingService rankingService = ServiceSinglePointAccess.getRankingService();

    public void startLogic(Ranking r){
        registerView = new RegisterView();
        GUIFrameSinglePointAccess.changePanel(registerView.getMainPanel(), "Better Each Day");

        registerView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic(r);
            }
        });

        registerView.getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = registerView.getEmailField().getText();
                String name = registerView.getNameField().getText();
                String password = String.valueOf(registerView.getPasswordField1().getPassword());

                ValidateImpl validate = new ValidateImpl();
                boolean validateEmail = validate.validateEmail(email);
                boolean validatePassword = validate.validatePassword(password);
                boolean validateName = validate.validateName(name);

                if(!validateEmail){
                    try {
                        registerView.getNameField().setText("");
                        registerView.getEmailField().setText("");
                        registerView.getPasswordField1().setText("");
                        GUIFrameSinglePointAccess.showDialogMessage("Wrong email format! Needs to be smth@gmail|proton|yahoo.com");
                        throw new ValidationException("Wrong email format!");
                    } catch (ValidationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(!validateName){
                    try {
                        registerView.getNameField().setText("");
                        registerView.getEmailField().setText("");
                        registerView.getPasswordField1().setText("");
                        GUIFrameSinglePointAccess.showDialogMessage("Wrong name format! Needs to be your full name with no numbers!");
                        throw new ValidationException("Wrong name format!");
                    } catch (ValidationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else if(!validatePassword){
                    try {
                        registerView.getNameField().setText("");
                        registerView.getEmailField().setText("");
                        registerView.getPasswordField1().setText("");
                        GUIFrameSinglePointAccess.showDialogMessage("Wrong name format! Needs to have at least 1 uppercase, 1 digit, no spaces and 6 characters!");
                        throw new ValidationException("Wrong password format!");
                    } catch (ValidationException ex) {
                        throw new RuntimeException(ex);
                    }
                }
                else{
                    registerView.getNameField().setText("");
                    registerView.getEmailField().setText("");
                    registerView.getPasswordField1().setText("");

                    Person person = personService.findByEmail(email);
                    if(person != null) {
                        GUIFrameSinglePointAccess.showDialogMessage("Email already in use!");
                    }
                    else{
                        PersonService personService = ServiceSinglePointAccess.getPersonService();
                        Person savedPerson = personService.register(name, password, email);
                        savedPerson.setTokens(100);
                        savedPerson = personService.update(savedPerson);

                        rankingService.addPersonRanking(r, savedPerson);

                        LoginController loginController = new LoginController();
                        loginController.startLogic(r);
                    }
                }
            }
        });
    }
}
