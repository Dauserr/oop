package com.example;

import java.util.Date;

public class TimeDepositAccount extends Account {

    public TimeDepositAccount(double balance, Date maturityDate) {
        super(balance);
        this.maturityDate = maturityDate;
    }

    // time deposit account specific code
    private final Date maturityDate;
    
    @Override
    public String toString() {
        return getDescription() + ": current balance is " + balance;
    }

    // time deposit account specific code
    @Override
    public boolean withdraw(double amount) {
        Date today = new Date();
        if(today.after(maturityDate) && amount <= balance) {
            balance -= amount;
            return true;
        }
        return false;
    }

    // time deposit account specific code
    @Override
    public String getDescription() {
        return "Time Deposit Account " + maturityDate;
    }
    
}