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
    public boolean deposit(double dep) {
        setBalance(getBalance() + dep);
        return true;
    }

    @Override
    public boolean withdraw(double amount) {
        if(hasOverdraft){
            if(getBalance() - overdraft <= 0){
                System.out.println("Balance too low to preform this action");
                return false;
            }
        }
        else{
            if(getBalance() <= 0){
                System.out.println("Balance too low to preform this action");
                return false;
            }
        }

        setBalance(getBalance() - amount);
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
