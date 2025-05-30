package com.example.ecommerce.customer;

import org.springframework.stereotype.Service;

@Service
public class CustomerMapper {

  public Customer toCustomer(CustomerRequest request) {
    if (request == null) {
      return null;
    }

    return Customer.builder()
        .id(request.id())
        .firstName(request.firstName())
        .lastName(request.lastName())
        .email(request.email())
        .address(request.address())
        .build();
  }

  public CustomerResponse toDto(Customer customer) {
    return CustomerResponse.builder()
        .id(customer.getId())
        .firstName(customer.getFirstName())
        .lastName(customer.getLastName())
        .email(customer.getEmail())
        .address(customer.getAddress())
        .build();
  }
}
