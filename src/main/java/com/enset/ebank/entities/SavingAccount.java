package com.enset.ebank.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class SavingAccount extends  BankAccount {

    private double interestRate;

}
