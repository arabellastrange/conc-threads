package threads;

public class Driver {

    public static void main(String[] args) {

        ThreadGroup A = new ThreadGroup("G-A");
        Thread t = new Thread(A, new Runnable() {
            @Override
            public void run() {
                System.out.println("H");
                try {
                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "A thread");
        t.start();

        try {
            Thread.sleep(1000);

            ThreadManager tm = new ThreadManager();
            tm.run();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

  }


}
