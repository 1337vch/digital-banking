package com.enset.ebank.entities;

import com.enset.ebank.enums.OperationType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@ToString
public class AccountOperation  implements  Cloneable{


    @Id
    @GeneratedValue()
private Long id;
private Date operationDate;
private double amount;

    private String description;
    @Enumerated(EnumType.STRING)
    private OperationType type;

    @ManyToOne
    private BankAccount bankAccount;
    @Override
    public Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

}
