package com.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;

import com.bank.controller.AccountControl;

@SpringBootApplication
//@ComponentScan(basePackages = "com.example.bank.controller")
public class BankApplication { //implements CommandLineRunner {

//	@Autowired
//	private JdbcTemplate template;
	
	public static void main(String[] args) {
		SpringApplication.run(BankApplication.class, args);
	}

//	@Override
//	public void run(String... args) throws Exception {
//		
//	//	template.update("INSERT INTO ACCOUNT(account_type, account_number) VALUES('current', 0012250016006)");
//	}

}
