package threads.gui;

import javax.swing.table.AbstractTableModel;
import java.util.*;
import java.util.stream.Collectors;

public class ThreadTableModel extends AbstractTableModel {

    private List<Thread> threadList;
    private Vector<String> columnNames;
    private Vector<Vector<String>> data;
    private String query = "";

    public ThreadTableModel() {
        columnNames = new Vector<>();
        data = new Vector<>();

        threadList = ThreadGroupUtils.getAllThreads();

        String[] columns = new String[]{"ID", "Name", "Priority", "State", "Daemon", "Thread Group"};
        columnNames.addAll(Arrays.asList(columns));


        // update every 5 seconds
        int seconds = 5;
        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                fireTableDataChanged();
            }
        }, 0, seconds * 1000);
    }

    private void refresh() {
        data.clear();

        threadList = getThreads();

        for (int i = 0; i < threadList.size(); i++) {
            data.addElement(new Vector<>());

            for (int j = 0; j < columnNames.size(); j++) {
                String property = getThreadProperty(threadList.get(i), j);
                data.get(i).addElement(property);
            }
        }
    }

    private List<Thread> getThreads() {
        List<Thread> threads = ThreadGroupUtils.getAllThreads();
        if (query.equals("")) {
            return threads;
        }

        threads = threads.stream()
                .filter((t) -> t.getName().toLowerCase().startsWith(query.toLowerCase()))
                .collect(Collectors.toList());

        return threads;
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

    /**
     * Filters threads by query
     *
     * @param query
     */
    public void filterThreadName(String query) {
        this.query = query.trim();
        fireTableDataChanged();
    }

    /**
     * Filters threads by query
     * @param query
     */
    public void filterThreadGroup(String query) {
        this.query = query.trim();
        fireTableDataChanged();
    }
}
