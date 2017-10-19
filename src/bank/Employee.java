package bank;
public class Employee {
    BankSystem workingAt;
    int employeeID;
    int genID = 0;
    double time; // how often interest is paid to account - maybe defined in account classes?

    public Employee(){
        employeeID = genID++;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public AccountsI createAcc(String accType){
        switch (accType){
            case "Savings": AccountsI s = new SavingAccount();
                            workingAt.getBank().addAccount(s);
                            return s;
            case "Platinum": AccountsI p= new PlatinumAccount(0.02, 75);
                             workingAt.getBank().addAccount(p);
                             return p;
            case "Current" : AccountsI c = new CurrentAccount(4000, 0.01);
                             workingAt.getBank().addAccount(c);
                             return c;
        }
        return null;
    }

    public void deleteAcc(int accNum){
       // workingAt.getBank().removeAccount(workingAt.getBank().getAccount(accNum));
    }

    public void transfer(AccountsI fromAccount, AccountsI toAccount, double amount){

    }

    public void depositInterest(AccountsI account){
        account.deposit(account.checkBal() * account.getInterestRate() * time);
    }

    public void chargeFee(PlatinumAccount account){
        account.withdraw(account.getAccountFee());
    }

}
