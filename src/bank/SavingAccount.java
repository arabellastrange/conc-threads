package bank;

public class SavingAccount extends Account {

    public SavingAccount(double intialBalance, double interestRt, double interestLen){
        super(intialBalance, interestRt, interestLen);
    }

    @Override
    public void deposit(double dep) {

    }

    @Override
    public boolean withdraw(double amount) {
        return false;
    }

    @Override
    public void transfer(double amount, int AccNum) {

    }

}
