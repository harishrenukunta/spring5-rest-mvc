package guru.springframework.services;

import guru.springframework.api.v1.mapper.CustomerMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repository.CustomerRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

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

    @Override
    public CustomerDTO addCustomer(CustomerDTO customerDTO) {
        return saveCustomer(CustomerMapper.INSTANCE.customerDTOtoCustomer(customerDTO));
    }

    private CustomerDTO saveCustomer(final Customer customer){
        final Customer savedCustomer = customerRepository.save(customer);
        final CustomerDTO savedCustomerDTO = CustomerMapper.INSTANCE.customerToCustomerDTO(savedCustomer);
        savedCustomerDTO.setCustomerUrl(API_URL + savedCustomer.getId());
        return savedCustomerDTO;
    }

    @Override
    public CustomerDTO updateCustomer(final Long id, final CustomerDTO customerDTO) {
        final Customer customer = CustomerMapper.INSTANCE.customerDTOtoCustomer(customerDTO);
        customer.setId(id);
        return saveCustomer(customer);
    }

    @Override
    public CustomerDTO patchCustomer(Long id, CustomerDTO customerDTO) {
        return customerRepository.findById(id)
                .map(customer -> {
                    if(!StringUtils.isEmpty(customerDTO.getFirstname())){
                        customer.setFirstname(customerDTO.getFirstname());
                    }

                    if(!StringUtils.isEmpty(customerDTO.getLastname())){
                        customer.setLastname(customerDTO.getLastname());
                    }
                    return CustomerMapper.INSTANCE.customerToCustomerDTO(customerRepository.save(customer));
                })
                .orElseThrow(RuntimeException::new);
    }
}
