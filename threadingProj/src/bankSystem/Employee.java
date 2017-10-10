package bankSystem;
public class Employee {
    int employeeID;
    String employeeName;

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

}
