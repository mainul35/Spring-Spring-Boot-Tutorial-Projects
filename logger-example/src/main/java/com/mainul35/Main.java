package com.mainul35;


import org.apache.log4j.Logger;

public class Main {

    private static Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) {
        System.out.println("Printing..");
        AccountService account = new AccountService();
        account.withdraw(100);
    }
}
