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
    @Getter
    private JComboBox comboBox1;
    private JLabel answerNameLabel;
    @Getter
    private JTextField answerTextField;
    private JLabel answerLabel;
    @Getter
    private JLabel answerNameValue;
    @Getter
    private JLabel tokensValue;
    private JLabel answerDescriptionLabel;
    @Getter
    private JTextArea answerDescriptionField;
    @Getter
    private JButton submit2Button;
    @Getter
    private JButton back2Button;
    @Getter
    private JButton selectButton;
}
