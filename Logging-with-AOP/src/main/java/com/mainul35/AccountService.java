package com.mainul35;

public class AccountService {

    @Audit
    public void withdraw(int amount) {
        System.out.println("Withdraw amount is " + amount);
    }

    public void deposite(int amount) {
        System.out.println("Deposited amount is " + amount);
    }
}
