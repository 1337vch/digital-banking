package com.enset.ebank.services;

import com.enset.ebank.DTO.*;
import com.enset.ebank.exception.BalanceNotSufficientException;
import com.enset.ebank.exception.BankAccountNotExist;
import com.enset.ebank.exception.CustomerNotExist;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IBankAccountService {



    CustomerDTO saveCustomer(CustomerDTO customerDTO);

    CustomerDTO updateCustomer(CustomerDTO customerDTO);

    CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotExist;

    SavingAccountDTO saveSavingBankAccount(double initialBalance, double interestrate, Long customerId) throws CustomerNotExist;




    List<CustomerDTO> customersList();


    List<BankAccountDTO> bankAccountList();

    BankAccountDTO getBankAccount(String idAccount) throws BankAccountNotExist;


    CustomerDTO getCustomer(Long customerId) throws CustomerNotExist;

    void debit(String accountId, double amount, String description) throws BankAccountNotExist, BalanceNotSufficientException;
    void credit(String accountId, double amount, String description) throws BankAccountNotExist;

    void transfer(String accountIdSource, String accountIdDestination, double amount, String motif) throws BankAccountNotExist, BalanceNotSufficientException;


    void deleteCustomer(Long customerId) throws CustomerNotExist;

    List<AccountOperationDTO> accountHistory(String accountId);

    AccountOperationPageDTO accountHistoryPage(String accountId, Pageable pageable) throws BankAccountNotExist;
}
