package com.bank.entity;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "statement")
public class AccountStatementEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	@Column(name = "ID")
	@JsonIgnore
	private int id;

	@Column(name = "account_id")
	@JsonIgnore
	private int accountId;

	@Column(name = "datefield")
	private String dateField;

	@Column(name = "amount")
	private String amount;

	@ManyToOne
	@JoinColumn(name = "account_id", insertable = false, updatable = false)
	private AccountEntity account;

	public AccountEntity getAccount() {
		return account;
	}

	public void setAccount(AccountEntity account) {
		this.account = account;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getDateField() {
		return dateField;
	}

	public void setDateField(String dateField) {
		this.dateField = dateField;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public LocalDate getDate() {
		try {
			return LocalDate.parse(dateField, DateTimeFormatter.ofPattern("dd.MM.yyyy"));
		} catch (Exception e) {
			return null;
		}
	}

	public double getAmountNumber() {
		return Double.parseDouble(amount);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		AccountStatementEntity other = (AccountStatementEntity) obj;
		return id == other.id;
	}

	@Override
	public String toString() {
		return "StatementEntity [id=" + id + ", accountId=" + accountId + ", datefield=" + dateField + ", amount="
				+ amount + "]";
	}

}
