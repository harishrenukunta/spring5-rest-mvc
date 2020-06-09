package guru.springframework.services;

import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.domain.Customer;
import guru.springframework.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;


class CustomerServiceImplTest {

    @Mock
    CustomerRepository customerRepository;

    CustomerService customerService;

    @BeforeEach
    public void beforeEach(){
        MockitoAnnotations.initMocks(this);
        customerService = new CustomerServiceImpl(customerRepository);
    }

    @Test
    public void getCustomers(){
        Customer customer1 = new Customer();
        customer1.setFirstname("Mock firstname");
        customer1.setFirstname("Mock lastname");

        Customer customer2 = new Customer();
        customer2.setFirstname("Mock firstname2");
        customer2.setFirstname("Mock lastname2");

        when(customerRepository.findAll()).thenReturn(Arrays.asList(customer1, customer2));

        final List<CustomerDTO> allCustomers = customerService.getCustomers();
        assertThat(allCustomers.size()).isGreaterThan(10);
    }

    @Test
    public void getCustomerById(){
        Customer mockCustomer = new Customer();
        mockCustomer.setFirstname("Mock firstname");
        mockCustomer.setFirstname("Mock lastname");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockCustomer));

        final CustomerDTO customer = customerService.getCustomerById(String.valueOf(5));
        assertThat(customer).isEqualToComparingFieldByField(mockCustomer);
    }

}