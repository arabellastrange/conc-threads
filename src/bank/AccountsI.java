package bank;

interface AccountsI { // change to inheritance to avoid code repeat?
    //move money - write operations
    void deposit(double dep);
    boolean withdraw(double amount);

    void transfer(double amount); // needs to take in an account number to transfer to as well

    //balance info - read operations

    double checkBal();
    void printBal();

    //account info getters and setters
    int getAccountNumber();
    int getAccountSort();
    void setInterestRate(double interestRate);
    double getInterestRate();
}
