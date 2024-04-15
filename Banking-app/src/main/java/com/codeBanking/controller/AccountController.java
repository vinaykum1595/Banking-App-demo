package com.codeBanking.controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codeBanking.dto.AccountDto;
import com.codeBanking.service.AccountService;

@RestController
@RequestMapping("/api/accounts")
public class AccountController {
	
	@Autowired
	private AccountService accountService;

	public AccountController(AccountService accountService) {
		super();
		this.accountService = accountService;
	}

	
	// add account Rest API
	
	@PostMapping
	public ResponseEntity<AccountDto> addAccount(@RequestBody AccountDto accountDto){
		
		return new ResponseEntity<>(accountService.createAccount(accountDto), HttpStatus.CREATED);
	}
	
	//Get Account Rest API
	
	@GetMapping("/{Id}")
	public ResponseEntity<AccountDto> getAccountById(@PathVariable Long Id){
		   
		AccountDto accountDto = accountService.getAccountById(Id);
		
		return ResponseEntity.ok(accountDto);
		
	}
	
	//Deposit Rest API
	
	    @PutMapping("/{Id}/deposit")
		public ResponseEntity<AccountDto> deposit(@PathVariable Long Id, @RequestBody Map<String, Double> request){
			
			Double amount = request.get("amount");
			AccountDto accountDto = accountService.deposit(Id, amount);
			
			return ResponseEntity.ok(accountDto);
		}
	//withdraw Rest API
	    
	    @PutMapping("/{Id}/withdraw")
	    public ResponseEntity<AccountDto> withdraw(@PathVariable Long Id, @RequestBody Map<String, Double> request){
	    	
	    	double amount = request.get("amount");
	    	AccountDto accountDto =  accountService.withdraw(Id, amount);
	    	return ResponseEntity.ok(accountDto);
	    }
	    //Get All accounts Rest API
	    
	    @GetMapping
	    public ResponseEntity<List<AccountDto>> getAllAccounts(){
	    	
	    	List<AccountDto> accounts =  accountService.getAllAccounts();
	    	
	    	return ResponseEntity.ok(accounts);
	    }
	    
	    //delete account
	    
	    @DeleteMapping("/{Id}")
	    public ResponseEntity<String> deleteAccount(@PathVariable Long Id){
	    
	    accountService.deleteAccount(Id);
	    return ResponseEntity.ok("Account is deleted successfully");
	    }
}
