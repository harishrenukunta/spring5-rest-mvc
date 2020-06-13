package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;

import java.util.List;

public interface CustomerService {
    public List<CustomerDTO> getCustomers();
    public CustomerDTO getCustomerById(final String customerId);

    public CustomerDTO addCustomer(CustomerDTO customerDTO);
    public CustomerDTO updateCustomer(Long id, CustomerDTO customerDTO);
    public CustomerDTO patchCustomer(final Long id, final CustomerDTO customerDTO);
}
