package com.bank.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.bank.entity.AccountEntity;
import com.bank.service.AccountService;
import com.fasterxml.jackson.annotation.JsonFormat;

@RestController
@RequestMapping("/account")
public class AccountControl {

	@Autowired
	AccountService accountService;

	@GetMapping("/{id}")
	public ResponseEntity<AccountEntity> getAcountStatement(@PathVariable(name = "id", required = true) String id,
			@RequestParam (name = "fromDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate fromDate,
			@RequestParam (name = "toDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") LocalDate toDate,
			@RequestParam (name = "minAmount", required = false) Double minAmount,
			@RequestParam (name = "maxAmount", required = false) Double maxAmount) {
		return new ResponseEntity<>(accountService.getAcountStatement(id, fromDate, toDate, minAmount, maxAmount), HttpStatus.OK);
	}

}