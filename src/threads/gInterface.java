package threads;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class gInterface {
    JFrame welcomeWin;
    ArrayList<JComponent> elements = new ArrayList<JComponent>();

    public gInterface(){
        welcomeWin = new JFrame("Welcome");

        JPanel threadGroups = new JPanel();
        threadGroups.setLayout(new BoxLayout(threadGroups, BoxLayout.PAGE_AXIS));

        for(int i = 0; i < 3; i++){
            JLabel threadGroup = new JLabel("to be added thread group name");

            threadGroup.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseClicked(MouseEvent mouseEvent) {
                    JLabel childGroup = new JLabel("child groups are expanded");
                    System.out.println("clicked label!");
                }
            });

            JButton stop = new JButton("Stop");
            JButton start = new JButton("Start");
            elements.add(threadGroup);
            elements.add(start);
            elements.add(stop);
        }

        for(int i = 0;  i < elements.size(); i++){
            threadGroups.add(elements.get(i));
        }

        welcomeWin.add(threadGroups);
        welcomeWin.setMinimumSize(new Dimension(500,300));
        welcomeWin.setVisible(true);
    }
}
