package bank;


public class CurrentAccount extends Account {

    public CurrentAccount(double intialBalance, double interestRt, double intrestLn) {
        super(intialBalance, interestRt, intrestLn);
    }

    @Override
    public boolean deposit(double dep) {
        setBalance(getBalance() + dep);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if (getBalance() >= amount) {
            setBalance(getBalance() - amount);
            return true;
        } else
            return false;
    }

    @Override
    public boolean transfer(double amount, int AccNum) {
        if (amount <= getBalance()) {
            withdraw(amount);
            // CurrentAccount.deposit(amount);
            System.out.print("\nTransfer successful. Transferred: £" + amount);
        } else {
            System.out.print("\nTransfer failed, not enough balance!");
        }

        return true;
    }
}
