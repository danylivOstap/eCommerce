package com.example.ecommerce.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;

public record CustomerRequest(
     String id,
     @NotBlank(message = "Customer first name is required")
     String firstName,
     @NotBlank(message = "Customer last name is required")
     String lastName,
     @Email(message = "Customer email is not valid")
     String email,
     Address address
) {

}
