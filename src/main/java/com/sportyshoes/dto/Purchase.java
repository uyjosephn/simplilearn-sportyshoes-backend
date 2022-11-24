package com.sportyshoes.dto;

import com.sportyshoes.entity.Address;
import com.sportyshoes.entity.Customer;
import com.sportyshoes.entity.Order;
import com.sportyshoes.entity.OrderItem;
import lombok.Data;

import java.util.Set;

@Data
public class Purchase {

    private Customer customer;
    private Address shippingAddress;
    private Address billingAddress;
    private Order order;
    private Set<OrderItem> orderItems;

}
