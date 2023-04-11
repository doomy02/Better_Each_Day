package app.view;

import lombok.Getter;

import javax.swing.*;

public class MainMenuView {
    @Getter
    private JLabel pageLabel;
    @Getter
    private JTabbedPane tabbedPane1;
    @Getter
    private JButton signOutButton;
    @Getter
    private JPanel mainPanel;
    @Getter
    private JPanel profilePanel;
    @Getter
    private JPanel dailyPanel;
    @Getter
    private JPanel questPanel;
    @Getter
    private JLabel nameValue;
    @Getter
    private JLabel emailValue;
    @Getter
    private JLabel tokensValue;
    @Getter
    private JButton claimButton;
    @Getter
    private JButton questButton;
    private JLabel solvedLabel;
    @Getter
    private JLabel solvedValue;
    @Getter
    private JComboBox badgesBox;
    private JLabel badgesLabel;
    @Getter
    private JLabel questNameValue;
    private JLabel questNameLabel;
    private JLabel tokenSpentLabel;
    @Getter
    private JLabel tokenSpentValue;
    @Getter
    private JLabel firstPolyValue;
    private JLabel secondPolynomialLabel;
    @Getter
    private JLabel secondPolynomialValue;
    private JLabel operationLabel;
    @Getter
    private JLabel operationValue;
    private JLabel answerLabel;
    @Getter
    private JLabel answerValue;
    @Getter
    private JComboBox questBox;
    @Getter
    private JButton selectButton;
}
