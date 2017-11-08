package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class SavingAccount extends Account {
    private Condition balanceTooLow;
    private boolean waitingForMoreMoney = true;
    
    public SavingAccount(double initialBalance){
        // must have minimum initial balance of £20?
        super(initialBalance);
        setInterestRate(0);
        setInterestLength(0);
        balanceTooLow = balanceLock.newCondition();
    }

    @Override
    public boolean deposit(double dep) {
        balanceLock.lock();
        try{
            if(checkBal() < 10000){
                setBalance(checkBal() + dep);
                System.out.println("Thread " + Thread.currentThread().getId() + " is attempting to deposit \n" + "\t Deposit successful, deposited: £" + dep);
                balanceTooLow.signalAll();
                return true;
            }
            System.out.println("You have reached the maximum upper balance limit");
            return false;
        }
        finally {
            balanceLock.unlock();
        }
    }

    @Override
    public boolean withdraw(double amount) throws InterruptedException {
        balanceLock.lock();
        try{
            while (checkBal() <= amount){
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

    @Override
    public boolean verifyOverdraft(double amount) {
        System.out.println("You cannot set an overdraft on a savings account");
        return false;
    }


}
