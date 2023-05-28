package com.enset.ebank.entities;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CurrentAccount extends  BankAccount {



    private double overDraft;
}
