package com.bank.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.bank.entity.AccountEntity;

@Repository
public interface AccountStatementRepository extends CrudRepository<AccountEntity, Integer> {

}
