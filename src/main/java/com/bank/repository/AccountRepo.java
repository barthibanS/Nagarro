package com.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.entity.AccountEntity;

public interface AccountRepo extends CrudRepository<AccountEntity, Integer> {

}
