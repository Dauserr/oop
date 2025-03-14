/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

/**
 *
 * @author hans
 */
public class CheckingAccount extends Account  {

    private final double overDraftLimit;

    public CheckingAccount(double balance, double overDraftLimit) {
        super(balance);
        this.overDraftLimit = overDraftLimit;
    }

    public CheckingAccount(double balance) {
        this(balance, 0);
    }

    @Override
    public String getDescription() {
        return "Checking Account";
    }

    @Override
    public boolean withdraw(double amount) {
        if(amount <= balance + overDraftLimit) {
                balance -= amount;
                return true;
            } else {
                return false;
            }
    }
    
}
