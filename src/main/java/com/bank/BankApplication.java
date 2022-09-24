package com.bank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.session.HttpSessionEventPublisher;

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


//@Bean
//public HttpSessionEventPublisher httpSessionEventPublisher() {
//    return new HttpSessionEventPublisher();
//}
//
//@Bean
//public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//    http
//        .sessionManagement(session -> session
//            .maximumSessions(1)
//            .maxSessionsPreventsLogin(true)
//        );
//    return http.build();
//}
}

