package bank;

import java.util.ArrayList;

public class BankSystem {
    ArrayList<AccountsI> accounts = new ArrayList<>();
    ArrayList<Customer> customers = new ArrayList<>();
    String bankName;
    String bankAddress;

    public BankSystem(String name, String address){
        bankAddress = address;
        bankName = name;
    }

    public void addAccount(AccountsI a){
        accounts.add(a);
    }

    public void addCustomer(Customer c){
        customers.add(c);
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

}
