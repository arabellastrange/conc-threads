package threads;

import javax.swing.*;

public class gInterface {
    JFrame welcomeWin;

    public gInterface(){
        welcomeWin = new JFrame("Welcome");

        JPanel threadGroups = new JPanel();
        threadGroups.setLayout(new BoxLayout(threadGroups, BoxLayout.PAGE_AXIS));

        welcomeWin.add(threadGroups);
        welcomeWin.setVisible(true);
    }
}
