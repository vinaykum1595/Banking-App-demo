package com.codeBanking.service;

import java.util.List;

import com.codeBanking.dto.AccountDto;
import com.codeBanking.entity.Account;

public interface AccountService {

	
	AccountDto createAccount(AccountDto accountDto);
	
	
	AccountDto getAccountById(Long Id);
	
	AccountDto deposit(Long Id, double amount);
	
	AccountDto withdraw(Long Id, double amount);
	
	List<AccountDto> getAllAccounts();
	
	void deleteAccount(Long Id);
	
}
