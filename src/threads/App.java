package threads;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class App {

    public static final String TITLE = "Thread Manager";
    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JPanel mainPanel;
    private JList list;

    public App(){
        setupUI();
    }

    private void setupUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Main components
        mainPanel.add(main(), BorderLayout.CENTER);
        mainPanel.add(buttonPanel(), BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setMinimumSize(new Dimension(500,300));
        frame.setVisible(true);
    }

    private JPanel main() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        listModel = new DefaultListModel<>();
        refresh();

        list = new JList<>(listModel);

        mainPanel.add(list);

        return mainPanel;
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout());

        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(actionEvent -> {
            refresh();
        });

        JButton stopButton = new JButton("Stop");
        stopButton.addActionListener(actionEvent -> {
            int index = list.getSelectedIndex();

            Thread selectedThread = ThreadGroupUtils.getAllThreads().get(index);
            selectedThread.stop();
            refresh();
        });

        buttonPanel.add(refreshButton);
        buttonPanel.add(stopButton);

        return buttonPanel;
    }

    private void refresh() {
        listModel.clear();
//        ThreadGroup root = ThreadGroupUtils.getRootThreadGroup();
//        List<ThreadGroup> threadGroupList = ThreadGroupUtils.getChildThreadGroups(root, true);
//        for (ThreadGroup threadGroup : threadGroupList) {
//            listModel.addElement(threadGroup.getName());
//        }

        ThreadGroup root = ThreadGroupUtils.getRootThreadGroup();
        List<Thread> threadList = ThreadGroupUtils.getAllThreads();
        for (Thread thread : threadList) {
            listModel.addElement(thread.getName());
        }
    }
}
