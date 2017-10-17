package threads;

public class ThreadManager {

    public void run() {

        ThreadGroup rootThreadGroup = getRootThreadGroup();

        System.out.println("No of groups (root) " + rootThreadGroup.activeGroupCount());
        System.out.println("No of threads " + rootThreadGroup.activeCount());

        printThreadGroups(rootThreadGroup);
    }

    private void printThreadGroups(ThreadGroup rootThreadGroup) {
        if (rootThreadGroup == null) {
            return;
        }

        printThreads(rootThreadGroup);

        // Get child thread groups
        ThreadGroup[] childThreadGroups = getChildThreadGroups(rootThreadGroup);

        // Print child ThreadGroups
        for (int i = 1; i < childThreadGroups.length; i++) {
            printThreadGroups(childThreadGroups[i]);

        }
    }

    private void printThreads(ThreadGroup group) {
        System.out.println("=============");
        System.out.println("GROUP: " + group.getName());
        System.out.println("=============");

        Thread[] threads = new Thread[group.activeCount()];
        // set the false, so it only displays sub-threadgroups and not their children
        while ((group.enumerate(threads, false)) == threads.length) {
            threads = new Thread[threads.length * 2];
        }

        for (Thread thread : threads) {
            // If the thread is not null print the name
            if (!(thread == null)) {
                System.out.println("ID: " + thread.getId() + " Name: " + thread.getName() + " Priority: "
                        + thread.getPriority() + " State: " + thread.getState() + " Daemon: " + thread.isDaemon());

            }
        }
    }

    private ThreadGroup[] getChildThreadGroups(ThreadGroup rootThreadGroup) {

        // Get thread groups
        ThreadGroup[] threadGroups = new ThreadGroup[rootThreadGroup.activeGroupCount()];
        while (rootThreadGroup.enumerate(threadGroups, true) == threadGroups.length) {
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

        return allThreadGroups;
    }

    private int getNullIndex(Object[] allThreadGroups) {
        int nullIndex = -1; // Keep it explicit

        for (int i = 0; i < allThreadGroups.length; i++) {
            if (allThreadGroups[i] == null) {
                nullIndex = i;
                return nullIndex;
            }
        }

        return nullIndex;
    }

    private ThreadGroup getRootThreadGroup() {
        ThreadGroup group = Thread.currentThread().getThreadGroup();

        ThreadGroup parent = null;

        while ((group = group.getParent()) != null) {
            parent = group;
        }

        return parent;
    }
}
