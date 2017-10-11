package threads;

public class ThreadManager {

    public ThreadManager() {

    }

    public void run() {
        ThreadGroup rootThread = getRootThreadGroup();

        // Iterate to get Thread Groups
        System.out.println("No of groups (root) " + rootThread.activeGroupCount());
        System.out.println("No of threads " + rootThread.activeCount());


        // System Thread (All child threads of the root)
        ThreadGroup[] allThreadGroups = getChildThreadGroups(rootThread);


        // Loop through al thread groups
        for (ThreadGroup group : allThreadGroups) {
            System.out.println("=============");
            System.out.println("GROUP: " + group.getName());
            System.out.println("=============");

            Thread[] threads = new Thread[group.activeCount()];
            while ((group.enumerate(threads, true)) == threads.length) {
                threads = new Thread[threads.length * 2];
            }

            for (Thread thread : threads) {
                if (!(thread == null)) {
                    System.out.println(thread.getName());
                }
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

        //Copy array to result
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
