package com.enset.ebank.DTO;


import com.enset.ebank.enums.OperationType;

import lombok.Data;

import java.util.Date;


@Data
public class AccountOperationDTO {

    private Date operationDate;
    private double amount;

    private String description;

    private OperationType type;
}
