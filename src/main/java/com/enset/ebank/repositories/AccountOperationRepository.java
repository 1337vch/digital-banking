package com.enset.ebank.repositories;

import com.enset.ebank.entities.AccountOperation;
import com.enset.ebank.entities.BankAccount;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AccountOperationRepository extends JpaRepository<AccountOperation, Long> {


    List<AccountOperation> findByBankAccount_Id(String accountId);

    Page<AccountOperation> findByBankAccount(BankAccount account, Pageable page);





}
