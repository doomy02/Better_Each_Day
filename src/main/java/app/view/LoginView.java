package app.view;

import lombok.Getter;

import javax.swing.*;

public class LoginView {
    @Getter
    private JPanel mainPanel;
    private JLabel pageLabel;
    @Getter
    private JTextField emailField;
    @Getter
    private JPasswordField passwordField;
    @Getter
    private JButton signInButton;
    @Getter
    private JButton signUpButton;
    private JLabel emailLabel;
    private JLabel passwordLabel;
}
