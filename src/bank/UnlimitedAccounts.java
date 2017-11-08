package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public abstract class UnlimitedAccounts extends Account {
    private Condition balanceTooLow;
    private boolean waitingForMoreMoney = true;
    private double overdraft;
    private boolean hasOverdraft = false;

    public UnlimitedAccounts(double initialBalance) {
        super(initialBalance);
        balanceTooLow = balanceLock.newCondition();
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

    public void setHasOverdraft(boolean sho){
        hasOverdraft = sho;
    }

    public double getOverdraft(){
        return overdraft;
    }

    public void setOverdraft(double amount){
        overdraft = amount;
    }

    @Override
    public abstract boolean verifyOverdraft(double amount);
}
