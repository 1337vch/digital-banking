package com.enset.ebank.entities;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@DiscriminatorValue("CA")
public class CurrentAccount extends  BankAccount {



    private double overDraft;
}
