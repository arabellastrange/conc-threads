package threads;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreadGroupUtils {

    public static ThreadGroup getRootThreadGroup() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();

        ThreadGroup parent = null;

        while ((group = group.getParent()) != null) {
            parent = group;
        }

        return parent;
    }

    public static List<Thread> getThreads(ThreadGroup threadGroup) {
        List<Thread> threadList = new ArrayList<>();

        Thread[] threads = new Thread[threadGroup.activeCount()];
        // set the false, so it only displays sub-threadgroups and not their children
        while ((threadGroup.enumerate(threads, false)) == threads.length) {
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

        if (rootThreadGroup.activeCount() == 1) {
            return new ArrayList<>();
        }

        // Get thread groups
        ThreadGroup[] threadGroups = new ThreadGroup[rootThreadGroup.activeGroupCount()];
        while (rootThreadGroup.enumerate(threadGroups, false) == threadGroups.length) {
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
}
