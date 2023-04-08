package app.view;

import lombok.Getter;
import org.hibernate.annotations.GeneratorType;

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
}
