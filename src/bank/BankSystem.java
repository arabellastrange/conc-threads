package bank;

import java.util.ArrayList;

public class BankSystem {
    ArrayList<AccountsI> accounts = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    ArrayList<Employee> employees = new ArrayList<>();
    String bankName;
    String bankAddress;

    public BankSystem(String name, String address){
        bankAddress = address;
        bankName = name;
    }

    public BankSystem refresh(){
        return this;
    }

    public void addAccount(AccountsI a){
        accounts.add(a);
    }

    public void removeAccount(AccountsI a){
        accounts.remove(a);
    }

    public void addCustomer(Customer c){
        customers.add(c);
    }

    public void hireEmployee(Employee e){
        employees.add(e);
    }

    public void fireEmployee(Employee e){
        employees.remove(e);
    }

    // handle null exception pls
    public AccountsI getAccount(int accountNumber){
        for(AccountsI a : accounts){
            if(a.getAccountNumber() == accountNumber){
                return a;
            }
        }
        return null;
    }

    //need something else too ensure unique
    public Customer getCustomer(String name){
        for(Customer c: customers){
            if(c.getName().equals(name)){
                return c;
            }
        }
        return null;
    }

    public Employee getEmployee(int id){
        for(Employee e: employees){
            if(e.getEmployeeID() == id){
                return e;
            }
        }
        return null;
    }

}
