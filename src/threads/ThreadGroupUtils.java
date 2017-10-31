package threads;

import java.util.*;
import java.util.stream.Collectors;

public class ThreadGroupUtils {

    private static HashMap<ThreadGroup, List<ThreadGroup>> graph = new HashMap<>();


    public static List<ThreadGroup> getAdjacentThreadGroup(ThreadGroup threadGroup) {
        updateGraph(getRootThreadGroup());
        return graph.getOrDefault(threadGroup, null);
    }

    public static List<Thread> getAllThreads() {
        return getThreads(getRootThreadGroup(), true);
    }

    public static void updateGraph(ThreadGroup rootThreadGroup) {
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
        List<ThreadGroup>  childThreadGroups = getChildThreadGroups(rootThreadGroup);

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

    public static ThreadGroup getRootThreadGroup() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();

        ThreadGroup parent = null;

        while ((group = group.getParent()) != null) {
            parent = group;
        }

        return parent;
    }


    public static List<Thread> getThreads(ThreadGroup threadGroup) {
        return getThreads(threadGroup, false);
    }

    public static List<Thread> getThreads(ThreadGroup threadGroup, boolean recurse) {
        List<Thread> threadList = new ArrayList<>();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        // set the false, so it only displays sub-threadgroups and not their children
        while ((threadGroup.enumerate(threads, recurse)) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        for (Thread thread : threads) {
            // If the thread is not null print the name
            if (!(thread == null)) {
                threadList.add(thread);
            }
        }

        return threadList;
    }


    public static List<ThreadGroup> getChildThreadGroups(ThreadGroup rootThreadGroup) {
        return getChildThreadGroups(rootThreadGroup, false);
    }

    public static List<ThreadGroup> getChildThreadGroups(ThreadGroup rootThreadGroup, boolean displaySub) {

        if (rootThreadGroup.activeCount() == 1) {
            return new ArrayList<>();
        }

        // Get thread groups
        ThreadGroup[] threadGroups = new ThreadGroup[rootThreadGroup.activeGroupCount()];


        while (rootThreadGroup.enumerate(threadGroups, displaySub) == threadGroups.length) {
            // Make space for threads
            threadGroups = new ThreadGroup[threadGroups.length * 2];
        }

        // Get index of first null
        int nullIndex = getNullIndex(threadGroups);

        // Resize to the exact amount
        threadGroups = new ThreadGroup[nullIndex];
        rootThreadGroup.enumerate(threadGroups, true);

        // Set the first element to the threadGroup Root
        ThreadGroup[] allThreadGroups = new ThreadGroup[threadGroups.length + 1];
        allThreadGroups[0] = rootThreadGroup;

        // Copy array to result
        System.arraycopy(threadGroups, 0, allThreadGroups, 1, threadGroups.length);


        return new ArrayList<>(Arrays.asList(allThreadGroups));
    }

    private static int getNullIndex(Object[] allThreadGroups) {
        int nullIndex = -1; // Keep it explicit

        for (int i = 0; i < allThreadGroups.length; i++) {
            if (allThreadGroups[i] == null) {
                nullIndex = i;
                return nullIndex;
            }
        }

        return nullIndex;
    }

    public static ThreadGroup getThreadGroup(Thread thread) {
        List<ThreadGroup> tg = getChildThreadGroups(getRootThreadGroup(), true);

        for (ThreadGroup threadGroup : tg) {
            List<Thread> threads = getThreads(threadGroup);

            for (Thread t : threads) {
                if (t.getId() == thread.getId()) {
                    return threadGroup;
                }
            }
        }

        return getRootThreadGroup();
    }

    public static String printThreadInfo(Thread thread) {
        return "ID: " + thread.getId() + " | Name: " + thread.getName() + " | Priority: "
                + thread.getPriority() + " | State: " + thread.getState() + " | Daemon: " + thread.isDaemon();
    }

    public static Thread getThreadById(int id) {
        List<Thread> thread =  getAllThreads().stream()
                .filter(t -> t.getId() == id)
                .collect(Collectors.toList());

        return thread.isEmpty() ? null : thread.get(0);
    }

    /**
     * Returns a list of thread groups that the threads have
     *
     * @param threads
     * @return
     */
    public static List<ThreadGroup> getThreadGroupsFromThreads(List<Thread> threads) {
        Set<ThreadGroup> set = new HashSet<>();

        for (Thread thread : threads) {
            ThreadGroup tg = getThreadGroup(thread);
            set.add(tg);
        }

        return new ArrayList<>(set);
    }
}
