package bank;

public class SavingAccount extends Account {

    public SavingAccount(double intialBalance, double interestRt, double interestLen){
        super(intialBalance, interestRt, interestLen);
    }

    @Override
    public boolean deposit(double dep) {
        //should have upper limit!!
        balance = balance + dep;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (balance >= amount) {
            balance = balance - amount;
            return true;
        } else
            System.out.println("Insufficient funds.");
        return false;
    }

    @Override
    public boolean transfer(double amount, int AccNum) {
        if (amount <= this.balance) {
            withdraw(amount);
            System.out.print("\nTransfer successful. Transferred: £" + amount);
            return true;
        } else {
            System.out.print("\nTransfer failed, not enough balance!");
            return false;
        }
    }

}
