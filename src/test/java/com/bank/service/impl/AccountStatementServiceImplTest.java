package com.bank.service.impl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Optional;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.bank.entity.AccountEntity;
import com.bank.entity.AccountStatementEntity;
import com.bank.repository.AccountStatementRepository;

@ExtendWith(MockitoExtension.class)
class AccountStatementServiceImplTest {

	@Mock
	private AccountStatementRepository accountStatementRepository =  Mockito.mock(AccountStatementRepository.class);

	@InjectMocks
	private AccountStatementServiceImpl accountStatementService;

	private AccountEntity accountEntity;

	private AccountStatementEntity accountStatementEntity;

	@BeforeEach
	public void setup() {
		accountEntity = new AccountEntity();
		accountEntity.setAccountNumber("1555521565");
		accountEntity.setAccountType("current");
		accountEntity.setId(3);
		accountEntity.setStatement(new ArrayList<>());
		accountStatementEntity = new AccountStatementEntity();
		accountStatementEntity.setAccountId(3);
		accountStatementEntity.setDateField("19.08.2021");
		accountStatementEntity.setId(3);
		accountStatementEntity.setAmount("100.555463");
		accountEntity.getStatement().add(accountStatementEntity);
	}

	@DisplayName("JUnit test for getAcountStatement method")
	@Test
	void getAcountStatementTest() {
		
		accountStatementService.setMonths(60);
		when(accountStatementRepository.findById(accountEntity.getId())).thenReturn(Optional.of(accountEntity));
		accountEntity = accountStatementService.getAcountStatement(String.valueOf(accountEntity.getId()), null, null,
				null, null);
		assertFalse(accountEntity.getStatement().isEmpty());
	}
	
	@DisplayName("JUnit test for getAcountStatement method")
	@Test
	void getAcountStatementTestNegative() {
		
		accountStatementService.setMonths(0);
		accountStatementEntity.setDateField("19.08.2018");
		when(accountStatementRepository.findById(accountEntity.getId())).thenReturn(Optional.of(accountEntity));
		accountEntity = accountStatementService.getAcountStatement(String.valueOf(accountEntity.getId()), null, null,
				null, null);
		assertTrue(accountEntity.getStatement().isEmpty());
	}

}
