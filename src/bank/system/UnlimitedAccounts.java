package bank.system;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class UnlimitedAccounts extends Account {
    private Condition balanceTooLow;
    private boolean waitingForMoreMoney = true;
    Lock overdraftLock;
    private double overdraft;
    private boolean hasOverdraft = false;

    public UnlimitedAccounts(double initialBalance) {
        super(initialBalance);
        balanceTooLow = balanceLock.newCondition();
        overdraftLock = new ReentrantLock();
    }

    @Override
    public boolean deposit(double dep) {
        balanceLock.lock();
        try {
            setBalance(checkBal() + dep);
            System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to deposit \n" + "\t Deposit successful, deposited: £" + dep);
            balanceTooLow.signalAll();
            return true;
        }
        finally {
            balanceLock.unlock();
        }
    }

    @Override
    public boolean withdraw(double amount) throws InterruptedException {
        balanceLock.lock();
        try {
            while(isBalanceTooLow(amount)){
                if(!waitingForMoreMoney){
                    Thread.currentThread().interrupt();
                }
                waitingForMoreMoney = balanceTooLow.await(10, TimeUnit.SECONDS);
            }
            setBalance(checkBal() - amount);
            System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to withdraw \n" + "\t Withdrawal Successful, withdrew: £" + amount);
            return true;
        }
        finally {
            balanceLock.unlock();
        }
    }

    public boolean isBalanceTooLow(double amount){
        if (hasOverdraft()) {
            if(checkBal() + overdraft < amount){
                System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to withdraw \n" + "\t Balance too low to preform this action will wait for more money");
                return true;
            }
        } else {
            if (checkBal() <= 0) {
                System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to withdraw \n" + "\t Balance too low to preform this action will wait for more money");
                return true;
            }
        }
        return false;
    }

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public void setHasOverdraft(boolean overdraft){
        overdraftLock.lock();
        try {
            hasOverdraft = overdraft;
        }
        finally {
            overdraftLock.unlock();
        }

    }

    public double getOverdraft(){
        return overdraft;
    }

    public void setOverdraft(double amount){
        overdraftLock.lock();
        try{
            overdraft = amount;
        }
        finally {
            overdraftLock.unlock();
        }
    }

    @Override
    public abstract boolean verifyOverdraft(double amount);
}
