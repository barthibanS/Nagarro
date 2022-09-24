package com.bank.service;

import java.time.LocalDate;

import com.bank.entity.AccountEntity;

public interface AccountService {

	AccountEntity getAcountStatement(String id, LocalDate fromDate, LocalDate toDate, Double minAmount, Double maxAmount);

}
