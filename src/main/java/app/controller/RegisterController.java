package app.controller;

import app.model.Person;
import app.service.PersonService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.RegisterView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;

public class RegisterController {
    private RegisterView registerView;
    private PersonService personService = ServiceSinglePointAccess.getPersonService();

    public void startLogic(){
        registerView = new RegisterView();
        GUIFrameSinglePointAccess.changePanel(registerView.getMainPanel(), "Better Each Day");

        registerView.getBackButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginController loginController = new LoginController();
                loginController.startLogic();
            }
        });

        registerView.getRegister().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = registerView.getEmailField().getText();
                String name = registerView.getNameField().getText();
                String password = String.valueOf(registerView.getPasswordField1().getPassword());
                boolean ok = true;

                if(email.isEmpty() || name.isEmpty() || password.isEmpty()){
                    GUIFrameSinglePointAccess.showDialogMessage("Please, fill each field!");

                    registerView.getNameField().setText("");
                    registerView.getEmailField().setText("");
                    registerView.getPasswordField1().setText("");

                    ok = false;
                }
                else if(ok){
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

                        LoginController loginController = new LoginController();
                        loginController.startLogic();
                    }
                }
            }
        });
    }
}
