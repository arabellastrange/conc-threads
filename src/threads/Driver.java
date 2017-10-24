package threads;

public class Driver {

    public static void main(String[] args) {
        App gui = new App();

        ThreadGroup A = new ThreadGroup("G-A");
        Thread t = new Thread(A, () -> {
            System.out.println("H");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A thread");
        t.start();

        ThreadGroup B = new ThreadGroup("G-B");
        Thread b = new Thread(B, () -> {
            System.out.println("H");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B thread");
        b.start();

        try {
            Thread.sleep(1000);

            ThreadManager tm = new ThreadManager();
            tm.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

  }


}
