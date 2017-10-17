package threads;


public class ThreadManager {


    public ThreadManager() {

    }

    public void run() {

        ThreadGroup rootThreadGroup = ThreadGroupUtils.getRootThreadGroup();

        System.out.println("No of groups (root) " + rootThreadGroup.activeGroupCount());
        System.out.println("No of threads " + rootThreadGroup.activeCount());

//        updateGraph(rootThreadGroup);
        ThreadGroupUtils.updateGraph(rootThreadGroup);
    }


    public void filterByName(String str) {

    }
}
