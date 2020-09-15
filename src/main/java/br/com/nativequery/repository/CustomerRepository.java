package br.com.nativequery.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import br.com.nativequery.dto.CustomerDetailsDTO;
import br.com.nativequery.entities.Customer;

@Repository
public interface CustomerRepository extends CrudRepository<Customer, Integer> {
	
	List<Customer> findAll();
	
    @Query(value = " SELECT " +
				   "   cust.id as customerId, " +
				   "   cust.FIRST_NAME as firstName, " +
				   "   cust.LAST_NAME as lastName, " +
				   "   cust.CITY as city, " +
				   "   cust.COUNTRY as country, " +
				   "   corder.ORDER_NUMBER as orderNumber, " +
				   "   corder.TOTAL_AMOUNT as totalAmount " +
				   " FROM CUSTOMER cust " +
				   "     LEFT JOIN CUSTOMER_ORDER corder " +
				   "      ON corder.CUSTOMER_ID = cust.ID ", nativeQuery = true )                   
	 List<CustomerDetailsDTO> getCustomerDetails();

    @Query(value = " SELECT " +
                   "   cust.id as customerId, " +
	               "   cust.FIRST_NAME as firstName, " +
	               "   cust.LAST_NAME as lastName, " +
	               "   cust.CITY as city, " +
	               "   cust.COUNTRY as country, " +
	               "   corder.ORDER_NUMBER as orderNumber, " +
	               "   corder.TOTAL_AMOUNT as totalAmount " +
	               " FROM CUSTOMER cust LEFT JOIN CUSTOMER_ORDER corder " +
	               "    ON corder.CUSTOMER_ID = cust.ID " +
	               "  WHERE cust.FIRST_NAME like %:firstName% ", nativeQuery = true )
    List<CustomerDetailsDTO> searchCustomerByFirstName(@Param("firstName") String firstName);
    
}
