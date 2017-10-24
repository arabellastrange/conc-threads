package bank;
public class PlatinumAccount extends Account{
    private double overdraft;
    private double fee;
    private double feeLength;
    private boolean hasOverdraft = false;

    public PlatinumAccount(double intialBalance, double interestRt, double intrestLn, double accFee, double fLength){
        super(intialBalance, interestRt, intrestLn);
        fee = accFee;
        feeLength = fLength;
    }

    @Override
    public void deposit(double dep) {
        setBalance(getBalance() + dep);
    }

    @Override
    public boolean withdraw(double amount) {
        if (hasOverdraft) {
            if(getBalance() - overdraft <= 0){
                return false;
            }
        } else {
            if(getBalance() <= 0){
                return false;
            }
        }

        setBalance(getBalance() - amount);
        return true;

    }

    //takes in an account number gets an account by that number makes a deposit to it and withdraws an equal amount from this.acc
    @Override
    public void transfer(double amount, int AccountNum) {

    }

    public void setOverdraft(double overdraft) {
        this.overdraft = overdraft;
        hasOverdraft = true;
    }

    public boolean hasOverdraft() {
        return hasOverdraft;
    }

    public double getAccountFee(){
        return fee;
    }
}
