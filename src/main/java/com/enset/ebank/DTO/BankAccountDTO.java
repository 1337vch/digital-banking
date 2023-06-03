package com.enset.ebank.DTO;


import com.enset.ebank.entities.Customer;
import com.enset.ebank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class BankAccountDTO {

    String id;
    double balance;
    Date createdAt;
    AccountStatus status;
    CustomerDTO customer;
}
