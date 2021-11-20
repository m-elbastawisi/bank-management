package com.bank.cqrs.query.repository;

import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;
import org.springframework.stereotype.Repository;

import com.bank.cqrs.query.entity.Account;

import org.springframework.data.jpa.repository.JpaRepository;

@Repository
public interface  AccountRepository extends JpaRepository<Account, String>{

}
