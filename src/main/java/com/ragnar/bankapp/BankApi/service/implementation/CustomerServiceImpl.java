package com.ragnar.bankapp.BankApi.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ragnar.bankapp.BankApi.dto.CustomerUpdate;
import com.ragnar.bankapp.BankApi.exception.CustomerNotFoundExcepion;
import com.ragnar.bankapp.BankApi.model.Customer;
import com.ragnar.bankapp.BankApi.repository.CustomerRepository;
import com.ragnar.bankapp.BankApi.service.CustomerService;

@Service
@Transactional
public class CustomerServiceImpl implements CustomerService {

	@Autowired
	CustomerRepository customerReposoitory;

	@Override
	public Customer findCustumerById(Long id) throws CustomerNotFoundExcepion {
		Customer customer = customerReposoitory.findById(id)
				.orElseThrow(() -> new CustomerNotFoundExcepion("customer not found" + id));
		return customer;
	}
	@Override
	public void deleteCustomerById(Long id) {
		customerReposoitory.deleteById(id);
	}
	@Override
	public Customer registerCustomer(Customer customer) {
		return customerReposoitory.save(customer);
	}
	@Override
	public Customer updateCustomer(CustomerUpdate customer, Long id) throws CustomerNotFoundExcepion {
		Customer theCustomer = customerReposoitory.findById(id)
				.orElseThrow(() -> new CustomerNotFoundExcepion("customer not found" + id));
		theCustomer.setFirstName(customer.getFirstName());
		theCustomer.setLastName(customer.getLastName());
		theCustomer.setEmail(customer.getEmail());
		return customerReposoitory.save(theCustomer);
	}
	@Override
	public Iterable<Customer> getAllCustomers() {
     	return customerReposoitory.findAll();
	}

}
