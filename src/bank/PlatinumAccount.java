package bank;

public class PlatinumAccount extends Account{
    double overdraft;
    double fee;
    double feeLength;
    boolean hasOverdraft = false;

    public PlatinumAccount(double intialBalance, double interestRt, double intrestLn, double accFee, double fLength){
        super(intialBalance, interestRt, intrestLn);
        fee = accFee;
        feeLength = fLength;
    }

    @Override
    public boolean deposit(double dep) {
        balance += dep;
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if(hasOverdraft){
            if(balance - overdraft <= 0){
                System.out.println("Balance too low to preform this action");
                return false;
            }
        }
        else{
            if(balance <= 0){
                System.out.println("Balance too low to preform this action");
                return false;
            }
        }
        balance -= amount;
        return true;

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
