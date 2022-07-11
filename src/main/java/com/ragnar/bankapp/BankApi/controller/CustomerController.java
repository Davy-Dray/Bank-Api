package com.ragnar.bankapp.BankApi.controller;

import javax.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ragnar.bankapp.BankApi.exception.CustomerNotFoundExcepion;
import com.ragnar.bankapp.BankApi.model.Customer;
import com.ragnar.bankapp.BankApi.service.CustomerService;

@RestController
@RequestMapping("/api/v1/customer")
public class CustomerController {

	final CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@PostMapping("/signup")
	public void createCustomer(@Valid @RequestBody Customer customer) throws CustomerNotFoundExcepion {

		customerService.registerCustomer(customer);

	}

	@GetMapping("/customers")
	public Iterable<Customer> getCustomers() {

		return customerService.getAllCustomers();
	}

	@DeleteMapping("/{id}")
	public void deleteCustomer(Long id) {
		customerService.deleteCustomerById(id);

	}

}
