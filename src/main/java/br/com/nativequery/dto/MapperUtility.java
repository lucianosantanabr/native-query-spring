package br.com.nativequery.dto;

import org.springframework.stereotype.Component;

@Component
public class MapperUtility {

    public CustomerOrderDTO buildOrderDTO(Long orderNumber, Double totalAmount) {
        CustomerOrderDTO order = null;

        if (orderNumber != null) {
            order = new CustomerOrderDTO();
            order.setOrderNumber(orderNumber);
            order.setTotalAmount("$" + totalAmount);
        }
        return order;
    }
}
