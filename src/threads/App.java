package threads;

import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.Timer;

public class App {

    public static final String TITLE = "Thread Manager";
    private JFrame frame;
    private JPanel mainPanel;
    private JTable table;
    private ThreadTableModel tableModel;


    public App() {
        setupUI();
    }

    private void setupUI() {
        frame = new JFrame(TITLE);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        // mainPanel panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());

        // Main components
        mainPanel.add(mainPanel(), BorderLayout.CENTER);
        mainPanel.add(buttonPanel(), BorderLayout.EAST);

        frame.add(mainPanel);

        frame.setMinimumSize(new Dimension(600, 300));
        frame.setVisible(true);
    }

    private JPanel mainPanel() {
        mainPanel = new JPanel();
        mainPanel.setLayout(new FlowLayout());

        tableModel = new ThreadTableModel();
        table = new JTable(tableModel);

        JScrollPane scrollableTable = new JScrollPane(table);

        mainPanel.add(scrollableTable);

        return mainPanel;
    }

    private JPanel buttonPanel() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));

        JButton newButton = new JButton("New Thread");
        newButton.addActionListener(actionEvent -> {

            new Thread(() -> {
                try {
                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }).start();

        });

        JButton stopButton = new JButton("Stop Thread");
        stopButton.addActionListener(actionEvent -> {

            new Thread(new Runnable() {
                @Override
                public void run() {

                    int index =  table.getSelectedRow();

                    System.out.println(index);
                    if (index != -1) {
                        tableModel.getThreadAtIndex(index).stop();
                    }


                    tableModel.fireTableDataChanged();
                }
            }).start();

        });


        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(actionEvent -> {
            tableModel.fireTableDataChanged();
        });

        buttonPanel.add(newButton);
        buttonPanel.add(stopButton);
        buttonPanel.add(refreshButton);

        return buttonPanel;
    }



    private class ThreadTableModel extends AbstractTableModel {

        private List<Thread> threadList;
        private Vector<String> columnNames = new Vector<>();
        private Vector<Vector<String>> data = new Vector<>();

        public ThreadTableModel() {
            threadList = ThreadGroupUtils.getAllThreads();

            String[] columns = new String[]{"ID", "Name", "Priority", "State", "Daemon", "Thread Group"};
            columnNames.addAll(Arrays.asList(columns));


            // update every 5 seconds
            int seconds = 5;
            java.util.Timer timer = new Timer();
            timer.schedule(new TimerTask() {
                @Override
                public void run() {
                    refresh();
                }
            }, 0,seconds * 1000);
        }

        private void refresh() {
            data.clear();

            threadList = ThreadGroupUtils.getAllThreads();

            for (int i = 0; i < threadList.size(); i++) {
                data.addElement(new Vector<String>());

                for (int j = 0; j < columnNames.size(); j++) {
                    String property = getThreadProperty(threadList.get(i), j);
                    data.get(i).addElement(property);
                }
            }
        }

        private String getThreadProperty(Thread thread, int i) {
            switch (i) {
                case 0:
                    return String.valueOf(thread.getId());
                case 1:
                    return thread.getName();
                case 2:
                    return String.valueOf(thread.getPriority());
                case 3:
                    return String.valueOf(thread.getState());
                case 4:
                    return String.valueOf(thread.isDaemon());
                case 5:
                    return ThreadGroupUtils.getThreadGroup(thread).getName();
            }

            return "";
        }

        public Thread getThreadAtIndex(int i) {
            int threadId = Integer.valueOf(data.get(i).get(0));
            return ThreadGroupUtils.getThreadById(threadId);
        }

        @Override
        public void fireTableDataChanged() {
            refresh();
            super.fireTableDataChanged();
        }

        @Override
        public int getRowCount() {
            return data.size();
        }

        @Override
        public int getColumnCount() {
            return columnNames.size();
        }

        @Override
        public String getColumnName(int columnIndex) {
            return columnNames.get(columnIndex);
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return getValueAt(0, columnIndex).getClass();
        }

        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            return false;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            return data.get(rowIndex).get(columnIndex);
        }

    }
}
