package com.enset.ebank.exception;

public class BalanceNotSufficientException extends Throwable {
    public BalanceNotSufficientException(String noSufficientBalance) {
        super(noSufficientBalance);
    }
}
