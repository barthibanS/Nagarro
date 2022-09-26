package com.example.bank;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import com.bank.BankApplication;

@RunWith(SpringRunner.class)
class BankApplicationTests {
	  
	  @Test
	  void applicationContextTest() {
		  BankApplication.main(new String[] {});
	  }

}
