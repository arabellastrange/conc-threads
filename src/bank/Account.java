package bank;

import java.util.Random;

public abstract class Account {

    private int accountNumber;
    private int sortCode;
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
    }

    //move money - write operations
    public abstract boolean deposit(double dep);
    public abstract boolean withdraw(double amount);

    public boolean transfer( double amount, int toAccountNum){
        if(this.withdraw(amount)){
            BankSystem.getBank().getAccount(toAccountNum).deposit(amount);
            System.out.println("Transfer successful. Transferred: £" + amount);
            return true;
        }
        return false;
    }

    //balance info - read operations
    public double checkBal(){
        return balance;
    }

    public void printBal(){
        System.out.println("Thread " + Thread.currentThread().getId() + " is checking balance");
        System.out.println("Account number " + accountNumber + " has the balance of " + balance);
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

    @Override
    public String toString() {
        return "{ Account Number: " + accountNumber + " }";
    }
}