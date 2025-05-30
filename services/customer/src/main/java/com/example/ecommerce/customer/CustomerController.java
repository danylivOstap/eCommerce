package com.example.ecommerce.customer;

import jakarta.validation.Valid;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/customer")
@RequiredArgsConstructor
public class CustomerController {

  private final CustomerService customerService;

  @PostMapping
  public ResponseEntity<String> createCustomer(@RequestBody @Valid CustomerRequest request) {
    return ResponseEntity.ok(customerService.createCustomer(request));
  }

  @PutMapping
  public ResponseEntity<Void> updateCustomer(
      @RequestBody @Valid CustomerRequest request
  ) {
    customerService.updateCustomer(request);
    return ResponseEntity.accepted().build();
  }

  @GetMapping
  public ResponseEntity<List<CustomerResponse>> findAll() {
    return ResponseEntity.ok(customerService.findAll());
  }

  @GetMapping("/exists/{customerId}")
  public ResponseEntity<Boolean> existsById(@PathVariable String customerId) {
    return ResponseEntity.ok(customerService.existsById(customerId));
  }

  @GetMapping("/{customerId}")
  public ResponseEntity<CustomerResponse> getById(@PathVariable String customerId) {
    return ResponseEntity.ok(customerService.getById(customerId));
  }

  @DeleteMapping("/{customerId}")
  public ResponseEntity<Void> deleteById(@PathVariable String customerId) {
    customerService.deleteByID(customerId);
    return ResponseEntity.accepted().build();
  }
}
