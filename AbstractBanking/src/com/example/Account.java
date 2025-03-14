/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.example;

import java.util.Date;

/**
 *
 * @author hans
 */
public abstract class Account {
    // generic account code
    protected double balance;

    public Account(double balance) {
        this.balance= balance;
    }

    // generic account code
    public double getBalance() {
        return balance;
    }

    // generic account code
    public void deposit(double amount) {
        balance += amount;
    }

    // generic account code
    @Override
    public String toString() {
        return  " current balance is " + balance;
    }

    public abstract String getDescription();

    public abstract boolean withdraw(double amount) ;
    
}
