package com.ragnar.bankapp.BankApi.service;

import com.ragnar.bankapp.BankApi.dto.CustomerUpdate;
import com.ragnar.bankapp.BankApi.exception.CustomerNotFoundExcepion;
import com.ragnar.bankapp.BankApi.model.Customer;

public interface CustomerService {

	public Customer findCustumerById(Long id) throws CustomerNotFoundExcepion;

	public void deleteCustomerById(Long id);

	public Customer registerCustomer(Customer customer);

	public Customer updateCustomer(CustomerUpdate customer,Long id) throws CustomerNotFoundExcepion;

	public Iterable<Customer> getAllCustomers();
   
}
