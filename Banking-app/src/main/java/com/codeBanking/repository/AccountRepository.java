package com.codeBanking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.codeBanking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	
}
