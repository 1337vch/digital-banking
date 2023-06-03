package com.enset.ebank.DTO;


import com.enset.ebank.enums.AccountStatus;
import lombok.Data;

import java.util.Date;

@Data
public class CurrentAccountDTO extends  BankAccountDTO {
        double interestRate;
}
