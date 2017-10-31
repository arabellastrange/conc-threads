package bank;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;

public class PlatinumAccount extends UnlimitedAccounts {
    private double fee;

    public PlatinumAccount(double initialBalance){
        super(initialBalance);
        setInterestRate(0.2);
        setInterestLength(0.25);
        fee = 75;
    }


    public boolean verifyOverdraft(double overdraft) {
        setOverdraft(overdraft);
        setHasOverdraft(true);
        return true;
    }

    public double getAccountFee(){
        return fee;
    }
}
