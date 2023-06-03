package com.enset.ebank.mappers;


import com.enset.ebank.DTO.BankAccountDTO;
import com.enset.ebank.DTO.CurrentAccountDTO;
import com.enset.ebank.DTO.CustomerDTO;
import com.enset.ebank.DTO.SavingAccountDTO;
import com.enset.ebank.entities.BankAccount;
import com.enset.ebank.entities.CurrentAccount;
import com.enset.ebank.entities.Customer;
import com.enset.ebank.entities.SavingAccount;


public interface  BankAccountMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDto);
    SavingAccountDTO savingBankAccountToSavingBankAccountDTO(SavingAccount bankAccount);
    SavingAccount savingBankAccountDTOToSavingBankAccount( SavingAccountDTO bankAccountDTO);

    CurrentAccountDTO currentBankAccountToCurrentBankAccountDTO(CurrentAccount bankAccount);
    CurrentAccount currentBankAccountDTOToCurrentBankAccount(CurrentAccountDTO bankAccountDTO);


    BankAccountDTO bankAccountToBankAccountDTO(BankAccount bankAccount);

    BankAccount bankAccountDTOToBankAccount(BankAccountDTO bankAccountDTO);
}
