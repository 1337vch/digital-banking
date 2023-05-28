package com.enset.ebank;

import com.enset.ebank.entities.BankAccount;
import com.enset.ebank.entities.CurrentAccount;
import com.enset.ebank.entities.Customer;
import com.enset.ebank.entities.SavingAccount;
import com.enset.ebank.enums.AccountStatus;
import com.enset.ebank.repositories.AccountOperationRepository;
import com.enset.ebank.repositories.BankAccountRepository;
import com.enset.ebank.repositories.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;

@SpringBootApplication
public class DigitalBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}


	@Bean
	CommandLineRunner start(
    CustomerRepository customerRepository,
	BankAccountRepository bankAccountRepository,
	AccountOperationRepository accountOperationRepository
	){
		return  args -> {

			Stream.of("Achraf", "layla", "Hamid").forEach( name -> {
				Customer customer = new Customer();
				customer.setName(name);
				customer.setEmail(name + "@gmail.com");
				customerRepository.save(customer);



			});

			customerRepository.findAll().forEach( customer -> {

				//
				CurrentAccount currentAccount = new CurrentAccount();
				currentAccount.setCustomer(customer);
				currentAccount.setBalance(Math.random()*3000);
				currentAccount.setCreatedAt(new Date());
				currentAccount.setStatus(AccountStatus.CREATED);
				currentAccount.setOverDraft(3000);
				currentAccount.setId(UUID.randomUUID().toString());
				bankAccountRepository.save(currentAccount);
				//
				SavingAccount savingAccount = new SavingAccount();
				savingAccount.setCustomer(customer);
				savingAccount.setBalance(Math.random()*3000);
				savingAccount.setCreatedAt(new Date());
				savingAccount.setStatus(AccountStatus.CREATED);
				savingAccount.setInterestRate(5.5);
				savingAccount.setId(UUID.randomUUID().toString());
				bankAccountRepository.save(savingAccount);

			});



		};
	}

}
