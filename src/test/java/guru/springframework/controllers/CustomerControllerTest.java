package guru.springframework.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import guru.springframework.api.v1.model.CustomerDTO;
import guru.springframework.services.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.Arrays;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class CustomerControllerTest {

    @Mock
    CustomerService customerService;

    @InjectMocks
    CustomerController customerController;

    MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(customerController).build();
    }

    @Test
    void getCustomers() throws Exception {
        CustomerDTO customer1 = CustomerDTO.builder()
                .firstname("Somesh")
                .lastname("Gowalikar").customerUrl("/api/v1/customers/66").build();

        CustomerDTO customer2 = CustomerDTO.builder()
                .firstname("Ashutosh")
                .lastname("Rana").customerUrl("/api/v1/customers/95").build();

        when(customerService.getCustomers()).thenReturn(Arrays.asList(customer1, customer2));
        mockMvc.perform(get("/api/v1/customers/")
            .contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$.customers", hasSize(2)));
    }

    @Test
    void getCustomerByID() {
    }

    @Test
    public void updateCustomer() throws Exception {
        final CustomerDTO customerDTO = new CustomerDTO();
        customerDTO.setFirstname("TobeUpdated");
        customerDTO.setLastname("Lastname");

        final CustomerDTO savedCustomerDTO = new CustomerDTO();
        savedCustomerDTO.setFirstname(customerDTO.getFirstname());
        savedCustomerDTO.setLastname(customerDTO.getLastname());
        savedCustomerDTO.setCustomerUrl("/api/v1/customers/1");
        when(customerService.addCustomer(any(CustomerDTO.class))).thenReturn(savedCustomerDTO);

        mockMvc.perform(post("/api/v1/customers/")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(customerDTO)))
        .andExpect(status().isCreated())
        .andExpect(jsonPath("$.firstname", equalTo(customerDTO.getFirstname())))
                .andExpect(jsonPath("$.lastname", equalTo(customerDTO.getLastname())));
    }

    private String asJsonString(final CustomerDTO customerDTO) throws JsonProcessingException {
        final ObjectMapper om = new ObjectMapper();
        return om.writeValueAsString(customerDTO);
    }
}