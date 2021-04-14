package br.com.cvc.banktransferscheduler;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAutoConfiguration
public class BankTransferSchedulerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BankTransferSchedulerApplication.class, args);
	}

}