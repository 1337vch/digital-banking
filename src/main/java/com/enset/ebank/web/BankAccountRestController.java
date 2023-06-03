package com.enset.ebank.web;


import com.enset.ebank.DTO.AccountOperationDTO;
import com.enset.ebank.DTO.AccountOperationPageDTO;
import com.enset.ebank.DTO.BankAccountDTO;
import com.enset.ebank.exception.BankAccountNotExist;
import com.enset.ebank.services.IBankAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/accounts")
@RequiredArgsConstructor
public class BankAccountRestController {

    final private IBankAccountService bankAccountService;


   @GetMapping
    public List<BankAccountDTO> getBankAccounts(){
       return bankAccountService.bankAccountList();
   }

   @GetMapping("/{accountId}")

   public BankAccountDTO getBankAccounts(@PathVariable String accountId) throws BankAccountNotExist {
       return bankAccountService.getBankAccount(accountId);
   }

    @GetMapping("/{accountId}/operations-history")

    public List<AccountOperationDTO> getBankAccountsHistory(@PathVariable String accountId) throws BankAccountNotExist {
        return bankAccountService.accountHistory(accountId);
    }

    @GetMapping("/{accountId}/pageable-operations-history")

    public AccountOperationPageDTO getBankAccountsHistory(@PathVariable String accountId, Pageable pageable) throws BankAccountNotExist {
        return bankAccountService.accountHistoryPage(accountId,pageable);
    }



}
