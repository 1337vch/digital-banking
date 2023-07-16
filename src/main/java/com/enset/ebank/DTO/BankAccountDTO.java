package com.enset.ebank.DTO;


import com.enset.ebank.entities.Customer;
import com.enset.ebank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BankAccountDTO {

    private String id;
    private  double balance;
    private  Date createdAt;

    private String type;
    private AccountStatus status;
    private  CustomerDTO customer;
}
