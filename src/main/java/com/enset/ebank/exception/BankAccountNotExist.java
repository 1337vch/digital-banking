package com.enset.ebank.exception;

public class BankAccountNotExist extends Exception {
    public BankAccountNotExist(String customerNotExist) {
        super(customerNotExist);
    }
}
