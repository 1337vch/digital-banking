package com.enset.ebank;

import com.enset.ebank.DTO.CustomerDTO;

import com.enset.ebank.services.IBankAccountService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class DigitalBankingApplication {

	public static void main(String[] args) {
		SpringApplication.run(DigitalBankingApplication.class, args);
	}


     @Bean
	//@Order(0)
	CommandLineRunner start(IBankAccountService bankAccountService
							) {

		return  args -> {

		bankAccountService.customersList().stream().forEach(name -> System.out.println("Customer : " + name));
		var account = bankAccountService.getBankAccount("01cbc52a-34e2-46df-86b1-989f54400a62");
		//account.getAccountOperations().stream().forEach(op -> System.out.println("Op : " + op));


		};
	}

}
