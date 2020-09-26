package br.com.nativequery.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.nativequery.dto.CustomerDTO;
import br.com.nativequery.dto.CustomerDetailsDTO;
import br.com.nativequery.entities.Customer;
import br.com.nativequery.exception.ResourceNotFoundException;
import br.com.nativequery.repository.CustomerRepository;

@Service
public class CustomerService {

	@Autowired
	private CustomerRepository customerRepository;
	
	public CustomerService(CustomerRepository customerRepository) {
		this.customerRepository = customerRepository;
	}
	
	public CustomerDTO getById(Integer id) {
		CustomerDTO respDTO;
		 Optional<Customer> customerOpt = customerRepository.findById(id);
		 if(customerOpt.isPresent()) {
			 Customer customer = customerOpt.get();
			 respDTO = new CustomerDTO(customer);
			 return respDTO;
		 } else throw new ResourceNotFoundException("Customer not found for ID: " + id); 
	}
	
    public List<CustomerDetailsDTO> getCustomersAndOrderData() {
    	List<CustomerDetailsDTO> dto = customerRepository.getCustomerDetails();
    	if (!dto.isEmpty()) {
    		return dto;
    	} else throw new ResourceNotFoundException("There is not customer registration");
    }
    
    public List<CustomerDTO> getAllCustomer() {
    	 List<Customer> lstCustomers = customerRepository.findAll();
    	 if (!lstCustomers.isEmpty()) {
    		 List<CustomerDTO> dto;
    		    dto = lstCustomers
    			      .stream()
    				  .map(CustomerDTO::new)
    				  .collect(Collectors.toList());
    		 return dto;
    	 } else throw new ResourceNotFoundException("There is no customer registration");
    }
    
    
    public List<CustomerDetailsDTO> getCustomersByFirstName(String firstName) {
    	List<CustomerDetailsDTO> lstDTO = customerRepository.searchCustomerByFirstName(firstName);
    	if (!lstDTO.isEmpty()) {
    		return lstDTO;
    	} else throw new ResourceNotFoundException("There is no customer registration with the first name " + firstName);
    }

    
    public CustomerDTO save(Customer customer) {
    	Customer customerResp = customerRepository.save(customer);
    	if (customerResp != null) {
    		CustomerDTO respDTO;
    		respDTO = new CustomerDTO(customerResp);
    		return respDTO;
    	} else throw new ResourceNotFoundException("The record not saved");
    }
    
    public CustomerDTO update(Customer customer) {
    	verifyIfCustomerExists(customer.getId());
    	Customer customerResp = customerRepository.save(customer);
    	CustomerDTO respDTO;
    	respDTO = new CustomerDTO(customerResp);
    	return respDTO;
    }
    
    public void delete(Integer id) {
    	verifyIfCustomerExists(id);
    	customerRepository.deleteById(id);
    }
    
    private void verifyIfCustomerExists(Integer id) {
    	if(!customerRepository.existsById(id))
    		throw new ResourceNotFoundException("Customer not found for ID: " + id);
    }
    
}
