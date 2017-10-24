package bank;

import java.util.Random;

public abstract class Account {
    Random rand = new Random();

    int accountNumber;
    int sortCode;
    double balance;
    double interestRate;
    double interestLength; // value between 0 and 1 to indicate how often per year intrest is paid

    public Account(double intialBalance, double interestRt, double interestLn){
        accountNumber = rand.nextInt(199999) + 100000;
        sortCode = rand.nextInt(9999) + 1000;
        balance = intialBalance;
        interestRate = interestRt;
        interestLength = interestLn;
    }

    //move money - write operations
    public abstract boolean deposit(double dep);
    public abstract boolean withdraw(double amount);

    public boolean transfer(double amount, int toAccountNum){
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

    public double getInterestRate(){
        return interestRate;
    }

    public double getInterestLength() {
        return interestLength;
    }

    public void setInterestLength(double interestLength) {
        this.interestLength = interestLength;
    }

    public void makeAccountJoint(){

    }
}
