package threads;

import threads.gui.App;

import javax.swing.*;

public class Driver {

    public static void main(String[] args) {
        App gui = new App();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                gui.display();
            }
        });

        ThreadGroup A = new ThreadGroup("G-A");
        Thread threadA = new Thread(A, () -> {
            System.out.println("A");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "A thread");
        threadA.start();

        ThreadGroup B = new ThreadGroup("G-B");
        Thread threadB = new Thread(B, () -> {
            System.out.println("B");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "B thread");
        threadB.start();


        ThreadGroup C = new ThreadGroup(B,"G-C");
        Thread threadC = new Thread(C, () -> {
            System.out.println("C");
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }, "C thread");
        threadC.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
