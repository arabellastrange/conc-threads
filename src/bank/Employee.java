package bank;
public class Employee {
    int employeeID;
    String employeeName;
    double time; // how often interest is paid to account - maybe defined in account classes?

    public Employee(int id, String name){
        employeeID = id;
        employeeName = name;
    }

    public AccountsI createAcc(String accType){
        return null;
    }

    public void deleteAcc(int accNum){

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
