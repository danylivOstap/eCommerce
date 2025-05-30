package com.example.ecommerce.customer;

import com.example.ecommerce.exception.CustomerNotFoundException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
@RequiredArgsConstructor
public class CustomerService {

  private final CustomerRepository customerRepository;
  private final CustomerMapper customerMapper;

  public String createCustomer(CustomerRequest request) {
    var customer = customerRepository.save(customerMapper.toCustomer(request));
    return null;
  }

  public void updateCustomer(CustomerRequest request) {
    var customer = customerRepository.findById(request.id()).orElseThrow(
        () -> new CustomerNotFoundException(
          String.format("Can not update customer: No customer found by id: %s", request.id())
        ));
    mergeCustomer(customer, request);
    customerRepository.save(customer);
  }

  private void mergeCustomer(Customer customer, CustomerRequest request) {
    if (!request.firstName().isBlank()) {
      customer.setFirstName(request.firstName());
    }

    if (!request.lastName().isBlank()) {
      customer.setLastName(request.lastName());
    }

    if (!request.email().isBlank()) {
      customer.setEmail(request.email());
    }

    if (request.address() != null) {
      customer.setAddress(request.address());
    }
  }

  public List<CustomerResponse> findAll() {
    return customerRepository.findAll().stream()
        .map(customerMapper::toDto)
        .toList();
  }

  public Boolean existsById(String customerId) {
    return customerRepository.findById(customerId).isPresent();
  }

  public CustomerResponse getById(String customerId) {
    return customerRepository.findById(customerId)
        .map(customerMapper::toDto)
        .orElseThrow(() -> new CustomerNotFoundException(
            String.format("Can't find customer by id: %s", customerId)
        ));
  }

  public void deleteByID(String customerId) {
    customerRepository.deleteById(customerId);
  }
}
