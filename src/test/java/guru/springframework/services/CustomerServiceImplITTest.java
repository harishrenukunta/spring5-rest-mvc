package guru.springframework.services;


import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.bootstrap.CustomerBootstrap;
import guru.springframework.domain.Customer;
import guru.springframework.repository.CustomerRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class CustomerServiceImplITTest {

    @Autowired
    private CustomerRepository customerRepository;

    private CustomerService customerService;

    private CustomerBootstrap customerBootStrap;

    @BeforeEach
    public void beforeEach() throws Exception {
        customerService = new CustomerServiceImpl(customerRepository);
        customerBootStrap = new CustomerBootstrap(customerRepository);
        customerBootStrap.run(null);
    }

    @Test
    public void patchCustomerTest(){
        final Customer customer = this.getOneCustomer();
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("Harish patch operation");
        CustomerDTO patchedCustomer = null;
        if(customer != null){
            patchedCustomer = customerService.patchCustomer(customer.getId(), customerDTO);

        }

        assertThat(patchedCustomer).extracting("firstname").isEqualTo(customerDTO.getFirstname());
    }

    @Test
    public void patchCustomerLastname(){
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setLastname("Lastname patch");

        CustomerDTO patchedCustomer = null;
        final Customer customer = this.getOneCustomer();

        if(customer != null){
            patchedCustomer = customerService.patchCustomer(customer.getId(), customerDTO);
        }

        assertThat(patchedCustomer).extracting("lastname").isEqualTo(customerDTO.getLastname());
    }

    private Customer getOneCustomer(){
        return customerRepository.findAll()
                .get(0);

    }
}


