package bank.system;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class SavingAccount extends Account {
    private Condition balanceTooLow;
    private boolean waitingForMoreMoney = true;

    public SavingAccount(double initialBalance) {
        // must have minimum initial balance of £20?
        super(initialBalance);
        setInterestRate(0.195);
        setInterestLength(1);
        balanceTooLow = balanceLock.newCondition();
    }


    public boolean enoughMoney(){
        double test = checkBal();
        if (checkBal() < 20) {
            System.out.println(test + ": Should be less than 20");
            System.out.println("Thread " + Thread.currentThread().getId() + " is checking SavingAccountBalance \n" + "\t Balance too low to preform this action. £20 minimum for saving account.");
            return false;
        }
        System.out.println(test + ": Should be more than 20");
        System.out.println("Thread " + Thread.currentThread().getId() +": Account has enough money!");
        return true;
    }

    @Override
    public boolean deposit(double dep) {
        balanceLock.lock();
        try {
            if (checkBal() < 10000) {
                setBalance(checkBal() + dep);
                System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to deposit \n" + "\t Deposit successful, deposited: £" + dep);
                balanceTooLow.signalAll();
                return true;
            }
            System.out.println("You have reached the maximum upper balance limit");
            return false;
        } finally {
            balanceLock.unlock();
        }
    }

    @Override
    public boolean withdraw(double amount) throws InterruptedException {
        balanceLock.lock();
        try {
            while (checkBal() <= amount || checkBal() - amount <= 20) {
                if (!waitingForMoreMoney) {
                    Thread.currentThread().interrupt();

                }
                System.out.println("Thread " + Thread.currentThread().getId() + " Balance too low to preform this action, Savings Account must at least contain £20 at all time");
                waitingForMoreMoney = balanceTooLow.await(10, TimeUnit.SECONDS);
            }
            setBalance(checkBal() - amount);
            System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to withdraw \n" + "\t Withdrawal Successful, withdrew: £" + amount);
            return true;
        } finally {
            balanceLock.unlock();
        }
    }

    @Override
    public boolean verifyOverdraft(double amount) {
        System.out.println("You cannot set an overdraft on a savings account");
        return false;
    }


}