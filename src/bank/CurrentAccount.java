package bank;


public class CurrentAccount extends Account {

    public CurrentAccount(double intialBalance, double interestRt, double intrestLn){
        super(intialBalance, interestRt, intrestLn);
    }

    @Override
    public boolean deposit(double dep) {
        balance = balance + dep;
        return true;
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
    public boolean transfer(double amount, int AccNum) {
            if (amount <= this.balance) {
                withdraw(amount);
               // CurrentAccount.deposit(amount);
                System.out.print("\nTransfer successful. Transferred: £" + amount);
                return true;
            } else {
                System.out.print("\nTransfer failed, not enough balance!");
                return false;
            }
    }


}
