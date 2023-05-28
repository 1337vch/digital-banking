package com.enset.ebank.entities;

import com.enset.ebank.enums.AccountStatus;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;


@AllArgsConstructor @NoArgsConstructor
@Data
@Entity
public class BankAccount {


    @Id

    private String id;

    private double balance;

    private Date createdAt;



    private AccountStatus status;


    @ManyToOne
    private Customer customer;



    @OneToMany(mappedBy = "bankAccount")
    private List<AccountOperation> accountOperations;



}
