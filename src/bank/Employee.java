package bank;
public class Employee {
    int employeeID;
    BankSystem workingAt;
    String employeeName;
    double time; // how often interest is paid to account - maybe defined in account classes?

    public Employee(int id, String name, BankSystem b){
        employeeID = id;
        employeeName = name;
        workingAt = b;
    }

    public int getEmployeeID() {
        return employeeID;
    }

    public void createAcc(String accType){
        switch (accType){
            case "Savings": AccountsI s = new SavingAccount();
                            workingAt.refresh().addAccount(s);
                            break;
            case "Platinum": AccountsI p= new PlatinumAccount(011032, 98, 0.02, 75);
                             workingAt.refresh().addAccount(p);
                             break;
            case "Current" : AccountsI c = new CurrentAccount(011033, 4000,4, 0.01);
                             workingAt.refresh().addAccount(c);
                             break;
        }
    }

    public void deleteAcc(int accNum){
        workingAt.refresh().removeAccount(workingAt.refresh().getAccount(accNum));
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
