package com.bank.service.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bank.entity.AccountEntity;
import com.bank.entity.AccountStatementEntity;
import com.bank.exception.NoSuchAccountExistsException;
import com.bank.repository.AccountStatementRepository;
import com.bank.service.AccountStatementService;

/**
 * 
 * @author Nithya
 *
 */

//@Slf4j
@Service
public class AccountStatementServiceImpl implements AccountStatementService {

	@Autowired
	private AccountStatementRepository accountStatementRepository;

	@Value("${defaultmonth}")
	private int months;

	@Override
	public AccountEntity getAcountStatement(String id, LocalDate fromDate, LocalDate toDate, Double minAmount,
			Double maxAmount) {

		if (fromDate != null && toDate != null && (toDate.isBefore(fromDate) || fromDate.isEqual(toDate))) {
			throw new NoSuchAccountExistsException("Invalid date parameter");
		}

		if (minAmount != null && maxAmount != null && minAmount >= maxAmount) {
			throw new NoSuchAccountExistsException("Invalid Min/Max amount parameter");
		}

		if (toDate == null) {
			fromDate = LocalDate.now().minusMonths(months);
			toDate = LocalDate.now();
		}

		if (minAmount == null || maxAmount == null || maxAmount == 0) {
			minAmount = null;
			maxAmount = null;
		}

		AccountEntity accountEntity = null;
		Optional<AccountEntity> accountEntityOptional = accountStatementRepository.findById(Integer.parseInt(id));

		if (accountEntityOptional.isPresent()) {
			accountEntity = accountEntityOptional.get();
			Predicate<AccountStatementEntity> statementPredicate = generatePredicate(fromDate, toDate, minAmount,
					maxAmount);
			accountEntity.setStatement(filteredAttribute(accountEntity.getStatement(), statementPredicate));
		}

		return accountEntity;
	}

	private Predicate<AccountStatementEntity> generatePredicate(LocalDate fromDate, LocalDate toDate, Double minAmount,
			Double maxAmount) {
		return statementList -> (statementList.getDate().compareTo(fromDate) >= 0
				&& statementList.getDate().compareTo(toDate) <= 0
				&& (minAmount == null || statementList.getAmountNumber() >= minAmount)
				&& (maxAmount == null || statementList.getAmountNumber() <= maxAmount));
	}

	private List<AccountStatementEntity> filteredAttribute(List<AccountStatementEntity> statement,
			Predicate<AccountStatementEntity> statementPredicate) {
		statement = statement.stream().filter(statementPredicate).collect(Collectors.toList());
		return statement;
	}

}
