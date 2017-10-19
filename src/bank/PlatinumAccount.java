package bank;

import com.sun.jnlp.JNLPRandomAccessFileNSBImpl;

import java.util.Random;

public class PlatinumAccount implements AccountsI{
    int accountNumber;
    int sortCode;
    double balance;
    double overdraft;
    double interestRate;
    double fee;
    boolean hasOverdraft = false;
    Random rand = new Random();

    public PlatinumAccount(double intRate, double accFee){
        accountNumber = rand.nextInt(199999) + 100000;
        sortCode = rand.nextInt(9999) + 1000;
        interestRate = intRate;
        fee = accFee;
    }

    @Override
    public void deposit(double dep) {
        balance += dep;
    }

    @Override
    public boolean withdraw(double amount) {
        if(hasOverdraft){
            if(balance - overdraft <= 0){
                return false;
            }
        }
        else{
            if(balance <= 0){
                return false;
            }
        }
        balance -= amount;
        return true;

    }

    //takes in an account number gets an account by that number makes a deposit to it and withdraws an equal amount from this.acc
    @Override
    public void transfer(double amount) {

    }

    @Override
    public double checkBal() {
        return balance;
    }

    @Override
    public void printBal() {
        System.out.println(balance);
    }

    @Override
    public int getAccountNumber() {
        return accountNumber;
    }

    @Override
    public int getAccountSort() {
        return sortCode;
    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
        hasOverdraft = true;
    }

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }

    public double getInterestRate(){
        return interestRate;
    }

    public double getAccountFee(){
        return fee;
    }
}
