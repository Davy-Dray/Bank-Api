package com.ragnar.bankapp.BankApi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.ragnar.bankapp.BankApi.model.Customer;

public interface CustomerRepository extends CrudRepository<Customer, Long> {

    @Query("SELECT c FROM Customer c WHERE c.email=:email")
    Iterable<Customer> findCustomerByEmail(@Param("email") String email);

    boolean existsByEmail(String model);


}