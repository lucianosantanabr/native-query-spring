package br.com.nativequery.dto;

import org.springframework.beans.factory.annotation.Value;

/**
 * POJO for holding Customer related data
 */
public interface CustomerDetailsDTO {

    Integer getCustomerId();

    @Value("#{target.firstName + ' ' + target.lastName}")
    String getCustomerName();

    String getCity();

    String getCountry();

    @Value("#{@mapperUtility.buildOrderDTO(target.orderNumber, target.totalAmount)}")
    CustomerOrderDTO getOrder();
} 
