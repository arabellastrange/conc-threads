package threads;

public class Driver {

    public static void main(String[] args) {


        //Test code (a bit messy)

        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hi");

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("There");


                        try {

                            Thread.sleep(100000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();

                try {

                    Thread.sleep(100000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread.start();


        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("Hi");

                Thread thread = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("There");


                        try {
                            ThreadManager tm = new ThreadManager();
                            tm.run();

                            Thread.sleep(100000);

                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                });
                thread.start();



                try {

                    Thread.sleep(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        thread2.start();



  }


}
