package com.cholildev.tiket_renang.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cholildev.tiket_renang.model.Customer;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}
