package com.mainul35;


import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = LogManager.getLogger("console");

    public static void main(String[] args) {
        AccountService account = new AccountService();
        account.withdraw(100);
        account.deposite(200);
    }
}
