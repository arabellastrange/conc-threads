package bank;

import java.util.ArrayList;
import java.util.LinkedList;


public final class BankSystem {
    private static BankSystem bank = null;
    private LinkedList<AccountsI> accounts = new LinkedList();
    ArrayList<Employee> employees = new ArrayList<>();

    private BankSystem(){
        Employee emp =  new Employee();
        employees.add(emp);
    }

    // not thread safe
    public BankSystem getBank(){
        if (bank == null){
            bank = new BankSystem();
        }
        return bank;
    }

    public void addAccount(AccountsI a){
        accounts.add(a);
    }

    public void removeAccount(AccountsI a){
        accounts.remove(a);
    }

    public void addCustomer(Customer c){
        //customers.add(c);
    }

    public void removesCustomer(Customer c){
       // customers.remove(c);
    }

    public void hireEmployee(Employee e){
        employees.add(e);
    }

    public void fireEmployee(Employee e){
        employees.remove(e);
    }

//    // handle null exception pls
//    public AccountsI getAccount(int accountNumber){
//        for(AccountsI a : accounts){
//            if(a.getAccountNumber() == accountNumber){
//                return a;
//            }
//        }
//        return null;
//    }

    //need something else too ensure unique
//    public Customer getCustomer(String name){
//        for(Customer c: customers){
//            if(c.getName().equals(name)){
//                return c;
//            }
//        }
//        return null;
//    }

    public Employee getEmployee(int id){
        for(Employee e: employees){
            if(e.getEmployeeID() == id){
                return e;
            }
        }
        return null;
    }

}
