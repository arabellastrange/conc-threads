package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class PlatinumAccount extends Account{
    private boolean waitingForMoreMoney = true;
    private double overdraft;
    private double fee;
    private boolean hasOverdraft = false;
    private Condition balanceTooLow;

    public PlatinumAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0.2);
        setInterestLength(0.25);
        fee = 75;
        balanceTooLow = balanceLock.newCondition();
    }

    @Override
    public boolean deposit(double dep) {
        balanceLock.lock();
        try {
            setBalance(checkBal() + dep);
            System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to deposit \n"+ "\t Deposit successful, deposited: £" + dep);
            balanceTooLow.signalAll();
            return true;
        }
        finally {
            balanceLock.unlock();
        }
    }

    @Override
    public boolean withdraw(double amount) throws InterruptedException {
        if(!isBalanceTooLow()){
            balanceLock.lock();
            try {
                setBalance(checkBal() - amount);
                System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to withdraw \n" + "\t Withdrawal Successful, withdrew: £" + amount);
                return true;
            }
            finally {
                balanceLock.unlock();
            }
        }
        else{
            while(isBalanceTooLow()){
                if(!waitingForMoreMoney){
                    Thread.currentThread().interrupt();
                    waitingForMoreMoney = balanceTooLow.await(5, TimeUnit.SECONDS);
                }
            }
        }
        System.out.println("Withdrawal failed");
        return false;
    }

    public boolean isBalanceTooLow(){
        if (hasOverdraft()) {
            if(checkBal() - overdraft <= 0){
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
    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
        hasOverdraft = true;
    }

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public double getAccountFee(){
        return fee;
    }
}
