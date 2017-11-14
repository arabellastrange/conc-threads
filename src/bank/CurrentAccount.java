package bank;

public class CurrentAccount extends UnlimitedAccounts {
    public CurrentAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0.01);
        setInterestLength(1);
    }

    public boolean verifyOverdraft(double amount) {
        if(getOverdraft() <= 1000 || amount <= 1000){
            setOverdraft(amount);
            setHasOverdraft(true);
            return true;
        }
        else{
            System.out.println("You cannot set an overdraft of over £1000 with a current account, you may instead use a platinum account");
            return false;
        }
    }

}
