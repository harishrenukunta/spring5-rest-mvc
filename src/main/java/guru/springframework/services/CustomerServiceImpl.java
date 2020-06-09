package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.repository.CustomerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;
    public final static String API_URL = "/api/v1/customers/";

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public List<CustomerDTO> getCustomers() {
        return customerRepository.findAll().stream()
                .map(cust -> {
                    final CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(cust);
                    customerDTO.setCustomerUrl(API_URL + cust.getId());
                    return customerDTO;
                })
                .collect(Collectors.toList());
    }

    @Override
    public CustomerDTO getCustomerById(String customerId) {
        return customerRepository.findById(Long.valueOf(customerId))
                .map(cust -> {
                    final CustomerDTO customerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(cust);
                    customerDTO.setCustomerUrl(API_URL + cust.getId());
                    return customerDTO;
                })
                .orElse(null);
    }
}
