package com.enset.ebank.services;

import com.enset.ebank.DTO.BankAccountDTO;
import com.enset.ebank.DTO.CurrentAccountDTO;
import com.enset.ebank.DTO.CustomerDTO;
import com.enset.ebank.DTO.SavingAccountDTO;
import com.enset.ebank.entities.*;
import com.enset.ebank.enums.AccountStatus;
import com.enset.ebank.enums.OperationType;
import com.enset.ebank.exception.BalanceNotSufficientException;
import com.enset.ebank.exception.BankAccountNotExist;
import com.enset.ebank.exception.CustomerNotExist;
import com.enset.ebank.mappers.BankAccountMapper;
import com.enset.ebank.repositories.AccountOperationRepository;
import com.enset.ebank.repositories.BankAccountRepository;
import com.enset.ebank.repositories.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@Transactional()
@RequiredArgsConstructor

public class BankAccountService implements  IBankAccountService{

    final  BankAccountMapper mapper;
    final private CustomerRepository customerRepository;
    final private BankAccountRepository bankAccountRepository;

    final private AccountOperationRepository accountOperationRepository;

    @Override
    public CustomerDTO saveCustomer(CustomerDTO customerDTO) {


        Customer dbCustomer = customerRepository.save( mapper.customerDtoToCustomer(customerDTO));

        return mapper.customerToCustomerDTO(dbCustomer);
    }
    @Override
    public CustomerDTO updateCustomer(CustomerDTO customerDTO) {


        Customer dbCustomer = customerRepository.save( mapper.customerDtoToCustomer(customerDTO));

        return mapper.customerToCustomerDTO(dbCustomer);
    }

    @Override
    public CurrentAccountDTO saveCurrentBankAccount(double initialBalance, double overDraft, Long customerId) throws CustomerNotExist {
        //Check constraints
        CustomerDTO customer = getCustomer(customerId);

        CurrentAccount dbBankAccount ;
        dbBankAccount = new CurrentAccount();
        dbBankAccount.setCustomer(mapper.customerDtoToCustomer(customer));
        dbBankAccount.setBalance(initialBalance);
        dbBankAccount.setCreatedAt(new Date());
        dbBankAccount.setStatus(AccountStatus.CREATED);
        dbBankAccount.setOverDraft(overDraft);
        dbBankAccount.setId(UUID.randomUUID().toString());
        dbBankAccount = bankAccountRepository.save(dbBankAccount);
        return mapper.currentBankAccountToCurrentBankAccountDTO(dbBankAccount);


    }

    @Override
    public SavingAccountDTO saveSavingBankAccount(double initialBalance, double interestRate, Long customerId) throws CustomerNotExist {
        CustomerDTO customer = getCustomer(customerId);

        SavingAccount dbBankAccount ;
        dbBankAccount = new SavingAccount();
        dbBankAccount.setCustomer(mapper.customerDtoToCustomer(customer));
        dbBankAccount.setBalance(initialBalance);
        dbBankAccount.setCreatedAt(new Date());
        dbBankAccount.setStatus(AccountStatus.CREATED);
        dbBankAccount.setInterestRate(interestRate);
        dbBankAccount.setId(UUID.randomUUID().toString());
        dbBankAccount = bankAccountRepository.save(dbBankAccount);
        return mapper.savingBankAccountToSavingBankAccountDTO(dbBankAccount);
    }


    public BankAccount saveBankAccount(BankAccount bankAccount) throws RuntimeException {
        return  bankAccountRepository.save(bankAccount);
    }

    @Override
    public List<CustomerDTO> customersList() {
        return customerRepository.findAll().stream().map(c->mapper.customerToCustomerDTO(c)).collect(Collectors.toList());
    }

    @Override
    public List<BankAccountDTO> bankAccountList() {

        List<BankAccount> bankAccounts = bankAccountRepository.findAll();



        return bankAccounts.stream().map( account ->{
            if (account instanceof  SavingAccount ) {
                return  mapper.savingBankAccountToSavingBankAccountDTO((SavingAccount) account);
            }
            return  mapper.currentBankAccountToCurrentBankAccountDTO((CurrentAccount) account);

        }).collect(Collectors.toList());
    }

    @Override
    public BankAccountDTO getBankAccount(String idAccount) throws BankAccountNotExist {

        BankAccount bankAccount = getBankaccountNotExist(idAccount);
        if(bankAccount instanceof  SavingAccount)
        {
            return  mapper.savingBankAccountToSavingBankAccountDTO((SavingAccount) bankAccount);
        }else{
            return  mapper.savingBankAccountToSavingBankAccountDTO((SavingAccount) bankAccount);
        }

    }

    @Override
    public CustomerDTO getCustomer(Long customerId) throws  CustomerNotExist{
        var customer = customerRepository.findById(customerId).orElseThrow(()->new CustomerNotExist("CUSTOMER_NOT_EXIST"));
        return mapper.customerToCustomerDTO(customer);
    }

    @Override
    public void debit(String accountId, double amount, String description) throws BankAccountNotExist,  BalanceNotSufficientException {


        BankAccount bankAccount = getBankaccountNotExist(accountId);

        if(bankAccount.getBalance()<amount)
            throw new BalanceNotSufficientException("NO_SUFFICIENT_BALANCE");

        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.DEBIT);
        accountOperationRepository.save(accountOperation);

        double newBalance = bankAccount.getBalance() - amount;

        bankAccount.setBalance(newBalance);
        saveBankAccount(bankAccount);


    }

    @Override
    public void credit(String accountId, double amount, String description) throws BankAccountNotExist {

        BankAccount bankAccount = getBankaccountNotExist(accountId);


        AccountOperation accountOperation = new AccountOperation();
        accountOperation.setBankAccount(bankAccount);
        accountOperation.setOperationDate(new Date());
        accountOperation.setAmount(amount);
        accountOperation.setType(OperationType.DEBIT);
        accountOperationRepository.save(accountOperation);


        double newBalance = bankAccount.getBalance() + amount;
        bankAccount.setBalance(newBalance);
        saveBankAccount(bankAccount);
    }

    @Override
    public void transfer(String accountIdSource, String accountIdDestination, double amount, String motif) throws BankAccountNotExist, BalanceNotSufficientException {


        debit(accountIdSource,amount, "Transfer to " + accountIdDestination + " -- Comment : " + motif );
        credit(accountIdDestination,amount, "Transfer from " + accountIdSource + " -- Comment : " + motif );

    }

    @Override
    public void deleteCustomer(Long customerId) throws CustomerNotExist {
       getCustomer(customerId);
       customerRepository.deleteById(customerId);
    }


    private BankAccount getBankaccountNotExist(String accountId) throws BankAccountNotExist {
        return bankAccountRepository.findById(accountId)
                .orElseThrow(() -> new BankAccountNotExist("BANKACCOUNT_NOT_EXIST"));
    }


}
