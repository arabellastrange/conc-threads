package bank;


import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class CurrentAccount extends Account {
    private double overdraft;
    private boolean hasOverdraft = false;
    private Condition balanceTooLow;
    private boolean waitingForMoreMoney = true;

    public CurrentAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0.01);
        setInterestLength(1);
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

    public void setOverdraft(double amount) {
        if(overdraft <= 1000 || amount <= 1000){
            this.overdraft = amount;
            hasOverdraft = true;
        }
        else{
            System.out.println("You cannot set an overdraft of over £1000 with a current account, you may instead use a platinum account");
        }
    }

}
