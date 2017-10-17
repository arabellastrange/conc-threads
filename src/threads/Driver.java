package threads;

public class Driver {

    public static void main(String[] args) {
        gInterface gui = new gInterface();

        ThreadGroup A = new ThreadGroup("G-A");
        ThreadGroup B = new ThreadGroup("G-B");

        ThreadGroup C = new ThreadGroup(A, "G-C");

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        ThreadManager tm = new ThreadManager();
        tm.run();
  }


}
