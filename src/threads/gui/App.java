package threads.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class App {

    private static final String TITLE = "Thread Manager";
    private JTable table;
    private ThreadTableModel tableModel;
    private ThreadGroupComboBoxModel threadGroupComboBoxModel;

    public void display() {
        JFrame frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // mainPanel panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Main components
//        mainPanel.add(searchPanel(), BorderLayout.NORTH);
        mainPanel.add(mainPanel(), BorderLayout.CENTER);
        mainPanel.add(buttonPanel(), BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setMinimumSize(new Dimension(700, 500));
        frame.setVisible(true);
    }


    private JPanel mainPanel() {
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        tableModel = new ThreadTableModel();
        table = new JTable(tableModel);
        JScrollPane scrollableTable = new JScrollPane(table);

        mainPanel.add(searchPanel());
        mainPanel.add(scrollableTable);

        return mainPanel;
    }

    private JPanel searchPanel() {
        JPanel searchPanel = new JPanel();
        searchPanel.setLayout(new BoxLayout(searchPanel, BoxLayout.X_AXIS));

        JTextField searchTextField = new JTextField(30);
        searchTextField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                tableModel.filterThreadName(searchTextField.getText());
            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {

            }
        });

        JButton searchButton = new JButton("Search");
        searchButton.addActionListener(e -> tableModel.filterThreadName(searchTextField.getText()));

        searchPanel.add(searchTextField);
        searchPanel.add(searchButton);

        return searchPanel;
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        threadGroupComboBoxModel = new ThreadGroupComboBoxModel();
        JComboBox<ThreadGroup> threadGroupComboBox = new JThreadGroupComboBox(threadGroupComboBoxModel);
        threadGroupComboBox.setSelectedIndex(0);

        JButton newButton = new JButton("New Thread");
        newButton.addActionListener(actionEvent -> {

            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

            tableModel.fireTableDataChanged();
        });

        JButton stopButton = new JButton("Stop Thread");
        stopButton.addActionListener(actionEvent -> {

            new Thread(() -> {

                int index = table.getSelectedRow();

                if (index != -1) {
                    Thread thread = tableModel.getThreadAtIndex(index);

                    // Stop thread if it's not null
                    if (thread != null) {
                        thread.stop();
                    }
                }

                tableModel.fireTableDataChanged();
            }).start();
        });


        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(actionEvent -> {
            tableModel.fireTableDataChanged();

            threadGroupComboBoxModel.fireContentsChanged(threadGroupComboBoxModel, 0, threadGroupComboBoxModel.getSize() - 1);
//            threadGroupComboBox.revalidate();
//            threadGroupComboBox.updateUI();
//            threadGroupComboBox.repaint();
        });

//        buttonPanel.add(threadGroupComboBoxScrollPane);
        buttonPanel.add(newButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(threadGroupComboBox);

        return buttonPanel;
    }

}
