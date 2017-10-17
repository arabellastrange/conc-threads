package bank;

import org.omg.CORBA.Current;

public class CurrentAccount implements AccountsI {

    private double balance;
    private int accNumber;
    private double interestRate;
    private double interest;
    private int sortCode;


    public CurrentAccount(int acc, double bal, double rate, double interestRate){
        accNumber = acc;
        balance = bal;
        interestRate = rate;
    }

    @Override
    public void deposit(double dep) {
        balance = balance + dep;
    }

    @Override
    public boolean withdraw(double amount) {
        if(balance >= amount) {
            balance = balance - amount;
            return true;
        }
        else
            return false;
    }

    @Override
    public void transfer(double amount) {
            if (amount <= this.balance) {
                withdraw(amount);
               // CurrentAccount.deposit(amount);
                System.out.print("\nTransfer successful. Transferred: £" + amount);
            } else {
                System.out.print("\nTransfer failed, not enough balance!");
            }
    }

    @Override
    public double checkBal() {
        return balance;
    }

    @Override
    public void printBal() {
    System.out.print("Account number " + accNumber + " has the balance of " + balance);
    }

    @Override
    public int getAccountNumber() {
        return accNumber;
    }

    @Override
    public int getAccountSort() {
        return sortCode;
    }

    @Override
    public void setInterestRate(double rate) {
        interest = balance * rate;

    }

    @Override
    public double getInterestRate(){
       return interest;
    }


}
