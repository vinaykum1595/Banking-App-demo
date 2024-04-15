package com.codeBanking.service.Impl;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.codeBanking.dto.AccountDto;
import com.codeBanking.entity.Account;
import com.codeBanking.mapper.AccountMapper;
import com.codeBanking.repository.AccountRepository;
import com.codeBanking.service.AccountService;


@Service
public class AccountServiceImpl implements AccountService {

	@Autowired
	private AccountRepository accountRepository;
	
	
	public AccountServiceImpl(AccountRepository accountRepository) {
		super();
		this.accountRepository = accountRepository;
	}


	@Override
	public AccountDto createAccount(AccountDto accountDto) {
		
		Account account = AccountMapper.mapToAccount(accountDto);
		Account savedaccount = accountRepository.save(account);
		
		return AccountMapper.mapToAccountDto(savedaccount);// TODO Auto-generated method stub
		
	}


	@Override
	public AccountDto getAccountById(Long Id) {
		
		Account account= accountRepository
				.findById(Id)
				.orElseThrow(()-> new RuntimeException("Account doesn't exists"));// TODO Auto-generated method stub
		
       return AccountMapper.mapToAccountDto(account);
	}


	@Override
	public AccountDto deposit(Long Id, double amount) {
		
		Account account= accountRepository
				.findById(Id)
				.orElseThrow(()-> new RuntimeException("Account doesn't exists"));
		
	          double total = account.getBalance() + amount;
	          account.setBalance(total);
	          Account savedaccount = accountRepository.save(account);
	          
		return AccountMapper.mapToAccountDto(savedaccount);
	}


	@Override
	public AccountDto withdraw(Long Id, double amount) {
		Account account= accountRepository
				.findById(Id)
				.orElseThrow(()-> new RuntimeException("Account doesn't exists"));
		
		if(account.getBalance()<amount) {
			
			throw new RuntimeException("Insufficient Fund");
		}
		
		double total = account.getBalance()-amount;
		
		account.setBalance(total);
		
		Account savedaccount = accountRepository.save(account);
		
		
		return AccountMapper.mapToAccountDto(savedaccount);
	}


	@Override
	public List<AccountDto> getAllAccounts() {
		
		 List<Account> accounts = accountRepository.findAll();
		 
		 return accounts.stream().map((account)-> AccountMapper.mapToAccountDto(account)).collect(Collectors.toList());
		
	}


	@Override
	public void deleteAccount(Long Id) {
		
		Account account= accountRepository
				.findById(Id)
				.orElseThrow(()-> new RuntimeException("Account doesn't exists"));
		
		accountRepository.deleteById(Id);
	}

}
