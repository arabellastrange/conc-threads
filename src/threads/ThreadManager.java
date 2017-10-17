package threads;

import java.util.HashMap;
import java.util.List;

public class ThreadManager {

    private HashMap<ThreadGroup, List<ThreadGroup>> graph;

    public ThreadManager() {
        this.graph = new HashMap<>();
    }

    public void run() {

        ThreadGroup rootThreadGroup = ThreadGroupUtils.getRootThreadGroup();

        System.out.println("No of groups (root) " + rootThreadGroup.activeGroupCount());
        System.out.println("No of threads " + rootThreadGroup.activeCount());

        updateGraph(rootThreadGroup);
    }

    public void refresh() {
        graph = new HashMap<>();
        updateGraph(ThreadGroupUtils.getRootThreadGroup());
    }

    private void updateGraph(ThreadGroup rootThreadGroup) {
        if (rootThreadGroup == null) {
            return;
        }

        //Print out threads in Thread Group
        System.out.println("============");
        System.out.println("GROUP: " + rootThreadGroup.getName());
        System.out.println("============");
        for (Thread thread : ThreadGroupUtils.getThreads(rootThreadGroup)) {
                System.out.println("ID: " + thread.getId() + " Name: " + thread.getName() + " Priority: "
                        + thread.getPriority() + " State: " + thread.getState() + " Daemon: " + thread.isDaemon());
        }

        // Get child thread groups
        List<ThreadGroup>  childThreadGroups = ThreadGroupUtils.getChildThreadGroups(rootThreadGroup);

        //Remove first element because it is the root thread
        if (childThreadGroups.size() >= 1) {
            childThreadGroups.remove(0);
        }

        // add to graph
        graph.put(rootThreadGroup, childThreadGroups);

        // Print child ThreadGroups
        for (ThreadGroup tg : childThreadGroups) {
            updateGraph(tg);
        }
    }



}
