package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getCustomers();
    public CustomerDTO getCustomerById(final String customerId);
}
