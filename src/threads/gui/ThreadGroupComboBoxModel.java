package threads.gui;


import javax.swing.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

public class ThreadGroupComboBoxModel extends AbstractListModel<ThreadGroup> implements ComboBoxModel<ThreadGroup> {

    private List<ThreadGroup> threadGroups;
    private ThreadGroup selectedItem;

    public ThreadGroupComboBoxModel() {
        threadGroups = new ArrayList<>();

        java.util.Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                refresh();
            }
        }, 0, 5000);
    }

    private void refresh() {
        threadGroups = ThreadGroupUtils.getThreadGroupsFromThreads(ThreadGroupUtils.getAllThreads());
    }

    @Override
    protected void fireContentsChanged(Object o, int i, int i1) {
        System.out.println("fireContentsChanged");
        refresh();
        super.fireContentsChanged(o, i, i1);
    }

    @Override
    public int getSize() {
        return threadGroups.size();
    }

    @Override
    public ThreadGroup getElementAt(int i) {
        return threadGroups.get(i);
    }

    @Override
    public void setSelectedItem(Object o) {
        selectedItem = (ThreadGroup) o;
    }

    @Override
    public ThreadGroup getSelectedItem() {
        return selectedItem;
    }
}