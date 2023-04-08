package app.view;

import lombok.Getter;

import javax.swing.*;

public class RegisterView {
    private JLabel pageLabel;
    @Getter
    private JPanel mainPanel;
    private JLabel emailLabel;
    @Getter
    private JTextField emailField;
    private JLabel nameLabel;
    @Getter
    private JTextField nameField;
    private JLabel passwordLabel;
    @Getter
    private JPasswordField passwordField1;
    @Getter
    private JButton Register;
    @Getter
    private JButton backButton;
}
