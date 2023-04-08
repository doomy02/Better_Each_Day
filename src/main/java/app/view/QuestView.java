package app.view;

import lombok.Getter;

import javax.swing.*;

public class QuestView {
    private JLabel pageLabel;
    @Getter
    private JPanel mainPanel;
    private JTabbedPane tabbedPane1;
    @Getter
    private JTextField nameField;
    @Getter
    private JTextField tokensField;
    private JLabel descriptionLabel;
    private JLabel tokensLabel;
    private JLabel nameLabel;
    @Getter
    private JTextArea descriptionField;
    @Getter
    private JButton submitButton;
    @Getter
    private JButton backButton;
}
