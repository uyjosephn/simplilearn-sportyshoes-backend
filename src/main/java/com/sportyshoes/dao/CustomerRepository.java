package com.sportyshoes.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.sportyshoes.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
