package threads;

import bank.EmployeeMovingMoneyDriver;
import bank.InsufficientFunds2Driver;
import bank.PrintBalanceDrive;
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

        Thread threadA = new Thread(new PrintBalanceDrive(), "Balance print");
        threadA.start();

        Thread threadB = new Thread(new InsufficientFunds2Driver(), "Overdrawn balance");
        threadB.start();

        Thread threadC = new Thread(new EmployeeMovingMoneyDriver(), "Employee actions");
        threadC.start();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
