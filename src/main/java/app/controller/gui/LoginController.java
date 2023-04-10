package app.controller.gui;

import app.model.Person;
import app.model.Ranking;
import app.service.PersonService;
import app.single_point_access.GUIFrameSinglePointAccess;
import app.single_point_access.ServiceSinglePointAccess;
import app.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {
    private LoginView loginView;
    private final PersonService personService = ServiceSinglePointAccess.getPersonService();

    public void startLogic(Ranking ranking){
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
                    menuController.startLogic(person, ranking);
                }
                else{
                    GUIFrameSinglePointAccess.showDialogMessage("Invalid email or password!");
                }
            }
        });

        loginView.getSignUpButton().addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                RegisterController registerController = new RegisterController();
                registerController.startLogic(ranking);
            }
        });
    }
}
