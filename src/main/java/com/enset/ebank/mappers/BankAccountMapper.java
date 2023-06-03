package com.enset.ebank.mappers;


import com.enset.ebank.DTO.*;
import com.enset.ebank.entities.*;


public interface  BankAccountMapper {

    CustomerDTO customerToCustomerDTO(Customer customer);
    Customer customerDtoToCustomer(CustomerDTO customerDto);
    SavingAccountDTO savingBankAccountToSavingBankAccountDTO(SavingAccount bankAccount);
    SavingAccount savingBankAccountDTOToSavingBankAccount( SavingAccountDTO bankAccountDTO);

    CurrentAccountDTO currentBankAccountToCurrentBankAccountDTO(CurrentAccount bankAccount);
    CurrentAccount currentBankAccountDTOToCurrentBankAccount(CurrentAccountDTO bankAccountDTO);


    BankAccountDTO bankAccountToBankAccountDTO(BankAccount bankAccount);

    BankAccount bankAccountDTOToBankAccount(BankAccountDTO bankAccountDTO);

    AccountOperationDTO accountOperationToAccountOperationDTO(AccountOperation accountOperation);
}
