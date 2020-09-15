package br.com.nativequery.dto;

import br.com.nativequery.entities.Customer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class CustomerDTO {
	
	private Integer id;
	private String firstName;
	private String lastName;
    private String city;
    private String country;	
    
    public CustomerDTO(Customer customer) {
    	this.id = customer.getId();
    	this.firstName = customer.getFirstName();
    	this.lastName = customer.getLastName();
    	this.city = customer.getCity();
    	this.country = customer.getCountry();
    }

}
