package com.bank.service;

import java.time.LocalDate;

import com.bank.entity.AccountEntity;

public interface AccountStatementService {

	AccountEntity getAcountStatement(String id, LocalDate fromDate, LocalDate toDate, Double minAmount,
			Double maxAmount);

}
