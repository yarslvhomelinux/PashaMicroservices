package com.microservices.customer;

import com.microservices.clients.fraud.FraudCheckResponse;
import com.microservices.clients.fraud.FraudClient;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
@AllArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final RestTemplate restTemplate;
    private final FraudClient fraudClient;

    public void registerCustomer(CustomerRegistrationRequest request) {
        Customer customer = Customer.builder()
                .firstName(request.firstName())
                .lastName(request.lastName())
                .email(request.email())
                .build();

        //todo: check if email valid
        customerRepository.saveAndFlush(customer);
        FraudCheckResponse response = fraudClient.isFraudster(customer.getId());

        if (response.isFraudster()) {
            throw new IllegalStateException("fraudster");
        }

    }
}
