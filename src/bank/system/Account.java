package bank.system;

import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public abstract class Account {

    private int accountNumber;
    private int sortCode;
    Lock balanceLock;
    private double balance;
    private double interestRate;
    private double interestLength; // value between 0 and 1 to indicate how often per year interest is paid

    public enum Account_Types {
        SAVING, PLATINUM, CURRENT //if you want to use strategy no inheritance
    }

    public Account(double initialBalance){
        Random rand = new Random();
        accountNumber = rand.nextInt(199999) + 100000;
        sortCode = rand.nextInt(9999) + 1000;
        balance = initialBalance;
        balanceLock = new ReentrantLock();
    }

    //move money - write operations
    public abstract boolean deposit(double dep);
    public abstract boolean withdraw(double amount) throws InterruptedException;

    public boolean transfer( double amount, int toAccountNum) throws InterruptedException {
        System.out.println("Thread " + Thread.currentThread().getId() + " is attempting transfer");
        if(this.withdraw(amount)){
            BankSystem.getBank().getAccount(toAccountNum).deposit(amount);
            System.out.println("Transfer successful. Transferred: £" + amount);
            return true;
        }
        return false;
    }

    //balance info - read operations
    public double checkBal(){
        balanceLock.lock();
        try{
            return balance;
        }
        finally {
            balanceLock.unlock();
        }

    }

    public void printBal(){
        System.out.println("Thread " + Thread.currentThread().getId() + " is checking balance: \n" + "\t Account number " + accountNumber + " has the balance of " + checkBal());
       // System.out.println("Account number " + accountNumber + " has the balance of " + checkBal());
    }

    //account info getters and setters
    public int getAccountNumber(){
        return accountNumber;
    }
    public int getAccountSort(){
        return sortCode;
    }

    public void setInterestRate(double interestRt){
        interestRate = interestRt;
    }

    public double getInterestRate(){
        return interestRate;
    }

    public double getInterestLength() {
        return interestLength;
    }


    public void setBalance(double amount) {
        this.balance = amount;
    }

    public void setInterestLength(double interestLength) {
        this.interestLength = interestLength;
    }

    public abstract boolean verifyOverdraft(double amount);

    @Override
    public String toString() {
        return "{ Account Number: " + accountNumber + " }";
    }
}
