package br.com.nativequery.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import lombok.Data;

@Data
@Entity
@Table(name = "CUSTOMER_ORDER")
public class CustomerOrder {
	
    @Id
    @Column(name = "ID")
    private Integer id;

    @Column(name = "ORDER_NUMBER")
    private String orderNumber;

    @Column(name = "TOTAL_AMOUNT")
    private String totalAmount;

    @OneToOne
    @JoinColumn(name="CUSTOMER_ID", nullable=false)
    private Customer customer;
    
}
