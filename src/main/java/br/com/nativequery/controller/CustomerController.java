package br.com.nativequery.controller;

import java.util.List;

import javax.websocket.server.PathParam;

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

import br.com.nativequery.dto.CustomerDTO;
import br.com.nativequery.dto.CustomerDetailsDTO;
import br.com.nativequery.entities.Customer;
import br.com.nativequery.service.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "customers")
@RestController
@RequestMapping(path = "/customers")
public class CustomerController {
	
	private final CustomerService service;
	
	public CustomerController(CustomerService service) {
		this.service = service;
	}
	
	@ApiOperation(value = "Find by Customer", notes = "Method responsible for searching customer by id")
	@GetMapping(value = "/{id}")
	public ResponseEntity<CustomerDTO> findByCustomer(@ApiParam(value = "Company id", required = true, example = "1") @PathVariable Integer id) {
		CustomerDTO respDTO = service.getById(id);
		return ResponseEntity.ok().body(respDTO);
	}

	@ApiOperation(value = "Find All Customer records", notes = "Method responsible of listing all records")
	@GetMapping()
	public ResponseEntity<List<CustomerDTO>> findAllCustomers() {
		List<CustomerDTO> listDto = this.service.getAllCustomer();
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Find All Customer and Order records", notes = "Method responsible of listing all records")
	@GetMapping("/customerorder")
	public ResponseEntity<List<CustomerDetailsDTO>> findAllCustomer() {
		List<CustomerDetailsDTO> listDto = this.service.getCustomersAndOrderData();
		return ResponseEntity.ok().body(listDto);
	}
	
	@ApiOperation(value = "Find First Name Customer", notes = "Method responsible for listing by first name")
	@GetMapping(value = "findbyname/{name}")
	public ResponseEntity<List<CustomerDetailsDTO>> findByCustomerName(@ApiParam(value = "first name", required = true, example = "name") 
																	@PathVariable String name) {
		List<CustomerDetailsDTO> lstDTO = this.service.getCustomersByFirstName(name);
		return ResponseEntity.ok().body(lstDTO);
	}
	
	@ApiOperation(value = "Create a new Customer record", notes =  "Method responsible of create new record")
	@PostMapping
	public ResponseEntity<CustomerDTO> create(@ApiParam(value = "Customer", required = true) @RequestBody Customer customerReq) {
		try {
			CustomerDTO dtoResp = this.service.save(customerReq);
			return ResponseEntity.status(HttpStatus.CREATED).body(dtoResp);
		} catch (Exception e) {
		   log.error(e.getMessage());
		}
		return ResponseEntity.badRequest().build();
	}
	
	@ApiOperation(value = "Update Customer record", notes = "Method responsible of update record")
	@PutMapping
	public ResponseEntity<CustomerDTO> update(@ApiParam(value = "Customer", required = true) @RequestBody Customer customerReq) {
		CustomerDTO dtoResp = this.service.update(customerReq);
		return ResponseEntity.ok().body(dtoResp);
	}
	
	@ApiOperation(value = "Delete Customer record", notes = "Method responsible of delete record")
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<String> delete(@ApiParam(value = "Customer id", required = true) @PathVariable Integer id) {
		this.service.delete(id);
		return ResponseEntity.ok().build();
	}
}
