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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


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
        assertThat(allCustomers.size()).isGreaterThan(0);
    }

    @Test
    public void getCustomerById(){
        Customer mockCustomer = new Customer();
        mockCustomer.setFirstname("Mock firstname");
        mockCustomer.setFirstname("Mock lastname");

        when(customerRepository.findById(anyLong())).thenReturn(Optional.ofNullable(mockCustomer));

        final CustomerDTO customer = customerService.getCustomerById(String.valueOf(5));
        assertThat(customer).isEqualToComparingOnlyGivenFields(mockCustomer, "firstname", "lastname");
    }

    @Test
    public void updateCustomer(){
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Dummyfn");
        customerDTO.setLastname("Dummyln");

        final Customer customer = new Customer();
        customer.setFirstname(customerDTO.getFirstname());
        customer.setLastname(customerDTO.getLastname());
        customer.setId(1L);


        final CustomerDTO updatedCustomerDTO = new CustomerDTO();
        updatedCustomerDTO.setFirstname(customerDTO.getFirstname());
        updatedCustomerDTO.setLastname(customerDTO.getLastname());
        updatedCustomerDTO.setCustomerUrl("/api/v1/customers/" + customer.getId());

        when(customerRepository.save(any(Customer.class))).thenReturn(customer);
        final CustomerDTO returnCustomerDTO = customerService.updateCustomer(new Long(1), customerDTO);
        assertThat(returnCustomerDTO).isEqualToComparingFieldByField(updatedCustomerDTO);


    }

    @Test
    void deleteByCustomerId() {
        final Long id = 1L;
        customerRepository.deleteById(id);

        verify(customerRepository,times(1)).deleteById(anyLong());
    }
}