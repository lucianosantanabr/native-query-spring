package br.com.nativequery.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * POJO for holding Order related data
 */
@Getter
@Setter
public class CustomerOrderDTO {

	private Long orderNumber;
    private String totalAmount;
}
