package com.bank.repository;

import org.springframework.data.repository.CrudRepository;

import com.bank.entity.StatementEntity;

public interface StatementRepo extends CrudRepository<StatementEntity, Integer> {

}
