package com.enset.ebank.entities;

import com.enset.ebank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
public class AccountOperation {


    @Id
    @GeneratedValue()
private Long id;
private Date operationDate;
private double amount;

@Enumerated(EnumType.STRING)
private OperationType type;

@ManyToOne
private BankAccount bankAccount;

}
