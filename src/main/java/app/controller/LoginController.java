package app.controller;

import app.model.Person;
import app.service.PersonService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private PersonService personService = ServiceSinglePointAccess.getPersonService();

    public void startLogic(){
        loginView = new LoginView();
        GUIFrameSinglePointAccess.changePanel(loginView.getMainPanel(), "Better Each Day");

        loginView.getSignInButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String email = loginView.getEmailField().getText();
                String password = String.valueOf(loginView.getPasswordField().getPassword());

                Person person = personService.login(email, password);
                if(person != null) {
                    MainMenuController menuController = new MainMenuController();
                    menuController.startLogic(person);
                }
                else{
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid email or password!");
                }
            }
        });
    }
}
